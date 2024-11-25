package com.barrildorado.lbd.dto.empleado;

import com.barrildorado.lbd.model.Empleado;

public record DatosListadoEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo_personal,
        String correo_empresarial,
        String telefono,
        Boolean activo,
        String nombre_rol) {

    public DatosListadoEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo(),
                empleado.getUsuario().getCorreo(),
                empleado.getTelefono(),
                empleado.getActivo(),
                empleado.getUsuario().getId_rol().getNombre_rol()
                );
    }
}