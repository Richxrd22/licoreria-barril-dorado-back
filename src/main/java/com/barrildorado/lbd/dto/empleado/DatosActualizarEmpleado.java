package com.barrildorado.lbd.dto.empleado;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarEmpleado(
        @NotNull Long id_empleado,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String dni,
        @Email String correo,
        @NotBlank String telefono,
        @NotBlank String direccion,
        @NotNull Boolean activo) {
}