package com.barrildorado.lbd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query("SELECT r FROM Rol r WHERE r.nombre_rol = ?1")
    Optional<Rol> findByNombre_rol(String nombreRol);
}
