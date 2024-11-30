package com.barrildorado.lbd.service.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.empresa.DatosActualizarEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosListadoEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRegistroEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRespuestaEmpresa;



public interface EmpresaService {
    DatosRespuestaEmpresa getEmpresaById(Long id_empresa);
    Page<DatosListadoEmpresa> getAllEmpresas(Pageable pageable);
    Page<DatosListadoEmpresa> getAllEmpresasSinProveedorActivo(Pageable pageable, Long idProveedorActual);
    DatosRespuestaEmpresa createEmpresa(DatosRegistroEmpresa datosRegistroEmpresa);
    DatosRespuestaEmpresa updateEmpresa(DatosActualizarEmpresa datosActualizarEmpresa);
    void deleteEmpresa(Long id_empresa);
}
