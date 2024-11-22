package com.barrildorado.lbd.controller;


import com.barrildorado.lbd.dto.proveedor.*;
import com.barrildorado.lbd.service.proveedor.ProveedorService;

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
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaProveedor> registrarProveedor(
            @Valid @RequestBody DatosRegistroProveedor datosRegistroProveedor,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaProveedor datosRespuestaProveedor = proveedorService.createProveedor(datosRegistroProveedor);
        URI url = uriComponentsBuilder.path("/buscar/{id_proveedor}")
                .buildAndExpand(datosRespuestaProveedor.id_proveedor())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaProveedor);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoProveedor>> listarProveedores(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoProveedor> proveedorPage = proveedorService.getAllProveedores(paginacion);
        List<DatosListadoProveedor> proveedores = proveedorPage.getContent();
        return ResponseEntity.ok(proveedores);
    }
    
    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaProveedor> actualizarProveedor(
            @Valid @RequestBody DatosActualizarProveedor datosActualizarProveedor) {
        DatosRespuestaProveedor datosRespuestaProveedor = proveedorService.updateProveedor(datosActualizarProveedor);
        return ResponseEntity.ok(datosRespuestaProveedor);
    }

    @GetMapping("/buscar/{id_proveedor}")
    public ResponseEntity<?> buscarProveedor(@PathVariable Long id_proveedor) {
        try {
            DatosRespuestaProveedor proveedor = proveedorService.getProveedorById(id_proveedor);
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el Proveedor con ID " + id_proveedor;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id_proveedor) {
        try {
            proveedorService.deleteProveedor(id_proveedor);
            return ResponseEntity.ok("Eliminación Exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
