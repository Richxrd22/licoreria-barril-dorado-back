package com.barrildorado.lbd.dto.empleadousuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuarioEmpleado(
        @NotNull Long id_empleado,
        @NotNull Long id_usuario,
        @NotNull Long id_rol,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String dni,
        @Email String correo_personal,
        @NotBlank String telefono,
        @NotBlank String direccion,
        @NotNull Boolean activo

        ) {
}