package com.munnitorbackend.Service;

import com.munnitorbackend.Model.GanadoVacuna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.munnitorbackend.Repository.GanadoVacunaRepo;
import java.util.List;

@Service
public class GanadoVacunaServiceImplements implements IGanadoVacunaService {

    @Autowired
    private GanadoVacunaRepo ganadoVacunaRepo;

    @Override
    public GanadoVacuna guardarGanadoVacuna(GanadoVacuna ganadoVacuna) throws Exception {
        return ganadoVacunaRepo.save(ganadoVacuna);
    }

    @Override
    public List<GanadoVacuna> listarGanadoVacuna() {
        return ganadoVacunaRepo.findAll();
    }
}
