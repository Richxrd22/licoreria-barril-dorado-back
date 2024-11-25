package com.barrildorado.lbd.service.UsuarioEmpleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.empleadousuario.DatosActualizarUsuarioEmpleado;
import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.dto.login.DatosRespuestaLoginUsuario;
import com.barrildorado.lbd.exception.DuplicateEntityException;
import com.barrildorado.lbd.jwt.JwtService;
import com.barrildorado.lbd.model.Empleado;
import com.barrildorado.lbd.model.Rol;
import com.barrildorado.lbd.model.Usuario;
import com.barrildorado.lbd.repository.EmpleadoRepository;
import com.barrildorado.lbd.repository.RolRepository;
import com.barrildorado.lbd.repository.UsuarioRepository;

@Service
public class UsuarioEmpleadoImpl implements UsuarioEmpleadoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado) {
        Rol rol = rolRepository.findById(datosRegistroUsuarioEmpleado.id_rol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        for (Empleado empleadoBD : listaEmpleados) {
            if (empleadoBD.getCorreo().equals(datosRegistroUsuarioEmpleado.correo_personal()) ||
                    empleadoBD.getTelefono().equals(datosRegistroUsuarioEmpleado.telefono()) ||
                    empleadoBD.getDni().equals(datosRegistroUsuarioEmpleado.dni())) {
                throw new DuplicateEntityException("Empleado con atributos duplicados.");
            }
        }

        Empleado empleado = Empleado.builder()
                .nombre(datosRegistroUsuarioEmpleado.nombre())
                .apellido(datosRegistroUsuarioEmpleado.apellido())
                .dni(datosRegistroUsuarioEmpleado.dni())
                .correo(datosRegistroUsuarioEmpleado.correo_personal())
                .telefono(datosRegistroUsuarioEmpleado.telefono())
                .direccion(datosRegistroUsuarioEmpleado.direccion())
                .activo(datosRegistroUsuarioEmpleado.activo())
                .build();

        Usuario usuario = Usuario.builder()
                .clave(passwordEncoder.encode(datosRegistroUsuarioEmpleado.dni()))
                .correo("LBD" + datosRegistroUsuarioEmpleado.dni() + "@barrildorado.com")
                .id_empleado(empleado)
                .id_rol(rol)
                .build();

        empleadoRepository.save(empleado);
        usuarioRepository.save(usuario);

        String tokenGenerado = jwtService.getToken(usuario);
        return new DatosRespuestaLoginUsuario(tokenGenerado);
    }

    @Override
    public DatosRespuestaLoginUsuario updateUsuarioEmpleado(
            DatosActualizarUsuarioEmpleado datosActualizarUsuarioEmpleado) {
        // Buscar el empleado y validar su existencia
        Empleado empleado = empleadoRepository.findById(datosActualizarUsuarioEmpleado.id_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        // Validar duplicados (excepto para el empleado actual)
        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        for (Empleado empleadoBD : listaEmpleados) {
            if (!empleadoBD.getId_empleado().equals(empleado.getId_empleado())
                    && (empleadoBD.getCorreo().equals(datosActualizarUsuarioEmpleado.correo_personal()) ||
                            empleadoBD.getTelefono().equals(datosActualizarUsuarioEmpleado.telefono()) ||
                            empleadoBD.getDni().equals(datosActualizarUsuarioEmpleado.dni()))) {
                throw new DuplicateEntityException("Empleado con atributos duplicados.");
            }
        }
        // Actualizar los datos del empleado
        empleado.setNombre(datosActualizarUsuarioEmpleado.nombre());
        empleado.setApellido(datosActualizarUsuarioEmpleado.apellido());
        empleado.setDni(datosActualizarUsuarioEmpleado.dni());
        empleado.setCorreo(datosActualizarUsuarioEmpleado.correo_personal());
        empleado.setTelefono(datosActualizarUsuarioEmpleado.telefono());
        empleado.setDireccion(datosActualizarUsuarioEmpleado.direccion());
        empleado.setActivo(datosActualizarUsuarioEmpleado.activo());

        // Buscar el usuario asociado al empleado
        Usuario usuario = usuarioRepository.findById(datosActualizarUsuarioEmpleado.id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario asociado no encontrado"));
        usuario.setCorreo("LBD" + datosActualizarUsuarioEmpleado.dni() + "@barrildorado.com");
        usuario.setClave(usuario.getClave());
        // Actualizar el rol del usuario (si se proporciona un nuevo ID de rol)
        if (datosActualizarUsuarioEmpleado.id_rol() != null) {
            Rol nuevoRol = rolRepository.findById(datosActualizarUsuarioEmpleado.id_rol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setId_rol(nuevoRol);
        }

        // Guardar los cambios en las entidades
        empleadoRepository.save(empleado);
        usuarioRepository.save(usuario);

        // Generar un token actualizado para el usuario (opcional)
        String tokenGenerado = jwtService.getToken(usuario);

        return new DatosRespuestaLoginUsuario(tokenGenerado);
    }

}
