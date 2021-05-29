package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Vacuna;

import java.util.List;

public interface IVacunaService {
    Vacuna verificarVacunaDemtroDeEmpresa(Long idEmpresa, Long idVacuna) throws Exception;
    List<Vacuna> buscarVacunaDentroDeEmpresa(Long idEmpresa, String nombreVacuna)throws Exception;
    List<Vacuna> buscarTipoVacunaDentroDeEmpresa(Long idEmpresa, String tiposVacuna)throws Exception;
    List<Vacuna> obtenerVacunasDentroDeEmpresa(Long idEmpresa)throws Exception;
    List<Vacuna> buscarVacunaDentroDeEmpresaTambo(Long idEmpresa, Long idTambo,String nombreVacuna)throws Exception;
    List<Vacuna> buscarTipoDentroDeEmpresaTambo(Long idEmpresa,Long idTambo, String tipoVacuna)throws Exception;
    Vacuna guardar(Vacuna v);
}
