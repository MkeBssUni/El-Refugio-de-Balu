package com.balu.backend.modules.homeSpecification.controller;


import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.homeSpecification.model.Dto.SaveHomeDetailsDto;
import com.balu.backend.modules.homeSpecification.service.ServiceHomeSpecification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/homeSpecification")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class HomeSpecificationController {
    private final ServiceHomeSpecification serviceHomeSpecification;

    @PostMapping("/")
    public ResponseEntity<ResponseApi<?>> save (@RequestBody SaveHomeDetailsDto dto){
        ResponseApi<?> response = serviceHomeSpecification.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);

    }
}
