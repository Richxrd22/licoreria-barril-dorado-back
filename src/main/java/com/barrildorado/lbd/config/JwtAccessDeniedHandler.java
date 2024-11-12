package com.barrildorado.lbd.config;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // Registra el acceso denegado para depuración
        System.out.println("Acceso denegado: " + accessDeniedException.getMessage());

        // Responde con un mensaje de error más explícito
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Establecer el código de estado 403
        response.setContentType("application/json"); // Establecer tipo de contenido para una respuesta JSON

        // Escribir el mensaje de error en el cuerpo de la respuesta
        response.getWriter()
                .write("{\"error\": \"Acceso denegado: No tienes el rol necesario para acceder a este recurso\"}");
    }
}