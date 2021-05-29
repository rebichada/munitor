package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;

import java.util.Date;
import java.util.List;

public interface IGanadoDatosService {
    List<GanadoDatos> filtrarPorRangoTemperatura(Long idTambo, Long idEmpresa, Double temp1, Double temp2, Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorCantPasosMenorIgual(Long idTambo, Long idEmpresa,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorCantPasosMayorIgual(Long idTambo, Long idEmpresa,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorCantComieronIgual(Long idTambo, Long idEmpresa,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorCantComieronMenorIgual(Long idTambo, Long idEmpresa,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorPesoMenorIgual(Long idTambo, Long idEmpresa,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;
    List<GanadoDatos> filtrarPorPesoMayorIgual(Long idTambo, Long idEmpresa,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;

    GanadoDatos obtenerGanadoDatosById(Long idGanado, Date fechaDesde, Date fechaHasta) throws Exception;
    GanadoDatos filtrarGanadoPorRangoTemperatura(Long idGanado,Double temp1, Double temp2, Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorCantPasosMenorIgual(Long idGanado,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorCantPasosMayorIgual(Long idGanado,Double cantPasos,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorCantComieronIgual(Long idGanado,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorCantComieronMenorIgual(Long idGanado,Integer cantComieron,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorPesoMenorIgual(Long idGanado,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos filtrarGanadoPorPesoMayorIgual(Long idGanado,Double peso,Date fechaDesde, Date fechaHasta)throws Exception;
    GanadoDatos guardar(GanadoDatos ganadoDatos);
}
