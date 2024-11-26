package com.barrildorado.lbd.controller;

import com.barrildorado.lbd.dto.empresa.*;
import com.barrildorado.lbd.dto.producto.DatosListadoProducto;
import com.barrildorado.lbd.service.empresa.EmpresaService;

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
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaEmpresa> registrarEmpresa(
            @Valid @RequestBody DatosRegistroEmpresa datosRegistroEmpresa,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaEmpresa datosRespuestaEmpresa = empresaService.createEmpresa(datosRegistroEmpresa);
        URI url = uriComponentsBuilder.path("/buscar/{id_empresa}")
                .buildAndExpand(datosRespuestaEmpresa.id_empresa())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaEmpresa);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoEmpresa>> listarEmpresas() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoEmpresa> empresas = empresaService.getAllEmpresas(paginacion).getContent();
        return ResponseEntity.ok(empresas);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpresa> actualizarEmpresa(
            @Valid @RequestBody DatosActualizarEmpresa datosActualizarEmpresa) {
        DatosRespuestaEmpresa datosRespuestaEmpresa = empresaService.updateEmpresa(datosActualizarEmpresa);
        return ResponseEntity.ok(datosRespuestaEmpresa);
    }

    @GetMapping("/buscar/{id_empresa}")
    public ResponseEntity<?> buscarEmpresa(@PathVariable Long id_empresa) {
        try {
            DatosRespuestaEmpresa empresa = empresaService.getEmpresaById(id_empresa);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la Empresa con ID " + id_empresa;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{id_empresa}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id_empresa) {
        try {
            empresaService.deleteEmpresa(id_empresa);
            return ResponseEntity.ok("Eliminación Exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}