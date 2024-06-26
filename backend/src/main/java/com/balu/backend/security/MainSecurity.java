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
            "/api/person/send/confirmationCode",
            "/api/person/validate/code",
            "/api/pet/catalog",
            "/api/pet/details",
            "/api/category/carousel",
            "/api/address/",
            "/api/captcha/verify",

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
                        .requestMatchers("/api/person/find/contactInfo").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/person/paged/").hasAnyAuthority(Roles.ADMIN.name())
                        .requestMatchers("/api/pet/owned").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/pet/save").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/pet/update").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/pet/cancel").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/pet/requests").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/moderated").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/credential").hasAnyAuthority(Roles.MOD.name(),Roles.GENERAL.name())
                        .requestMatchers("/api/pet/select").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/adoption").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/end").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/pet/comment/all").hasAnyAuthority(Roles.MOD.name(), Roles.GENERAL.name())
                        .requestMatchers("/api/pet/comment").hasAnyAuthority(Roles.MOD.name(), Roles.GENERAL.name())
                        .requestMatchers("/api/favorite/pet/add").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/favorite/pet/remove").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/favorite/pet/catalog").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/adoption/saveRequest").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/adoption/paged").hasAnyAuthority(Roles.GENERAL.name())
                        .requestMatchers("/api/adoption/getAdoption").hasAnyAuthority(Roles.GENERAL.name(),Roles.MOD.name())
                        .requestMatchers("/api/adoption/byPet").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/adoption/changeStatus").hasAnyAuthority(Roles.MOD.name())
                        .requestMatchers("/api/adoption/changeStatusClosed").hasAnyAuthority(Roles.GENERAL.name())
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
