package com.balu.backend.modules.pets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.pets.model.dto.PetDto;
import com.balu.backend.modules.pets.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PetController {
    private final PetService petService;

    @PostMapping("/")
    public ResponseEntity<ResponseApi<?>> save(@Validated(PetDto.Save.class) @Valid @RequestBody PetDto dto) {
        ResponseApi<?> response = petService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
