package com.barrildorado.lbd.dto.producto;

import java.time.LocalDate;

import com.barrildorado.lbd.model.Producto;

public record DatosListadoProducto(
        Long id_producto,
        String nombre,
        String descripcion,
        int cantidad,
        Boolean estado_cantidad,
        LocalDate fecha_produccion,
        LocalDate fecha_vencimiento,
        String categoria) {
    public DatosListadoProducto(Producto producto) {
        this(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCantidad(),
                producto.getEstado_cantidad(),
                producto.getFecha_produccion(),
                producto.getFecha_vencimiento(),
                producto.getId_categoria().getNombre_categoria());
    }
}
