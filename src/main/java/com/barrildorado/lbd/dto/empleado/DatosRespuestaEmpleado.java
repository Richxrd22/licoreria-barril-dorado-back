package com.barrildorado.lbd.dto.empleado;

import com.barrildorado.lbd.model.Empleado;

public record DatosRespuestaEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo,
        String telefono,
        String direccion,
        Boolean activo) {

    public DatosRespuestaEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getDireccion(),
                empleado.getActivo());
    }
}