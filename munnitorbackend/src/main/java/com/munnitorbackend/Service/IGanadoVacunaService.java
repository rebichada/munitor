package com.munnitorbackend.Service;

import com.munnitorbackend.Model.GanadoVacuna;

import java.util.List;

public interface IGanadoVacunaService {
    GanadoVacuna guardarGanadoVacuna(GanadoVacuna ganadoVacuna) throws Exception;
    List<GanadoVacuna> listarGanadoVacuna();
}
