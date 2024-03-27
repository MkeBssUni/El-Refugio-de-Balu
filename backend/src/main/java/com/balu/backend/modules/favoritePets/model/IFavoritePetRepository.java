package com.balu.backend.modules.favoritePets.model;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoritePetRepository extends JpaRepository<FavoritePet, Long> {
    boolean existsByPetAndUser(Pet pet, User user);
}
