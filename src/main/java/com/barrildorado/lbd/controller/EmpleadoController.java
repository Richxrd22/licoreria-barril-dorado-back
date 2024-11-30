package com.barrildorado.lbd.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.barrildorado.lbd.dto.empleado.DatosActualizarEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosListadoEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRegistroEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRespuestaEmpleado;
import com.barrildorado.lbd.service.empleado.EmpleadoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaEmpleado> registrarEmpleado(
            @Valid @RequestBody DatosRegistroEmpleado datosRegistroEmpleado,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaEmpleado datosRespuestaEmpleado = empleadoService.createEmpleado(datosRegistroEmpleado);
        URI url = uriComponentsBuilder.path("/buscar/{id_empleado}")
                .buildAndExpand(datosRespuestaEmpleado.id_empleado())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaEmpleado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoEmpleado>> listarEmpleados() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoEmpleado> empleados = empleadoService.getAllEmpleados(paginacion).getContent();
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpleado> actualizarEmpleado(
            @Valid @RequestBody DatosActualizarEmpleado datosActualizarEmpleado) {
        DatosRespuestaEmpleado datosRespuestaEmpleado = empleadoService.updateEmpleado(datosActualizarEmpleado);
        return ResponseEntity.ok(datosRespuestaEmpleado);
    }

    @GetMapping("/buscar/{id_empleado}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long id_empleado) {
        try {
            DatosRespuestaEmpleado empleado = empleadoService.getEmpleadoById(id_empleado);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el Empleado con ID " + id_empleado;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{id_empleado}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id_empleado) {
        try {
            empleadoService.deleteEmpleado(id_empleado);
            return ResponseEntity.ok("Eliminación Exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscarPorCorreo/{correo}")
    public ResponseEntity<DatosRespuestaEmpleado> getEmpleadoByCorreo(@PathVariable String correo) {
        try {
            DatosRespuestaEmpleado empleado = empleadoService.getEmpleadoByCorreo(correo);
            return ResponseEntity.ok(empleado); 
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}