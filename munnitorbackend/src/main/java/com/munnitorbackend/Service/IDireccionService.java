package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Direccion;
import java.util.List;

public interface IDireccionService {
    Direccion guardarDireccion(Direccion direccion) throws Exception;
    List<Direccion> listarDirecciones();
    List<Direccion> listarDireccionesDeEmpleados(Long idEmpresa);
    List<Direccion> listarDireccionesDeEmpleadosTambo(Long idEmpresa, Long idTambo);
    List<Direccion> listarDireccioneEmpleado(Long idEmpleado);

}
