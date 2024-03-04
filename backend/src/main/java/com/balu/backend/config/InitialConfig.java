package com.balu.backend.config;

import com.balu.backend.modules.roles.model.IRoleRepository;
import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.statusses.model.IStatusRepository;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.statusses.model.Statusses;
import com.balu.backend.modules.users.model.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
@AllArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final IUserRepository iUserRepository;
    private final IStatusRepository iStatusRepository;
    private final IRoleRepository iRoleRepository;

    @Override
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public void run(String... args) throws Exception {
        getOrSaveStatus(Statusses.ADOPTADA);
        getOrSaveStatus(Statusses.APROBADA);
        getOrSaveStatus(Statusses.CERRADA);
        getOrSaveStatus(Statusses.PENDIENTE);
        getOrSaveStatus(Statusses.EN_REVISION);

        getOrSaveRoles(Roles.GENERAL);
        getOrSaveRoles(Roles.ADMINISTRADOR);
        getOrSaveRoles(Roles.MODERADOR);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void getOrSaveStatus (Statusses name){
        Optional<Status> optionalStatus = iStatusRepository.findByName(name);
        optionalStatus.orElseGet(() -> iStatusRepository.saveAndFlush(new Status(name)));
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void getOrSaveRoles (Roles name){
        Optional<Role> optionalRole = iRoleRepository.findByName(name);
        optionalRole.orElseGet(() -> iRoleRepository.saveAndFlush(new Role(name)));
    }
}

