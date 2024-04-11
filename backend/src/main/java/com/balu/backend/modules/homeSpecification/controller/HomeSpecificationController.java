package com.balu.backend.modules.homeSpecification.controller;


import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.homeSpecification.model.Dto.SaveHomeDetailsDto;
import com.balu.backend.modules.homeSpecification.model.Dto.UpdateHomeSpecificationDto;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.homeSpecification.service.ServiceHomeSpecification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homeSpecification")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class HomeSpecificationController {
    private final ServiceHomeSpecification serviceHomeSpecification;

    @PostMapping("/")
    public ResponseEntity<ResponseApi<Boolean>> save(@RequestBody SaveHomeDetailsDto dto) {
        ResponseApi<Boolean> response = serviceHomeSpecification.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<HomeSpecification>> get(@PathVariable Long id) {
        ResponseApi<HomeSpecification> response = serviceHomeSpecification.get(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<HomeSpecification>> update(@PathVariable Long id, @RequestBody UpdateHomeSpecificationDto dto) {
        ResponseApi<HomeSpecification> response = serviceHomeSpecification.update(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}