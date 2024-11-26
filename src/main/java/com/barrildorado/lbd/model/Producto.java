package com.barrildorado.lbd.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.barrildorado.lbd.dto.producto.DatosActualizarProducto;
import com.barrildorado.lbd.dto.producto.DatosRegistroProducto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_producto")
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id_producto;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean estado_cantidad;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = ISO.DATE)
    private LocalDate fecha_produccion;

    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = ISO.DATE)
    private LocalDate fecha_vencimiento;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria id_categoria;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;

    public Producto(@Valid DatosRegistroProducto datosRegistroProducto, Categoria categoria, Proveedor proveedor) {
        this.nombre = datosRegistroProducto.nombre();
        this.descripcion = datosRegistroProducto.descripcion();
        this.cantidad = datosRegistroProducto.cantidad();
        this.estado_cantidad = datosRegistroProducto.estado_cantidad();
        this.fecha_produccion = datosRegistroProducto.fecha_produccion();
        this.fecha_vencimiento = datosRegistroProducto.fecha_vencimiento();
        this.precio = datosRegistroProducto.precio();
        this.id_categoria = categoria;
        this.id_proveedor = proveedor;
        this.activo = datosRegistroProducto.activo();
    }

    public void actualizar(@Valid DatosActualizarProducto datosActualizarProducto, Categoria categoria,
            Proveedor proveedor) {

        this.nombre = datosActualizarProducto.nombre();
        this.descripcion = datosActualizarProducto.descripcion();
        this.cantidad = datosActualizarProducto.cantidad();
        this.estado_cantidad = datosActualizarProducto.estado_cantidad();
        this.fecha_produccion = datosActualizarProducto.fecha_produccion();
        this.fecha_vencimiento = datosActualizarProducto.fecha_vencimiento();
        this.precio = datosActualizarProducto.precio();
        this.id_categoria = categoria;
        this.id_proveedor = proveedor;
        this.activo = datosActualizarProducto.activo();
    }

}
