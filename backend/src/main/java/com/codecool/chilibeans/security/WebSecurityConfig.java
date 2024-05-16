package com.codecool.chilibeans.security;

import com.codecool.chilibeans.security.jwt.AuthEntryPointJwt;
import com.codecool.chilibeans.security.jwt.AuthTokenFilter;
import com.codecool.chilibeans.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtUtils jwtUtils;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/clients/auth/**").permitAll()
                        .requestMatchers("/api/clients/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/clients/{id}").permitAll()
                        //TODO possible patch and delete handled in service layer
                        .requestMatchers(HttpMethod.GET, "/api/recipes/").permitAll()
                        .requestMatchers("/api/clients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/recipes").hasRole("USER")
                        .requestMatchers(HttpMethod.PATCH, "/api/recipes").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/recipes").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/diets").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/diets").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/units").hasRole("USER")
                        .requestMatchers("/api/units/**").hasRole("ADMIN")
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
