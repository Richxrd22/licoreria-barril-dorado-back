package com.barrildorado.lbd.dto.empresa;
import com.barrildorado.lbd.model.Empresa;

public record DatosRespuestaEmpresa(
        Long id_empresa,
        String nombre,
        String ruc,
        String website) {

    public DatosRespuestaEmpresa(Empresa empresa) {
        this(
                empresa.getId_empresa(),
                empresa.getNombre(),
                empresa.getRuc(),
                empresa.getWebsite());
    }
}