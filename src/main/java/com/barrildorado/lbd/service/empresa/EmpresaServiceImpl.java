package com.barrildorado.lbd.service.empresa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.empresa.DatosActualizarEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosListadoEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRegistroEmpresa;
import com.barrildorado.lbd.dto.empresa.DatosRespuestaEmpresa;
import com.barrildorado.lbd.dto.proveedor.DatosListadoProveedor;
import com.barrildorado.lbd.model.Empresa;
import com.barrildorado.lbd.model.Proveedor;
import com.barrildorado.lbd.repository.EmpresaRepository;
import com.barrildorado.lbd.repository.ProveedorRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

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
        Page<Empresa> empresaPage = empresaRepository.findAll(pageable);
        return empresaPage.map(DatosListadoEmpresa::new);
    }

    @Override
    public DatosRespuestaEmpresa createEmpresa(DatosRegistroEmpresa datosRegistroEmpresa) {
        Empresa empresa = empresaRepository.save(new Empresa(datosRegistroEmpresa));
        return new DatosRespuestaEmpresa(empresa);
    }

    @Override
    public DatosRespuestaEmpresa updateEmpresa(DatosActualizarEmpresa datosActualizarEmpresa) {
        Empresa empresa = empresaRepository.getReferenceById(datosActualizarEmpresa.id_empresa());
        empresa.actualizar(datosActualizarEmpresa);
        empresa = empresaRepository.save(empresa);
        return new DatosRespuestaEmpresa(empresa);
    }

    @Override
    public void deleteEmpresa(Long id_empresa) {
        Empresa empresa = empresaRepository.findById(id_empresa).orElse(null);
        if (empresa != null) {
            empresaRepository.delete(empresa);
        } else {
            throw new IllegalArgumentException("No se encontro la Empresa con el ID proporcionado");
        }
    }

    @Override
    public Page<DatosListadoEmpresa> getAllEmpresasSinProveedorActivo(Pageable pageable, Long idProveedorActual) {
        // Obtener la lista de proveedores activos
        List<Long> proveedoresActivos = proveedorRepository.findAll().stream()
                .filter(proveedor -> Boolean.TRUE.equals(proveedor.getActivo())) // Filtrar proveedores activos
                .map(proveedor -> proveedor.getId_empresa().getId_empresa()) // Obtener el id_empresa de proveedores
                                                                             // activos
                .collect(Collectors.toList());

        // Si se pasa un idProveedorActual, eliminarlo de la lista de empresas a excluir
        if (idProveedorActual != null) {
            Proveedor proveedorActual = proveedorRepository.findById(idProveedorActual).orElse(null);
            if (proveedorActual != null) {
                proveedoresActivos.remove(proveedorActual.getId_empresa().getId_empresa()); // Excluir la empresa del
                                                                                            // proveedor actual
            }
        }

        // Obtener empresas paginadas
        Page<Empresa> empresaPage = empresaRepository.findAll(pageable);

        // Filtrar empresas cuyo id_empresa no está en la lista de proveedores activos
        List<DatosListadoEmpresa> empresasSinProveedorActivo = empresaPage.getContent().stream()
                .filter(empresa -> !proveedoresActivos.contains(empresa.getId_empresa())) // Excluir empresas con
                                                                                          // proveedores activos
                .map(DatosListadoEmpresa::new) // Convertir a DTO
                .collect(Collectors.toList());

        // Devolver la página con las empresas filtradas
        return new PageImpl<>(empresasSinProveedorActivo, pageable, empresaPage.getTotalElements());
    }

}
