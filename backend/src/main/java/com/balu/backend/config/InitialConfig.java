package com.balu.backend.config;

import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.roles.model.IRoleRepository;
import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
@AllArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final IUserRepository iUserRepository;
    private final IStatusRepository iStatusRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final HashService hashService;

    @Override
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public void run(String... args) throws Exception {
        String password = "baluchis";
        this.getOrSaveStatus(Statusses.ADOPTED);
        this.getOrSaveStatus(Statusses.PENDING);
        this.getOrSaveStatus(Statusses.APPROVED);
        this.getOrSaveStatus(Statusses.CLOSED);
        this.getOrSaveStatus(Statusses.IN_REVISION);

        this.getOrSaveRoles(Roles.GENERAL);
        this.getOrSaveRoles(Roles.ADMIN);
        this.getOrSaveRoles(Roles.MOD);

        this.getOrSaveUser("baluchis@mail.com", password, Roles.ADMIN);
        this.getOrSaveUser("baluchis_general@mail.com", password, Roles.GENERAL);
        this.getOrSaveUser("baluchis_mod@mail.com", password, Roles.MOD);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void getOrSaveStatus (Statusses name){
        Optional<Status> optionalStatus = iStatusRepository.findByName(name);
        if(optionalStatus.isEmpty()) iStatusRepository.saveAndFlush(new Status(name));
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void getOrSaveRoles (Roles name){
        Optional<Role> optionalRole = iRoleRepository.findByName(name);
        if(optionalRole.isEmpty())iRoleRepository.saveAndFlush(new Role(name));
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void getOrSaveUser (String username, String password, Roles role) throws Exception {
        username = hashService.encrypt(username);
        Optional<Role> optionalRole = iRoleRepository.findByName(role);
        Optional<User> optionalUser = iUserRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            String finalUsername = username;
            optionalRole.ifPresent(role1 -> iUserRepository.saveAndFlush(new User(finalUsername, passwordEncoder.encode(password), role1)));
        }
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

