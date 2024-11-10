package com.barrildorado.lbd.model;

import com.barrildorado.lbd.dto.rol.DatosActualizarRol;
import com.barrildorado.lbd.dto.rol.DatosRegistroRol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@EqualsAndHashCode(of = "id_rol")
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    @Column(unique = true, nullable = false)
    private String nombre_rol;

    public Rol(@Valid DatosRegistroRol datosRegistroRol) {
        this.nombre_rol = datosRegistroRol.nombre_rol();

    }

    public void actualizar(@Valid DatosActualizarRol datosActualizarRol) {

        this.nombre_rol = datosActualizarRol.nombre_rol();

    }

}
