package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Tambo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITamboService {
    List<Tambo> obtenerTodosLosTambosDeUnaEmpresa(Long idEmpresa)throws Exception;
    Tambo guardar(Tambo t) throws Exception;
    Tambo obtenerTamboPorEmpleado(Long idEmpleado)throws Exception;
    void eliminar(Long id) throws Exception;
    Tambo findById(Long id) throws Exception;
    List<Tambo> listAll();
    boolean existsById(Long idTambo);
}
