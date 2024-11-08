package com.barrildorado.lbd.dto.proveedor;
import com.barrildorado.lbd.model.Proveedor;

public record DatosListadoProveedor(
        Long id_proveedor,
        String nombre,
        String apellido,
        String correo,
        String dni,
        String telefono,
        String empresa) {

    public DatosListadoProveedor(Proveedor proveedor) {
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