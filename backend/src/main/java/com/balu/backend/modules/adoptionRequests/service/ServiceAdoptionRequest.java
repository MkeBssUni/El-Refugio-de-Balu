package com.balu.backend.modules.adoptionRequests.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestRepository;
import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import com.balu.backend.modules.hash.service.HashService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ServiceAdoptionRequest {
    private final IAdoptionRequestRepository iAdoptionRequestRepository;
    private final HashService hashService;

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



}
