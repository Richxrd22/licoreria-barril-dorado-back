package com.barrildorado.lbd.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Registra el error de autenticación para la depuración
        System.out.println("Error de autenticación: " + authException.getMessage());

        // Establece el código de estado 401 (No autorizado)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Establece el tipo de contenido como JSON
        response.setContentType("application/json");

        // Crea un objeto de error en formato JSON
        String errorMessage = "{\"error\": \"Error de autenticación: " + authException.getMessage() + "\"}";

        // Escribe el mensaje de error en el cuerpo de la respuesta
        response.getWriter().write(errorMessage);
    }
}