package com.barrildorado.lbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrildorado.lbd.model.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
