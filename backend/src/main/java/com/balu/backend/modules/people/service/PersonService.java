package com.balu.backend.modules.people.service;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.people.model.IPersonRepository;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.people.model.PublicRegisterDto;
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

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> publicRegister(PublicRegisterDto dto){
        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());

        Person person = new Person();
        User user = new User();
        Optional<Role> role = iRoleRepository.findByName(Roles.GENERAL);
        if(role.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ROLE_NOT_FOUND.name());
        user.savePublicRegister(dto.getUsername(),encoder.encode(dto.getPassword()),role.get());
        user = iUserRepository.saveAndFlush(user);
        System.out.println("user: "+user.toString());
        person.savePublicRegister(dto,user);
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person), HttpStatus.CREATED, false,"OK");
    }
}
