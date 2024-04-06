package com.balu.backend.modules.adoptionRequests.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestModViewPaged;
import com.balu.backend.modules.adoptionRequests.model.IAdoptionRequestViewPaged;
import com.balu.backend.modules.adoptionRequests.model.dto.*;
import com.balu.backend.modules.adoptionRequests.service.ServiceAdoptionRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @PostMapping("/paged/")
    public ResponseEntity<ResponseApi<Page<IAdoptionRequestViewPaged>>> getByUserId(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sort,
            @RequestParam(defaultValue = "asc", required = false) String direction,
            @RequestBody GetAdoptionRequestDto dto)throws Exception{
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(direction), sort));
        ResponseApi<Page<IAdoptionRequestViewPaged>> responseApi = serviceAdoptionRequest.adoptionByUserPaged(dto,pageable);
        return new ResponseEntity<>(responseApi, responseApi.getStatus());
    }

    @PostMapping("/byPet/")
    public ResponseEntity<ResponseApi<Page<IAdoptionRequestModViewPaged>>> getByPetId(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sort,
            @RequestParam(defaultValue = "asc", required = false) String direction,
            @RequestBody GetAdoptionRequestModDto dto)throws Exception{
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(direction), sort));
        ResponseApi<Page<IAdoptionRequestModViewPaged>> responseApi = serviceAdoptionRequest.adoptionByModerador(dto,pageable);
        return new ResponseEntity<>(responseApi, responseApi.getStatus());
    }

    @PostMapping("/getAdoption")
    public ResponseEntity<ResponseApi<Optional<AdoptionRequest>>> getByAdoption(@RequestBody GetByIdAdoptionRequestDto dto)throws Exception{
        try {
            ResponseApi<Optional<AdoptionRequest>> response = serviceAdoptionRequest.findByIdAdoption(dto.getIdAdoption());
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

    @PutMapping("/changeStatus")
    public ResponseEntity<ResponseApi<?>> changeStatus (@RequestBody ChangeStatusAdoptionRequestDto dto){
        ResponseApi<?> response = serviceAdoptionRequest.changeStatus(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/changeStatusClosed")
    public ResponseEntity<ResponseApi<?>> changeStatusGeneral (@RequestBody GetByIdAdoptionRequestDto dto){
        ResponseApi<?> response = serviceAdoptionRequest.changeStatusBygeneral(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
