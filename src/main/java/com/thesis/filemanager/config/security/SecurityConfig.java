package com.thesis.filemanager.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ProtectedKeyFilter protectedKeyFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, ProtectedKeyFilter protectedKeyFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.protectedKeyFilter = protectedKeyFilter;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO: Implement with new authentication service
        // auth.userDetailsService(myUserDetailsService)
        //         .passwordEncoder(passwordEncoder());
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/openapi/**",
            "/swagger-ui.html",
            "/webjars/swagger-ui/**",
            // other public endpoints
            "/users/authenticate",
            "/users/register/admin"
    };

//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("**")
////                .antMatchers(AUTH_WHITELIST)
//                .permitAll()
//                .anyRequest().authenticated()
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/register/admin", "/register/user", "/users/authenticate").permitAll()
                                .requestMatchers("/api/pictures/*/getProfilePic").permitAll()
                                .requestMatchers("/api/pdf-files/*/name").permitAll()
                        .requestMatchers("/csrf").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(protectedKeyFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
