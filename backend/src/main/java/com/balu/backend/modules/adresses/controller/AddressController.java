package com.balu.backend.modules.adresses.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adresses.model.dto.AddressDto;
import com.balu.backend.modules.adresses.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/")
    public ResponseEntity<ResponseApi<Boolean>> update(@RequestBody AddressDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            return new ResponseEntity<>(addressService.update(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/details")
    public ResponseEntity<ResponseApi<AddressDto>> save(@RequestBody AddressDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            return new ResponseEntity<>(addressService.addressInfo(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}