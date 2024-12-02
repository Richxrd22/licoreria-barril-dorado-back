package com.barrildorado.lbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.MovimientoStock;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {
    @Query(value = "SELECT " +
            "MONTH(fecha_movimiento) AS mes, " +
            "SUM(CASE WHEN tipo_movimiento = 'ENTRADA' THEN cantidad ELSE 0 END) AS entrada, " +
            "SUM(CASE WHEN tipo_movimiento = 'SALIDA' THEN cantidad ELSE 0 END) AS salida " +
            "FROM movimiento_stock " +
            "WHERE fecha_movimiento BETWEEN :startDate AND :endDate " +
            "GROUP BY MONTH(fecha_movimiento) " +
            "ORDER BY mes", nativeQuery = true)
    List<Object[]> obtenerMovimientosPorMes(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

}
