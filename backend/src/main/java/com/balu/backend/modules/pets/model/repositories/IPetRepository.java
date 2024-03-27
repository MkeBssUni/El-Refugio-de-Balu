package com.balu.backend.modules.pets.model.repositories;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.pets.model.views.IPetCredentialView;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<Pet,Long> {
    @Query(value = "select p.name as name, p.breed as breed, lower(c.name) as category, lower(concat(p.weight,' ',p.weight_unit)) as weight, lower(p.size) as size, lower(p.life_stage) as lifeStage, lower(concat(p.age,' ',p.age_unit)) as age, lower(gender) as gender from pets p inner join categories c on p.category_id = c.id where p.id = ?1", nativeQuery = true)
    Optional<IPetCredentialView> findCredentialById(Long id);

    boolean existsByIdAndOwner(Long id, User owner);
}
