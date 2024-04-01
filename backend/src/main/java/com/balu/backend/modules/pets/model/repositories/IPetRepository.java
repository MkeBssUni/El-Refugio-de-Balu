package com.balu.backend.modules.pets.model.repositories;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.pets.model.views.*;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<Pet,Long> {
    @Query(value = "select p.id as id, p.main_image as image, p.name as name, concat(a.city, ', ', a.state) as location from pets p " +
                        "inner join addresses a on p.user_owner_id = a.user_id " +
                        "where p.status_id = 3 and lower(p.size) like %?1% and lower(p.life_stage) like %?2% and lower(a.state) like %?3% " +
                        "and (p.gender = ?4 or ?4 is null) and (p.category_id = ?5 or ?5 is null)", nativeQuery = true)
    Page<IPetsView> findAllPaged(String size, String lifeStage, String location, String gender, Long categoryId, Pageable pageable);

    @Query(value = "select p.id as id, p.main_image as image, p.name as name, concat(a.city, ', ', a.state) as location, " +
                        "case when f.id is not null then true else false end as favorite from pets p " +
                        "inner join addresses a on p.user_owner_id = a.user_id " +
                        "left join favorite_pets f ON p.id = f.pet_id and f.user_id = ?6 " +
                        "where p.status_id = 3 and lower(p.size) like %?1% and lower(p.life_stage) like %?2% and lower(a.state) like %?3% " +
                        "and (p.gender = ?4 or ?4 is null) and (p.category_id = ?5 or ?5 is null) and p.user_owner_id != ?6", nativeQuery = true)
    Page<IPetsByUserView> findAllByUserPaged(String size, String lifeStage, String location, String gender, Long categoryId, Long userId, Pageable pageable);

    @Query(value = "select p.name as name, p.breed as breed, lower(c.name) as category, lower(concat(p.weight,' ',p.weight_unit)) as weight, lower(p.size) as size, lower(p.life_stage) as lifeStage, lower(concat(p.age,' ',p.age_unit)) as age, lower(gender) as gender from pets p inner join categories c on p.category_id = c.id where p.id = ?1", nativeQuery = true)
    Optional<IPetCredentialView> findCredentialById(Long id);

    @Query(value = "select p.id as id, c.name as category, p.name as name, p.breed as breed, lower(p.size) as size, lower(p.gender) as gender, p.age as age, lower(p.age_unit) as ageUnit, lower(s.name) as status from pets p " +
                        "inner join categories c on p.category_id = c.id inner join statusses s on p.status_id = s.id " +
                        "where p.status_id = 2 and (c.id = ?1 or ?1 is null) and lower(p.size) like %?2% and (lower(p.gender) = ?3 or ?3 is null) and (lower(p.name) like %?4% or lower(p.breed) like %?4%)",
            nativeQuery = true)
    Page<IPetRequestsView> findNewPetRequests(Long categoryId, String size, String gender, String searchValue, Pageable pageable);

    @Query(value = "select p.id as id, p.cancel_reason as cancelRequest, count(a.id) as requests, count(a.id) as requests, c.name as category, p.name as name, lower(s.name) as status from pets p " +
                        "inner join categories c on p.category_id = c.id " +
                        "inner join statusses s on p.status_id = s.id " +
                        "left join adoption_requests a on p.id = a.pet_id " +
                        "where p.user_moderator_id = ?1 and (p.category_id = ?2 or ?2 is null) and lower(s.name) like %?3% and lower(p.name) like %?4% " +
                        "group by p.id", nativeQuery = true)
    Page<IMyPetsAsModView> findMyPetsAsMod(Long userId, Long categoryId, String status, String searchValue, Pageable pageable);

    @Query(value = "select p.id as id, count(pc.id) as comments, p.name as name, concat(a.city, ', ', a.state) as location, lower(s.name) as status from pets p " +
                        "inner join addresses a on p.user_owner_id = a.user_id " +
                        "inner join statusses s on p.status_id = s.id " +
                        "left join pet_comments pc on p.id = pc.pet_id " +
                        "where p.user_owner_id = ?1 and lower(s.name) like %?2% and (lower(p.name) like %?3% or lower(a.city) like %?3% or lower(a.state) like %?3%) " +
                        "group by p.id", nativeQuery = true)
    Page<IMyPetsView> findMyPetsByOwner(Long ownerId, String status, String searchValue, Pageable pageable);

    boolean existsByIdAndOwner(Long id, User owner);

    @Query(value = "select count(*) from pets where status_id = 5 or status_id = 3 and user_moderator_id = ?1", nativeQuery = true)
    int countActivePetsByUser(Long userId);
}
