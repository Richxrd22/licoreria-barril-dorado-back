package com.barrildorado.lbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Empresa;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
    
}
