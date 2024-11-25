package com.barrildorado.lbd.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarContraseñaUsuarioAdmin( 
    @NotNull Long id_usuario,    
@NotBlank String nueva_contraseña) {

}
