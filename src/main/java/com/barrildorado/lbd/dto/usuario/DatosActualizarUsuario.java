package com.barrildorado.lbd.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull Long id_usuario,
        @NotBlank String clave,
        @Email @NotBlank String correo,
        @NotNull Long id_empleado,
        @NotNull Long id_rol) {
}