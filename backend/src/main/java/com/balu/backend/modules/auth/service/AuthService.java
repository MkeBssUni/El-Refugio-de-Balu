package com.balu.backend.modules.auth.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.auth.dto.SignedDto;
import com.balu.backend.modules.auth.dto.UserSignInDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import com.balu.backend.modules.users.model.IUserRepository;
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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final HashService hashService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository iUserRepository;
    private final LogService logService;

    public ResponseEntity<ResponseApi<SignedDto>> signIn(UserSignInDto userSignInDto){
        Optional<User> userOptional = userService.loadUserByUsername(userSignInDto.getUsername());
        if(userOptional.isEmpty()) return new ResponseEntity<>(new ResponseApi<>(HttpStatus.UNAUTHORIZED, true, ErrorMessages.INCORRECT_CREDENTIALS.name()), HttpStatus.UNAUTHORIZED);
        User user = userOptional.get();

        String tableAffected = "USERS";
        if(user.isBlocked()){
            if(user.getBlockedAt()==null){
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.UNAUTHORIZED, true, ErrorMessages.INACTIVE_USER.name()), HttpStatus.UNAUTHORIZED);
            }else if(user.getBlockedAt().isBefore(LocalDateTime.now().minusMinutes(30))) {
                user.setAttempts(2);
                user.setBlocked(false);
                user.setBlockedAt(null);
                logService.saveLog("User with id:" + user.getId() +" has been unblocked after 30 minutes", LogTypes.UNBLOCK, tableAffected);
            }else{
                logService.saveLog("User with id:" + user.getId() +" has tried to log in while blocked", LogTypes.FAILED_LOGIN, tableAffected);
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.UNAUTHORIZED, true, ErrorMessages.ACCOUNT_BLOCKED_TEMPORARY.name()), HttpStatus.UNAUTHORIZED);
            }
        }

        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getUsername(), hashService.decrypt(userSignInDto.getPassword())));

            user.setAttempts(0);

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtProvider.generateToken(auth);

            String prefix = "Bearer";
            SignedDto signedDto = new SignedDto(token, prefix,hashService.encrypt(user.getId()),user.getRole(),user.isProfileCompleted());

            signedDto.setUserId(hashService.encrypt(user.getId()));

            user.setLastAccess(LocalDateTime.now());
            iUserRepository.saveAndFlush(user);

            logService.saveLog("User with id:" + user.getId() +" has logged in", LogTypes.LOGIN, tableAffected);

            return new ResponseEntity<>(new ResponseApi<>(signedDto, HttpStatus.OK, false, "OK"), HttpStatus.OK);
        }catch (Exception e){
            user.setAttempts(user.getAttempts()+1);
            if(user.getAttempts() == 3){
                user.setBlocked(true);
                user.setBlockedAt(LocalDateTime.now());
                logService.saveLog("User with id:  "+user.getId()+" has been blocked", LogTypes.BLOCK, tableAffected);
                iUserRepository.saveAndFlush(user);
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.UNAUTHORIZED, true, ErrorMessages.ACCOUNT_JUST_BLOCKED.name()), HttpStatus.BAD_REQUEST);
            }
            iUserRepository.saveAndFlush(user);
            logService.saveLog("User with id:" + user.getId() +" has failed to log in", LogTypes.FAILED_LOGIN, tableAffected);
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.UNAUTHORIZED, true, ErrorMessages.INCORRECT_CREDENTIALS.name()), HttpStatus.BAD_REQUEST);
        }
    }
}