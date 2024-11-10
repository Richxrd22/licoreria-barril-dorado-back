package com.barrildorado.lbd.service.rol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.rol.DatosActualizarRol;
import com.barrildorado.lbd.dto.rol.DatosListadoRol;
import com.barrildorado.lbd.dto.rol.DatosRegistroRol;
import com.barrildorado.lbd.dto.rol.DatosRespuestaRol;



public interface RolService {
     DatosRespuestaRol getRolById(Long id_rol);

    Page<DatosListadoRol> getAllRoles(Pageable pageable);

    DatosRespuestaRol createRol(DatosRegistroRol datosRegistroRol);

    DatosRespuestaRol updateRol(DatosActualizarRol datosActualizarRol);

    void deleteRol(Long id_rol);
}
