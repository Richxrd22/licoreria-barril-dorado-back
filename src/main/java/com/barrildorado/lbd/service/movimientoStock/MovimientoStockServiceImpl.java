package com.barrildorado.lbd.service.movimientoStock;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.movimiento.DatosListadoMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRegistroMovimiento;
import com.barrildorado.lbd.dto.movimiento.DatosRespuestaMovimiento;
import com.barrildorado.lbd.model.MovimientoStock;
import com.barrildorado.lbd.model.Producto;
import com.barrildorado.lbd.repository.MovimientoStockRepository;
import com.barrildorado.lbd.repository.ProductoRepository;

@Service
public class MovimientoStockServiceImpl implements MovimientoStockService {

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public MovimientoStock obtenerMovimientoPorId(Long id_movimiento) {
        return movimientoStockRepository.findById(id_movimiento)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con ID: " + id_movimiento));
    }

    @Override
    public void eliminarMovimiento(Long id_movimiento) {
        movimientoStockRepository.deleteById(id_movimiento);
    }

    @Override
    public List<MovimientoStock> filtrarMovimientosPorTipo(String tipo_movimiento) {
        return movimientoStockRepository.findAll()
                .stream()
                .filter(movimiento -> movimiento.getTipo_movimiento().toString().equalsIgnoreCase(tipo_movimiento))
                .collect(Collectors.toList());
    }

    @Override
    public Page<DatosListadoMovimiento> getAllMovimiento(Pageable pageable) {
        Page<MovimientoStock> movimientoStock = movimientoStockRepository.findAll(pageable);
        return movimientoStock.map(DatosListadoMovimiento::new);
    }

    @Override
    public DatosRespuestaMovimiento createMovimiento(DatosRegistroMovimiento datosRegistroMovimiento) {
        Producto producto = productoRepository.getReferenceById(datosRegistroMovimiento.id_producto());
        MovimientoStock movimientoStock = movimientoStockRepository
                .save(new MovimientoStock(datosRegistroMovimiento, producto));
        return new DatosRespuestaMovimiento(movimientoStock);

    }

   

}
