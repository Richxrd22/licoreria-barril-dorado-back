package com.barrildorado.lbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Empleado;
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{
    
}
