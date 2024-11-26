package com.barrildorado.lbd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Empleado;
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{
     // Método para buscar un empleado por correo
     Optional<Empleado> findByCorreo(String correo);
}
