package com.barrildorado.lbd.service.rol;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.rol.DatosActualizarRol;
import com.barrildorado.lbd.dto.rol.DatosListadoRol;
import com.barrildorado.lbd.dto.rol.DatosRegistroRol;
import com.barrildorado.lbd.dto.rol.DatosRespuestaRol;
import com.barrildorado.lbd.model.Rol;
import com.barrildorado.lbd.repository.RolRepository;

public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public DatosRespuestaRol getRolById(Long id_rol) {
        Optional<Rol> rolOptional = rolRepository.findById(id_rol);
        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();
            return new DatosRespuestaRol(rol);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }

    @Override
    public Page<DatosListadoRol> getAllRoles(Pageable pageable) {
        Page<Rol> rolPage= rolRepository.findAll(pageable);
        return rolPage.map(DatosListadoRol::new);
    }


    @Override
    public DatosRespuestaRol createRol(DatosRegistroRol datosRegistroRol) {
        Rol rol = rolRepository.save(new Rol(datosRegistroRol));
        return new DatosRespuestaRol(rol);
    }
    @Override
    public DatosRespuestaRol updateRol(DatosActualizarRol datosActualizarRol) {
        Rol rol=rolRepository.getReferenceById(datosActualizarRol.id_rol());
        rol.actualizar(datosActualizarRol);
        rol = rolRepository.save(rol);
        return new DatosRespuestaRol(rol);
    }

    @Override
    public void deleteRol(Long id_rol) {
        Rol rol = rolRepository.findById(id_rol).orElse(null);
        if(rol != null){
            rolRepository.delete(rol);
        }else{
            throw new IllegalArgumentException("No se encontro el Rol con el ID proporcionado");
        }
    }

}
