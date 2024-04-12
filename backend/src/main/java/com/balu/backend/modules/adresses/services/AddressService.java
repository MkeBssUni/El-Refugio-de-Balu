package com.balu.backend.modules.adresses.services;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adresses.model.Address;
import com.balu.backend.modules.adresses.model.IAddressRepository;
import com.balu.backend.modules.adresses.model.dto.AddressDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.HomeTypes;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeImageRepository;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeSpecificationRepository;
import com.balu.backend.modules.people.model.IPersonRepository;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
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
import java.util.Optional;
import com.balu.backend.kernel.ErrorMessages;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {
    private final IAddressRepository addressRepository;
    private final HashService hashService;
    private final IUserRepository iUserRepository;
    private final IPersonRepository iPersonRepository;
    private final Validations validations = new Validations();
    private final HomeImageRepository homeImageRepository;
    private final HomeSpecificationRepository homeSpecificationRepository;
    private final IPetRepository iPetRepository;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> update (AddressDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<User> optionalUser = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getUserId())));
        if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        Optional<Address> optionalAddress = addressRepository.findByUserId(Long.valueOf(hashService.decrypt(dto.getUserId())));
        if(optionalAddress.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.NOT_FOUND.name());

        if(validations.isNotBlankString(dto.getCountry(), dto.getStreet(), dto.getColony(), dto.getCity(), dto.getState(), dto.getPostalCode(), dto.getAddressReference(), dto.getExteriorNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isInvalidMinAndMaxLength(dto.getPostalCode(),5,5)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getCountry(),3,50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getStreet(),3,50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getColony(),3,50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getCity(),3,50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getState(),3,50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getAddressReference(),20,200)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());

        if(validations.isInvalidImage(dto.getHomeSpecification().getHomeImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_IMAGE.name());
        if(dto.getHomeSpecification().getNumberOfResidents()<=0) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());

        try{
            HomeTypes homeType = HomeTypes.valueOf(String.valueOf(dto.getHomeSpecification().getType()).toUpperCase());
            optionalAddress.get().saveFullAddres(dto);
            optionalAddress.get().getHomeSpecification().saveFull(homeType,dto.getHomeSpecification().isOutdoorArea(),dto.getHomeSpecification().getNumberOfResidents());
            optionalAddress.get().getHomeSpecification().getHomeImage().setImage(dto.getHomeSpecification().getHomeImage());

            addressRepository.saveAndFlush(optionalAddress.get());
            homeImageRepository.saveAndFlush(optionalAddress.get().getHomeSpecification().getHomeImage());
            homeSpecificationRepository.saveAndFlush(optionalAddress.get().getHomeSpecification());
            optionalUser.get().setProfileCompleted(true);
            iUserRepository.saveAndFlush(optionalUser.get());
            return new ResponseApi<>(HttpStatus.OK, false, "Address updated successfully");
        }catch (IllegalArgumentException e){
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_TYPEHOUSE.name());
        }
    }
    @Transactional(readOnly = true)
    public ResponseApi<AddressDto> addressInfo(AddressDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<User> optionalUser;
        if(dto.getPetId()!=null){
            optionalUser = iUserRepository.findById(iPetRepository.findOwnerIdByPetId(Long.valueOf(hashService.decrypt(dto.getPetId()))));
        }else if(dto.getUserId()!=null){
            optionalUser = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getUserId())));
        }else{
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }

        if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        Optional<Person> optionalPerson = iPersonRepository.findByUserId(optionalUser.get().getId());
        if(optionalPerson.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(hashService.encrypt(optionalUser.get().getAddress().getId()));
        addressDto.setCountry(optionalUser.get().getAddress().getCountry());
        addressDto.setStreet(optionalUser.get().getAddress().getStreet());
        addressDto.setColony(optionalUser.get().getAddress().getColony());
        addressDto.setCity(optionalUser.get().getAddress().getCity());
        addressDto.setState(optionalUser.get().getAddress().getState());
        addressDto.setPostalCode(optionalUser.get().getAddress().getPostalCode());
        addressDto.setAddressReference(optionalUser.get().getAddress().getAddressReference());
        addressDto.setExteriorNumber(optionalUser.get().getAddress().getExteriorNumber());
        addressDto.setInteriorNumber(optionalUser.get().getAddress().getInteriorNumber());
        return new ResponseApi<>(addressDto, HttpStatus.OK, false, "Address retrieved successfully");
    }
}