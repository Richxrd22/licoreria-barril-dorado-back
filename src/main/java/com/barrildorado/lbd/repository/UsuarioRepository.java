package com.barrildorado.lbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barrildorado.lbd.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
}
