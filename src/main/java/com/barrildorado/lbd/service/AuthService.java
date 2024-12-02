package com.barrildorado.lbd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.dto.login.DatosLoginUsuario;
import com.barrildorado.lbd.dto.login.DatosRespuestaLoginUsuario;
import com.barrildorado.lbd.exception.CustomException;
import com.barrildorado.lbd.jwt.JwtService;
import com.barrildorado.lbd.model.Empleado;
import com.barrildorado.lbd.repository.EmpleadoRepository;
import com.barrildorado.lbd.repository.UsuarioRepository;
import com.barrildorado.lbd.service.UsuarioEmpleado.UsuarioEmpleadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioEmpleadoService usuarioEmpleadoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public DatosRespuestaLoginUsuario login(DatosLoginUsuario request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.correo(), request.clave()));

        UserDetails usuario = usuarioRepository.findByCorreo(request.correo()).orElseThrow();

        Empleado empleado = empleadoRepository.findByUsuarioCorreo(request.correo()).orElseThrow();

        if (!empleado.getActivo()) {
            throw new CustomException("El usuario está deshabilitado");
        }

        String token = jwtService.getToken(usuario);

        return new DatosRespuestaLoginUsuario(token);
    }

    public DatosRespuestaLoginUsuario register(DatosRegistroUsuarioEmpleado request) {
        return usuarioEmpleadoService.createUsuarioEmpleado(request);
    }

}
