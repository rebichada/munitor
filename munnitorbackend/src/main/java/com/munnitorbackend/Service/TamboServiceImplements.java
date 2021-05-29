package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Tambo;
import com.munnitorbackend.Repository.TamboRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TamboServiceImplements implements ITamboService{

    @Autowired
    private TamboRepo tamboRepo;

    @Override
    public List<Tambo> obtenerTodosLosTambosDeUnaEmpresa(Long idEmpresa) {
        return tamboRepo.findByEmpresaEquals(idEmpresa);
    }

    @Override
    public Tambo obtenerTamboDentroDeUnaEmpresa(Long idTambo, Long idEmpresa) {
        return tamboRepo.findByIdEqualsAndEmpresaEquals(idTambo,idEmpresa);
    }

    @Override
    public Tambo guardar(Tambo t) {
        return tamboRepo.save(t);
    }
}
