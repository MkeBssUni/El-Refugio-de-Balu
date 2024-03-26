package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
    

    Optional<AdoptionRequest> findByUser_Id(Long idUser);

//    Page<GetAdoptionRequestDto> findByUser_Id(int id);

}
