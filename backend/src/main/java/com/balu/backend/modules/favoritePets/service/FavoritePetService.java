package com.balu.backend.modules.favoritePets.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.favoritePets.model.FavoritePet;
import com.balu.backend.modules.favoritePets.model.IFavoritePetRepository;
import com.balu.backend.modules.favoritePets.model.dto.AddFavoritePetDto;
import com.balu.backend.modules.favoritePets.model.dto.FavoritePetsCatalog;
import com.balu.backend.modules.favoritePets.model.dto.FindFavoritePetsDto;
import com.balu.backend.modules.favoritePets.model.dto.RemoveFavoritePetDto;
import com.balu.backend.modules.favoritePets.model.views.IFavoritePetsView;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
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
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FavoritePetService {
    private final IFavoritePetRepository favoritePetRepository;
    private final IPetRepository petRepository;
    private final IUserRepository userRepository;
    private final LogService logService;
    private final HashService hashService;
    private final Validations validations = new Validations();

    @Transactional(readOnly = true)
    public ResponseApi<Page<FavoritePetsCatalog>> findFavoritePets(FindFavoritePetsDto dto, Pageable pageable) {
        if (dto.getUser() == null || validations.isNotBlankString(dto.getUser())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        if (dto.getSearchValue() == null) {
            dto.setSearchValue("");
        } else {
            dto.setSearchValue(dto.getSearchValue().toLowerCase().trim());
        }

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        Page<IFavoritePetsView> favoritePets = favoritePetRepository.findByUserPaged(user.getId(), dto.getSearchValue(), pageable);
        Page<FavoritePetsCatalog> favoritePetsCatalog = favoritePets.map(favoritePet ->
                {
                    try {
                        return new FavoritePetsCatalog(
                                hashService.encrypt(favoritePet.getId()),
                                favoritePet.getName(),
                                favoritePet.getImage(),
                                favoritePet.getLocation()
                        );
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return new ResponseApi<>(favoritePetsCatalog, HttpStatus.OK, false, "Favorite pets catalog retrieved successfully");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> addFavoritePet(AddFavoritePetDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        if (petRepository.existsByIdAndOwner(pet.getId(), user)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

        if (favoritePetRepository.existsByPetAndUser(pet, user)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ALREADY_EXISTS.name());

        FavoritePet favoritePet = new FavoritePet(user, pet);
        FavoritePet favoritePetSaved = favoritePetRepository.saveAndFlush(favoritePet);
        if (favoritePetSaved == null) return new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.PET_NOT_ADDED_AS_FAVORITE.name());

        logService.saveLog("Pet added to favorites: " + favoritePetSaved.getId(), LogTypes.INSERT, "FAVORITE_PETS");

        return new ResponseApi<>(true,HttpStatus.OK,false, "Pet added to favorites successfully");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> removeFavoritePet(RemoveFavoritePetDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

        Long petId = decryptId(dto.getPet());
        if (petId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        Pet pet = optionalPet.get();

        Long userId = decryptId(dto.getUser());
        if (userId == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_ID.name());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        User user = optionalUser.get();

        Optional<FavoritePet> optionalFavoritePet = favoritePetRepository.findByPetAndUser(pet, user);
        if (optionalFavoritePet.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.NOT_FOUND.name());
        FavoritePet favoritePet = optionalFavoritePet.get();


        favoritePetRepository.delete(favoritePet);
        logService.saveLog("Pet removed from favorites: " + favoritePet.getId(), LogTypes.DELETE, "FAVORITE_PETS");

        return new ResponseApi<>(true,HttpStatus.OK,false, "Pet removed from favorites successfully");
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
