package com.balu.backend.modules.pets.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.pets.model.*;
import com.balu.backend.modules.pets.model.dto.PetDto;
import com.balu.backend.modules.pets.model.enums.AgeUnits;
import com.balu.backend.modules.pets.model.enums.Genders;
import com.balu.backend.modules.pets.model.enums.LifeStages;
import com.balu.backend.modules.pets.model.enums.WeightUnits;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PetService {
    private final Validations validations = new Validations();
    private final IPetRepository petRepository;
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    private final IStatusRepository statusRepository;
    private final IMedicalRecordRepository medicalRecordRepository;
    private final IPetImageRepository petImageRepository;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> save(PetDto dto) {
        if (dto.getName() == null || validations.isNotBlankString(dto.getName().trim()) || dto.getGender() == null || validations.isNotBlankString(dto.getGender().trim()) || dto.getBreed() == null || validations.isNotBlankString(dto.getBreed().trim()) || dto.getAge() == 0 || dto.getAgeUnit() == null || validations.isNotBlankString(dto.getAgeUnit().trim()) || dto.getLifeStage() == null || validations.isNotBlankString(dto.getLifeStage().trim()) || dto.getWeight() == null || dto.getWeight() == 0.00 || dto.getWeightUnit() == null || validations.isNotBlankString(dto.getWeightUnit().trim()) || dto.getDescription() == null || validations.isNotBlankString(dto.getDescription().trim()) || dto.getCharacteristics() == null || dto.getCharacteristics().length < 3 || dto.getMainImage() == null || validations.isNotBlankString(dto.getMainImage().trim()) || dto.getCategory() == null || dto.getOwner() == null)
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (validations.isInvalidEnum(dto.getGender().toUpperCase().trim(), Genders.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isInvalidEnum(dto.getAgeUnit().toUpperCase().trim(), AgeUnits.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isInvalidEnum(dto.getLifeStage().toUpperCase().trim(), LifeStages.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if (validations.isInvalidEnum(dto.getWeightUnit().toUpperCase().trim(), WeightUnits.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if (validations.isInvalidName(dto.getName()) || validations.isInvalidName(dto.getBreed())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
        if (validations.isInvalidMinAndMaxLength(dto.getName().trim(), 3, 30) || validations.isInvalidMinAndMaxLength(dto.getBreed().trim(), 3, 50) || validations.isInvalidMinAndMaxLength(dto.getDescription().trim(), 100, 1500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        if (dto.getAge() < 0 || dto.getWeight() < 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if (validations.isInvalidImage(dto.getMainImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
        if (dto.getImages() != null) {
            if (dto.getImages().length > 4) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            HashSet<String> imagesSet = new HashSet<>();
            imagesSet.add(dto.getMainImage());
            for (String image : dto.getImages()) {
                boolean isAdded = imagesSet.add(image);
                if (!isAdded) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_IMAGE.name());
                if (validations.isInvalidImage(image)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
            }
        }

        if(dto.getCharacteristics().length > 20) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        for (String characteristic : dto.getCharacteristics()) {
            if (validations.isInvalidName(characteristic.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
            if (validations.isInvalidMinAndMaxLength(characteristic.trim(), 5, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        if (dto.getSpecialCares() != null) {
            for (String specialCare : dto.getSpecialCares()) {
                if (validations.isInvalidName(specialCare.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                if (validations.isInvalidMinAndMaxLength(specialCare.trim(), 20, 200)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            }
        }

        if (dto.getDiseases() != null) {
            for (String disease : dto.getDiseases()) {
                if (validations.isInvalidName(disease.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                if (validations.isInvalidMinAndMaxLength(disease.trim(), 3, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            }
        }

        if (dto.getAllergies() != null) {
            for (String allergy : dto.getAllergies()) {
                if (validations.isInvalidName(allergy.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                if (validations.isInvalidMinAndMaxLength(allergy.trim(), 3, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            }
        }

        if (dto.getObservations() != null) {
            if (validations.isInvalidMinAndMaxLength(dto.getObservations(), 50, 500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
        }

        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategory().getId());
        if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Category category = optionalCategory.get();

        Optional<User> optionalUser = userRepository.findById(dto.getOwner().getId());
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        Optional<Status> optionalStatus = statusRepository.findByName(Statusses.PENDING);
        if (!optionalStatus.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Status status = optionalStatus.get();

        Pet pet = new Pet(dto.getName().trim(),dto.getGender(), dto.getBreed().trim(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getDescription().trim(), String.join(",", dto.getCharacteristics()), dto.getSpecialCares() != null ? String.join(",", dto.getCharacteristics()) : null, dto.getMainImage().trim(), category, user, status);
        Pet savedPet = petRepository.saveAndFlush(pet);
        if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.PET_NOT_SAVED.name());

        MedicalRecord medicalRecord = new MedicalRecord(dto.isVaccinated(), dto.isSterilized(), dto.isDewormed(), dto.isMicrochip(), dto.getObservations() != null ? dto.getObservations().trim() : null, dto.getDiseases() != null ? String.join(",", dto.getDiseases()) : null, dto.getAllergies() != null ? String.join(",", dto.getAllergies()) : null, savedPet);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.saveAndFlush(medicalRecord);
        if (savedMedicalRecord == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.MEDICAL_RECORD_NOT_SAVED.name());

        List<PetImage> petImages = new ArrayList<>();
        if (dto.getImages() != null) {
            for (String image : dto.getImages()) {
                PetImage petImage = new PetImage(image.trim(), savedPet);
                PetImage savedPetImage = petImageRepository.saveAndFlush(petImage);
                if (savedPetImage != null) petImages.add(savedPetImage);
            }
            if (petImages.size() != dto.getImages().length) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
        }

        return new ResponseApi<>(savedPet, HttpStatus.CREATED,false, "OK");
    }
}
