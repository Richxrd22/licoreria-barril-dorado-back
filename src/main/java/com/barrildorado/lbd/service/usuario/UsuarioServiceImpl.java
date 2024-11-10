package com.barrildorado.lbd.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.usuario.DatosActualizarUsuario;
import com.barrildorado.lbd.dto.usuario.DatosListadoUsuario;
import com.barrildorado.lbd.dto.usuario.DatosRegistroUsuario;
import com.barrildorado.lbd.dto.usuario.DatosRespuestaUsuario;
import com.barrildorado.lbd.model.Empleado;
import com.barrildorado.lbd.model.Rol;
import com.barrildorado.lbd.model.Usuario;
import com.barrildorado.lbd.repository.EmpleadoRepository;
import com.barrildorado.lbd.repository.RolRepository;
import com.barrildorado.lbd.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public DatosRespuestaUsuario getUsuarioById(Long id_usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new DatosRespuestaUsuario(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public Page<DatosListadoUsuario> getAllUsuarios(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        return usuarioPage.map(DatosListadoUsuario::new);
    }

    @Override
    public DatosRespuestaUsuario createUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        Empleado empleado = empleadoRepository.getReferenceById(datosRegistroUsuario.id_empleado());
        Rol rol = rolRepository.getReferenceById(datosRegistroUsuario.id_rol());
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario, empleado, rol));
        return new DatosRespuestaUsuario(usuario);
    }

    @Override
    public DatosRespuestaUsuario updateUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarUsuario.id_empleado());
        Rol rol = rolRepository.getReferenceById(datosActualizarUsuario.id_rol());
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id_usuario());
        usuario.actualizar(datosActualizarUsuario, empleado, rol);
        usuario = usuarioRepository.save(usuario);
        return new DatosRespuestaUsuario(usuario);
    }

    @Override
    public void deleteUsuario(Long id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        } else {
            throw new IllegalArgumentException("No se encontro el Usuario con el ID proporcionado");
        }
    }
}
