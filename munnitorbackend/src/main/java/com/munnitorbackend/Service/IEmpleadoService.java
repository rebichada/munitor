package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empleado;

import java.util.List;

public interface IEmpleadoService {
    List<Empleado> obtenerEmpleadosEmpresa(Long idEmpresa);
    List<Empleado> obtenerEmpleadosTambo(Long idTambo);
    Empleado newEmpleado(Empleado empleado) throws Exception;
    Empleado obtenerEmpleadoPorIdUser(Long idUser);
    Empleado findById(Long idEmpleado);
    void deleteEmpleado(Long idEmpleado) throws Exception;
    Empleado guardar(Empleado empleado) throws  Exception;
}
