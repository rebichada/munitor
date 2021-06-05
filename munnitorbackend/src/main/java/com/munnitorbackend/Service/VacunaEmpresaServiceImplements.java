package com.munnitorbackend.Service;

import com.munnitorbackend.Model.VacunaEmpresa;
import com.munnitorbackend.Repository.VacunaEmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaEmpresaServiceImplements implements IVacunaEmpresaService{

    @Autowired
    private VacunaEmpresaRepo vacunaEmpresaRepo;

    @Override
    public VacunaEmpresa guardar(VacunaEmpresa vacunaEmpresa) {
        return vacunaEmpresaRepo.save(vacunaEmpresa);
    }

    @Override
    public List<VacunaEmpresa> listar() {
        return vacunaEmpresaRepo.findAll();
    }

    @Override
    public List<VacunaEmpresa> listarVacunasDeEmpresa(Long idEmpresa) {
        return null;
    }
}
