package com.balu.backend.modules.people.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.people.model.PublicRegisterDto;
import com.balu.backend.modules.people.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class PersonController {
    private final PersonService personService;

    @PostMapping("/publicRegister")
    public ResponseEntity<ResponseApi<Person>> publicRegister(@Validated(PublicRegisterDto.class) @RequestBody PublicRegisterDto dto){
        ResponseApi<Person> response = personService.publicRegister(dto);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
