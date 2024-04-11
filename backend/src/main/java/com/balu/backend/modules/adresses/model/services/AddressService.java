package com.balu.backend.modules.adresses.model.services;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adresses.model.model.Address;
import com.balu.backend.modules.adresses.model.model.IAddressRepository;
import com.balu.backend.modules.adresses.model.model.dto.AddressDto;
import com.balu.backend.modules.adresses.model.model.dto.SaveAddressDto;
import com.balu.backend.modules.adresses.model.model.dto.UpdateAddressDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.HomeTypes;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeImageRepository;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeSpecificationRepository;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import io.micrometer.common.util.StringUtils;
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
import java.util.List;
import java.util.Optional;
import com.balu.backend.kernel.ErrorMessages;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {
    private final IAddressRepository addressRepository;
    private final HashService hashService;
    private final IUserRepository userRepository;
    private final Validations validations = new Validations();
    private final HomeImageRepository homeImageRepository;
    private final HomeSpecificationRepository homeSpecificationRepository;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> update (AddressDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<Address> optionalAddress = addressRepository.findByUserId(Long.valueOf(hashService.decrypt(dto.getUserId())));
        if(optionalAddress.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.ALREADY_EXISTS.name());

        if(validations.isNotBlankString(dto.getCountry(), dto.getStreet(), dto.getColony(), dto.getCity(), dto.getState(), dto.getPostalCode(), dto.getAddressReference(), dto.getExteriorNumber(), dto.getInteriorNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isInvalidMinAndMaxLength(dto.getPostalCode(),5,5)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getCountry(),3,10)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getStreet(),3,100)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getColony(),3,100)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getCity(),3,100)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getState(),3,100)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());
        if(validations.isInvalidMinAndMaxLength(dto.getAddressReference(),20,1000)) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_LENGTH.name());

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
            return new ResponseApi<>(HttpStatus.OK, false, "Address updated successfully");
        }catch (IllegalArgumentException e){
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_TYPEHOUSE.name());
        }
    }
}