package com.barrildorado.lbd.dto.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarEmpresa(
        @NotNull Long id_empresa,
        @NotBlank String nombre,
        @NotBlank String ruc,
        String website) {
}