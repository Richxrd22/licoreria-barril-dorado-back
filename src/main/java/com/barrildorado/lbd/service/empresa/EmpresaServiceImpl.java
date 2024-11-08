package com.barrildorado.lbd.service.empresa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.empresa.DatosActualizarEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosListadoEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRegistroEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRespuestaEmpresa;
import com.barrildorado.lbd.model.Empresa;
import com.barrildorado.lbd.repository.EmpresaRepository;

public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public DatosRespuestaEmpresa getEmpresaById(Long id_empresa) {
         Optional<Empresa> empresaOptional = empresaRepository.findById(id_empresa);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            return new DatosRespuestaEmpresa(empresa);
        } else {
            throw new RuntimeException("Empresa no encontrado");
        }
    }

    @Override
    public Page<DatosListadoEmpresa> getAllEmpresas(Pageable pageable) {
       Page<Empresa> empresaPage= empresaRepository.findAll(pageable);
        return empresaPage.map(DatosListadoEmpresa::new);
    }

    @Override
    public DatosRespuestaEmpresa createEmpresa(DatosRegistroEmpresa datosRegistroEmpresa) {
        Empresa empresa = empresaRepository.save(new Empresa(datosRegistroEmpresa));
        return new DatosRespuestaEmpresa(empresa);
    }

    @Override
    public DatosRespuestaEmpresa updateEmpresa(DatosActualizarEmpresa datosActualizarEmpresa) {
        Empresa empresa=empresaRepository.getReferenceById(datosActualizarEmpresa.id_empresa());
        empresa.actualizar(datosActualizarEmpresa);
        empresa = empresaRepository.save(empresa);
        return new DatosRespuestaEmpresa(empresa);
    }

    @Override
    public void deleteEmpresa(Long id_empresa) {
        Empresa empresa = empresaRepository.findById(id_empresa).orElse(null);
        if(empresa != null){
            empresaRepository.delete(empresa);
        }else{
            throw new IllegalArgumentException("No se encontro la Empresa con el ID proporcionado");
        }
    }

}
