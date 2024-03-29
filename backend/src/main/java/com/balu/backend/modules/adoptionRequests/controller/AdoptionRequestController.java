package com.balu.backend.modules.adoptionRequests.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adoptionRequests.model.dto.GetAdoptionRequestDto;
import com.balu.backend.modules.adoptionRequests.model.dto.GetByModeradorDto;
import com.balu.backend.modules.adoptionRequests.model.dto.SaveAdoptionRequestDto;
import com.balu.backend.modules.adoptionRequests.service.ServiceAdoptionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/adoption")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class AdoptionRequestController {
    private final ServiceAdoptionRequest serviceAdoptionRequest;

    @GetMapping("/")
    public ResponseEntity<ResponseApi<Optional<AdoptionRequest>>> getByUserId(@RequestBody GetAdoptionRequestDto dto)throws Exception{
        try {
            ResponseApi<Optional<AdoptionRequest>> response = serviceAdoptionRequest.adoptionByUser(dto.getIdUser());
            return new ResponseEntity<>(response,response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byPet")
    public ResponseEntity<ResponseApi<Optional<AdoptionRequest>>> getByPetId(@RequestBody GetByModeradorDto dto)throws Exception{
        try {
            ResponseApi<Optional<AdoptionRequest>> response = serviceAdoptionRequest.adoptionByUser(dto.getPetId());
            return new ResponseEntity<>(response,response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<?>> save (@RequestBody SaveAdoptionRequestDto dto){
        ResponseApi<?> response = serviceAdoptionRequest.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
