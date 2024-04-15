package com.balu.backend.modules.adoptionRequests.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
    Optional<AdoptionRequest> findByUser_Id(Long idUser);
    @Query(value="select adopt.id, adopt.created_at as createdAt, adopt.pet_id as petId, pet.name as petname, cat.name as specie, statu.name as status " +
            "from adoption_requests adopt " +
            "inner join pets pet on adopt.pet_id = pet.id " +
            "inner join categories cat on cat.id = pet.category_id " +
            "inner join statusses  statu on statu.id = adopt.status_id " +
            "where adopt.user_id = :idUser " +
            "and lower(pet.name) like lower(concat('%', :namePet, '%'))", nativeQuery = true)
    Page<IAdoptionRequestViewPaged> findAllPaged(@Param("idUser") Long idUser, @Param("namePet") String namePet, Pageable pageable);

    @Query(value = "SELECT adoption.id, adoption.created_at AS createdAt, sta.name AS status, CONCAT(person.name, ' ', person.last_name, ' ', person.sur_name) AS fullname " +
            "FROM adoption_requests adoption " +
            "INNER JOIN users us ON adoption.user_id = us.id " +
            "INNER JOIN statusses sta ON sta.id = adoption.status_id " +
            "INNER JOIN people person ON person.user_id = us.id " +
            "WHERE adoption.pet_id = :idPet " +
            "AND (LOWER(person.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(person.last_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(person.sur_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(sta.name) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "ORDER BY adoption.id ASC",
            nativeQuery = true)
    Page<IAdoptionRequestModViewPaged> findAllPagedMod(@Param("idPet") Long idPet, @Param("search") String search, Pageable pageable);

    @Query(value = "select count(*) from adoption_requests adoption \n" +
            "inner join statusses estado on estado.id = adoption.status_id \n" +
            "where estado.name=\"PENDING\" and adoption.user_id = :idUser ;", nativeQuery = true)
    int countAdoptionByUser(@Param("idUser") Long idUser);

    @Query(value = "select count(*) from adoption_requests inner join pets on adoption_requests.pet_id = pets.id\n" +
            "inner join statusses estado on estado.id = adoption_requests.status_id \n" +
            " where adoption_requests.pet_id = :idPet and estado.name = \"PENDING\";",nativeQuery = true)
    int countAdoptionByPet(@Param("idPet") Long idPet);



    Optional<AdoptionRequest> findByUser_IdAndPet_Id(Long userId, Long petId);

    @Modifying
    @Query(value = "UPDATE adoption_requests SET status_id = 4 WHERE pet_id = :idPet AND id != :id", nativeQuery = true)
    int changeIfAdopted(@Param("idPet") Long idPet, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE adoption_requests SET  status_id= :status WHERE id = :id", nativeQuery = true)
    Integer changeStatusAdoptionRequest(@Param("id") Long id, @Param("status") Long idStatus);

    @Query(value = "select users.username as username from adoption_requests inner join users on adoption_requests.user_id = users.id \n" +
            "inner join statusses on statusses.id = adoption_requests.status_id where pet_id = 1 and statusses.name=\"CLOSED\"",nativeQuery = true)
    Optional<IAdoptionRequestClosed> getClosed ();

    Long countAdoptionRequestByPet_Moderator_Id(Long idModerador);




}
