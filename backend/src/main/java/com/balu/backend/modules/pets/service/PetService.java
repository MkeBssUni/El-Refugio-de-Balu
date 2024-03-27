package com.balu.backend.modules.pets.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryRepository;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import com.balu.backend.modules.pets.model.*;
import com.balu.backend.modules.pets.model.dto.PetCatalogPagedDto;
import com.balu.backend.modules.pets.model.dto.SavePetDto;
import com.balu.backend.modules.pets.model.enums.*;
import com.balu.backend.modules.pets.model.repositories.IMedicalRecordRepository;
import com.balu.backend.modules.pets.model.repositories.IPetImageRepository;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
import com.balu.backend.modules.pets.model.views.IPetCredentialView;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final HashService hashService;
    private final LogService logService;

    @Transactional(readOnly = true)
    public ResponseApi<?> findAllPaged(PetCatalogPagedDto dto, Pageable pageable) {
        if (dto.getGender() != null && validations.isInvalidEnum(dto.getGender().toUpperCase().trim(), Genders.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if (dto.getCategory() != null) {
            Long categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        }

        if (dto.getOwner() != null && (dto.getLocation() == null || !validations.isNotBlankString(dto.getLocation().trim()))) {
            Long ownerId = decryptId(dto.getOwner());
            if (ownerId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<User> optionalUser = userRepository.findById(ownerId);
            if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            User user = optionalUser.get();
            if (user.getAddress() != null) dto.setLocation(user.getAddress().getState());
        }

        return new ResponseApi<>(petRepository.findAll(pageable), HttpStatus.OK,false, "OK");
    }

    @Transactional(readOnly = true)
    public ResponseApi<?> findPetCredentials(String pet) {
        if (pet == null || validations.isNotBlankString(pet.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        Long petId = decryptId(pet);
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<IPetCredentialView> optionalPet = petRepository.findCredentialById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        return new ResponseApi<>(optionalPet.get(), HttpStatus.OK,false, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> save(SavePetDto dto) {
        try {
            if (dto.getName() == null || validations.isNotBlankString(dto.getName().trim()) || dto.getGender() == null || validations.isNotBlankString(dto.getGender().trim()) || dto.getBreed() == null || validations.isNotBlankString(dto.getBreed().trim()) || dto.getAge() == 0 || dto.getAgeUnit() == null || validations.isNotBlankString(dto.getAgeUnit().trim()) || dto.getLifeStage() == null || validations.isNotBlankString(dto.getLifeStage().trim()) || dto.getWeight() == null || dto.getWeight() == 0.00 || dto.getWeightUnit() == null || validations.isNotBlankString(dto.getWeightUnit().trim()) || dto.getSize() == null || validations.isNotBlankString(dto.getSize().trim()) || dto.getDescription() == null || validations.isNotBlankString(dto.getDescription().trim()) || dto.getCharacteristics() == null || dto.getCharacteristics().length < 3 || dto.getMainImage() == null || validations.isNotBlankString(dto.getMainImage().trim()) || dto.getCategory() == null || validations.isNotBlankString(dto.getCategory().trim()) || dto.getOwner() == null || validations.isNotBlankString(dto.getOwner().trim()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

            if (validations.isInvalidEnum(dto.getGender().toUpperCase().trim(), Genders.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            if (validations.isInvalidEnum(dto.getAgeUnit().toUpperCase().trim(), AgeUnits.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            if (validations.isInvalidEnum(dto.getLifeStage().toUpperCase().trim(), LifeStages.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            if (validations.isInvalidEnum(dto.getWeightUnit().toUpperCase().trim(), WeightUnits.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            if (validations.isInvalidEnum(dto.getSize().toUpperCase().trim(), Sizes.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

            if (validations.isInvalidName(dto.getName()) || validations.isInvalidName(dto.getBreed())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
            if (validations.isInvalidMinAndMaxLength(dto.getName().trim(), 3, 30) || validations.isInvalidMinAndMaxLength(dto.getBreed().trim(), 3, 50) || validations.isInvalidMinAndMaxLength(dto.getDescription().trim(), 100, 1500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            if (dto.getObservations() != null && validations.isInvalidMinAndMaxLength(dto.getObservations().trim(), 50, 500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            if (dto.getAge() < 0 || dto.getWeight() < 0) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

            if(dto.getCharacteristics().length > 20) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            for (String characteristic : dto.getCharacteristics()) {
                if (validations.isInvalidName(characteristic.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                if (validations.isInvalidMinAndMaxLength(characteristic.trim(), 3, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
            }
            String characteristics = String.join(",", dto.getCharacteristics());

            String specialCares = null;
            if (dto.getSpecialCares() != null) {
                for (String specialCare : dto.getSpecialCares()) {
                    if (validations.isInvalidName(specialCare.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                    if (validations.isInvalidMinAndMaxLength(specialCare.trim(), 20, 200)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                }
                specialCares = String.join(",", dto.getSpecialCares());
            }

            String diseases = null;
            if (dto.getDiseases() != null) {
                for (String disease : dto.getDiseases()) {
                    if (validations.isInvalidName(disease.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                    if (validations.isInvalidMinAndMaxLength(disease.trim(), 3, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                }
                diseases = String.join(",", dto.getDiseases());
            }

            String allergies = null;
            if (dto.getAllergies() != null) {
                for (String allergy : dto.getAllergies()) {
                    if (validations.isInvalidName(allergy.trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                    if (validations.isInvalidMinAndMaxLength(allergy.trim(), 3, 50)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                }
                allergies = String.join(",", dto.getAllergies());
            }

            if (validations.isInvalidImage(dto.getMainImage()) || validations.isInvalidImageLength(dto.getMainImage())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
            if (dto.getImages() != null) {
                if (dto.getImages().length > 4) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                HashSet<String> imagesSet = new HashSet<>();
                imagesSet.add(dto.getMainImage());
                for (String image : dto.getImages()) {
                    boolean isAdded = imagesSet.add(image);
                    if (!isAdded) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_IMAGE.name());
                    if (validations.isInvalidImage(image) || validations.isInvalidImageLength(image)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
                }
            }

            Long categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Category category = optionalCategory.get();

            Long ownerId = decryptId(dto.getOwner());
            if (ownerId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<User> optionalUser = userRepository.findById(ownerId);
            if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            User user = optionalUser.get();
            if (!user.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
            if (user.getAddress() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

            Optional<Status> optionalStatus = statusRepository.findByName(Statusses.PENDING);
            if (!optionalStatus.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Status status = optionalStatus.get();

            Pet pet = new Pet(dto.getName().trim(),dto.getGender(), dto.getBreed().trim(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getSize(), dto.getDescription().trim(), characteristics, specialCares, dto.getMainImage().trim(), category, user, status);
            Pet savedPet = petRepository.saveAndFlush(pet);
            if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.PET_NOT_SAVED.name());

            MedicalRecord medicalRecord = new MedicalRecord(dto.isVaccinated(), dto.isSterilized(), dto.isDewormed(), dto.isMicrochip(), dto.getObservations() != null ? dto.getObservations().trim() : null, diseases, allergies, savedPet);
            MedicalRecord savedMedicalRecord = medicalRecordRepository.saveAndFlush(medicalRecord);
            if (savedMedicalRecord == null) {
                petRepository.delete(savedPet);
                return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.MEDICAL_RECORD_NOT_SAVED.name());
            }

            if (dto.getImages() != null) {
                List<PetImage> petImages = new ArrayList<>();
                for (String image : dto.getImages()) {
                    PetImage petImage = new PetImage(image.trim(), savedPet);
                    PetImage savedPetImage = petImageRepository.saveAndFlush(petImage);
                    if (savedPetImage != null) petImages.add(savedPetImage);
                }
                if (petImages.size() != dto.getImages().length) {
                    medicalRecordRepository.delete(savedMedicalRecord);
                    petRepository.delete(savedPet);
                    for (PetImage petImage : petImages) petImageRepository.delete(petImage);
                    return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                }
            }

            logService.saveLog("New pet in adoption request registered: " + savedPet.getId(), LogTypes.INSERT, "PETS | MEDICAL_RECORDS | PET_IMAGES");

            return new ResponseApi<>(HttpStatus.CREATED,false, "Pet saved successfully");
        } catch (Exception e) {
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    public Long decryptId(String encryptedId) {
        try {
            String decryptedId = hashService.decrypt(encryptedId);
            return Long.parseLong(decryptedId);
        } catch (Exception e) {
            return null;
        }
    }
}
