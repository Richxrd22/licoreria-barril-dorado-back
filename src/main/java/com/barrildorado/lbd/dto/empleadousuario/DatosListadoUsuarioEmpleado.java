package com.barrildorado.lbd.dto.empleadousuario;

public record DatosListadoUsuarioEmpleado(
    String nombre,
    String apellido,
    String dni,
    String correo_personal,
    String correo_empresarial,
    String telefono,
    Boolean activo,
    String nombre_rol
) {
    
} 