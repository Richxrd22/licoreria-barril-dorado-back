package com.barrildorado.lbd.dto.categoria;

import com.barrildorado.lbd.model.Categoria;

public record DatosRespuestaCategoria(
        Long id_categoria,
        String nombre_categoria,
        int activo) {

    public DatosRespuestaCategoria(Categoria categoria) {
        this(
                categoria.getId_categoria(),
                categoria.getNombre_categoria(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0 // Convertir a 0 o 1
        );
    }
}