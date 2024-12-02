package com.barrildorado.lbd.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.barrildorado.lbd.dto.TipoMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRegistroMovimiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@EqualsAndHashCode(of = "id_movimiento")
@Table(name = "movimiento_stock")
public class MovimientoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long id_movimiento;

    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = ISO.DATE)
    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDate fecha_movimiento;

    @Column(nullable = false)
    private int cantidad;

    @Enumerated(EnumType.STRING) // Para mapear el ENUM
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipo_movimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto id_producto;


     public MovimientoStock(@Valid DatosRegistroMovimiento datosRegistroMovimiento, Producto producto) {
        this.id_producto = producto;
        this.tipo_movimiento = datosRegistroMovimiento.tipo_movimiento();
        this.cantidad = datosRegistroMovimiento.cantidad();
        this.fecha_movimiento = datosRegistroMovimiento.fecha_movimiento();
    }
}
