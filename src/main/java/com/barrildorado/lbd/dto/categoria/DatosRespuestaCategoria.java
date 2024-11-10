package com.barrildorado.lbd.dto.categoria;

import com.barrildorado.lbd.model.Categoria;

public record DatosRespuestaCategoria(
        Long id_categoria,
        String nombre_categoria) {

    public DatosRespuestaCategoria(Categoria categoria) {
        this(
                categoria.getId_categoria(),
                categoria.getNombre_categoria());
    }
}