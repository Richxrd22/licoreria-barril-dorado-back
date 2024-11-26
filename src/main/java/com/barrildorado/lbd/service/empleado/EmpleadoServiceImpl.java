package com.barrildorado.lbd.service.empleado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.dto.empleado.DatosActualizarEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosListadoEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRegistroEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRespuestaEmpleado;
import com.barrildorado.lbd.model.Empleado;
import com.barrildorado.lbd.repository.EmpleadoRepository;
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public DatosRespuestaEmpleado getEmpleadoById(Long id_empleado) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id_empleado);
        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            return new DatosRespuestaEmpleado(empleado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    @Override
    public Page<DatosListadoEmpleado> getAllEmpleados(Pageable pageable) {
        Page<Empleado> empleadoPage = empleadoRepository.findAll(pageable);
        return empleadoPage.map(DatosListadoEmpleado::new);
    }

    @Override
    public DatosRespuestaEmpleado createEmpleado(DatosRegistroEmpleado datosRegistroEmpleado) {
        Empleado empleado = empleadoRepository.save(new Empleado(datosRegistroEmpleado));
        return new DatosRespuestaEmpleado(empleado);
    }

    @Override
    public DatosRespuestaEmpleado updateEmpleado(DatosActualizarEmpleado datosActualizarEmpleado) {
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarEmpleado.id_empleado());
        empleado.actualizar(datosActualizarEmpleado);
        empleado = empleadoRepository.save(empleado);
        return new DatosRespuestaEmpleado(empleado);
    }

    @Override
    public void deleteEmpleado(Long id_empleado) {
        Empleado empleado = empleadoRepository.findById(id_empleado).orElse(null);
        if(empleado != null){
            empleadoRepository.delete(empleado);
        }else{
            throw new IllegalArgumentException("No se encontro el Empleado con el ID proporcionado");
        }
    }

}