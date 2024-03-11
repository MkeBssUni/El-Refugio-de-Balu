package com.balu.backend.modules.people.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.Validations;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.people.model.IPersonRepository;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.people.model.PublicRegisterDto;
import com.balu.backend.modules.people.model.SaveAdminOrModDto;
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
}
