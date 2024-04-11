package com.balu.backend.modules.adresses.model.services;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adresses.model.model.Address;
import com.balu.backend.modules.adresses.model.model.IAddressRepository;
import com.balu.backend.modules.adresses.model.model.dto.SaveAddressDto;
import com.balu.backend.modules.adresses.model.model.dto.UpdateAddressDto;
import com.balu.backend.modules.hash.service.HashService;
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

    @Transactional(readOnly = true)
    public ResponseApi<List<Address>> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return new ResponseApi<>(addresses, HttpStatus.OK, false, "OK");
    }

    @Transactional(readOnly = true)
    public ResponseApi<Address> getAddressByUserId(String userId) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Long id = Long.valueOf(hashService.decrypt(userId));
        Optional<Address> optionalAddress = addressRepository.findByUserId(id);
        if (optionalAddress.isEmpty()) {
            return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_ADDRESS_FOUND.name());
        }
        Address address = optionalAddress.get();
        return new ResponseApi<>(address, HttpStatus.OK, false, "OK");
    }

    @Transactional(rollbackFor = {NoSuchAlgorithmException.class, NoSuchPaddingException.class, InvalidAlgorithmParameterException.class, InvalidKeyException.class, IllegalBlockSizeException.class, BadPaddingException.class})
    public ResponseApi<Address> saveAddress(SaveAddressDto address) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (StringUtils.isEmpty(address.getCountry()) || StringUtils.isEmpty(address.getStreet()) || StringUtils.isEmpty(address.getColony()) || StringUtils.isEmpty(address.getCity()) || StringUtils.isEmpty(address.getState()) || StringUtils.isEmpty(address.getPostalCode()) || StringUtils.isEmpty(address.getAddressReference()) || StringUtils.isEmpty(address.getExteriorNumber()) || StringUtils.isEmpty(address.getInteriorNumber())) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }
        /*long userId = Long.parseLong(hashService.decrypt(address.getUserId()));
        User user = userRepository.getById(userId);

        Address encryptedAddress = new Address
                (0L,
                hashService.decrypt(address.getCountry()),
                hashService.decrypt(address.getStreet()),
                hashService.decrypt(address.getColony()),
                        hashService.decrypt(address.getCity()),
                        hashService.decrypt(address.getStreet()),
                        hashService.decrypt(address.getPostalCode()),
                        hashService.decrypt(address.getAddressReference()),
                        hashService.decrypt(address.getExteriorNumber()),
                        hashService.decrypt(address.getInteriorNumber()),
                        user,
                        null
                );
         */
        address.setUserId(hashService.decrypt(address.getUserId()));
        Long userId = Long.parseLong(address.getUserId());
        Address address1 = new Address();
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(address.getUserId()));
        if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND,true, ErrorMessages.NOT_FOUND.name());
        address.setUser(optionalUser.get());
        address1.save(address);
//        Address encryptedAddress= new Address();
//        encryptedAddress.setCountry(address.getCountry());
//        encryptedAddress.setStreet(address.getStreet());
//        encryptedAddress.setColony(address.getColony());
//        encryptedAddress.setCity(address.getPostalCode());
//        encryptedAddress.setState(address.getState());
//        encryptedAddress.setPostalCode(address.getPostalCode());
//        encryptedAddress.setAddressReference(address.getAddressReference());
//        encryptedAddress.setExteriorNumber(address.getExteriorNumber());
//        encryptedAddress.setExteriorNumber(address.getExteriorNumber());
//        encryptedAddress.setUser(user);
        //Address savedAddress = addressRepository.save(encryptedAddress);
        addressRepository.saveAndFlush(address1);
        return new ResponseApi<>(HttpStatus.CREATED, false, "Address saved successfully");
    }

    @Transactional(rollbackFor = {NoSuchAlgorithmException.class, NoSuchPaddingException.class, InvalidAlgorithmParameterException.class, InvalidKeyException.class, IllegalBlockSizeException.class, BadPaddingException.class})
    public ResponseApi<Address> updateAddress(UpdateAddressDto updateAddressDto) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (updateAddressDto == null || (StringUtils.isEmpty(updateAddressDto.getCountry()) && StringUtils.isEmpty(updateAddressDto.getStreet()) && StringUtils.isEmpty(updateAddressDto.getColony()) && StringUtils.isEmpty(updateAddressDto.getCity()) && StringUtils.isEmpty(updateAddressDto.getState()) && StringUtils.isEmpty(updateAddressDto.getPostalCode()) && StringUtils.isEmpty(updateAddressDto.getAddressReference()) && StringUtils.isEmpty(updateAddressDto.getExteriorNumber()) && StringUtils.isEmpty(updateAddressDto.getInteriorNumber()))) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        }

        Optional<Address> optionalAddress = addressRepository.findById(updateAddressDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_ADDRESS_FOUND.name());
        }
        Address existingAddress = optionalAddress.get();
        existingAddress.setCountry(updateAddressDto.getCountry());
        existingAddress.setStreet(updateAddressDto.getStreet());
        existingAddress.setColony(updateAddressDto.getColony());
        existingAddress.setCity(updateAddressDto.getCity());
        existingAddress.setCity(updateAddressDto.getCity());
        existingAddress.setState(updateAddressDto.getState());
        existingAddress.setPostalCode(updateAddressDto.getPostalCode());
        existingAddress.setAddressReference(updateAddressDto.getAddressReference());
        existingAddress.setExteriorNumber(updateAddressDto.getExteriorNumber());
        existingAddress.setInteriorNumber(updateAddressDto.getInteriorNumber());
        Address encryptedAddress = encryptAddressFields(existingAddress);
        Address updatedAddress = addressRepository.save(encryptedAddress);
        return new ResponseApi<>(updatedAddress, HttpStatus.OK, false, "Address updated successfully");
    }

    private Address encryptAddressFields(Address address) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Address encryptedAddress = new Address();
        encryptedAddress.setCountry(hashService.encrypt(address.getCountry()));
        encryptedAddress.setStreet(hashService.encrypt(address.getStreet()));
        encryptedAddress.setColony(hashService.encrypt(address.getColony()));
        encryptedAddress.setCity(hashService.encrypt(address.getCity()));
        encryptedAddress.setState(hashService.encrypt(address.getState()));
        encryptedAddress.setPostalCode(hashService.encrypt(address.getPostalCode()));
        encryptedAddress.setAddressReference(hashService.encrypt(address.getAddressReference()));
        encryptedAddress.setExteriorNumber(hashService.encrypt(address.getExteriorNumber()));
        encryptedAddress.setInteriorNumber(hashService.encrypt(address.getInteriorNumber()));
        return encryptedAddress;
    }
}