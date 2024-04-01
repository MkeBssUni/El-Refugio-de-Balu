package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
    Optional<AdoptionRequest> findByUser_Id(Long idUser);
    @Query(value="select adopt.id, adopt.created_at, adopt.pet_id, pet.name as petname, cat.name as specie, statu.name as status " +
            "from adoption_requests adopt " +
            "inner join pets pet on adopt.pet_id = pet.id " +
            "inner join categories cat on cat.id = pet.category_id " +
            "inner join statusses  statu on statu.id = adopt.status_id " +
            "where adopt.user_id = :idUser " +
            "and lower(pet.name) like lower(concat('%', :namePet, '%'))", nativeQuery = true)
    Page<IAdoptionRequestViewPaged> findAllPaged(@Param("idUser") Long idUser, @Param("namePet") String namePet, Pageable pageable);


    Optional<AdoptionRequest> findById(Long idAdoption);
    Long countByUser_IdAndStatus_Id(Long idUser, Long statusId);

    Optional<AdoptionRequest> findByUser_IdAndPet_Id(Long userId, Long petId);
    Optional<AdoptionRequest> findByPet_Id(Long idPet);
    Long countAdoptionRequestByPet_Id(Long idPet);

    @Modifying
    @Query(value = "UPDATE adoption_requests SET  status_id= :status WHERE id = :id", nativeQuery = true)
    Integer changeStatusAdoptionRequest(@Param("id") Long id, @Param("status") Long idStatus);

    Long countAdoptionRequestByPet_Moderator_Id(Long idModerador);

}
