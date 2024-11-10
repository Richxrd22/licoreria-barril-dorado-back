package com.barrildorado.lbd.dto.categoria;
import com.barrildorado.lbd.model.Categoria;

public record DatosListadoCategoria(
        Long id_categoria,
        String nombre_categoria) {

    public DatosListadoCategoria(Categoria categoria) {
        this(
                categoria.getId_categoria(),
                categoria.getNombre_categoria());
    }
}