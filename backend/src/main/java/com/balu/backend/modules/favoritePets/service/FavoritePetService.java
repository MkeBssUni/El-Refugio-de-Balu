package com.balu.backend.modules.favoritePets.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.favoritePets.model.FavoritePet;
import com.balu.backend.modules.favoritePets.model.IFavoritePetRepository;
import com.balu.backend.modules.favoritePets.model.dto.FavoritePetDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.pets.model.repositories.IPetRepository;
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
public class FavoritePetService {
    private final IFavoritePetRepository favoritePetRepository;
    private final IPetRepository petRepository;
    private final IUserRepository userRepository;
    private final HashService hashService;
    private final Validations validations = new Validations();

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<?> addFavoritePet(FavoritePetDto dto) {
        if (dto.getPet() == null || validations.isNotBlankString(dto.getPet()) || dto.getUser() == null || validations.isNotBlankString(dto.getUser()))
            return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());

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

        if (petRepository.existsByIdAndOwner(pet.getId(), user)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_USER.name());

        if (favoritePetRepository.existsByPetAndUser(pet, user)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ALREADY_EXISTS.name());

        FavoritePet favoritePet = new FavoritePet(user, pet);
        favoritePetRepository.saveAndFlush(favoritePet);
        return new ResponseApi<>(HttpStatus.OK,false, "Pet added to favorites successfully");
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
