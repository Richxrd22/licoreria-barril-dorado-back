package com.barrildorado.lbd.dto.empresa;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroEmpresa(
        @NotBlank String nombre,
        @NotBlank String ruc,
        String website) {
}