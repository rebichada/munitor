package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Repository.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements IEmpleadoService{

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @Override
    public List<Empleado> obtenerEmpleadosEmpresa(Long idEmpresa) {
        return empleadoRepo.findByEmpresaEquals(idEmpresa);
    }

    @Override
    public List<Empleado> obtenerEmpleadosTambo(Long idTambo) {
        return empleadoRepo.findByIdTamboEquals(idTambo);
    }

    @Override
    public Empleado newEmpleado(Empleado empleado) throws Exception {
        return empleadoRepo.save(empleado);
    }

    @Override
    public Empleado obtenerEmpleadoPorIdUser(Long idUser) {
        return empleadoRepo.findByUserEquals(idUser);
    }

    @Override
    public Empleado findById(Long idEmpleado) {
        return empleadoRepo.findById(idEmpleado).get();
    }

    @Override
    public void deleteEmpleado(Long idEmpleado) throws Exception {
        empleadoRepo.deleteById(idEmpleado);
    }

    @Override
    public Empleado guardar(Empleado e) throws Exception {
        return empleadoRepo.save(e);
    }

}
