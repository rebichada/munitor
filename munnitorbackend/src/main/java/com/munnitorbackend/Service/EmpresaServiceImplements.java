package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empresa;
import com.munnitorbackend.Repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImplements implements IEmpresaService {

    @Autowired
    private EmpresaRepo empresaRepo;

    @Override
    public Empresa obtenerEmpresaPorIdTambo(Long idTambo) {
        return empresaRepo.findByTamboEquals(idTambo);
    }

    @Override
    public Empresa obtenerEmpresaPorIdEmpleado(Long idEmpleado) {
        return empresaRepo.findByEmpleadoEquals(idEmpleado);
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return empresaRepo.findAll();
    }

    @Override
    public Empresa findById(Long idEmpresa) {
        return empresaRepo.getOne(idEmpresa);
    }

    @Override
    public void deleteEmpresa(Long idEmpresa) throws Exception {
        empresaRepo.deleteById(idEmpresa);
    }


}

