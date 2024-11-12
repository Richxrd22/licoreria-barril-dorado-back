package com.barrildorado.lbd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.dto.login.DatosLoginUsuario;
import com.barrildorado.lbd.dto.login.DatosRespuestaLoginUsuario;
import com.barrildorado.lbd.jwt.JwtService;
import com.barrildorado.lbd.repository.UsuarioRepository;
import com.barrildorado.lbd.service.usuario.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    
    public DatosRespuestaLoginUsuario login(DatosLoginUsuario request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.correo(), request.clave()));
        UserDetails usuario = usuarioRepository.findByCorreo(request.correo()).orElseThrow();
        String token = jwtService.getToken(usuario);
        return new DatosRespuestaLoginUsuario(token);
    }

    public DatosRespuestaLoginUsuario register(DatosRegistroUsuarioEmpleado request) {
        return usuarioService.createUsuarioEmpleado(request);
    }

}
