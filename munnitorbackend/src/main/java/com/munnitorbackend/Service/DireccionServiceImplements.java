package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Direccion;
import com.munnitorbackend.Repository.DireccionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImplements implements IDireccionService{

    @Autowired
    DireccionRepo direccionRepo;

    @Override
    public Direccion guardarDireccion(Direccion direccion) {
        return direccionRepo.save(direccion);
    }

    @Override
    public List<Direccion> listarDirecciones() {
        return direccionRepo.findAll();
    }

    @Override
    public List<Direccion> listarDireccionesDeEmpleados(Long idEmpresa) {
        return null;
    }

    @Override
    public List<Direccion> listarDireccionesDeEmpleadosTambo(Long idEmpresa, Long idTambo) {
        return null;
    }

    @Override
    public List<Direccion> listarDireccioneEmpleado(Long idEmpleado) {
        return null;
    }
}
