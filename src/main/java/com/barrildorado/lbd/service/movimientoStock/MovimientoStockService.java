package com.barrildorado.lbd.service.movimientoStock;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.movimiento.DatosListadoMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRegistroMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRespuestaMovimiento;
import com.barrildorado.lbd.model.MovimientoStock;

public interface MovimientoStockService {

    Page<DatosListadoMovimiento> getAllMovimiento(Pageable pageable);


    MovimientoStock obtenerMovimientoPorId(Long id_movimiento);

    DatosRespuestaMovimiento createMovimiento(DatosRegistroMovimiento datosRegistroMovimiento);

    void eliminarMovimiento(Long id_movimiento);

    List<MovimientoStock> filtrarMovimientosPorTipo(String tipo_movimiento);
}
