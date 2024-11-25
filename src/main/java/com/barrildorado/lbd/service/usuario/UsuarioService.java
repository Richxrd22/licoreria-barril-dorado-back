package com.barrildorado.lbd.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.usuario.DatosActualizarContraseñaUsuarioAdmin;
import com.barrildorado.lbd.dto.usuario.DatosActualizarUsuario;
import com.barrildorado.lbd.dto.usuario.DatosListadoUsuario;
import com.barrildorado.lbd.dto.usuario.DatosRegistroUsuario;
import com.barrildorado.lbd.dto.usuario.DatosRespuestaUsuario;

public interface UsuarioService {

    DatosRespuestaUsuario getUsuarioById(Long id_usuario);

    DatosRespuestaUsuario getUsuarioByCorreo(String correo);

    Page<DatosListadoUsuario> getAllUsuarios(Pageable pageable);

    DatosRespuestaUsuario createUsuario(DatosRegistroUsuario datosRegistroUsuario);

    DatosRespuestaUsuario updateUsuario(DatosActualizarUsuario datosActualizarUsuario);
    
    void cambiarContraseñaDirecta( DatosActualizarContraseñaUsuarioAdmin datosActualizarContraseñaUsuarioAdmin);

    void deleteUsuario(Long id_usuario);

}