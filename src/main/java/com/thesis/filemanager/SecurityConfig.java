package com.thesis.filemanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/api/pdf-files*") // Disable CSRF protection for /api/pdf-files
                        .ignoringRequestMatchers("/api/pdf-files/*") // Disable CSRF protection for /api/pdf-files
                ) // Disable CSRF protection
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/pdf-files*").permitAll() // Allow access to the POST endpoint
                        .requestMatchers("/api/pdf-files/*").permitAll() // Allow access to the POST endpoint
                        .anyRequest().authenticated() // Require authentication for other requests
                );

        return http.build();
    }
}

