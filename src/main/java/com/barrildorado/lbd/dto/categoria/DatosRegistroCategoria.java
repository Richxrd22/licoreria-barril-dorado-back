package com.barrildorado.lbd.dto.categoria;


import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCategoria(
        @NotBlank String nombre_categoria) {
}