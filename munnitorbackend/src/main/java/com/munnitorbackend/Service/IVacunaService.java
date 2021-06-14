package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Vacuna;

import java.util.Date;
import java.util.List;

public interface IVacunaService {
    //repo VacunaEmpresa
    List<Vacuna> obtenerVacunasDentroDeEmpresa(Long idEmpresa)throws Exception;
    List<Vacuna> obtenerVacunasDentroDeUnaEmpresaEnUnTambo(Long idEmpresa,Long idTambo) throws Exception;
    Vacuna obtenerVacunaDentroDeEmpresa(Long idEmpresa, Long idVacuna) throws Exception;
    Vacuna obtenerVacunaDentroDeUnaEmpresaEnUnTambo(Long idEmpresa,Long idTambo, Long idVacuna) throws Exception;
    boolean verificarVacunaDentroDeUnaEmpresaEnUnTambo(Long idEmpresa,Long idTambo,Long idVacuna);
    boolean verificarVacunaDentroDeUnaEmpresa(Long idEmpresa,Long idVacuna);
    void eliminarVacunaDentroDeEmpresa(Long idEmpresa, Long idVacuna)throws Exception;

    List<Vacuna> buscarVacunaDentroDeEmpresa(Long idEmpresa, String nombreVacuna)throws Exception;
    List<Vacuna> buscarTipoVacunaDentroDeEmpresa(Long idEmpresa, String tiposVacuna)throws Exception;

    List<Vacuna> buscarVacunaDentroDeEmpresaTambo(Long idEmpresa, Long idTambo,String nombreVacuna)throws Exception;
    List<Vacuna> buscarTipoDentroDeEmpresaTambo(Long idEmpresa,Long idTambo, String tipoVacuna)throws Exception;

    //repo GanadoVacuna
    List<Vacuna> obtenerVacunasAplicadasAlGanado(Long idGanado)throws Exception;
    boolean verificarVacunaAplicadaAlGanado(Long idVacuna,Long idGanado)throws Exception;
    void eliminarVacunasParaUnGanado(Long idVacuna,Long idGanado) throws Exception;
    List<Vacuna> obtenerVacunasEnUnRangoDeFechasParaUnGanado(Long idGanado,Date fechaDesde, Date FechaHasta)throws Exception;
    List<Vacuna> obtenerVacunasEnUnaFechaParaUnGanado(Long idGanado,Date fecha)throws Exception;


    //repoVacuna
    Vacuna guardar(Vacuna v) throws Exception;
    Vacuna getOneVacuna(Long id)throws Exception;
    //solo el root tiene acceso a estos servicios
    List<Vacuna> listarTodasLasVacunasDelSistema();
    void eliminarVacunaDelSistema(Long idVacuna)throws Exception;
    boolean existsById(Long idVacuna);

}
