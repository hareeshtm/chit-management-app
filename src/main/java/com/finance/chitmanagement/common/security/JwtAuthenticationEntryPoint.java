package com.finance.chitmanagement.common.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handle Unauthorized (401) – JWT Entry Point
 * This component is used to handle unauthorized access attempts.
 * Spring doesn’t throw a normal exception for unauthenticated access, so we handle it using a custom AuthenticationEntryPoint
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{ \"error\": \"Access Denied: You do not have permission to access this resource\" }");
    }
}


/* * This method is called whenever an unauthenticated user tries to access a protected resource.
    * It sets the response status to 401 (Unauthorized) and returns a JSON error message.
    *  Can JwtAuthenticationEntryPoint throw an exception that gets caught by @ControllerAdvice?
 No, it won’t be handled by your global exception handler class (annotated with @ControllerAdvice) because:

JwtAuthenticationEntryPoint is part of the Spring Security filter chain, and exceptions here happen before your controller is reached.

Global exception handlers in Spring (@ControllerAdvice) only catch exceptions thrown from within controller methods (i.e., after successful authentication and authorization).
 */