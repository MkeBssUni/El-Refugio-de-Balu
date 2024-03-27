package com.balu.backend.modules.favoritePets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.favoritePets.model.dto.FavoritePetDto;
import com.balu.backend.modules.favoritePets.service.FavoritePetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite/pet")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class FavoritePetController {
    private final FavoritePetService favoritePetService;

    @PostMapping("/add")
    public ResponseEntity<ResponseApi<?>> addFavoritePet(@RequestBody FavoritePetDto dto) {
        ResponseApi<?> response = favoritePetService.addFavoritePet(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
