package com.balu.backend.modules.auth.controller;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.auth.dto.SignedDto;
import com.balu.backend.modules.auth.dto.UserSignInDto;
import com.balu.backend.modules.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/")
    public ResponseEntity<ResponseApi<SignedDto>> signIn(@RequestBody UserSignInDto userSignInDto){
        return authService.signIn(userSignInDto);
    }
}
