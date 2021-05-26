package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Ganado;

import java.util.Date;
import java.util.List;

public interface IGanadoService {
    Ganado obtenerPorIdCaravana(Long idTambo, Long idEmpresa, Long idCaravana);
    Ganado obtenerPorId(Long idGanado);
    List<Ganado> filtrarPorCUIGCaravana(Long idTambo, Long idEmpresa, String cuig);
    List<Ganado> filtrarPorRangoTemperatura(Double temp1, Double temp2);
    List<Ganado> filtrarPorCantPasosMenorIgual(Double cantPasos);
    List<Ganado> filtrarPorCantPasosMayorIgual(Double cantPasos);
    List<Ganado> filtrarPorCantComieronIgual(Integer cantComieron);
    List<Ganado> filtrarPorCantComieronMenorIgual(Integer cantComieron);
    List<Ganado> filtrarPorCantPartos(Integer cantPartos);

    List<Ganado> filtrarPorPesoMenorIgual(Double peso);
    List<Ganado> filtrarPorPesoMayorIgual(Double peso);
    List<Ganado> filtrarPorSexo(String sexo);
    List<Ganado> filtrarPorFechaNacimientoMayorIgual(Date fecha);
    List<Ganado> filtrarPorRangoFechaNacimiento(Date fechaDesde, Date fechaHasta);
    List<Ganado> filtrarPorEmpresaTamboVacunaDistinta(Long idTambo, Long idEmpresa, Long idVacuna);
    List<Ganado> filtrarPorEmpresaTamboVacunaIgual(Long idTambo, Long idEmpresa, Long idVacuna);
    List<Ganado> listarPorEmpresaTambo(Long idTambo, Long idEmpresa);
    List<Ganado> listarPorEmpresaTamboEmpleado(Long idTambo, Long idEmpresa, Long idEmpleado);

}
