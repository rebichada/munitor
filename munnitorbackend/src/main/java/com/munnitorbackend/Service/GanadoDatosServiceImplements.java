package com.munnitorbackend.Service;

import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Repository.GanadoDatosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GanadoDatosServiceImplements implements IGanadoDatosService{

    @Autowired
    private GanadoDatosRepo ganadoDatosRepo;

    @Override
    public List<GanadoDatos> filtrarPorRangoTemperatura(Long idTambo, Long idEmpresa, Double temp1, Double temp2, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByTemperatureIsRange(idTambo,idEmpresa,temp1,temp2,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorCantPasosMenorIgual(Long idTambo, Long idEmpresa, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByPasosIsAfter(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorCantPasosMayorIgual(Long idTambo, Long idEmpresa, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByPasosIsBefore(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorCantComieronIgual(Long idTambo, Long idEmpresa, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByCantidadComio(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorCantComieronMenorIgual(Long idTambo, Long idEmpresa, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByCantidadComioLessOrEquals(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorPesoMenorIgual(Long idTambo, Long idEmpresa, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByPesoIsLessThanEqual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public List<GanadoDatos> filtrarPorPesoMayorIgual(Long idTambo, Long idEmpresa, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        List<GanadoDatos> ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByPesoIsHigherThanEqual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos obtenerGanadoDatosById(Long idGanado, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.getDatosGanadoInFecha(idGanado,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorRangoTemperatura(Long idGanado, Double temp1, Double temp2, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoTemperatureIsRange(idGanado,temp1,temp2,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorCantPasosMenorIgual(Long idGanado, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoPasosIsAfter(idGanado,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorCantPasosMayorIgual(Long idGanado, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoPasosIsBefore(idGanado,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorCantComieronIgual(Long idGanado, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoCantidadComio(idGanado,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorCantComieronMenorIgual(Long idGanado, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoCantidadComioLessOrEquals(idGanado,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorPesoMenorIgual(Long idGanado, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoPesoIsLessThanEqual(idGanado,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

    @Override
    public GanadoDatos filtrarGanadoPorPesoMayorIgual(Long idGanado, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        GanadoDatos ganadoDatos;
        try {
            ganadoDatos=ganadoDatosRepo.findByGanadoPesoIsHigherThanEqual(idGanado,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganadoDatos;
    }

}
