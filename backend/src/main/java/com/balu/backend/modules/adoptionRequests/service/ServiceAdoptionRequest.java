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
    private final IAdoptionRequestRepository repository;
    private final HashService hashService;
    private final Validations validations = new Validations();

    @Transactional(readOnly = true)
    public ResponseApi<Page<GetCategoryListDto>> getListBygeneral(String User){
        Page<AdoptionRequest> adoptionRequests = repository.findAllByUser(User);

        if(adoptionRequests.isEmpty())
            return new ResponseApi<>(
                    HttpStatus.OK,
                    false,
                    ErrorMessages.NO_RECORDS.name()
            );

        Page<GetAdoptionRequestDto> requestListDto = adoptionRequests.stream()
                .map(request -> {
                    try {
                        return new GetAdoptionRequestDto(hashService.encrypt(request.getId()) , hashService.encrypt(request.getUser()));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidAlgorithmParameterException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalBlockSizeException e) {
                        throw new RuntimeException(e);
                    } catch (BadPaddingException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return new ResponseApi<>(
                requestListDto,
                HttpStatus.OK,
                false,
                "ok"
        );
    }
}
