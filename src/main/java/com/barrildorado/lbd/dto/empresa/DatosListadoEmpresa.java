package com.barrildorado.lbd.dto.empresa;

import com.barrildorado.lbd.model.Empresa;

public record DatosListadoEmpresa(
    Long id_empresa,
    String nombre,
    String ruc,
    String website) {

public DatosListadoEmpresa(Empresa empresa) {
    this(
            empresa.getId_empresa(),
            empresa.getNombre(),
            empresa.getRuc(),
            empresa.getWebsite());
}
}