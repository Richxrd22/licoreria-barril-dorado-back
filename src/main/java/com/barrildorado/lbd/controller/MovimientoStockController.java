package com.barrildorado.lbd.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.barrildorado.lbd.dto.movimiento.DatosListadoMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRegistroMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRespuestaMovimiento;
import com.barrildorado.lbd.dto.movimiento.MovimientoPorMes;
import com.barrildorado.lbd.model.MovimientoStock;
import com.barrildorado.lbd.repository.MovimientoStockRepository;
import com.barrildorado.lbd.service.movimientoStock.MovimientoStockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimiento-stock")
public class MovimientoStockController {
    @Autowired
    private MovimientoStockService movimientoService;

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoMovimiento>> listarProductos() {
        Pageable paginacion = Pageable.unpaged(); // Para traer todos los productos sin paginación
        List<DatosListadoMovimiento> movimientos = movimientoService.getAllMovimiento(paginacion).getContent();
        return ResponseEntity.ok(movimientos);
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaMovimiento> registrarMovimiento(
            @Valid @RequestBody DatosRegistroMovimiento datosRegistroMovimiento,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaMovimiento datosRespuestaMovimiento = movimientoService.createMovimiento(datosRegistroMovimiento);
        URI url = uriComponentsBuilder.path("/buscar/{id_movimiento}")
                .buildAndExpand(datosRegistroMovimiento.id_producto()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMovimiento);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MovimientoStock>> filtrarMovimientosPorTipo(
            @PathVariable("tipo") String tipoMovimiento) {
        List<MovimientoStock> movimientos = movimientoService.filtrarMovimientosPorTipo(tipoMovimiento);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/por-mes")
    public List<MovimientoPorMes> obtenerMovimientosPorMes(@RequestParam("year") int year) {
        // Construir las fechas de inicio y fin
        String startDate = year + "-01-01"; // 1 de enero del año
        String endDate = year + "-12-31"; // 31 de diciembre del año

        // Llamar al repositorio para obtener los movimientos
        List<Object[]> resultados = movimientoStockRepository.obtenerMovimientosPorMes(startDate, endDate);

        // Mapear los resultados a MovimientoPorMes
        return resultados.stream()
                .map(r -> new MovimientoPorMes(
                        ((Number) r[0]).intValue(), // mes
                        ((Number) r[1]).intValue(), // entrada
                        ((Number) r[2]).intValue() // salida
                ))
                .toList();
    }

}
