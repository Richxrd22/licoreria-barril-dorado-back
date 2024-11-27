package com.barrildorado.lbd.dto.proveedor;

import com.barrildorado.lbd.model.Proveedor;

public record DatosRespuestaProveedor(
        Long id_proveedor,
        String nombre,
        String apellido,
        String correo,
        String dni,
        String telefono,
        Long id_empresa,
        String empresa,
        int activo) {

    public DatosRespuestaProveedor(Proveedor proveedor) {
        this(
                proveedor.getId_proveedor(),
                proveedor.getNombre(),
                proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getDni(),
                proveedor.getTelefono(),
                proveedor.getId_empresa().getId_empresa(),
                proveedor.getId_empresa().getNombre(),
                proveedor.getActivo() != null && proveedor.getActivo() ? 1 : 0  // Convertir a 0 o 1
                );
    }
}