package com.barrildorado.lbd.controller.Test;

import com.barrildorado.lbd.dto.Login.DatosLoginUsuario;
import com.barrildorado.lbd.dto.Login.DatosRespuestaLoginUsuario;
import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaLoginUsuario> login(@RequestBody DatosLoginUsuario request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaLoginUsuario> register(@RequestBody DatosRegistroUsuarioEmpleado request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
