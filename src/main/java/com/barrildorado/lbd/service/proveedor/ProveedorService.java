package com.barrildorado.lbd.service.proveedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.proveedor.DatosActualizarProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosListadoProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosRegistroProveedor;
import com.barrildorado.lbd.dto.proveedor.DatosRespuestaProveedor;

public interface ProveedorService {
    DatosRespuestaProveedor getProveedorById(Long id_proveedor);

    Page<DatosListadoProveedor> getAllProveedores(Pageable pageable);

    DatosRespuestaProveedor createProveedor(DatosRegistroProveedor datosRegistroProveedor);

    DatosRespuestaProveedor updateProveedor(DatosActualizarProveedor datosActualizarProveedor);

    void deleteProveedor(Long id_proveedor);
}
