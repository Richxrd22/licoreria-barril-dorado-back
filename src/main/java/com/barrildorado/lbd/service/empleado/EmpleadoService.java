package com.barrildorado.lbd.service.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.barrildorado.lbd.dto.empleado.DatosActualizarEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosListadoEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRegistroEmpleado;
import com.barrildorado.lbd.dto.empleado.DatosRespuestaEmpleado;

public interface EmpleadoService {

  DatosRespuestaEmpleado getEmpleadoById(Long id_empleado);

  Page<DatosListadoEmpleado> getAllEmpleados(Pageable pageable);

  DatosRespuestaEmpleado createEmpleado(DatosRegistroEmpleado datosRegistroEmpleado);

  DatosRespuestaEmpleado updateEmpleado(DatosActualizarEmpleado datosActualizarEmpleado);

  void deleteEmpleado(Long id_empleado);

}