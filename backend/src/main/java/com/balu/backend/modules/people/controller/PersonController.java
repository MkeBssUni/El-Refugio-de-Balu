package com.balu.backend.modules.people.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.people.model.PersonDto;
import com.balu.backend.modules.people.model.PublicRegisterDto;
import com.balu.backend.modules.people.model.SaveAdminOrModDto;
import com.balu.backend.modules.people.service.PersonService;
import lombok.AllArgsConstructor;
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
}
