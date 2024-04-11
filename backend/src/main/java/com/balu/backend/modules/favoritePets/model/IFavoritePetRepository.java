package com.balu.backend.modules.favoritePets.model;

import com.balu.backend.modules.favoritePets.model.views.IFavoritePetsView;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IFavoritePetRepository extends JpaRepository<FavoritePet, Long> {
    boolean existsByPetAndUser(Pet pet, User user);

    Optional<FavoritePet> findByPetAndUser(Pet pet, User user);

    @Query(value = "select p.id as id, p.name as name, p.main_image as image, concat(a.city, ', ', a.state) as location from favorite_pets f " +
                        "inner join pets p on p.id = f.pet_id " +
                        "inner join addresses a on p.user_owner_id = a.user_id " +
                        "where p.status_id = 3 and f.user_id = ?1 and (lower(p.name) like %?2% or lower(a.city) like %?2% or lower(a.state) like %?2%) order by f.added_at desc",
            nativeQuery = true)
    Page<IFavoritePetsView> findByUserPaged(Long userId, String searchValue, Pageable pageable);
}
