package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {

    Page<GetAdoptionRequestDto> findByUser_Id(int id);

}
