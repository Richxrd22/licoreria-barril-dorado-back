package com.barrildorado.lbd.dto.movimiento;

import java.time.LocalDate;

import com.barrildorado.lbd.dto.TipoMovimiento;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroMovimiento(
        @NotNull Long id_producto,
        @NotNull int cantidad,
        @NotNull LocalDate fecha_movimiento,
        @NotNull TipoMovimiento tipo_movimiento

) {

}
