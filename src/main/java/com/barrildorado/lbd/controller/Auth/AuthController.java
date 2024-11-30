package com.barrildorado.lbd.controller.Auth;

import com.barrildorado.lbd.dto.empleadousuario.DatosActualizarUsuarioEmpleado;
import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.dto.login.DatosLoginUsuario;
import com.barrildorado.lbd.dto.login.DatosRespuestaLoginUsuario;
import com.barrildorado.lbd.exception.DuplicateEntityException;
import com.barrildorado.lbd.exception.UnauthorizedOperationException;
import com.barrildorado.lbd.service.AuthService;
import com.barrildorado.lbd.service.UsuarioEmpleado.UsuarioEmpleadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioEmpleadoService usuarioEmpleadoService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaLoginUsuario> login(@RequestBody DatosLoginUsuario request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DatosRegistroUsuarioEmpleado request) {
        try {
            DatosRespuestaLoginUsuario respuesta = authService.register(request);
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (UnauthorizedOperationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DatosRespuestaLoginUsuario> update(
            @RequestBody DatosActualizarUsuarioEmpleado datosActualizarUsuarioEmpleado) {
        try {
            DatosRespuestaLoginUsuario respuesta = usuarioEmpleadoService
                    .updateUsuarioEmpleado(datosActualizarUsuarioEmpleado);

            return ResponseEntity.ok(respuesta);
        } catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
