package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Repository.GanadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GanadoServiceImplements implements IGanadoService {

    @Autowired
    private GanadoRepo ganadoRepo;

    @Override
    public Ganado obtenerGanadoPorIdCaravana(Long idCaravana) throws Exception{
        Ganado g;
        try {
            g=ganadoRepo.findByCaravanaEquals(idCaravana);
            if (g==null){
                throw new Exception("Este codigo " + idCaravana + " de Caravana no esta asignado a un Ganado" );
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado obtenerPorId(Long idGanado)throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.getById(idGanado);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;

    }

    @Override
    public Ganado guardar(Ganado ganado) throws Exception {
        Ganado g;
        try{
            g= ganadoRepo.save(ganado);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> findAll() {
        return ganadoRepo.findAll();
    }

    @Override
    public List<Ganado> filtrarPorCUIGCaravana(Long idTambo, Long idEmpresa, String cuig) throws Exception{
        List<Ganado> g;
        try {
            g=ganadoRepo.findByCUIDCaravanaLike(idTambo,idEmpresa,cuig);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorRangoTemperatura(Long idTambo, Long idEmpresa, Double temp1, Double temp2, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByTemperatureIsRange(idTambo,idEmpresa,temp1,temp2,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorCantPasosMenorIgual(Long idTambo, Long idEmpresa, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByPasosIsAfter(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorCantPasosMayorIgual(Long idTambo, Long idEmpresa, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByPasosIsBefore(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorCantComieronIgual(Long idTambo, Long idEmpresa, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByCantidadComio(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorCantComieronMenorIgual(Long idTambo, Long idEmpresa, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByCantidadComioLessOrEquals(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorPesoMenorIgual(Long idTambo, Long idEmpresa, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByPesoIsLessThanEqual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorPesoMayorIgual(Long idTambo, Long idEmpresa, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByPesoIsHigherThanEqual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorRangoTemperatura(Long idGanado, Double temp1, Double temp2, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoTemperatureIsRange(idGanado,temp1,temp2,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorCantPasosMenorIgual(Long idGanado, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoPasosIsAfter(idGanado,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorCantPasosMayorIgual(Long idGanado, Double cantPasos, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoPasosIsBefore(idGanado,cantPasos,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorCantComieronIgual(Long idGanado, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoCantidadComio(idGanado,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorCantComieronMenorIgual(Long idGanado, Integer cantComieron, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoCantidadComioLessOrEquals(idGanado,cantComieron,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorPesoMenorIgual(Long idGanado, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
        Ganado g;
        try {
            g=ganadoRepo.findByGanadoPesoIsLessThanEqual(idGanado,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public Ganado filtrarGanadoPorPesoMayorIgual(Long idGanado, Double peso, Date fechaDesde, Date fechaHasta) throws Exception {
       Ganado g;
        try {
            g=ganadoRepo.findByGanadoPesoIsHigherThanEqual(idGanado,peso,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }


    @Override
    public List<Ganado> filtrarPorCantPartosMenor(Long idTambo, Long idEmpresa, Integer cantPartos) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByCantidadMenorServidas(idTambo,idEmpresa,cantPartos);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorSexo(Long idTambo, Long idEmpresa, String sexo) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findBySexoEquals(idTambo,idEmpresa,sexo);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorFechaNacimientoMenorIgual(Long idTambo, Long idEmpresa, Date fecha) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByFechaDeNacimientoIsLessThanEqual(idTambo,idEmpresa,fecha);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorFechaNacimientoMayorIgual(Long idTambo, Long idEmpresa, Date fecha) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByFechaDeNacimientoIsHigherThanEqual(idTambo,idEmpresa,fecha);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorRangoFechaNacimiento(Long idTambo, Long idEmpresa, Date fechaDesde, Date fechaHasta) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByRangeFechaDeNacimiento(idTambo,idEmpresa,fechaDesde,fechaHasta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorEmpresaTamboVacunaDistinta(Long idTambo, Long idEmpresa, Long idVacuna) throws Exception{
        List<Ganado> g;
        try {
            g=ganadoRepo.findByNotVacunas(idTambo,idEmpresa,idVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> filtrarPorEmpresaTamboVacunaIgual(Long idTambo, Long idEmpresa, Long idVacuna) throws Exception{
        List<Ganado> g;
        try {
            g=ganadoRepo.findByVacunas(idTambo,idEmpresa,idVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> listarPorEmpresaTambo(Long idTambo, Long idEmpresa) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByTamboAndEmpresa(idTambo,idEmpresa);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> listarPorEmpresaTamboEmpleado(Long idTambo, Long idEmpresa, Long idEmpleado) throws Exception{
        List<Ganado> g;
        try {
            g=ganadoRepo.findByEmpleado(idTambo,idEmpresa,idEmpleado);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> listarPorEmpresa(Long idEmpresa) throws Exception {
        List<Ganado> g;
        try {
            g=ganadoRepo.findByEmpresa(idEmpresa);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Ganado> listarUltimaTemperaturaCantPasosEnUnDia(Long idTambo, Long idEmpresa) throws Exception {
        List<Ganado> ganados;
        try{
            ganados=ganadoRepo.findByIdEqualsAndAndCaravanaAndGanadoDatos(idEmpresa, idTambo);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ganados;
    }

}
