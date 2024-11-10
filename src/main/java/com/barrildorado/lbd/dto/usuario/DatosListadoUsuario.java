package com.barrildorado.lbd.dto.usuario;
import com.barrildorado.lbd.model.Usuario;

public record DatosListadoUsuario(
        Long id_usuario,
        String correo,
        String nombre_empleado,
        String nombre_rol) {

    public DatosListadoUsuario(Usuario usuario) {
        this(
                usuario.getId_usuario(),
                usuario.getCorreo(),
                usuario.getId_empleado().getNombre() + " " + usuario.getId_empleado().getApellido(),
                usuario.getId_rol().getNombre_rol());
    }
}