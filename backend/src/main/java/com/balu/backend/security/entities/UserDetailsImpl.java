package com.balu.backend.security.entities;

import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.users.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final LocalDateTime lastAccess;
    private final Role role;
    private final boolean blocked;

    public UserDetailsImpl(String username, String password, LocalDateTime lastAccess, Role role, boolean blocked) {
        this.username = username;
        this.password = password;
        this.lastAccess = lastAccess;
        this.role = role;
        this.blocked = blocked;
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getLastAccess(), user.getRole(), user.isBlocked());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return username;}
    public LocalDateTime getLastAccess() {return lastAccess;}
    public Role getRole() {return role;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {return !blocked;}
}
