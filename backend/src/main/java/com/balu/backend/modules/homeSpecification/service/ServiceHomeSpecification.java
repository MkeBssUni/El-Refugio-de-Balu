package com.balu.backend.modules.homeSpecification.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adresses.model.model.IAddressRepository;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.*;
import com.balu.backend.modules.homeSpecification.model.Dto.SaveHomeDetailsDto;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeImageRepository;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeSpecificationRepository;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ServiceHomeSpecification {

    private final HomeSpecificationRepository homeSpecificationRepository;
    private final IAddressRepository iAddressRepository;

    private final HomeImageRepository homeImageRepository;
    private final HashService hashService;

    private final Validations validations = new Validations();

    private final IUserRepository iUserRepository;

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ResponseApi<?> save(SaveHomeDetailsDto dto){
        try{
            if(dto.getIdUser() == null|| validations.isNotBlankString(dto.getIdUser()) || dto.getType() == null || validations.isNotBlankString(dto.getType()) || dto.getNumberOfResidents() == 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());
            HomeTypes homeType;
            homeType = HomeTypes.valueOf(dto.getType().toUpperCase());
            if(homeType == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_TYPEHOUSE.name());

            HomeSpecification homeSpecification = new HomeSpecification(homeType,dto.isOutdoorArea(),dto.getNumberOfResidents());
            HomeSpecification saveHomeSpecification = homeSpecificationRepository.saveAndFlush(homeSpecification);
            if(saveHomeSpecification == null){
                return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.HOMESPECIFICATION_NOT_SAVED.name());
            }
            Long idUser = decryptId(dto.getIdUser());
            Optional<User> optionalUser =  iUserRepository.findById(idUser);
            if(!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.INVALID_USER.name());
            User user = optionalUser.get();
            Integer saveAdress = iAddressRepository.changeHomeSpeceficationAssign(user.getAddress().getId(),saveHomeSpecification.getId());
            if(saveAdress == 0){
                return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.HOMESPECIFICATION_NOT_SAVED.name());
            }

            if(dto.getHomeImages() != null){
                List<HomeImage> homeImages = new ArrayList<>();
                for(String image : dto.getHomeImages()){
                    if(validations.isInvalidImageLength(image)) return new  ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                    HomeImage homeImage = new HomeImage(image.trim(),saveHomeSpecification);
                    HomeImage saveHomeImage = homeImageRepository.saveAndFlush(homeImage);
                    if(saveHomeImage != null) homeImages.add(saveHomeImage);
                }
                if(homeImages.size() != dto.getHomeImages().length){
                    homeSpecificationRepository.delete(saveHomeSpecification);
                    for(HomeImage homeImage : homeImages) homeImageRepository.delete(homeImage);
                    return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                }
            }


            return new ResponseApi<>(HttpStatus.CREATED,false,"Adoption request saved successfully");
        }catch (Exception e){
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());
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


}
