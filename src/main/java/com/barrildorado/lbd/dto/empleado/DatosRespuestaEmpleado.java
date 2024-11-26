package com.barrildorado.lbd.dto.empleado;

import com.barrildorado.lbd.model.Empleado;

public record DatosRespuestaEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo_personal,
        String telefono,
        String direccion,
        Long id_usuario,
        String correo_empresarial,
        Long id_rol,
        String nombre_rol,
        int activo) {

    public DatosRespuestaEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getDireccion(),
                empleado.getUsuario().getId_usuario(),
                empleado.getUsuario().getCorreo(),
                empleado.getUsuario().getId_rol().getId_rol(),
                empleado.getUsuario().getId_rol().getNombre_rol(),
                empleado.getActivo() != null && empleado.getActivo() ? 1 : 0  // Convertir a 0 o 1
                );
    }
}