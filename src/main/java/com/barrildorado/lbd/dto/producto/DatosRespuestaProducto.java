package com.barrildorado.lbd.dto.producto;

import java.time.LocalDate;

import com.barrildorado.lbd.model.Producto;

public record DatosRespuestaProducto(
        Long id_producto,
        String nombre,
        String descripcion,
        int cantidad,
        double precio,
        int estado_cantidad,  // Cambiado a int para 0 y 1
        LocalDate fecha_produccion,
        LocalDate fecha_vencimiento,
        Long id_categoria,
        String categoria,
        Long id_proveedor,
        String proveedor,
        String telefono_proveedor,
        int activo  // Cambiado a int para 0 y 1
        ) {

    public DatosRespuestaProducto(Producto producto) {
        this(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCantidad(),
                producto.getPrecio(),
                producto.getEstado_cantidad() != null && producto.getEstado_cantidad() ? 1 : 0,  // Convertir a 0 o 1
                producto.getFecha_produccion(),
                producto.getFecha_vencimiento(),
                producto.getId_categoria().getId_categoria(),
                producto.getId_categoria().getNombre_categoria(),
                producto.getId_proveedor().getId_proveedor(),
                producto.getId_proveedor().getNombre() + " " + producto.getId_proveedor().getApellido(),
                producto.getId_proveedor().getTelefono(),
                producto.getActivo() != null && producto.getActivo() ? 1 : 0  // Convertir a 0 o 1
        );
    }
}
