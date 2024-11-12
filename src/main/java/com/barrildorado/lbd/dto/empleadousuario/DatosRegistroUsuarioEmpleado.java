package com.barrildorado.lbd.dto.empleadousuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuarioEmpleado(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String dni,
        @Email String correoEmpleado,
        @NotBlank String telefono,
        @NotBlank String direccion,
        @NotNull Boolean activo,
        @NotBlank String clave,
        @Email @NotBlank String correo,
        @NotNull Long id_rol) {
}