package com.balu.backend.security.service;

import com.balu.backend.modules.users.model.User;
import com.balu.backend.modules.users.service.UserService;
import com.balu.backend.security.entities.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            Optional<User> user = Optional.ofNullable(userService.loadUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UserNotFound")));
            if (user.isPresent()) {
                return UserDetailsImpl.build(user.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
