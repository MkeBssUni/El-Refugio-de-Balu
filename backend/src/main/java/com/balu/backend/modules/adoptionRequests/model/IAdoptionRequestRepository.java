package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
    Optional<AdoptionRequest> findByUser_Id(Long idUser);
    Long countByUser_IdAndStatus_Id(Long idUser, Long statusId);

    Optional<AdoptionRequest> findByUser_IdAndPet_Id(Long userId, Long petId);
    Optional<AdoptionRequest> findByPet_Id(Long idPet);
    Long countAdoptionRequestByPet_Id(Long idPet);

    @Modifying
    @Query(value = "UPDATE adoption_requests SET  status_id= :status WHERE id = :id", nativeQuery = true)
    Integer changeStatusAdoptionRequest(@Param("id") Long id, @Param("status") Long idStatus);

}
