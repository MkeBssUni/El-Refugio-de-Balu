package com.balu.backend.modules.adresses.model.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adresses.model.model.Address;
import com.balu.backend.modules.adresses.model.model.dto.GetAddressDetailsDto;
import com.balu.backend.modules.adresses.model.model.dto.SaveAddressDto;
import com.balu.backend.modules.adresses.model.model.dto.UpdateAddressDto;
import com.balu.backend.modules.adresses.model.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class AddressController { private final AddressService addressService;

    @PostMapping("/details")
    public ResponseEntity<ResponseApi<Address>> getAddressByUserId(@RequestBody GetAddressDetailsDto addressDetailsDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<Address> responseApi = addressService.getAddressByUserId(addressDetailsDto.getUserId());

        return new ResponseEntity<>(responseApi, responseApi.getStatus());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<Address>> saveAddress(@RequestBody SaveAddressDto saveAddressDto) {
        try {
            ResponseApi<Address> responseApi = addressService.saveAddress(saveAddressDto);
            return new ResponseEntity<>(responseApi, responseApi.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseApi<Address>> updateAddress( @RequestBody UpdateAddressDto updateAddressDto) {
        try {
            ResponseApi<Address> responseApi = addressService.updateAddress(updateAddressDto);
            System.out.println(responseApi.getMessage());
            return new ResponseEntity<>(responseApi, responseApi.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}