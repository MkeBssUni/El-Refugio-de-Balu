package com.balu.backend.modules.pets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.pets.model.dto.*;
import com.balu.backend.modules.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PetController {
    private final PetService petService;

    @PostMapping("/catalog")
    public ResponseEntity<ResponseApi<?>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestBody PetCatalogPagedDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return ResponseEntity.ok(petService.findAllPaged(dto, pageable));
    }

    @PostMapping("/requests")
    public ResponseEntity<ResponseApi<?>> findNewPetRequestsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestBody FindPetRequestsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return ResponseEntity.ok(petService.findNewPetRequests(dto, pageable));
    }

    @PostMapping("/credential")
    public ResponseEntity<ResponseApi<?>> findCredentialById(@RequestBody PetCredentialDto dto) {
        ResponseApi<?> response = petService.findPetCredentials(dto.getId());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/select")
    public ResponseEntity<ResponseApi<?>> select(@RequestBody SelectPetDto dto) {
        ResponseApi<?> response = petService.select(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<ResponseApi<?>> comment(@RequestBody CommentPetDto dto) {
        ResponseApi<?> response = petService.comment(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<?>> save(@RequestBody SavePetDto dto) {
        ResponseApi<?> response = petService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
