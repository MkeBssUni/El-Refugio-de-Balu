package com.balu.backend.modules.pets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.pets.model.dto.PetDto;
import com.balu.backend.modules.pets.model.dto.PetsPagedDto;
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
            @RequestBody PetsPagedDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return ResponseEntity.ok(petService.findAllPaged(dto, pageable));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<?>> save(@RequestBody PetDto dto) {
        ResponseApi<?> response = petService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
