package com.balu.backend.modules.pets.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.pets.model.dto.*;
import com.balu.backend.modules.pets.model.views.ICommentView;
import com.balu.backend.modules.pets.model.views.IPetCredentialView;
import com.balu.backend.modules.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PetController {
    private final PetService petService;

    @PostMapping("/catalog")
    public ResponseEntity<ResponseApi<Page<PetCatalog>>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestBody FindPetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(petService.findAllPaged(dto, pageable));
    }

    @PostMapping("/details")
    public ResponseEntity<ResponseApi<PetDetails>> findDetails(@RequestBody FindPetDetailsDto dto) {
        ResponseApi<PetDetails> response = petService.findPetDetails(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/owned")
    public ResponseEntity<ResponseApi<Page<MyPetsCatalog>>> findMyPetsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestBody FindMyPetsDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(petService.findMyPets(dto, pageable));
    }

    @PostMapping("/requests")
    public ResponseEntity<ResponseApi<Page<PetRequestList>>> findNewPetRequestsPaged(
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
    public ResponseEntity<ResponseApi<Page<MyPetsAsModList>>> findMyPetsAsModPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestBody FindMyPetsAsModDto dto
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        ResponseApi<Page<MyPetsAsModList>> response = petService.findMyPetsAsMod(dto, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/credential")
    public ResponseEntity<ResponseApi<IPetCredentialView>> findCredentialById(@RequestBody PetCredentialDto dto) {
        ResponseApi<IPetCredentialView> response = petService.findPetCredentials(dto.getId());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/select")
    public ResponseEntity<ResponseApi<Boolean>> select(@RequestBody SelectPetDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<Boolean> response = petService.select(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/adoption")
    public ResponseEntity<ResponseApi<Boolean>> complete(@RequestBody CompleteAdoptionDto dto) {
        ResponseApi<Boolean> response = petService.completeAdoption(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/end")
    public ResponseEntity<ResponseApi<Boolean>> end(@RequestBody EndPetRequestDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<Boolean> response = petService.end(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<ResponseApi<Boolean>> comment(@RequestBody CommentPetDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<Boolean> response = petService.comment(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/comment/all")
    public ResponseEntity<ResponseApi<List<ICommentView>>> comments(@RequestBody FindCommentsDto dto) {
        ResponseApi<List<ICommentView>> response = petService.findComments(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseApi<Boolean>> save(@RequestBody SavePetDto dto) {
        ResponseApi<Boolean> response = petService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi<Boolean>> update(@RequestBody UpdatePetDto dto) {
        ResponseApi<Boolean> response = petService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<ResponseApi<Boolean>> cancel(@RequestBody CancelDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseApi<Boolean> response = petService.cancel(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
