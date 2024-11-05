package com.barrildorado.lbd.service.producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.producto.DatosActualizarProducto;
import com.barrildorado.lbd.dto.producto.DatosListadoProducto;
import com.barrildorado.lbd.dto.producto.DatosRegistroProducto;
import com.barrildorado.lbd.dto.producto.DatosRespuestaProducto;

public interface ProductoService {

    DatosRespuestaProducto getProductoById(Long id_producto);
    Page<DatosListadoProducto> getAllProductos(Pageable pageable);
    DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto);
    DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto);
    void deleteProducto(Long id_producto);

}

