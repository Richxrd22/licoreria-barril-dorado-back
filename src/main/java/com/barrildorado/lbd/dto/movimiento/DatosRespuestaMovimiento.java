package com.barrildorado.lbd.dto.movimiento;

import java.time.LocalDate;

import com.barrildorado.lbd.model.MovimientoStock;

public record DatosRespuestaMovimiento(
        Long id_movimiento,
        Long id_producto,
        int cantidad,
        LocalDate fecha_movimiento,
        String tipo_movimiento) {
    public DatosRespuestaMovimiento(MovimientoStock movimientoStock) {
        this(
                movimientoStock.getId_movimiento(),
                movimientoStock.getId_producto().getId_producto(),
                movimientoStock.getCantidad(),
                movimientoStock.getFecha_movimiento(),
                movimientoStock.getTipo_movimiento().name());
    }
}
