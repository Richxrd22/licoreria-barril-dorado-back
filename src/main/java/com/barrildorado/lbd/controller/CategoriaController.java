package com.barrildorado.lbd.controller;
import com.barrildorado.lbd.dto.categoria.*;
import com.barrildorado.lbd.service.categoria.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaCategoria> registrarCategoria(
            @Valid @RequestBody DatosRegistroCategoria datosRegistroCategoria,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaCategoria datosRespuestaCategoria = categoriaService.createCategoria(datosRegistroCategoria);
        URI url = uriComponentsBuilder.path("/buscar/{id_categoria}")
                .buildAndExpand(datosRespuestaCategoria.id_categoria())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaCategoria);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoCategoria>> listarCategorias(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoCategoria> categoriaPage = categoriaService.getAllCategorias(paginacion);
        return ResponseEntity.ok(categoriaPage);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaCategoria> actualizarCategoria(
            @Valid @RequestBody DatosActualizarCategoria datosActualizarCategoria) {
        DatosRespuestaCategoria datosRespuestaCategoria = categoriaService.updateCategoria(datosActualizarCategoria);
        return ResponseEntity.ok(datosRespuestaCategoria);
    }

    @GetMapping("/buscar/{id_categoria}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Long id_categoria) {
        try {
            DatosRespuestaCategoria categoria = categoriaService.getCategoriaById(id_categoria);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la Categoria con ID " + id_categoria;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{id_categoria}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id_categoria) {
        try {
            categoriaService.deleteCategoria(id_categoria);
            return ResponseEntity.ok("Eliminación Exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}