package com.barrildorado.lbd.dto.rol;

import com.barrildorado.lbd.model.Rol;

public record DatosListadoRol(
    Long id_rol,
    String nombre_rol) {

  public DatosListadoRol(Rol rol) {
    this(
        rol.getId_rol(),
        rol.getNombre_rol());
  }
}