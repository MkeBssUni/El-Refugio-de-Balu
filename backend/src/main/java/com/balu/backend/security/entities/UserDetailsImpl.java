package com.balu.backend.security.entities;

import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.users.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final LocalDateTime lastAccess;
    private final Role role;
    private final boolean blocked;
    private final GrantedAuthority authority;
    private final boolean profileCompleted;

    public UserDetailsImpl(String username, String password, LocalDateTime lastAccess, Role role, boolean blocked,  GrantedAuthority authority, boolean profileCompleted) {
        this.username = username;
        this.password = password;
        this.lastAccess = lastAccess;
        this.role = role;
        this.blocked = blocked;
        this.authority = authority;
        this.profileCompleted = profileCompleted;
    }

    public static UserDetailsImpl build(User user){
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName().name()));
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getLastAccess(), user.getRole(), user.isBlocked(), authorities.stream().findFirst().get(), user.isProfileCompleted());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singleton(authority);
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
    public boolean isProfileCompleted() {return profileCompleted;}
}
