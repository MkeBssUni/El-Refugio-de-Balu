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
import com.balu.backend.modules.pets.model.dto.*;
import com.balu.backend.modules.pets.model.enums.*;
import com.balu.backend.modules.pets.model.repositories.ICommentRepository;
import com.balu.backend.modules.pets.model.repositories.IMedicalRecordRepository;
import com.balu.backend.modules.pets.model.repositories.IPetImageRepository;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
import com.balu.backend.modules.pets.model.views.*;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
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
    private final ICommentRepository commentRepository;
    private final HashService hashService;
    private final LogService logService;

    @Transactional(readOnly = true)
    public ResponseApi<?> findAllPaged(FindPetsDto dto, Pageable pageable) {
        if (dto.getSize() != null) dto.setSize(dto.getSize().toLowerCase().trim());
        if (dto.getLifeStage() != null) dto.setLifeStage(dto.getLifeStage().toLowerCase().trim());
        if (dto.getGender() != null) {
            if (validations.isNotBlankString(dto.getGender().trim())) dto.setGender(null);
            else dto.setGender(dto.getGender().toLowerCase().trim());
        }
        if (dto.getLocation() != null) dto.setLocation(dto.getLocation().toLowerCase().trim());

        Long categoryId = null;
        if (dto.getCategory() != null) {
            categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        }

        Long userId = null;
        if (dto.getUser() != null) {
            userId = decryptId(dto.getUser());
            if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            User user = optionalUser.get();
            if (!user.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
            if (dto.getLocation() == null) {
                if (user.getAddress().getState() != null) dto.setLocation(user.getAddress().getState().toLowerCase().trim());
                else dto.setLocation("");
            }

            Page<IPetsByUserView> pets = petRepository.findAllByUserPaged(dto.getSize(), dto.getLifeStage(), dto.getLocation(), dto.getGender(), categoryId, userId, pageable);
            Page<PetCatalog> petsCatalog = pets.map(pet -> {
                try {
                    return new PetCatalog(
                            hashService.encrypt(pet.getId()),
                            pet.getName(),
                            pet.getLocation(),
                            pet.getFavorite() == 1 ? true : false
                    );
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                    throw new RuntimeException(e);
                }
            });
            return new ResponseApi<>(petsCatalog, HttpStatus.OK,false, "Pets retrieved successfully");
        } else {
            Page<IPetsView> pets = petRepository.findAllPaged(dto.getSize(), dto.getLifeStage(), dto.getLocation(), dto.getGender(), categoryId, pageable);
            Page<PetCatalog> petsCatalog = pets.map(pet -> {
                try {
                    return new PetCatalog(
                            hashService.encrypt(pet.getId()),
                            pet.getName(),
                            pet.getLocation(),
                            false
                    );
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                    throw new RuntimeException(e);
                }
            });
            return new ResponseApi<>(petsCatalog, HttpStatus.OK,false, "Pets retrieved successfully");
        }
    }

    @Transactional(readOnly = true)
    public ResponseApi<?> findPetDetails(FindPetDetailsDto dto) {
        if (dto.getId() == null || validations.isNotBlankString(dto.getId().trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        Long petId = decryptId(dto.getId());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();

        PetDetails petDetails = new PetDetails(
                pet.getMainImage(),
                pet.getPetImages().stream().map(PetImage::getImage).toArray(String[]::new),
                pet.getName(),
                pet.getOwner().getAddress().getCity() + ", " + pet.getOwner().getAddress().getState(),
                pet.getGender().toString(),
                pet.getCategory().getName(),
                pet.getBreed(),
                pet.getSize().toString(),
                pet.getLifeStage().toString(),
                pet.getAge(),
                pet.getAgeUnit().toString(),
                pet.getWeight(),
                pet.getWeightUnit().toString(),
                pet.getCharacteristics().split(","),
                pet.getDescription(),
                pet.getMedicalRecord().isVaccinated(),
                pet.getMedicalRecord().isDewormed(),
                pet.getMedicalRecord().isSterilized(),
                pet.getMedicalRecord().isMicrochip(),
                pet.getMedicalRecord().getDiseases() != null ? pet.getMedicalRecord().getDiseases().split(",") : null,
                pet.getMedicalRecord().getAllergies() != null ? pet.getMedicalRecord().getAllergies().split(",") : null,
                pet.getMedicalRecord().getObservations(),
                pet.getSpecialCares() != null ? pet.getSpecialCares().split(",") : null
        );

        return new ResponseApi<>(petDetails, HttpStatus.OK,false, "Pet details retrieved successfully");
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

    @Transactional(readOnly = true)
    public ResponseApi<?> findMyPets(FindMyPetsDto dto, Pageable pageable) {
        if (dto.getUser() == null || validations.isNotBlankString(dto.getUser().trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (dto.getStatus() == null) {
            dto.setStatus("");
        } else {
            if (validations.isInvalidEnum(dto.getStatus().toUpperCase().trim(), Statusses.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            dto.setStatus(dto.getStatus().toLowerCase().trim());
        }

        if (dto.getSearchValue() == null) {
            dto.setSearchValue("");
        } else {
            dto.setSearchValue(dto.getSearchValue().toLowerCase().trim());
        }

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();
        if (!user.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());

        Page<IMyPetsView> myPets = petRepository.findMyPetsByOwner(userId, dto.getStatus(), dto.getSearchValue(), pageable);

        Page<MyPetsCatalog> myPetsCatalog = myPets.map(myPet -> {
            try {
                return new MyPetsCatalog(
                        hashService.encrypt(myPet.getId()),
                        myPet.getComments(),
                        myPet.getName(),
                        myPet.getLocation(),
                        myPet.getStatus()
                );
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });

        return new ResponseApi<>(myPetsCatalog, HttpStatus.OK,false, "My pets retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ResponseApi<?> findNewPetRequests(FindPetRequestsDto dto, Pageable pageable) {
        if (dto.getSearchValue() == null) {
            dto.setSearchValue("");
        } else {
            dto.setSearchValue(dto.getSearchValue().toLowerCase().trim());
        }

        Long categoryId = null;
        if (dto.getCategory() != null) {
            categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        }

        Page<IPetRequestsView> petRequests = categoryId != null ? petRepository.findNewPetRequestsByCategory(categoryId, dto.getSearchValue(), pageable) : petRepository.findNewPetRequests(dto.getSearchValue(), pageable);

        Page<PetRequestList> petRequestList = petRequests.map(petRequest -> {
            try {
                return new PetRequestList(
                        hashService.encrypt(petRequest.getId()),
                        petRequest.getCategory(),
                        petRequest.getName(),
                        petRequest.getBreed(),
                        petRequest.getSize(),
                        petRequest.getGender(),
                        petRequest.getAge(),
                        petRequest.getAgeUnit(),
                        petRequest.getStatus()
                );
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });

        return new ResponseApi<>(petRequestList, HttpStatus.OK,false, "New pet requests retrieved successfully");
    }

    @Transactional(readOnly = true)
    public ResponseApi<?> findMyPetsAsMod(FindMyPetsAsModDto dto, Pageable pageable) {
        if (dto.getUser() == null || validations.isNotBlankString(dto.getUser().trim())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (dto.getStatus() == null) {
            dto.setStatus("");
        } else {
            if (validations.isInvalidEnum(dto.getStatus().toUpperCase().trim(), Statusses.class)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
            dto.setStatus(dto.getStatus().toLowerCase().trim());
        }

        if (dto.getSearchValue() == null) {
            dto.setSearchValue("");
        } else {
            dto.setSearchValue(dto.getSearchValue().toLowerCase().trim());
        }

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();
        if (!user.getRole().getName().equals(Roles.MOD)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());

        Long categoryId = null;
        if (dto.getCategory() != null) {
            categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        }

        Page<IMyPetsAsModView> myPetsAsMod = categoryId != null ? petRepository.findMyPetsAsModByCategory(userId, categoryId, dto.getStatus(), dto.getSearchValue(), pageable) : petRepository.findMyPetsAsMod(userId, dto.getStatus(), dto.getSearchValue(), pageable);

        Page<MyPetsAsModList> myPetsAsModList = myPetsAsMod.map(myPetAsMod -> {
            try {
                return new MyPetsAsModList(
                        hashService.encrypt(myPetAsMod.getId()),
                        myPetAsMod.getComments(),
                        myPetAsMod.getCategory(),
                        myPetAsMod.getName(),
                        myPetAsMod.getStatus()
                );
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });

        return new ResponseApi<>(myPetsAsModList, HttpStatus.OK,false, "My pets as moderator retrieved successfully");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> save(SavePetDto dto) {
        try {
            if (dto.getName() == null || validations.isNotBlankString(dto.getName().trim()) || dto.getGender() == null || validations.isNotBlankString(dto.getGender().trim()) || dto.getBreed() == null || validations.isNotBlankString(dto.getBreed().trim()) || dto.getAge() == 0 || dto.getAgeUnit() == null || validations.isNotBlankString(dto.getAgeUnit().trim()) || dto.getLifeStage() == null || validations.isNotBlankString(dto.getLifeStage().trim()) || dto.getWeight() == null || dto.getWeight() == 0.00 || dto.getWeightUnit() == null || validations.isNotBlankString(dto.getWeightUnit().trim()) || dto.getSize() == null || validations.isNotBlankString(dto.getSize().trim()) || dto.getDescription() == null || validations.isNotBlankString(dto.getDescription().trim()) || dto.getCharacteristics() == null || dto.getCharacteristics().length < 3 || dto.getMainImage() == null || validations.isNotBlankString(dto.getMainImage().trim()) || dto.getCategory() == null || validations.isNotBlankString(dto.getCategory().trim()) || dto.getOwner() == null || validations.isNotBlankString(dto.getOwner().trim()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

            String errorMessage = saveAndUpdatePetValidations(dto.getName(), dto.getGender(), dto.getBreed(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getSize(), dto.getDescription(), dto.getCharacteristics(), dto.getSpecialCares(), dto.getDiseases(), dto.getAllergies(), dto.getObservations(), dto.getMainImage(), dto.getImages());
            switch (errorMessage) {
                case "invalid field":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
                case "invalid format":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                case "invalid length":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                case "duplicate record":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());
                case "invalid image":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
                case "duplicate image":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_IMAGE.name());
                case "":
                    break;
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
            Status status = optionalStatus.get();

            Pet pet = new Pet(dto.getName().trim(),dto.getGender(), dto.getBreed().trim(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getSize(), dto.getDescription().trim(), String.join(",", dto.getCharacteristics()), dto.getSpecialCares() != null ? String.join(",", dto.getSpecialCares()) : null, dto.getMainImage().trim(), category, user, status);
            Pet savedPet = petRepository.saveAndFlush(pet);
            if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.PET_NOT_SAVED.name());

            MedicalRecord medicalRecord = new MedicalRecord(dto.isVaccinated(), dto.isSterilized(), dto.isDewormed(), dto.isMicrochip(), dto.getObservations() != null ? dto.getObservations().trim() : null, dto.getDiseases() != null ? String.join(",", dto.getDiseases()) : null, dto.getAllergies() != null ? String.join(",", dto.getAllergies()) : null, savedPet);
            pet.setMedicalRecord(medicalRecord);

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

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> update(UpdatePetDto dto) {
        try {
            if (dto.getId() == null || validations.isNotBlankString(dto.getId().trim()) || dto.getName() == null || validations.isNotBlankString(dto.getName().trim()) || dto.getGender() == null || validations.isNotBlankString(dto.getGender().trim()) || dto.getBreed() == null || validations.isNotBlankString(dto.getBreed().trim()) || dto.getAge() == 0 || dto.getAgeUnit() == null || validations.isNotBlankString(dto.getAgeUnit().trim()) || dto.getLifeStage() == null || validations.isNotBlankString(dto.getLifeStage().trim()) || dto.getWeight() == null || dto.getWeight() == 0.00 || dto.getWeightUnit() == null || validations.isNotBlankString(dto.getWeightUnit().trim()) || dto.getSize() == null || validations.isNotBlankString(dto.getSize().trim()) || dto.getDescription() == null || validations.isNotBlankString(dto.getDescription().trim()) || dto.getCharacteristics() == null || dto.getCharacteristics().length < 3 || dto.getMainImage() == null || validations.isNotBlankString(dto.getMainImage().trim()) || dto.getCategory() == null || validations.isNotBlankString(dto.getCategory().trim()) || dto.getOwner() == null || validations.isNotBlankString(dto.getOwner().trim()))
                return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

            Long petId = decryptId(dto.getId());
            if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Pet> optionalPet = petRepository.findById(petId);
            if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Pet pet = optionalPet.get();

            if (!pet.getStatus().getName().equals(Statusses.IN_REVISION)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_ALLOWED.name());

            Long ownerId = decryptId(dto.getOwner());
            if (ownerId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<User> optionalUser = userRepository.findById(ownerId);
            if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());

            if (!pet.getOwner().getId().equals(ownerId)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

            String errorMessage = saveAndUpdatePetValidations(dto.getName(), dto.getGender(), dto.getBreed(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getSize(), dto.getDescription(), dto.getCharacteristics(), dto.getSpecialCares(), dto.getDiseases(), dto.getAllergies(), dto.getObservations(), dto.getMainImage(), dto.getImages());
            switch (errorMessage) {
                case "invalid field":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
                case "invalid format":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FORMAT.name());
                case "invalid length":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());
                case "duplicate record":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());
                case "invalid image":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_IMAGE.name());
                case "duplicate image":
                    return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_IMAGE.name());
                case "":
                    break;
            }

            Long categoryId = decryptId(dto.getCategory());
            if (categoryId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
            Category category = optionalCategory.get();

            pet.update(dto.getName().trim(), dto.getGender(), dto.getBreed().trim(), dto.getAge(), dto.getAgeUnit(), dto.getLifeStage(), dto.getWeight(), dto.getWeightUnit(), dto.getSize(), dto.getDescription().trim(),
                    String.join(",", dto.getCharacteristics()), dto.getSpecialCares() != null ? String.join(",", dto.getSpecialCares()) : null, dto.getMainImage().trim(), category);

            pet.getMedicalRecord().update(dto.isVaccinated(), dto.isSterilized(), dto.isDewormed(), dto.isMicrochip(), dto.getObservations() != null ? dto.getObservations().trim() : null, dto.getDiseases() != null ? String.join(",", dto.getDiseases()) : null, dto.getAllergies() != null ? String.join(",", dto.getAllergies()) : null);

            Pet updatedPet = petRepository.saveAndFlush(pet);
            if (updatedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.PET_NOT_UPDATED.name());

            if (dto.getImages() != null) {
                for (PetImage petImage : updatedPet.getPetImages()) petImageRepository.delete(petImage);
                List<PetImage> petImages = new ArrayList<>();
                for (String image : dto.getImages()) {
                    PetImage petImage = new PetImage(image.trim(), updatedPet);
                    PetImage savedPetImage = petImageRepository.saveAndFlush(petImage);
                    if (savedPetImage != null) petImages.add(savedPetImage);
                }
                if (petImages.size() != dto.getImages().length) {
                    petRepository.saveAndFlush(optionalPet.get());
                    return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.IMAGE_NOT_SAVED.name());
                }
            }

            logService.saveLog("Pet " + updatedPet.getId() + " updated by " + ownerId, LogTypes.UPDATE, "PETS | MEDICAL_RECORDS | PET_IMAGES");

            return new ResponseApi<>(HttpStatus.OK,false, "Pet updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> select(SelectPetDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet().trim()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser().trim()) || dto.getStatus() == null || validations.isNotBlankString(dto.getStatus().trim()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (!dto.getStatus().equalsIgnoreCase(Statusses.APPROVED.name()) && !dto.getStatus().equalsIgnoreCase(Statusses.IN_REVISION.name())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        Optional<Status> optionalStatus = statusRepository.findByName(Statusses.valueOf(dto.getStatus().toUpperCase()));
        Status status = optionalStatus.get();

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();
        if (pet.getModerator() != null || !pet.getStatus().getName().equals(Statusses.PENDING)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_ALLOWED.name());

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        if (petRepository.countActivePetsByUser(userId) == 10) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

        pet.setModerator(user);
        pet.setStatus(status);
        Pet savedPet = petRepository.saveAndFlush(pet);
        if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());

        if (dto.getStatus().equalsIgnoreCase(Statusses.APPROVED.name())) {
            logService.saveLog("Pet " + savedPet.getId() + " approved by " + user.getId(), LogTypes.UPDATE, "PETS");
            return new ResponseApi<>(HttpStatus.OK,false, "Pet approved successfully");
        } else {
            logService.saveLog("Pet " + savedPet.getId() + " sent to revision by " + user.getId(), LogTypes.UPDATE, "PETS");
            return new ResponseApi<>(HttpStatus.OK,false, "Pet sent to revision successfully");
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> end(EndPetRequestDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet().trim()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser().trim()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();
        if (!pet.getStatus().getName().equals(Statusses.PENDING) && !pet.getStatus().getName().equals(Statusses.IN_REVISION)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_ALLOWED.name());

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();
        if (!user.getRole().getName().equals(Roles.MOD)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
        if (pet.getStatus().getName().equals(Statusses.IN_REVISION)) {
            if (pet.getModerator() != user) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());
        }

        if (pet.getStatus().getName().equals(Statusses.PENDING)) pet.setModerator(user);
        pet.setStatus(statusRepository.findByName(Statusses.CLOSED).get());
        Pet savedPet = petRepository.saveAndFlush(pet);
        if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());

        logService.saveLog("Pet " + savedPet.getId() + " request closed by " + user.getId(), LogTypes.UPDATE, "PETS");

        return new ResponseApi<>(HttpStatus.OK,false, "Pet request closed successfully");
    }

    @Transactional(readOnly = true)
    public ResponseApi<?> findComments(FindCommentsDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet().trim()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        if (user.getRole().getName().equals(Roles.GENERAL)) {
            if (pet.getOwner() != user) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());
        } else if (user.getRole().getName().equals(Roles.MOD)) {
            if (pet.getModerator() != user) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());
        } else {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
        }

        List<ICommentView> comments = commentRepository.findByPet(petId);
        return new ResponseApi<>(comments, HttpStatus.OK,false, "Comments retrieved successfully");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> comment(CommentPetDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet().trim()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser().trim()) || dto.getComment() == null || validations.isNotBlankString(dto.getComment().trim()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (validations.isInvalidMinAndMaxLength(dto.getComment().trim(), 30, 500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();
        if (!pet.getStatus().getName().equals(Statusses.IN_REVISION) && !pet.getStatus().getName().equals(Statusses.APPROVED)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_ALLOWED.name());

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();
        if (!user.getRole().getName().equals(Roles.MOD) && !user.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());
        if (pet.getModerator() != user && pet.getOwner() != user) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

        Comment comment = new Comment(dto.getComment().trim(), pet, user);
        Comment savedComment = commentRepository.saveAndFlush(comment);
        if (savedComment == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());

        logService.saveLog("Pet " + pet.getId() + " commented by " + user.getId(), LogTypes.INSERT, "COMMENTS");

        return new ResponseApi<>(HttpStatus.OK,false, "Comment saved successfully");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> cancel(CancelDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet().trim()) || dto.getOwner() == null || validations.isNotBlankString(dto.getOwner().trim()) || dto.getCancelReason() == null || validations.isNotBlankString(dto.getCancelReason().trim()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (validations.isInvalidMinAndMaxLength(dto.getCancelReason().trim(), 50, 500)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_LENGTH.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();
        if (!pet.getStatus().getName().equals(Statusses.IN_REVISION) && !pet.getStatus().getName().equals(Statusses.APPROVED) && !pet.getStatus().getName().equals(Statusses.PENDING)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_ALLOWED.name());

        Long ownerId = decryptId(dto.getOwner());
        if (ownerId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(ownerId);
        if (!optionalUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User owner = optionalUser.get();
        if (!owner.getRole().getName().equals(Roles.GENERAL)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ROLE.name());

        if (pet.getOwner() != owner) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

        pet.setCancelReason(dto.getCancelReason());
        if (pet.getStatus().getName().equals(Statusses.PENDING)) pet.setStatus(statusRepository.findByName(Statusses.CLOSED).get());
        if (pet.getStatus().getName().equals(Statusses.APPROVED)) pet.setStatus(statusRepository.findByName(Statusses.IN_REVISION).get());

        Pet savedPet = petRepository.saveAndFlush(pet);
        if (savedPet == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name());

        String logMessage = pet.getStatus().equals(Statusses.PENDING) ? "Pet " + savedPet.getId() + " request canceled by " + owner.getId() : "Pet " + savedPet.getId() + " cancellation request sent by " + owner.getId();
        logService.saveLog(logMessage, LogTypes.UPDATE, "PETS");

        return new ResponseApi<>(HttpStatus.OK,false, "Pet cancellation process successful");
    }

    public Long decryptId(String encryptedId) {
        try {
            String decryptedId = hashService.decrypt(encryptedId);
            return Long.parseLong(decryptedId);
        } catch (Exception e) {
            return null;
        }
    }

    public String saveAndUpdatePetValidations(String name, String gender, String breed, int age, String ageUnit, String lifeStage, Double weight, String weightUnit, String size, String description, String[] characteristics, String[] specialCares, String[] diseases, String[] allergies, String observations, String mainImage, String[] images) {
        if (validations.isInvalidEnum(gender.toUpperCase().trim(), Genders.class)) return "invalid field";
        if (validations.isInvalidEnum(ageUnit.toUpperCase().trim(), AgeUnits.class)) return "invalid field";
        if (validations.isInvalidEnum(lifeStage.toUpperCase().trim(), LifeStages.class)) return "invalid field";
        if (validations.isInvalidEnum(weightUnit.toUpperCase().trim(), WeightUnits.class)) return "invalid field";
        if (validations.isInvalidEnum(size.toUpperCase().trim(), Sizes.class)) return "invalid field";

        if (validations.isInvalidName(name) || validations.isInvalidName(breed)) return "invalid format";
        if (validations.isInvalidMinAndMaxLength(name.trim(), 3, 30) || validations.isInvalidMinAndMaxLength(breed.trim(), 3, 50) || validations.isInvalidMinAndMaxLength(description.trim(), 100, 1500)) return "invalid length";
        if (observations != null && validations.isInvalidMinAndMaxLength(observations.trim(), 50, 500)) return "invalid length";
        if (age < 0 || weight < 0) return "invalid field";

        if(characteristics.length > 20) return "invalid length";
        for (int i = 0; i < characteristics.length; i++) {
            if (validations.isInvalidName(characteristics[i].trim())) return "invalid format";
            if (validations.isInvalidMinAndMaxLength(characteristics[i].trim(), 3, 50)) return "invalid length";
            for (int j = i + 1; j < characteristics.length; j++) {
                if (characteristics[i].equals(characteristics[j])) return "duplicate record";
            }
        }

        if (specialCares != null) {
            for (int i = 0; i < specialCares.length; i++) {
                if (validations.isInvalidName(specialCares[i].trim())) return "invalid format";
                if (validations.isInvalidMinAndMaxLength(specialCares[i].trim(), 20, 200)) return "invalid length";
                for (int j = i + 1; j < specialCares.length; j++) {
                    if (specialCares[i].equals(specialCares[j])) return "duplicate record";
                }
            }
        }

        if (diseases != null) {
            for (int i = 0; i < diseases.length; i++) {
                if (validations.isInvalidName(diseases[i].trim())) return "invalid format";
                if (validations.isInvalidMinAndMaxLength(diseases[i].trim(), 3, 50)) return "invalid length";
                for (int j = i + 1; j < diseases.length; j++) {
                    if (diseases[i].equals(diseases[j])) return "duplicate record";
                }
            }
        }

        if (allergies != null) {
            for (int i = 0; i < allergies.length; i++) {
                if (validations.isInvalidName(allergies[i].trim())) return "invalid format";
                if (validations.isInvalidMinAndMaxLength(allergies[i].trim(), 3, 50)) return "invalid length";
                for (int j = i + 1; j < allergies.length; j++) {
                    if (allergies[i].equals(allergies[j])) return "duplicate record";
                }
            }
        }

        if (validations.isInvalidImage(mainImage) || validations.isInvalidImageLength(mainImage)) return "invalid image";
        if (images != null) {
            if (images.length > 4) return "invalid length";
            HashSet<String> imagesSet = new HashSet<>();
            imagesSet.add(mainImage);
            for (String image : images) {
                boolean isAdded = imagesSet.add(image);
                if (!isAdded) return "duplicate image";
                if (validations.isInvalidImage(image) || validations.isInvalidImageLength(image)) return "invalid image";
            }
        }

        return "";
    }

}
