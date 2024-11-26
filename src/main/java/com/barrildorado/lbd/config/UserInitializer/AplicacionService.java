package com.barrildorado.lbd.config.UserInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AplicacionService {
    @Autowired
    private CreacionUsuarioService creacionUsuarioService;

    @PostConstruct
    public void init() {
        // Crear Usuario, Empleado y Rol
        creacionUsuarioService.crearUsuarioEmpleadoRol();
    }
}
