package com.barrildorado.lbd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barrildorado.lbd.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar un usuario por correo
    Optional<Usuario> findByCorreo(String correo);

}
