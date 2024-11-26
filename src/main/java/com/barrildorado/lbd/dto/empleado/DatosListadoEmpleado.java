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
        int activo,
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
                empleado.getActivo() != null && empleado.getActivo() ? 1 : 0, // Convertir a 0 o 1,
                empleado.getUsuario().getId_rol().getNombre_rol());
    }
}