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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ServiceAdoptionRequest {
    private final IAdoptionRequestRepository iAdoptionRequestRepository;

    @Transactional(readOnly = true)
    public Page<GetAdoptionRequestDto> adoptionByUser(int id){
        return iAdoptionRequestRepository.findByUser_Id(id);
    }



}
