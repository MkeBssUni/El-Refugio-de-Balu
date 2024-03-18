package com.balu.backend.modules.categories.services;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import com.balu.backend.modules.categories.model.dto.SaveCategoryDto;
import com.balu.backend.modules.categories.model.dto.UpdateCategoryDto;
import com.balu.backend.modules.hash.service.HashService;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;
    private final HashService hashService;
    private final Validations validations = new Validations();

    @Transactional(readOnly = true)
    public ResponseApi<List<GetCategoryListDto>> getListCategories() {
        List<Category> activeCategories = iCategoryRepository.findByStatusTrue();

        if (activeCategories.isEmpty())
            return new ResponseApi<>(
                    HttpStatus.OK,
                    false,
                    ErrorMessages.NO_RECORDS.name()
            );
       List<GetCategoryListDto> categoryListDtos= activeCategories.stream()
               .map(category -> {
                   try {
                       return new GetCategoryListDto(hashService.encrypt(category.getId()) , hashService.encrypt(category.getName()));
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
    public ResponseApi<Category> saveCategory(SaveCategoryDto saveCategoryDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        saveCategoryDto.setName(hashService.decrypt(saveCategoryDto.getName()));
        saveCategoryDto.setDescription(hashService.decrypt(saveCategoryDto.getDescription()));
        saveCategoryDto.setImage(hashService.decrypt(saveCategoryDto.getImage()));

        if (saveCategoryDto.getName() == null && saveCategoryDto.getDescription() == null && saveCategoryDto.getImage() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        if (!(this.iCategoryRepository.findByName(saveCategoryDto.getName()) == null))
            return new ResponseApi<>(HttpStatus.CONFLICT, true, ErrorMessages.DUPLICATE_RECORD.name());
        if (validations.isNotBlankString(saveCategoryDto.getName()) && validations.isNotBlankString(saveCategoryDto.getDescription()) && validations.isNotBlankString(saveCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isValidBase64Image(saveCategoryDto.getImage()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.UNSUPPORTED_IMAGE_FORMAT.name());
        LocalDateTime Date=LocalDateTime.now();
        Category newCategory = this.iCategoryRepository.saveAndFlush(new Category(0l, saveCategoryDto.getName(), saveCategoryDto.getDescription(), saveCategoryDto.getImage(), Date, true, null));
        return new ResponseApi<>(
                newCategory,
                HttpStatus.CREATED,
                true,
                "Created successfully"
        );
    }

    @Transactional(rollbackFor ={SQLException.class,Exception.class} )
    public ResponseApi<Integer> updateCategory(UpdateCategoryDto updateCategoryDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
      updateCategoryDto.setName(hashService.decrypt(updateCategoryDto.getName()));
      updateCategoryDto.setDescription(hashService.decrypt(updateCategoryDto.getDescription()));
      updateCategoryDto.setImage(hashService.decrypt(updateCategoryDto.getImage()));
        Long id;
        try {
          id = Long.parseLong(hashService.decrypt(updateCategoryDto.getId()));
        } catch (NumberFormatException e) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_ID.name());
        }

      if (updateCategoryDto.getId()== null && updateCategoryDto.getImage()== null && updateCategoryDto.getDescription()== null && updateCategoryDto.getImage()== null)
          return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
      if(!(this.iCategoryRepository.findByName(updateCategoryDto.getName()) == null))
          return new ResponseApi<>(HttpStatus.CONFLICT, true, ErrorMessages.DUPLICATE_RECORD.name());
      if(validations.isNotBlankString(updateCategoryDto.getName()) && validations.isNotBlankString(updateCategoryDto.getDescription()) && validations.isNotBlankString(updateCategoryDto.getImage()))
          return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
      if(!validations.isValidBase64Image(updateCategoryDto.getImage()))
          return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.UNSUPPORTED_IMAGE_FORMAT.name());

      Integer category = this.iCategoryRepository.updateCategory(id,updateCategoryDto.getName(),updateCategoryDto.getDescription(),updateCategoryDto.getImage());
      return new ResponseApi<>(
              category,
              HttpStatus.OK,
              false,
              "successful modification"
      );

    }
}
