package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Caravana;

import java.util.List;

public interface ICaravanaService {
    Caravana newCaravana(Caravana caravana) throws Exception;
    List<Caravana> obtenerCaravanasTambo(Long idEmpresa, Long idTambo);
    List<Caravana> obtenerCaravanasEmpresa(Long idEmpresa);
    List<Caravana> obtenerCaravanasSinAsignarTambo(Long idEmpresa, Long idTambo);
    List<Caravana> obtenerCaravanasSinAsignarEmpresa(Long idEmpresa);
    List<Caravana> obtenerCaravanasAsignadasTambo(Long idEmpresa, Long idTambo);
    List<Caravana> obtenerCaravanasAsignadasEmpresa(Long idEmpresa);
    Caravana findById(Long idCaravana);
    void deleteCaravana(Long idCaravana) throws Exception;

}
