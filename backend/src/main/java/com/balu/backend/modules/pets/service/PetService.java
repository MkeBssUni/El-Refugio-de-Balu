package com.balu.backend.modules.pets.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.pets.model.IPetRepository;
import com.balu.backend.modules.pets.model.dto.PetDto;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PetService {
    private final Validations validations = new Validations();
    private final IPetRepository petRepository;
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> save(PetDto dto) {
        if (dto.getName() == null || !validations.isNotBlankString(dto.getName()) || dto.getGender() == null || dto.getBreed() == null || !validations.isNotBlankString(dto.getBreed()) || dto.getAge() == 0 || dto.getAgeUnit() == null || dto.getLifeStage() == null || dto.getWeight() == null || dto.getWeight() == 0 || dto.getWeightUnit() == null || dto.getDescription() == null || !validations.isNotBlankString(dto.getDescription()) || dto.getMainImage() == null || !validations.isNotBlankString(dto.getMainImage()) || dto.getCategory() == null || dto.getOwner() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (validations.isInvalidName(dto.getName()) || validations.isInvalidName(dto.getBreed())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
        if (validations.isInvalidMinAndMaxLength(dto.getName(), 3, 50) || validations.isInvalidMinAndMaxLength(dto.getBreed(), 3, 50) || validations.isInvalidMinAndMaxLength(dto.getDescription(), 100, 500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        if (dto.getAge() < 0 || dto.getWeight() < 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if (validations.isInvalidImage(dto.getMainImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
        if (dto.getImages() != null) {
            for (String image : dto.getImages()) {
                if (validations.isInvalidImage(image)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
            }
        }

        String[] characteristics = dto.getCharacteristics().split(",");
        if (characteristics.length > 20) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        for (String characteristic : characteristics) {
            if (validations.isInvalidName(characteristic)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
            if (validations.isInvalidMinAndMaxLength(characteristic, 3, 20)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        String[] diseases = dto.getDiseases().split(",");
        for (String disease : diseases) {
            if (validations.isInvalidName(disease)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
            if (validations.isInvalidMinAndMaxLength(disease, 5, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        String[] allergies = dto.getAllergies().split(",");
        for (String allergy : allergies) {
            if (validations.isInvalidName(allergy)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
            if (validations.isInvalidMinAndMaxLength(allergy, 5, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        if (dto.getObservations() != null) {
            if (validations.isInvalidMinAndMaxLength(dto.getObservations(), 50, 250)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategory().getId());
        if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Category category = optionalCategory.get();

        Optional<User> optionalUser = userRepository.findById(dto.getOwner().getId());
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        return null;
    }
}
