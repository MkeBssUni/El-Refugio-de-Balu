package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {

    // Para traer informacion por medio de un usuario
    //listado de solicitudes
    Page<AdoptionRequest> findAllByUser (String User);

    // Para traer informacion por medio de un moderador
    //listado de solicitudes


}
