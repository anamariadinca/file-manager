package com.thesis.filemanager.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ProtectedKeyFilter extends OncePerRequestFilter {

    @Value("${protected.key:placeholder}")
    private String protectedKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestKey = request.getHeader("x-api-key");

        String requestURI = request.getRequestURI();
        if (requestURI.matches("/api/pictures/[^/]+/getProfilePic")
                || requestURI.matches("/api/pdf-files/[^/]+/name")
                || "/register/user".equals(requestURI) ) {
            if (protectedKey.equals(requestKey)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}