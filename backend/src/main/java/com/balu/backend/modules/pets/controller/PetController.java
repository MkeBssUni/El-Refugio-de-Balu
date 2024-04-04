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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/pet")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PetController {
    private final PetService petService;

    @PostMapping("/catalog")
    public ResponseEntity<ResponseApi<?>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestBody FindPetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(petService.findAllPaged(dto, pageable));
    }

    @PostMapping("/details")
    public ResponseEntity<ResponseApi<?>> findDetails(@RequestBody FindPetDetailsDto dto) {
        ResponseApi<?> response = petService.findPetDetails(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/owned")
    public ResponseEntity<ResponseApi<?>> findMyPetsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestBody FindMyPetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(petService.findMyPets(dto, pageable));
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

    @PostMapping("/moderated")
    public ResponseEntity<ResponseApi<?>> findMyPetsAsModPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestBody FindMyPetsAsModDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        ResponseApi<?> response = petService.findMyPetsAsMod(dto, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
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

    @PostMapping("/adoption")
    public ResponseEntity<ResponseApi<?>> complete(@RequestBody CompleteAdoptionDto dto) {
        ResponseApi<?> response = petService.completeAdoption(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/end")
    public ResponseEntity<ResponseApi<?>> end(@RequestBody EndPetRequestDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<?> response = petService.end(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<ResponseApi<?>> comment(@RequestBody CommentPetDto dto) {
        ResponseApi<?> response = petService.comment(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/comment/all")
    public ResponseEntity<ResponseApi<?>> comments(@RequestBody FindCommentsDto dto) {
        ResponseApi<?> response = petService.findComments(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseApi<?>> save(@RequestBody SavePetDto dto) {
        ResponseApi<?> response = petService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi<?>> update(@RequestBody UpdatePetDto dto) {
        ResponseApi<?> response = petService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<ResponseApi<?>> cancel(@RequestBody CancelDto dto) {
        ResponseApi<?> response = petService.cancel(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
