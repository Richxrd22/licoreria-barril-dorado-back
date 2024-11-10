package com.barrildorado.lbd.service.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.categoria.DatosActualizarCategoria;
import com.barrildorado.lbd.dto.categoria.DatosListadoCategoria;
import com.barrildorado.lbd.dto.categoria.DatosRegistroCategoria;
import com.barrildorado.lbd.dto.categoria.DatosRespuestaCategoria;



public interface CategoriaService {
    DatosRespuestaCategoria getCategoriaById(Long id_categoria);

    Page<DatosListadoCategoria> getAllCategorias(Pageable pageable);

    DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria);

    DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria);

    void deleteCategoria(Long id_categoria);
}
