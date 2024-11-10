package com.barrildorado.lbd.dto.rol;

import com.barrildorado.lbd.model.Rol;

public record DatosRespuestaRol(
        Long id_rol,
        String nombre_rol) {

    public DatosRespuestaRol(Rol rol) {
        this(
                rol.getId_rol(),
                rol.getNombre_rol());
    }
}