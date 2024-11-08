package com.barrildorado.lbd.dto.empleado;

import com.barrildorado.lbd.model.Empleado;

public record DatosListadoEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo,
        String telefono,
        Boolean activo) {

    public DatosListadoEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getActivo());
    }
}