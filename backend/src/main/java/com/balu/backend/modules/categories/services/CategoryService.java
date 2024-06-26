package com.balu.backend.modules.categories.services;

import com.balu.backend.kernel.CustomException;
import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.categories.model.ICategoryViewPaged;
import com.balu.backend.modules.categories.model.dto.*;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;
    private final HashService hashService;
    private final Validations validations = new Validations();
    private final LogService logService;
    private final static String categories = "CATEGORIES";

    @Transactional(readOnly = true)
    public ResponseApi<List<GetCategoryListDto>> getListCategories() {
        List<Category> activeCategories = iCategoryRepository.findByStatusTrue();

        if (activeCategories.isEmpty())
            return new ResponseApi<>(
                    HttpStatus.OK,
                    false,
                    ErrorMessages.NO_RECORDS.name()
            );
        List<GetCategoryListDto> categoryListDtos = activeCategories.stream()
                .map(category -> {
                    try {
                        return new GetCategoryListDto(hashService.encrypt(category.getId()), category.getName());
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                             InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                        try {
                            throw new CustomException("Error",e);
                        } catch (CustomException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                })
                .collect(Collectors.toList());
        return new ResponseApi<>(
                categoryListDtos,
                HttpStatus.OK,
                false,
                "ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> saveCategory(SaveCategoryDto saveCategoryDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        saveCategoryDto.setUserId(hashService.decrypt(saveCategoryDto.getUserId()));
        saveCategoryDto.setName(hashService.decrypt(saveCategoryDto.getName()));
        saveCategoryDto.setDescription(hashService.decrypt(saveCategoryDto.getDescription()));
        saveCategoryDto.setImage(saveCategoryDto.getImage());
        if (saveCategoryDto.getName() == null && saveCategoryDto.getDescription() == null && saveCategoryDto.getImage() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());

        Optional<Category> existentCategory = iCategoryRepository.findByNameIgnoreCase(saveCategoryDto.getName());
        if (existentCategory.isPresent()) return new ResponseApi<>(HttpStatus.CONFLICT, true, ErrorMessages.DUPLICATE_RECORD.name());
        
        if (validations.isNotBlankString(saveCategoryDto.getName()) && validations.isNotBlankString(saveCategoryDto.getDescription()) && validations.isNotBlankString(saveCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isValidBase64Image(saveCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.UNSUPPORTED_IMAGE_FORMAT.name());
        LocalDateTime date = LocalDateTime.now();
        Category newCategory = this.iCategoryRepository.saveAndFlush(new Category(0l, saveCategoryDto.getName(), saveCategoryDto.getDescription(), saveCategoryDto.getImage(), date, true, null));
        logService.saveLog("Registration of new category "+newCategory.getId() +" in the system for user with id: " + saveCategoryDto.getUserId(), LogTypes.INSERT, categories);
        return new ResponseApi<>(
                true,
                HttpStatus.CREATED,
                true,
                "Created successfully"
        );
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Integer> updateCategory(UpdateCategoryDto updateCategoryDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        updateCategoryDto.setUserId(hashService.decrypt(updateCategoryDto.getUserId()));
        updateCategoryDto.setId(hashService.decrypt(updateCategoryDto.getId()));
        updateCategoryDto.setName(hashService.decrypt(updateCategoryDto.getName()));
        updateCategoryDto.setDescription(hashService.decrypt(updateCategoryDto.getDescription()));
        updateCategoryDto.setImage(updateCategoryDto.getImage());
        Long id;

        try {
            id = Long.parseLong(updateCategoryDto.getId());
        } catch (NumberFormatException e) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_ID.name());
        }
        Optional<Category> optionalCategory = iCategoryRepository.findById(id);
        if(optionalCategory.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.NO_RECORDS.name());

        if (updateCategoryDto.getId() == null && updateCategoryDto.getImage() == null && updateCategoryDto.getDescription() == null && updateCategoryDto.getImage() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        if (validations.isNotBlankString(updateCategoryDto.getName()) && validations.isNotBlankString(updateCategoryDto.getDescription()) && validations.isNotBlankString(updateCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isValidBase64Image(updateCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.UNSUPPORTED_IMAGE_FORMAT.name());

        if(!(optionalCategory.get().getName().equalsIgnoreCase(updateCategoryDto.getName()))){
            Optional<Category> existentCategory = iCategoryRepository.findByNameIgnoreCase(updateCategoryDto.getName());
            if(existentCategory.isPresent()) return new ResponseApi<>(HttpStatus.CONFLICT, true, ErrorMessages.DUPLICATE_RECORD.name());
        }
        optionalCategory.get().setName(updateCategoryDto.getName());
        optionalCategory.get().setDescription(updateCategoryDto.getDescription());
        optionalCategory.get().setImage(updateCategoryDto.getImage());
        logService.saveLog("Update of category "+optionalCategory.get().getId() +" for user with id: " + updateCategoryDto.getUserId(), LogTypes.UPDATE, categories);
        iCategoryRepository.saveAndFlush(optionalCategory.get());
        return new ResponseApi<>(
                HttpStatus.OK,
                false,
                "successful modification"
        );

    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Integer> changeStatusCategory(ChangeStatusCategoryDto changeStatusCategoryDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (validations.isInvalidId(changeStatusCategoryDto.getId()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_ID.name());
        if (validations.isValidBooleanStatus(changeStatusCategoryDto.getStatus()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_STATUS.name());
        if (changeStatusCategoryDto.getId() == null && changeStatusCategoryDto.getStatus() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        Long id = Long.parseLong(hashService.decrypt(changeStatusCategoryDto.getId()));
        Boolean status = Boolean.parseBoolean(hashService.decrypt(changeStatusCategoryDto.getStatus()));
        String userId = hashService.decrypt(changeStatusCategoryDto.getUserId());
        Integer category = this.iCategoryRepository.changeStatusCategory(id, !status);
        logService.saveLog("ChangeStatus of category "+id +" for user with id: " + userId, LogTypes.UPDATE, categories);
        //No me dejo usar el enumerador de CHANGE_STATUS
        return new ResponseApi<>(
                category,
                HttpStatus.OK,
                false,
                "successful status change"
        );

    }

    @Transactional(readOnly = true)
    public ResponseApi<Page<ICategoryViewPaged>> getPaged(SearchCategoryDto searchCategoryDto, Pageable peageable){
        searchCategoryDto.setSearchCategoryValue(searchCategoryDto.getSearchCategoryValue().toLowerCase());
        Page<ICategoryViewPaged> categoryPage= iCategoryRepository.findAllPaged(searchCategoryDto.getSearchCategoryValue(),searchCategoryDto.getSearchCategoryValue(),peageable);
        return new ResponseApi<>(categoryPage,HttpStatus.OK,false,"OK");
    }

    @Transactional(readOnly = true)
    public ResponseApi<List<CarouselCategoryDto>> carouselList(){
        List<Category> list = iCategoryRepository.caroruselList();
        if (list.isEmpty())
            return new ResponseApi<>(
                    HttpStatus.OK,
                    false,
                    ErrorMessages.NO_RECORDS.name()
            );

    List<CarouselCategoryDto> carouselCategoryDtoList =  list.stream().map(
            category -> {
                try {
                    return  new CarouselCategoryDto(hashService.encrypt(category.getId()),category.getName(),category.getDescription(),category.getImage());
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                         InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                    try {
                        throw new CustomException("Error",e);
                    } catch (CustomException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }).collect(Collectors.toList());
    return  new ResponseApi<>(
            carouselCategoryDtoList,
            HttpStatus.OK,
            false,
            "ok"
    );
    }

}
