package com.balu.backend.security;

import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.security.jwt.JwtAuthenticationFilter;
import com.balu.backend.security.jwt.JwtEntryPoint;
import com.balu.backend.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {
    private static final String[] WHITE_LIST_URL ={
            "/api/person/publicRegister",
            "/api/person/reset/password",
            "/api/auth/",
            "/api/category/list",
            "/api/person/activate/account",
            "/api/person/send/newCode",
            "/api/pet/catalog",
            "/api/address"
    };

    private final UserDetailsServiceImpl userDetailsService;
    public MainSecurity(UserDetailsServiceImpl service){
        this.userDetailsService = service;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtEntryPoint entryPoint() {
        return new JwtEntryPoint();
    }

    @Bean
    public JwtAuthenticationFilter filter() {
        return new JwtAuthenticationFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/api/person/").hasAnyAuthority(Roles.ADMIN.name())
                        .requestMatchers("/api/person/paged/").hasAnyAuthority(Roles.ADMIN.name())
                        .requestMatchers("/api/pet/").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/pet/credential").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/requests").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/moderated").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/select").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/comment").hasAnyAuthority(Roles.MOD.name(), Roles.GENERAL.name())
                        .requestMatchers("/api/pet/end").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/favorite/pet/add").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/favorite/pet/remove").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/favorite/pet/catalog").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/adoption/").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/address").hasAnyAuthority( Roles.GENERAL.name())
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.clearAuthentication(true).logoutUrl("/api/auth/signout"));
        return http.build();
    }

}
