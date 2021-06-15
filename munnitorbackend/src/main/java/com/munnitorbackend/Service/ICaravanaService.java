package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Caravana;

import java.util.List;

public interface ICaravanaService {
    Caravana newCaravana(Caravana caravana) throws Exception;
    List<Caravana> obtenerCaravanasTambo(Long idEmpresa, Long idTambo)throws Exception;
    List<Caravana> obtenerCaravanasEmpresa(Long idEmpresa)throws Exception;
    List<Caravana> obtenerCaravanasSinAsignarTambo(Long idEmpresa, Long idTambo)throws Exception;
    List<Caravana> obtenerCaravanasSinAsignarEmpresa(Long idEmpresa)throws Exception;
    List<Caravana> obtenerCaravanasAsignadasTambo(Long idEmpresa, Long idTambo)throws Exception;
    List<Caravana> obtenerCaravanasAsignadasEmpresa(Long idEmpresa)throws Exception;
    Caravana findById(Long idCaravana) throws Exception;
    void deleteCaravana(Long idCaravana) throws Exception;
    Caravana guardar(Caravana caravana) throws Exception;
    boolean existsBtId(Long idCaravana);
    List<Caravana> listaAll();
}
