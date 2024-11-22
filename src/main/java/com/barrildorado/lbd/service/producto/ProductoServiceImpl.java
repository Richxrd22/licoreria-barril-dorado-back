package com.barrildorado.lbd.service.producto;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.producto.DatosActualizarProducto;
import com.barrildorado.lbd.dto.producto.DatosListadoProducto;
import com.barrildorado.lbd.dto.producto.DatosRegistroProducto;
import com.barrildorado.lbd.dto.producto.DatosRespuestaProducto;
import com.barrildorado.lbd.model.Categoria;
import com.barrildorado.lbd.model.Producto;
import com.barrildorado.lbd.model.Proveedor;
import com.barrildorado.lbd.repository.CategoriaRepository;
import com.barrildorado.lbd.repository.ProductoRepository;
import com.barrildorado.lbd.repository.ProveedorRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaProducto getProductoById(Long id_producto) {
        Optional<Producto> productoOptional = productoRepository.findById(id_producto);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            return new DatosRespuestaProducto(producto);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    @Override
    public Page<DatosListadoProducto> getAllProductos(Pageable pageable) {
        Page<Producto> productoPage= productoRepository.findAll(pageable);
        return productoPage.map(DatosListadoProducto::new);
    }
    

    @Override
    public DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto) {
        Categoria categoria=categoriaRepository.getReferenceById(datosRegistroProducto.id_categoria());
        Proveedor proveedor = proveedorRepository.getReferenceById(datosRegistroProducto.id_proveedor());
        Producto producto = productoRepository.save(new Producto(datosRegistroProducto,categoria,proveedor));
        return new DatosRespuestaProducto(producto);
    }

    @Override
    public DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto) {
        Producto producto=productoRepository.getReferenceById(datosActualizarProducto.id_producto());
        Categoria categoria=categoriaRepository.getReferenceById(datosActualizarProducto.id_categoria());
        Proveedor proveedor=proveedorRepository.getReferenceById(datosActualizarProducto.id_proveedor());
        producto.actualizar(datosActualizarProducto, categoria, proveedor);
        producto = productoRepository.save(producto);
        return new DatosRespuestaProducto(producto);
    }

    @Override
    public void deleteProducto(Long id_producto) {
        Producto producto = productoRepository.findById(id_producto).orElse(null);
        if(producto != null){
            productoRepository.delete(producto);
        }else{
            throw new IllegalArgumentException("No se encontro el Producto con el ID proporcionado");
        }
    }

}
