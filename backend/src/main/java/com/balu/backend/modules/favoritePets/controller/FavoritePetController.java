package com.balu.backend.modules.favoritePets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.favoritePets.model.dto.AddFavoritePetDto;
import com.balu.backend.modules.favoritePets.model.dto.FindFavoritePetsDto;
import com.balu.backend.modules.favoritePets.model.dto.RemoveFavoritePetDto;
import com.balu.backend.modules.favoritePets.service.FavoritePetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite/pet")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class FavoritePetController {
    private final FavoritePetService favoritePetService;

    @PostMapping("/catalog")
    public ResponseEntity<ResponseApi<?>> findFavoritePets(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "12", required = false) int size,
            @RequestBody FindFavoritePetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseApi<?> response = favoritePetService.findFavoritePets(dto, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseApi<?>> addFavoritePet(@RequestBody AddFavoritePetDto dto) {
        ResponseApi<?> response = favoritePetService.addFavoritePet(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseApi<?>> removeFavoritePet(@RequestBody RemoveFavoritePetDto dto) {
        ResponseApi<?> response = favoritePetService.removeFavoritePet(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
