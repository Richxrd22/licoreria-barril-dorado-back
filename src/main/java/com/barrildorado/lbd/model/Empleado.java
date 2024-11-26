package com.barrildorado.lbd.model;

import com.barrildorado.lbd.dto.empleado.DatosActualizarEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRegistroEmpleado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, equals, hashCode y toString
@Entity
@NoArgsConstructor // Constructor sin parámetros para JPA
@AllArgsConstructor // Constructor con todos los parámetros
@Builder // Permite usar el patrón de construcción
@EqualsAndHashCode(of = "id_empleado")
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false, length = 9)
    private String telefono;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String direccion;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @JsonIgnore
    @OneToOne(mappedBy = "id_empleado")
    private Usuario usuario;

    public Empleado(@Valid DatosRegistroEmpleado datosRegistroEmpleado) {
        this.nombre = datosRegistroEmpleado.nombre();
        this.apellido = datosRegistroEmpleado.apellido();
        this.dni = datosRegistroEmpleado.dni();
        this.correo = datosRegistroEmpleado.correo();
        this.telefono = datosRegistroEmpleado.telefono();
        this.direccion = datosRegistroEmpleado.direccion();
        this.activo = datosRegistroEmpleado.activo();
    }

    public void actualizar(@Valid DatosActualizarEmpleado datosActualizarEmpleado) {
        this.nombre = datosActualizarEmpleado.nombre();
        this.apellido = datosActualizarEmpleado.apellido();
        this.dni = datosActualizarEmpleado.dni();
        this.correo = datosActualizarEmpleado.correo();
        this.telefono = datosActualizarEmpleado.telefono();
        this.direccion = datosActualizarEmpleado.direccion();
        this.activo = datosActualizarEmpleado.activo();
    }

}
