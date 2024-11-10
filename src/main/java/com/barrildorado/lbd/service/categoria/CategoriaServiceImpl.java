package com.barrildorado.lbd.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.categoria.DatosActualizarCategoria;
import com.barrildorado.lbd.dto.categoria.DatosListadoCategoria;
import com.barrildorado.lbd.dto.categoria.DatosRegistroCategoria;
import com.barrildorado.lbd.dto.categoria.DatosRespuestaCategoria;
import com.barrildorado.lbd.model.Categoria;
import com.barrildorado.lbd.repository.CategoriaRepository;

public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public DatosRespuestaCategoria getCategoriaById(Long id_categoria) {
          Optional<Categoria> categoriaOptional = categoriaRepository.findById(id_categoria);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            return new DatosRespuestaCategoria(categoria);
        } else {
            throw new RuntimeException("Categoria no encontrado");
        }
    }
    @Override
    public Page<DatosListadoCategoria> getAllCategorias(Pageable pageable) {
         Page<Categoria> categoriaPage= categoriaRepository.findAll(pageable);
        return categoriaPage.map(DatosListadoCategoria::new);
    }

    @Override
    public DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria) {
        Categoria categoria = categoriaRepository.save(new Categoria(datosRegistroCategoria));
        return new DatosRespuestaCategoria(categoria);
    }

    @Override
    public DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria) {
        Categoria categoria=categoriaRepository.getReferenceById(datosActualizarCategoria.id_categoria());
        categoria.actualizar(datosActualizarCategoria);
        categoria = categoriaRepository.save(categoria);
        return new DatosRespuestaCategoria(categoria);
    }
    @Override
    public void deleteCategoria(Long id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria).orElse(null);
        if(categoria != null){
            categoriaRepository.delete(categoria);
        }else{
            throw new IllegalArgumentException("No se encontro la Categoria con el ID proporcionado");
        }
    }
    
}
