package com.barrildorado.lbd.model;

import java.util.List;

import com.barrildorado.lbd.dto.proveedor.DatosActualizarProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosRegistroProveedor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(of = "id_proveedor")
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true, nullable = false)
    private String correo;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(unique = true, nullable = false, length = 9)
    private String telefono;
    @Column
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa id_empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "id_proveedor")
    private List<Producto> productos;

    public Proveedor(@Valid DatosRegistroProveedor datosRegistroProveedor, Empresa empresa) {
        this.nombre = datosRegistroProveedor.nombre();
        this.apellido = datosRegistroProveedor.apellido();
        this.correo = datosRegistroProveedor.correo();
        this.dni = datosRegistroProveedor.dni();
        this.telefono = datosRegistroProveedor.telefono();
        this.id_empresa = empresa;
    }

    public void actualizar(@Valid DatosActualizarProveedor datosActualizarProveedor, Empresa empresa) {

        this.nombre = datosActualizarProveedor.nombre();
        this.apellido = datosActualizarProveedor.apellido();
        this.correo = datosActualizarProveedor.correo();
        this.dni = datosActualizarProveedor.dni();
        this.telefono = datosActualizarProveedor.telefono();

        this.id_empresa = empresa;
    }

}
