package com.balu.backend.modules.categories.services;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import com.balu.backend.modules.hash.service.HashService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;
    private final HashService hashService;
    private final Validations validations = new Validations();

    @Transactional(readOnly = true)
    public ResponseApi<List<GetCategoryListDto>> getListCategories(){
        List<GetCategoryListDto> category = iCategoryRepository.getListCategories();
        if(category.isEmpty())
            return new ResponseApi<>(
                    HttpStatus.OK,
                    false,
                    ErrorMessages.NO_RECORDS.name()
            );

        return new ResponseApi<>(
               category,
                HttpStatus.OK,
                false,
                "OK"
        );
    }

}
