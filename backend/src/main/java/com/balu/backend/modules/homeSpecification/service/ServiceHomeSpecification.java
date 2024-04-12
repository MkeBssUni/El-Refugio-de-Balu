package com.balu.backend.modules.homeSpecification.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adresses.model.Address;
import com.balu.backend.modules.adresses.model.IAddressRepository;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.*;
import com.balu.backend.modules.homeSpecification.model.Dto.SaveHomeDetailsDto;
import com.balu.backend.modules.homeSpecification.model.Dto.UpdateHomeSpecificationDto;
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
    public ResponseApi<Boolean> save(SaveHomeDetailsDto dto){
        try{
            if(dto.getIdUser() == null|| validations.isNotBlankString(dto.getIdUser()) || dto.getType() == null || validations.isNotBlankString(dto.getType()) || dto.getNumberOfResidents() <= 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true,ErrorMessages.MISSING_FIELDS.name());

            Optional<User> optionalUser = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getIdUser())));
            if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NOT_FOUND.name());

            Optional<Address> optionalAddress = iAddressRepository.findByUserId(optionalUser.get().getId());
            if(optionalAddress.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());

            if(validations.isInvalidImage(dto.getMainImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_IMAGE.name());
            if(dto.getType() == null || validations.isNotBlankString(dto.getType())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());

            if(dto.getNumberOfResidents() <= 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());

            try {
                HomeTypes homeType = HomeTypes.valueOf(dto.getType().toUpperCase());
                HomeSpecification homeSpecification = new HomeSpecification(homeType,dto.isOutdoorArea(),dto.getNumberOfResidents(),optionalAddress.get());
                homeSpecification = homeSpecificationRepository.saveAndFlush(homeSpecification);
                HomeImage homeImage = new HomeImage(dto.getMainImage(),homeSpecification);
                homeImageRepository.saveAndFlush(homeImage);
                optionalAddress.get().setHomeSpecification(homeSpecification);
                iAddressRepository.saveAndFlush(optionalAddress.get());
                return new ResponseApi<>(HttpStatus.OK, false, "Home specification created successfully");
            } catch (IllegalArgumentException e) {
                return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_TYPEHOUSE.name());
            }
        }catch (Exception e){
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    @Transactional(readOnly = true)
    public ResponseApi<HomeSpecification> get(Long specificationId) {
        try {
            Optional<HomeSpecification> optionalSpecification = homeSpecificationRepository.findById(specificationId);
            if (optionalSpecification.isPresent()) {
                HomeSpecification specification = optionalSpecification.get();
                return new ResponseApi<>(specification, HttpStatus.OK, false, "Home specification retrieved successfully");
            } else {
                return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());
            }
        } catch (Exception e) {
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<HomeSpecification> update(UpdateHomeSpecificationDto dto) {
        try {
            Optional<User> optionalUser = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getIdUser())));
            if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NOT_FOUND.name());

            Optional<Address> optionalAddress = iAddressRepository.findByUserId(optionalUser.get().getId());
            if(optionalAddress.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());

            Optional<HomeSpecification> optionalSpecification = homeSpecificationRepository.findById(optionalAddress.get().getHomeSpecification().getId());
            if(optionalSpecification.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());

            if(validations.isInvalidImage(dto.getMainImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_IMAGE.name());
            if(dto.getType() == null || validations.isNotBlankString(dto.getType())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());

            if(dto.getNumberOfResidents() <= 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());

            try {
                HomeTypes.valueOf(dto.getType().toUpperCase());

                optionalSpecification.get().setType(HomeTypes.valueOf(dto.getType().toUpperCase()));
                optionalSpecification.get().setOutdoorArea(dto.isOutdoorArea());
                optionalSpecification.get().setNumberOfResidents(dto.getNumberOfResidents());
                optionalSpecification.get().getHomeImage().setImage(dto.getMainImage());

                homeSpecificationRepository.saveAndFlush(optionalSpecification.get());
                return new ResponseApi<>(HttpStatus.OK, false, "Home specification updated successfully");
            } catch (IllegalArgumentException e) {
                return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_TYPEHOUSE.name());
            }
        } catch (Exception e) {
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name());
        }
    }

}
