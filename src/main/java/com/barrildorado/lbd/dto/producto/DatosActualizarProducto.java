package com.barrildorado.lbd.dto.producto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProducto(
        @NotNull Long id_producto,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull int cantidad,
        @NotNull double precio,
        @NotNull Boolean estado_cantidad,
        @NotBlank LocalDate fecha_produccion,
        @NotBlank LocalDate fecha_vencimiento,
        @NotNull Long id_categoria,
        @NotNull Long id_proveedor) {

}
