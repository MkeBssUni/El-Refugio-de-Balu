package com.balu.backend.modules.adoptionRequests.service;

import com.balu.backend.kernel.*;
import com.balu.backend.modules.adoptionRequestImage.model.AdoptionRequestImage;
import com.balu.backend.modules.adoptionRequestImage.model.AdoptionRequestImagesRepository;
import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestRepository;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestViewPaged;
import com.balu.backend.modules.adoptionRequests.model.dto.ChangeStatusAdoptionRequestDto;
import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.adoptionRequests.model.dto.GetByIdAdoptionRequestDto;
import com.balu.backend.modules.adoptionRequests.model.dto.SaveAdoptionRequestDto;
import com.balu.backend.modules.hash.service.HashService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final LogService logService;
    private final EmailService emailService;
    private final AdoptionRequestImagesRepository requestImagesRepository;



    @Transactional(readOnly = true)
    public ResponseApi<Page<IAdoptionRequestViewPaged>> adoptionByUserPaged(GetAdoptionRequestDto dto, Pageable pageable) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (dto.getSearchValue() != null) {
            dto.setSearchValue(dto.getSearchValue().toLowerCase());
        }
        if(dto.getIdUser() == null) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }
        Page<IAdoptionRequestViewPaged> page = iAdoptionRequestRepository.findAllPaged(Long.parseLong(hashService.decrypt(dto.getIdUser())), dto.getSearchValue(), pageable);
        return new ResponseApi<>(page,HttpStatus.OK,false,"OK");
    }

    @Transactional(readOnly = true)
    public ResponseApi<Optional<AdoptionRequest>> findByIdAdoption(String idAdoption) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(idAdoption == null) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }
        Optional<AdoptionRequest> adoptionRequestOptional = iAdoptionRequestRepository.findById(Long.parseLong(hashService.decrypt(idAdoption)));
        if (adoptionRequestOptional.isPresent()) {
            return new ResponseApi<>(adoptionRequestOptional,HttpStatus.OK, false, "Success");
        } else {
            return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());
        }
    }



    @Transactional(readOnly = true)
    public ResponseApi<Optional<AdoptionRequest>> adoptionByModerador(String idPet) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(idPet == null) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }
        Optional<AdoptionRequest> adoptionRequestOptional = iAdoptionRequestRepository.findByPet_Id(Long.parseLong(hashService.decrypt(idPet)));
        if (adoptionRequestOptional.isPresent()) {
            return new ResponseApi<>(adoptionRequestOptional,HttpStatus.OK, false, "Success");
        } else {
            return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ResponseApi<?> save(SaveAdoptionRequestDto dto){
        try{
            Long userId = decryptId(dto.getUser());
            Long petId = decryptId(dto.getPet());

            if(dto.getUser() == null || validations.isNotBlankString(dto.getUser()) || dto.getPet() == null || validations.isNotBlankString(dto.getPet())  || dto.getAdditional_information() == null || validations.isNotBlankString(dto.getAdditional_information()) || dto.getHomeImage() == null || dto.getHomeSpecification() == null)
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());

            if(duplicateRequest(userId,petId)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.DUPLICATE_REQUEST.name());

            if(validations.isNotBlankString(dto.getReasonsForAdoption().getPeopleAgreeToAdopt()) || validations.isNotBlankString(dto.getReasonsForAdoption().getHaveHadPets()) || validations.isNotBlankString(dto.getReasonsForAdoption().getWhereWillThePetBe())||
                    validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatDidYouDoWhenThePetGotSick()) || validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatKindOfPetsHaveYouHadBefore())|| validations.isNotBlankString(dto.getPreviousExperiencieDto().getWhatMemoriesDoYouHaveWithYourPet()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());

            if(validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getPeopleAgreeToAdopt().trim(),3,100) || validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getHaveHadPets().trim(),5,100) || validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getWhereWillThePetBe().trim(),5,100)
            ||validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatDidYouDoWhenThePetGotSick().trim(),5,100)
                    || validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatKindOfPetsHaveYouHadBefore().trim(),5,100)
                    || validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getWhatMemoriesDoYouHaveWithYourPet().trim(),5,100)||
            validations.isInvalidMinAndMaxLength(dto.getAdditional_information().trim(),5,100)){
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
            }


            if(dto.getReasonsForAdoption().getAdditionalComments().length() > 0){
                if(validations.isInvalidMinAndMaxLength(dto.getReasonsForAdoption().getAdditionalComments().trim(),5,100)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
            }

            if(dto.getPreviousExperiencieDto().getLastPet().length()>2){
                if(validations.isInvalidMinAndMaxLength(dto.getPreviousExperiencieDto().getLastPet().trim(),5,100))return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_LENGTH.name());
            }


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


            if(petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Pet> optionalPet = petRepository.findById(petId);
            if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Pet pet = optionalPet.get();



            Long countByPet = iAdoptionRequestRepository.countAdoptionRequestByPet_Id(petId);
            int count = countByPet != null ? countByPet.intValue() : 0;
            if(count >=20)return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.LIMIT_ADOPTIONREQUEST.name());


            Optional<Status> optionalStatus = statusRepository.findByName(Statusses.PENDING);
            if (!optionalStatus.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Status status = optionalStatus.get();

             if(dto.getReasonsForAdoption() != null){
                 dto.getReasonsForAdoption().setPeopleAgreeToAdopt(hashService.encrypt(dto.getReasonsForAdoption().getPeopleAgreeToAdopt()));
                 dto.getReasonsForAdoption().setAdditionalComments(hashService.encrypt(dto.getAdditional_information()));
                 dto.getReasonsForAdoption().setHaveHadPets(hashService.encrypt(dto.getReasonsForAdoption().getHaveHadPets()));
                 dto.getReasonsForAdoption().setWhereWillThePetBe(hashService.encrypt(dto.getReasonsForAdoption().getWhereWillThePetBe()));
             }

             if(dto.getPreviousExperiencieDto() != null){
                 dto.getPreviousExperiencieDto().setLastPet(hashService.encrypt(dto.getPreviousExperiencieDto().getLastPet()));
                 dto.getPreviousExperiencieDto().setWhatDidYouDoWhenThePetGotSick(hashService.encrypt(dto.getPreviousExperiencieDto().getWhatDidYouDoWhenThePetGotSick()));
                 dto.getPreviousExperiencieDto().setWhatKindOfPetsHaveYouHadBefore(hashService.encrypt(dto.getPreviousExperiencieDto().getWhatKindOfPetsHaveYouHadBefore()));
                 dto.getPreviousExperiencieDto().setWhatMemoriesDoYouHaveWithYourPet(hashService.encrypt(dto.getPreviousExperiencieDto().getWhatMemoriesDoYouHaveWithYourPet()));
                 dto.setAdditional_information(hashService.encrypt(dto.getAdditional_information()));
             }

            AdoptionRequest adoptionRequest = new AdoptionRequest(user,pet,status,parseJson(dto.getReasonsForAdoption()),parseJson(dto.getPreviousExperiencieDto()),dto.getAdditional_information());
            AdoptionRequest saveAdoption = iAdoptionRequestRepository.saveAndFlush(adoptionRequest);
            if(saveAdoption == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.ADOPTIONREQUEST_NOT_SAVED.name());


            if(dto.getImageAdoption() != null){
                List<AdoptionRequestImage>  adoptionRequestImages = new ArrayList<>();
                for(String image : dto.getImageAdoption()){
                    if(validations.isInvalidImageLength(image)) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                    AdoptionRequestImage requestImage = new AdoptionRequestImage(image.trim(),saveAdoption);
                    AdoptionRequestImage saveImage = requestImagesRepository.saveAndFlush(requestImage);
                    if(saveImage != null) adoptionRequestImages.add(saveImage);
                }
                if(adoptionRequestImages.size() != dto.getHomeImage().length){
                    iAdoptionRequestRepository.delete(saveAdoption);
                    for(AdoptionRequestImage adoptionRequestImagede : adoptionRequestImages) requestImagesRepository.delete(adoptionRequestImagede);
                    return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                }
            }
            sendEmailModerador(pet.getModerator().getId(),pet.getName());
            logService.saveLog("New adoption request registered: "+saveAdoption.getId(), LogTypes.INSERT,"ADOPTIONREQUEST | ADOPTIONREQUESTIMAGES");
            return new ResponseApi<>(HttpStatus.CREATED,false,"Adoption request saved successfully");
        }catch (Exception e){
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ResponseApi<?> changeStatusBygeneral(GetByIdAdoptionRequestDto dto){
        try{
            if(dto.getIdAdoption() == null || validations.isNotBlankString(dto.getIdAdoption()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());
            Long idAdoption = decryptId(dto.getIdAdoption());
            Optional<AdoptionRequest> optional = iAdoptionRequestRepository.findById(idAdoption);
            if(!optional.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.NOT_FOUND.name());
            Optional<Status> optionalStatus = statusRepository.findByName(Statusses.CLOSED);
            if (!optionalStatus.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Status status = optionalStatus.get();
            Long idStatus = status.getId();
            Integer adoption = this.iAdoptionRequestRepository.changeStatusAdoptionRequest(idAdoption,idStatus);
            if(adoption == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.NOT_CHANGESTATUS_ADOPTIONREQUEST.name());
            if(adoption != null){
                logService.saveLog("Change status request adoption by:"+idAdoption, LogTypes.UPDATE,"ADOPTIONREQUEST");
            }
            return new ResponseApi<>(adoption,HttpStatus.OK,false,"Adoption request change status successfully");
        }catch (Exception e){
            System.out.println(e);
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ResponseApi<?> changeStatus(ChangeStatusAdoptionRequestDto dto){
        try{
            if(dto.getAdoptionId() == null || validations.isNotBlankString(dto.getAdoptionId())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());
            Long idAdoption = decryptId(dto.getAdoptionId());
            Optional<AdoptionRequest> optionalAdoptionRequest = iAdoptionRequestRepository.findById(idAdoption);
            if(!optionalAdoptionRequest.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.NOT_FOUND.name());
            AdoptionRequest adoptionRequest = optionalAdoptionRequest.get();
            Optional<Status> statusOptional= statusRepository.findById(adoptionRequest.getStatus().getId());
            if(!statusOptional.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.NOT_FOUND.name());
            Status status = statusOptional.get();
            if(status.getName() != Statusses.PENDING) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.ERROR_STATUS.name());
            if(dto.getStatus() != Statusses.ADOPTED && dto.getStatus() != Statusses.CLOSED) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.ERROR_STATUS.name());
            Optional<Status> statusID = statusRepository.findByName(dto.getStatus());
            Long idStatus = statusID.get().getId();
            Integer adoption = this.iAdoptionRequestRepository.changeStatusAdoptionRequest(idAdoption,idStatus);
            if(adoption == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.NOT_CHANGESTATUS_ADOPTIONREQUEST.name());
            Optional<Pet> optionalPet = petRepository.findById(adoptionRequest.getPet().getId());
            if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Pet pet = optionalPet.get();
            Optional<User> userOptional = userRepository.findById(adoptionRequest.getUser().getId());
            if(!userOptional.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            User user = userOptional.get();
            if(adoption != null){
                if(dto.getStatus() == Statusses.ADOPTED){
                    emailService.sendAdoptionApprovalTemplate(hashService.decrypt(user.getUsername()),pet.getName());
                }
                if(dto.getStatus() == Statusses.CLOSED){
                    emailService.finalizeAdoptionTemplate(hashService.decrypt(user.getUsername()),pet.getName());
                }
            }
            logService.saveLog("Change status request adoption by: "+adoption, LogTypes.CHANGE_STATUS,"ADOPTIONREQUEST");
            return new ResponseApi<>(adoption,HttpStatus.OK,false,"Adoption request change status successfully");
        }catch (Exception e){
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    public boolean duplicateRequest(Long userId, Long petId){
        try{
            Optional<AdoptionRequest> existingRequest = iAdoptionRequestRepository.findByUser_IdAndPet_Id(userId, petId);
            if (existingRequest.isPresent()) {
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean sendEmailModerador(Long idUser,String namePet){
        try{
            Optional<User> optionalUser = userRepository.findById(idUser);
            if(!optionalUser.isPresent()) return false;
            User moderador = optionalUser.get();
            Long countRequest = iAdoptionRequestRepository.countAdoptionRequestByPet_Moderator_Id(moderador.getId());
            int count = countRequest != null ? countRequest.intValue() : 0;
            if(count > 1){
                emailService.activeRequestTemplate(hashService.decrypt(moderador.getUsername()),namePet,count);
            }
            return true;
        }catch (Exception e){
            return false;
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
