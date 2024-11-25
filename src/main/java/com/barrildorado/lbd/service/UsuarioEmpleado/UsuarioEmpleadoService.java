package com.barrildorado.lbd.service.UsuarioEmpleado;

import com.barrildorado.lbd.dto.empleadousuario.DatosActualizarUsuarioEmpleado;
import com.barrildorado.lbd.dto.empleadousuario.DatosRegistroUsuarioEmpleado;
import com.barrildorado.lbd.dto.login.DatosRespuestaLoginUsuario;

public interface UsuarioEmpleadoService {
    DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    DatosRespuestaLoginUsuario updateUsuarioEmpleado(DatosActualizarUsuarioEmpleado datosActualizarUsuarioEmpleado);

}
