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
    private final LoggingFilter loggingFilter;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils, LoggingFilter loggingFilter) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
        this.loggingFilter = loggingFilter;
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
                        //don t indicate admin, just close the paths for users, URL should not tell whether it is admin or user
                        //Client endpoints change around logic
                        .requestMatchers("/clients/auth/**").permitAll()
                        .requestMatchers("/clients/user/**").hasRole("USER")
                        .requestMatchers("/clients/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clients/{id}").permitAll()

                        //Recipe endpoints
                        .requestMatchers(HttpMethod.GET, "/recipes/**").permitAll()
                        .requestMatchers("api/recipes/user/**").hasRole("USER")

                        //Diets endpoints
                        .requestMatchers("/diets/user/**").hasRole("USER")
                        .requestMatchers("/diets/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/diets/**").permitAll()

                        //Unit endpoints
                        .requestMatchers(HttpMethod.GET, "/units/**").permitAll()
                        .requestMatchers("/units/admin/**").hasRole("ADMIN")

                        //Error
                        .requestMatchers("/error").permitAll()

                        //Any other request
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
