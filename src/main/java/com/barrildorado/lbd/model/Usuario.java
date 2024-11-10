package com.barrildorado.lbd.model;

import com.barrildorado.lbd.dto.usuario.DatosActualizarUsuario;
import com.barrildorado.lbd.dto.usuario.DatosRegistroUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@EqualsAndHashCode(of = "id_usuario")
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol id_rol;

    @OneToOne
    @JoinColumn(name = "id_empleado", unique = true, nullable = false)
    private Empleado id_empleado;

    public Usuario(@Valid DatosRegistroUsuario datosRegistroUsuario, Empleado empleado, Rol rol) {
        this.clave = datosRegistroUsuario.clave();
        this.correo = datosRegistroUsuario.correo();
        this.id_empleado = empleado;
        this.id_rol = rol;
    }

    public void actualizar(@Valid DatosActualizarUsuario datosActualizarUsuario, Empleado empleado, Rol rol) {

        this.clave = datosActualizarUsuario.clave();
        this.correo = datosActualizarUsuario.correo();
        this.id_empleado = empleado;
        this.id_rol = rol;

    }
}
