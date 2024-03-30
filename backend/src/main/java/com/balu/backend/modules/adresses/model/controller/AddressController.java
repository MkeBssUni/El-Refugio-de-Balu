package com.balu.backend.modules.adresses.model.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adresses.model.model.Address;
import com.balu.backend.modules.adresses.model.model.dto.UpdateAddressDto;
import com.balu.backend.modules.adresses.model.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class AddressController { private final AddressService addressService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseApi<Address>> getAddressByUserId(@PathVariable Long userId) {
        ResponseApi<Address> responseApi = addressService.getAddressByUserId(userId);
        return new ResponseEntity<>(responseApi, responseApi.getStatus());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<Address>> saveAddress(@RequestBody Address address) {
        try {
            ResponseApi<Address> responseApi = addressService.saveAddress(address);
            return new ResponseEntity<>(responseApi, responseApi.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<ResponseApi<Address>> updateAddress(@PathVariable Long addressId, @RequestBody UpdateAddressDto updateAddressDto) {
        try {
            ResponseApi<Address> responseApi = addressService.updateAddress(addressId, updateAddressDto);
            return new ResponseEntity<>(responseApi, responseApi.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}