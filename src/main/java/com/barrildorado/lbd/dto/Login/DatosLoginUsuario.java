package com.barrildorado.lbd.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosLoginUsuario(

        @Email @NotBlank String correo,
        @NotBlank String clave) {

}
