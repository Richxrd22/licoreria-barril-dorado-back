package com.barrildorado.lbd.dto.proveedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroProveedor(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @Email String correo,
        @NotBlank String dni,
        @NotBlank String telefono,
        @NotNull Long id_empresa) {
}