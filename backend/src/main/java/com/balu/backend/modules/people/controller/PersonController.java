package com.balu.backend.modules.people.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.SearchDto;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.people.model.dto.ChangePasswordDto;
import com.balu.backend.modules.people.model.dto.PersonDto;
import com.balu.backend.modules.people.model.dto.PublicRegisterDto;
import com.balu.backend.modules.people.model.dto.SaveAdminOrModDto;
import com.balu.backend.modules.people.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PersonController {
    private final PersonService personService;

    @PostMapping("/publicRegister")
    public ResponseEntity<ResponseApi<Person>> publicRegister(@RequestBody PublicRegisterDto dto) throws Exception {
        ResponseApi<Person> response = personService.publicRegister(dto);
        return new ResponseEntity<>(response, response.getStatus());
    }
    @PostMapping("/")
    public ResponseEntity<ResponseApi<Person>> saveAdminOrMod(@RequestBody SaveAdminOrModDto dto) throws Exception {
        ResponseApi<Person> response = personService.saveAdminOrMod(dto);
        return new ResponseEntity<>(response, response.getStatus());
    }
    @PostMapping("/details")
    public ResponseEntity<ResponseApi<Person>> getDetails(@RequestBody PersonDto dto) throws Exception{
        try {
            ResponseApi<Person> response = personService.getDetails(dto);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/change/status")
    public ResponseEntity<ResponseApi<Person>> changeStatus(@RequestBody PersonDto dto) throws Exception{
        try{
            ResponseApi<Person> response = personService.changeStatus(dto);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/change/password")
    public ResponseEntity<ResponseApi<Person>> changePassword(@RequestBody ChangePasswordDto dto) throws Exception{
        try{
            ResponseApi<Person> response = personService.changePassword(dto);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/paged/")
    public ResponseEntity<ResponseApi<Page<Person>>> getPage(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sort,
            @RequestParam(defaultValue = "asc", required = false) String direction,
            @RequestBody SearchDto dto) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        ResponseApi<Page<Person>> responseApi = personService.getPaged(dto, pageable);
        return new ResponseEntity<>(responseApi, responseApi.getStatus());
    }
    @PatchMapping("/reset/password")
    public ResponseEntity<ResponseApi<String>> resetPassword(@RequestBody PersonDto dto) throws Exception{
        try{
            ResponseApi<String> response = personService.resetPassword(dto);
            return new ResponseEntity<>(response, response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR, true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
