package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;

import java.util.Date;
import java.util.List;

public interface IGanadoService {
    Ganado obtenerGanadoPorIdCaravana(Long idCaravana) throws Exception;
    Ganado obtenerPorId(Long idGanado) throws Exception;
    Ganado guardar(Ganado ganado)throws Exception;
    List<Ganado> findAll();
    List<Ganado> filtrarPorCUIGCaravana(Long idTambo, Long idEmpresa, String cuig) throws Exception;

    List<Ganado> filtrarPorRangoTemperatura(Long idTambo, Long idEmpresa,Double temp1, Double temp2, Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorCantPasosMenorIgual(Long idTambo, Long idEmpresa,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorCantPasosMayorIgual(Long idTambo, Long idEmpresa,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorCantComieronIgual(Long idTambo, Long idEmpresa,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorCantComieronMenorIgual(Long idTambo, Long idEmpresa,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorPesoMenorIgual(Long idTambo, Long idEmpresa,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorPesoMayorIgual(Long idTambo, Long idEmpresa,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;

    Ganado filtrarGanadoPorRangoTemperatura(Long idGanado,Double temp1, Double temp2, Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorCantPasosMenorIgual(Long idGanado,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorCantPasosMayorIgual(Long idGanado,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorCantComieronIgual(Long idGanado,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorCantComieronMenorIgual(Long idGanado,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorPesoMenorIgual(Long idGanado,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;
    Ganado filtrarGanadoPorPesoMayorIgual(Long idGanado,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;



    List<Ganado> filtrarPorCantPartosMenor(Long idTambo, Long idEmpresa,Integer cantPartos)throws Exception;
    List<Ganado> filtrarPorSexo(Long idTambo, Long idEmpresa,String sexo)throws Exception;
    List<Ganado> filtrarPorFechaNacimientoMenorIgual(Long idTambo, Long idEmpresa,Date fecha)throws Exception;
    List<Ganado> filtrarPorFechaNacimientoMayorIgual(Long idTambo, Long idEmpresa,Date fecha)throws Exception;
    List<Ganado> filtrarPorRangoFechaNacimiento(Long idTambo, Long idEmpresa,Date fechaDesde, Date fechaHasta)throws Exception;
    List<Ganado> filtrarPorEmpresaTamboVacunaDistinta(Long idTambo, Long idEmpresa, Long idVacuna)throws Exception;
    List<Ganado> filtrarPorEmpresaTamboVacunaIgual(Long idTambo, Long idEmpresa, Long idVacuna)throws Exception;
    List<Ganado> listarPorEmpresaTambo(Long idTambo, Long idEmpresa) throws Exception;
    List<Ganado> listarPorEmpresaTamboEmpleado(Long idTambo, Long idEmpresa, Long idEmpleado)throws Exception;
    List<Ganado> listarPorEmpresa(Long idEmpresa) throws Exception;

    List<Ganado> listarUltimaTemperaturaCantPasosEnUnDia(Long idTambo, Long idEmpresa) throws Exception;

    boolean existById(Long idGanado);

}
