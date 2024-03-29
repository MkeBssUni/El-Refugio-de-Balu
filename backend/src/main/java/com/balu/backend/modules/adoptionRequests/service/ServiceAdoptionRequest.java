package com.balu.backend.modules.adoptionRequests.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestRepository;
import com.balu.backend.modules.adoptionRequests.model.dto.SaveAdoptionRequestDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.HomeImage;
import com.balu.backend.modules.homeSpecification.model.HomeImageRepository;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.homeSpecification.model.HomeSpecificationRepository;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class ServiceAdoptionRequest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final IAdoptionRequestRepository iAdoptionRequestRepository;
    private final HashService hashService;
    private final Validations validations = new Validations();
    private final IUserRepository userRepository;
    private final IPetRepository petRepository;
    private final IStatusRepository statusRepository;
    private final HomeSpecificationRepository homeSpecificationRepository;
    private final HomeImageRepository homeImageRepository;
    private final LogService logService;

    @Transactional(readOnly = true)
    public ResponseApi<Optional<AdoptionRequest>> adoptionByUser(String idUser) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(idUser == null) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }
        Optional<AdoptionRequest> adoptionRequestOptional = iAdoptionRequestRepository.findByUser_Id(Long.parseLong(hashService.decrypt(idUser)));
        if (adoptionRequestOptional.isPresent()) {
            return new ResponseApi<>(adoptionRequestOptional,HttpStatus.OK, false, "Success");
        } else {
            return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ResponseApi<?> save(SaveAdoptionRequestDto dto){
        try{
            if(dto.getUser() == null || validations.isNotBlankString(dto.getUser()) || dto.getPet() == null || validations.isNotBlankString(dto.getPet())  ||dto.getPlace_of_residence() == null || validations.isNotBlankString(dto.getPlace_of_residence()) || dto.getAdditional_information() == null || validations.isNotBlankString(dto.getAdditional_information()) || dto.getHomeImage() == null || dto.getHomeSpecification() == null)
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());

            if(validations.isNotBlankString(dto.getReasonsForAdoption().getPeopleAgreeToAdopt()) || validations.isNotBlankString(dto.getReasonsForAdoption().getHaveHadPets()) || validations.isNotBlankString(dto.getReasonsForAdoption().getWhereWillThePetBe())||
                    validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatDidYouDoWhenThePetGotSick()) || validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatKindOfPetsHaveYouHadBefore())|| validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatMemoriesDoYouHaveWithYourPet()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());

            if(validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getPeopleAgreeToAdopt().trim(),3,50) || validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getHaveHadPets().trim(),5,50) || validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getWhereWillThePetBe().trim(),5,50)
            ||validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatDidYouDoWhenThePetGotSick().trim(),5,50)
                    || validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatKindOfPetsHaveYouHadBefore().trim(),5,50)
                    || validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatMemoriesDoYouHaveWithYourPet().trim(),5,50)||
            validations.isInvalidMinAndMaxLength(dto.getAdditional_information().trim(),5,100)){
                System.out.println("Error aqui");
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
            }


            if(dto.getReasonsForAdoption().getAdditionalComments().length() > 0){
                if(validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getAdditionalComments().trim(),5,100)) {
                    System.out.println("comments");
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
                }
            }

            if(dto.getPreviousExperiencieDto().getLastPet().length()>2){
                if(validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getLastPet().trim(),5,50)){
                    System.out.println("Error en previos experiencie");
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
                }

            }

            Long userId = decryptId(dto.getUser());
            if(userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_ID.name());
            Optional<User>  optionalUser = userRepository.findById(userId);
            if(!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            User user = optionalUser.get();
            if (!user.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
            Optional<AdoptionRequest> aplicationCountAdoptionActive = iAdoptionRequestRepository.findByUser_Id(userId);
            if (aplicationCountAdoptionActive.isPresent()) {
                Long idStatus = 2L;
                Long activeAdoptionRequestsCount = iAdoptionRequestRepository.countByUser_IdAndStatus_Id(userId, idStatus);
                int count = activeAdoptionRequestsCount != null ? activeAdoptionRequestsCount.intValue() : 0;

                if (count >= 5) {
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MAX_ADOPTIONREQUEST.name());
                }
            }
            if (user.getAddress() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

            Long petId = decryptId(dto.getPet());
            if(petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Pet> optionalPet = petRepository.findById(petId);
            if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Pet pet = optionalPet.get();

            Optional<AdoptionRequest> existingRequest = iAdoptionRequestRepository.findByUser_IdAndPet_Id(userId, petId);
            if (existingRequest.isPresent()) {
                return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.DUPLICATE_REQUEST.name());
            }

            Optional<Status> optionalStatus = statusRepository.findByName(Statusses.PENDING);
            if (!optionalStatus.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Status status = optionalStatus.get();


            AdoptionRequest adoptionRequest = new AdoptionRequest(user,pet,status,parseJson(dto.getReasonsForAdoption()),parseJson(dto.getPreviousExperiencieDto()),dto.getAdditional_information());
            AdoptionRequest saveAdoption = iAdoptionRequestRepository.saveAndFlush(adoptionRequest);
            if(saveAdoption == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.ADOPTIONREQUEST_NOT_SAVED.name());


            HomeSpecification homeSpecification = new HomeSpecification(dto.getHomeSpecification().getType(),dto.getHomeSpecification().isOutdoorArea(),dto.getHomeSpecification().getNumberOfResidents());
            HomeSpecification saveHomeSpecification = homeSpecificationRepository.saveAndFlush(homeSpecification);
            if(saveHomeSpecification == null){
                iAdoptionRequestRepository.delete(saveAdoption);
                return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.HOMESPECIFICATION_NOT_SAVED.name());
            }

            if(dto.getHomeImage() != null){
                List<HomeImage> homeImages = new ArrayList<>();
                for(String image : dto.getHomeImage()){
                    HomeImage homeImage = new HomeImage(image.trim(),saveHomeSpecification);
                    HomeImage saveHomeImage = homeImageRepository.saveAndFlush(homeImage);
                    if(saveHomeImage != null) homeImages.add(saveHomeImage);
                }
                if(homeImages.size() != dto.getHomeImage().length){
                    homeSpecificationRepository.delete(saveHomeSpecification);
                    iAdoptionRequestRepository.delete(saveAdoption);
                    for(HomeImage homeImage : homeImages) homeImageRepository.delete(homeImage);
                    return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                }
            }
            logService.saveLog("New adoption request registered: "+saveAdoption.getId(), LogTypes.INSERT,"ADOPTIONREQUEST | HOMESPECEFITACTION | HOMEIMAGES");
            return new ResponseApi<>(HttpStatus.CREATED,false,"Adoption request saved successfully");
        }catch (Exception e){
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name());
        }
    }


    public Long decryptId(String encryptedId) {
        try {
            String decryptedId = hashService.decrypt(encryptedId);
            return Long.parseLong(decryptedId);
        } catch (Exception e) {
            return null;
        }
    }

    public static String parseJson(Object objeto) {
        try {
            return objectMapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }




}
