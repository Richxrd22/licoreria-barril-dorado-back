package com.barrildorado.lbd.dto.rol;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRol(
        @NotBlank String nombre_rol) {
}