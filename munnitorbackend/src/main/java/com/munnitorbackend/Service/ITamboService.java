package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Tambo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITamboService {
    List<Tambo> obtenerTodosLosTambosDeUnaEmpresa(Long idEmpresa);
    Tambo obtenerTamboDentroDeUnaEmpresa(Long idTambo, Long idEmpresa);
    Tambo guardar(Tambo t) throws Exception;
    Tambo obtenerTamboPorEmpleado(Long idEmpleado);
}
