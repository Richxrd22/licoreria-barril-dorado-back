package com.barrildorado.lbd.dto.producto;

import java.time.LocalDate;

import com.barrildorado.lbd.model.Producto;

public record DatosRespuestaProducto(
        Long id_producto,
        String nombre,
        String descripcion,
        int cantidad,
        double precio,
        Boolean estado_cantidad,
        LocalDate fecha_produccion,
        LocalDate fecha_vencimiento,
        String id_categoria,
        String categoria,
        String id_proveedor,
        String proveedor,
        String telefono_proveedor) {

    public DatosRespuestaProducto(Producto producto) {
        this(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCantidad(),
                producto.getPrecio(),
                producto.getEstado_cantidad(),
                producto.getFecha_produccion(),
                producto.getFecha_vencimiento(),
                producto.getId_categoria().getId_categoria().toString(),
                producto.getId_categoria().getNombre_categoria(),
                producto.getId_proveedor().getId_proveedor().toString(),
                producto.getId_proveedor().getNombre() +" "+ producto.getId_proveedor().getApellido(),
                producto.getId_proveedor().getTelefono()
                );
    }
}