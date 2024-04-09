package com.balu.backend.modules.favoritePets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.favoritePets.model.dto.AddFavoritePetDto;
import com.balu.backend.modules.favoritePets.model.dto.FavoritePetsCatalog;
import com.balu.backend.modules.favoritePets.model.dto.FindFavoritePetsDto;
import com.balu.backend.modules.favoritePets.model.dto.RemoveFavoritePetDto;
import com.balu.backend.modules.favoritePets.service.FavoritePetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/favorite/pet")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class FavoritePetController {
    private final FavoritePetService favoritePetService;

    @PostMapping("/catalog")
    public ResponseEntity<ResponseApi<Page<FavoritePetsCatalog>>> findFavoritePets(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "12", required = false) int size,
            @RequestBody FindFavoritePetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseApi<Page<FavoritePetsCatalog>> response = favoritePetService.findFavoritePets(dto, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseApi<Boolean>> addFavoritePet(@RequestBody AddFavoritePetDto dto) {
        ResponseApi<Boolean> response = favoritePetService.addFavoritePet(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseApi<Boolean>> removeFavoritePet(@RequestBody RemoveFavoritePetDto dto) {
        ResponseApi<Boolean> response = favoritePetService.removeFavoritePet(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
