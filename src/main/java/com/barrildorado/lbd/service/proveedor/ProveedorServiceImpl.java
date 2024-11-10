package com.barrildorado.lbd.service.proveedor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.proveedor.DatosActualizarProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosListadoProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosRegistroProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosRespuestaProveedor;
import com.barrildorado.lbd.model.Empresa;
import com.barrildorado.lbd.model.Proveedor;
import com.barrildorado.lbd.repository.EmpresaRepository;
import com.barrildorado.lbd.repository.ProveedorRepository;
@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public DatosRespuestaProveedor getProveedorById(Long id_proveedor) {
         Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id_proveedor);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            return new DatosRespuestaProveedor(proveedor);
        } else {
            throw new RuntimeException("Proveedor no encontrado");
        }
    }
    @Override
    public Page<DatosListadoProveedor> getAllProveedores(Pageable pageable) {
       Page<Proveedor> proveedorPage= proveedorRepository.findAll(pageable);
        return proveedorPage.map(DatosListadoProveedor::new);
    }


    @Override
    public DatosRespuestaProveedor createProveedor(DatosRegistroProveedor datosRegistroProveedor) {
        Empresa empresa = empresaRepository.getReferenceById(datosRegistroProveedor.id_empresa());
        Proveedor proveedor = proveedorRepository.save(new Proveedor(datosRegistroProveedor,empresa));
        return new DatosRespuestaProveedor(proveedor);
    }
    @Override
    public DatosRespuestaProveedor updateProveedor(DatosActualizarProveedor datosActualizarProveedor) {
        Proveedor proveedor=proveedorRepository.getReferenceById(datosActualizarProveedor.id_proveedor());
        Empresa empresa=empresaRepository.getReferenceById(datosActualizarProveedor.id_empresa());
        proveedor.actualizar(datosActualizarProveedor, empresa);
        proveedor = proveedorRepository.save(proveedor);
        return new DatosRespuestaProveedor(proveedor);
    }

    @Override
    public void deleteProveedor(Long id_proveedor) {
        Proveedor proveedor = proveedorRepository.findById(id_proveedor).orElse(null);
        if(proveedor != null){
            proveedorRepository.delete(proveedor);
        }else{
            throw new IllegalArgumentException("No se encontro el Proveedor con el ID proporcionado");
        }
    }
    
}
