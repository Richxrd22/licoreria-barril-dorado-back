package com.barrildorado.lbd.dto.proveedor;

import com.barrildorado.lbd.model.Proveedor;

public record DatosRespuestaProveedor(
        Long id_proveedor,
        String nombre,
        String apellido,
        String correo,
        String dni,
        String telefono,
        String nombre_empresa) {

    public DatosRespuestaProveedor(Proveedor proveedor) {
        this(
                proveedor.getId_proveedor(),
                proveedor.getNombre(),
                proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getDni(),
                proveedor.getTelefono(),
                proveedor.getId_empresa().getNombre());
    }
}