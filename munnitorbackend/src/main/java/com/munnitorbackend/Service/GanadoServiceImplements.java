package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Repository.GanadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GanadoServiceImplements implements IGanadoService{

    @Autowired
    private GanadoRepo ganadoRepo;

    @Override
    public Ganado obtenerPorIdCaravana(Long idTambo, Long idEmpresa, Long idCaravana) {
        return null;
    }

    @Override
    public Ganado obtenerPorId(Long idGanado) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCUIGCaravana(Long idTambo, Long idEmpresa, String cuig) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorRangoTemperatura(Double temp1, Double temp2) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCantPasosMenorIgual(Double cantPasos) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCantPasosMayorIgual(Double cantPasos) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCantComieronIgual(Integer cantComieron) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCantComieronMenorIgual(Integer cantComieron) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorCantPartos(Integer cantPartos) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorPesoMenorIgual(Double peso) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorPesoMayorIgual(Double peso) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorSexo(String sexo) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorFechaNacimientoMayorIgual(Date fecha) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorRangoFechaNacimiento(Date fechaDesde, Date fechaHasta) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorEmpresaTamboVacunaDistinta(Long idTambo, Long idEmpresa, Long idVacuna) {
        return null;
    }

    @Override
    public List<Ganado> filtrarPorEmpresaTamboVacunaIgual(Long idTambo, Long idEmpresa, Long idVacuna) {
        return null;
    }

    @Override
    public List<Ganado> listarPorEmpresaTambo(Long idTambo, Long idEmpresa) {
        return null;
    }

    @Override
    public List<Ganado> listarPorEmpresaTamboEmpleado(Long idTambo, Long idEmpresa, Long idEmpleado) {
        return null;
    }
}
