package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
    Optional<AdoptionRequest> findByUser_Id(Long idUser);
    Long countByUser_IdAndStatus_Id(Long idUser, Long statusId);

    Optional<AdoptionRequest> findByUser_IdAndPet_Id(Long userId, Long petId);

}
