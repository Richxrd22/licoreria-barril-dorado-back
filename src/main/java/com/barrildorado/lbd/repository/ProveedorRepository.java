package com.barrildorado.lbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Proveedor;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long>{
    
}
