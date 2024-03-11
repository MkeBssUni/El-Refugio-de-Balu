package com.balu.backend.modules.people.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.people.model.*;
import com.balu.backend.modules.people.model.dto.ChangePasswordDto;
import com.balu.backend.modules.people.model.dto.PersonDto;
import com.balu.backend.modules.people.model.dto.PublicRegisterDto;
import com.balu.backend.modules.people.model.dto.SaveAdminOrModDto;
import com.balu.backend.modules.roles.model.IRoleRepository;
import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {
    private final IPersonRepository iPersonRepository;
    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder encoder;
    private final Validations validations = new Validations();
    private final HashService hashService;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> publicRegister(PublicRegisterDto dto) throws Exception {
        if(dto.getUsername() == null || dto.getLastname() == null || dto.getName() == null || dto.getPhoneNumber() == null || dto.getPassword() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isNotBlankString(dto.getUsername()) && validations.isNotBlankString(dto.getPassword()) && validations.isNotBlankString((dto.getName())) && validations.isNotBlankString(dto.getLastname()) && validations.isNotBlankString(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidEmail(dto.getUsername())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPassword(dto.getPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getName())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getLastname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getSurname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPhoneNumber(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());

        Person person = new Person();
        User user = new User();
        Optional<Role> role = iRoleRepository.findByName(Roles.GENERAL);
        if(role.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ROLE_NOT_FOUND.name());
        user.save(hashService.encrypt(dto.getUsername()),encoder.encode(dto.getPassword()),role.get());
        user = iUserRepository.saveAndFlush(user);
        person.savePublicRegister(dto,user);
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person), HttpStatus.CREATED, false,"OK");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> saveAdminOrMod(SaveAdminOrModDto dto) throws Exception {
        //Validar que el usuario que hace la petición sea un administrador
        //Falta desencriptar los datos
        if(dto.getUsername() == null || dto.getLastname() == null || dto.getName() == null || dto.getPhoneNumber() == null || dto.getPassword() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isNotBlankString(dto.getUsername()) && validations.isNotBlankString(dto.getPassword()) && validations.isNotBlankString((dto.getName())) && validations.isNotBlankString(dto.getLastname()) && validations.isNotBlankString(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidEmail(dto.getUsername())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPassword(dto.getPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getName())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getLastname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getSurname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPhoneNumber(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());
        Optional<Role> role = iRoleRepository.findById(dto.getRole().getId());
        if(role.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ROLE_NOT_FOUND.name());

        Person person = new Person();
        User user = new User();
        user.save(hashService.encrypt(dto.getUsername()), encoder.encode(dto.getPassword()),role.get());
        user = iUserRepository.saveAndFlush(user);
        person.saveAdminOrMod(dto,user);
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person), HttpStatus.CREATED, false,"OK");
    }
    @Transactional(readOnly = true)
    public ResponseApi<Person> getDetails(PersonDto dto){
        //Igual, faltaría meter la lógica de la encriptación
        if(dto.getPersonId() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        Optional<Person> person = iPersonRepository.findById(dto.getPersonId());
        return person.map(value -> new ResponseApi<>(value, HttpStatus.OK, false, "OK")).orElseGet(() -> new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name()));

    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> changeStatus(PersonDto dto) throws Exception{
        Optional<Person> person = iPersonRepository.findById(dto.getPersonId());
        if(person.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        person.get().getUser().setBlocked(!(person.get().getUser().isBlocked()));
        if(person.get().getUser().isBlocked()){
            person.get().getUser().setBlockedAt(LocalDateTime.now());
        }else{
            person.get().getUser().setBlockedAt(null);
        }
        iUserRepository.saveAndFlush(person.get().getUser());
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person.get()), HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> changePassword(ChangePasswordDto dto){
        Optional<Person> person = iPersonRepository.findById(dto.getPersonId());
        if(person.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        if(!encoder.matches(dto.getOldPassword(),person.get().getUser().getPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPassword(dto.getNewPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        person.get().getUser().setPassword(encoder.encode(dto.getNewPassword()));

        person.get().getUser().setAttempts(0);
        person.get().getUser().setBlocked(false);
        person.get().getUser().setBlockedAt(null);

        iUserRepository.saveAndFlush(person.get().getUser());
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person.get()), HttpStatus.OK, false, "OK");
    }
}
