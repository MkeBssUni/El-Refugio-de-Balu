package com.balu.backend.modules.auth.service;

import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.auth.dto.SignedDto;
import com.balu.backend.modules.auth.dto.UserSignInDto;
import com.balu.backend.modules.users.model.User;
import com.balu.backend.modules.users.service.UserService;
import com.balu.backend.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final String prefix = "Bearer";
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<ResponseApi<SignedDto>> signIn(UserSignInDto userSignInDto){
        try {
            Optional<User> userOptional = userService.loadUserByUsername(userSignInDto.getUsername());
            if (userOptional.isEmpty())
                return new ResponseEntity<>(new ResponseApi<>(null, HttpStatus.NOT_FOUND, true, "NotFound"), HttpStatus.BAD_REQUEST);
            User user = userOptional.get();

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getUsername(), userSignInDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtProvider.generateToken(auth);
            SignedDto signedDto = new SignedDto(token, prefix,user.getId(),user.getRole());
            signedDto.setUserId(user.getId());

            return new ResponseEntity<>(new ResponseApi<>(signedDto, HttpStatus.OK, false, "ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("este es el error que se esta generando: "+e);
            String message = "CredentialsMismatch";
            return new ResponseEntity<>(new ResponseApi<>(null, HttpStatus.UNAUTHORIZED, true, message), HttpStatus.BAD_REQUEST);
        }
    }
}
