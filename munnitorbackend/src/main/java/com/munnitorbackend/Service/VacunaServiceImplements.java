package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VacunaServiceImplements implements IVacunaService{

    @Autowired
    private VacunaRepo vacunaRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    @Autowired
    private TamboRepo tamboRepo;

    @Autowired
    private GanadoRepo ganadoRepo;

    @Autowired
    private VacunaEmpresaRepo vacunaEmpresaRepo;

    @Override
    public Vacuna obtenerVacunaDentroDeEmpresa(Long idEmpresa, Long idVacuna) throws Exception {
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            if (!vacunaRepo.existsById(idVacuna)) throw new Exception("No existe una vacuna con este codigo: " + idVacuna);
            if (!vacunaRepo.existsByIdEmpresaAndIdVacuna(idEmpresa,idVacuna)) throw new Exception("No existe el codigo de vacuna '"+ idVacuna + "' dentro de la empresa.");
            return vacunaRepo.findByIdEmpresaAndIdVacuna(idEmpresa,idVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public boolean verificarVacunaDentroDeUnaEmpresaEnUnTambo(Long idEmpresa, Long idTambo, Long idVacuna) {
        return vacunaRepo.existsByIdEmpresaAndIdTamboAndIdVacuna(idEmpresa,idTambo,idVacuna);
    }

    @Override
    public Vacuna obtenerVacunaDentroDeUnaEmpresaEnUnTambo(Long idEmpresa, Long idTambo, Long idVacuna) throws Exception {
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            if (!tamboRepo.existsById(idTambo)) throw new Exception("No existe el tambo con este codigo: " + idTambo);
            if (!vacunaRepo.existsById(idVacuna)) throw new Exception("No existe una vacuna con este codigo: " + idVacuna);
            if (!empresaRepo.existsByIdTambo(idTambo)) throw new Exception("No existe este codigo de tambo '"+ idTambo + "' dentro de la empresa.");
            if (!vacunaRepo.existsByIdEmpresaAndIdVacuna(idEmpresa,idVacuna)) throw new Exception("No existe el codigo de vacuna '"+ idVacuna + "' dentro de la empresa.");
            if (!this.verificarVacunaDentroDeUnaEmpresaEnUnTambo(idEmpresa,idTambo,idVacuna)) throw new Exception("No existe la vacuna ("+ idVacuna + ") para este tambo (" + idTambo +") en esta empresa ("+ idEmpresa+")");
            return vacunaRepo.findByIdEmpresaAndIdVacuna(idEmpresa,idVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> obtenerVacunasDentroDeUnaEmpresaEnUnTambo(Long idEmpresa, Long idTambo) throws Exception {
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            if (!tamboRepo.existsById(idTambo)) throw new Exception("No existe el tambo con este codigo: " + idTambo);
            if (!empresaRepo.existsByIdTambo(idTambo)) throw new Exception("No existe este codigo de tambo '"+ idTambo + "' dentro de la empresa.");
            return vacunaRepo.findByEmpresaAndTambo(idEmpresa,idTambo);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public boolean verificarVacunaDentroDeUnaEmpresa(Long idEmpresa, Long idVacuna) {
        return vacunaRepo.existsByIdEmpresaAndIdVacuna(idEmpresa,idVacuna);
    }

    @Override
    public List<Vacuna> buscarVacunaDentroDeEmpresa(Long idEmpresa, String nombreVacuna) throws Exception{
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            return vacunaRepo.findByNombreLikeAndEmpresaEquals(idEmpresa,nombreVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> buscarTipoVacunaDentroDeEmpresa(Long idEmpresa, String tipoVacuna) throws Exception{
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            return vacunaRepo.findByTipoEqualsAndEmpresaEquals(idEmpresa,tipoVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> obtenerVacunasDentroDeEmpresa(Long idEmpresa) throws Exception{
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            return vacunaRepo.findByEmpresa(idEmpresa);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }


    @Override
    public List<Vacuna> buscarVacunaDentroDeEmpresaTambo(Long idEmpresa, Long idTambo, String nombreVacuna) throws Exception{
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            if (!tamboRepo.existsById(idTambo)) throw new Exception("No existe el tambo con este codigo: " + idTambo);
            if (!empresaRepo.existsByIdTambo(idTambo)) throw new Exception("No existe este codigo de tambo '"+ idTambo + "' dentro de la empresa.");
            return vacunaRepo.findByNombreLikeAndEmpresaEqualsAndTamboEquals(idEmpresa,idTambo,nombreVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> buscarTipoDentroDeEmpresaTambo(Long idEmpresa, Long idTambo, String tipoVacuna) throws Exception{
        try{
            return vacunaRepo.findByTipoEqualsAndEmpresaEqualsAndTamboEquals(idEmpresa,idTambo,tipoVacuna);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> obtenerVacunasAplicadasAlGanado(Long idGanado) throws Exception {
        try{
            if (!ganadoRepo.existsById(idGanado)) throw new Exception("No existe este codigo ("+ idGanado +") de Ganado.");
            return vacunaRepo.findByIdGanado(idGanado);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existen vacunas aplicadas para este codigo ("+ idGanado +") de Ganado.");
        }
        catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public boolean verificarVacunaAplicadaAlGanado(Long idVacuna, Long idGanado) throws Exception {
        try{
            if (!ganadoRepo.existsById(idGanado)) throw new Exception("No existe este codigo ("+ idGanado +") de Ganado.");
            if (!vacunaRepo.existsById(idVacuna)) throw new Exception("No existe una vacuna con este codigo: " + idVacuna);
            return vacunaRepo.existsByIdVacunaAndIdGanado(idVacuna,idGanado);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> obtenerVacunasEnUnRangoDeFechasParaUnGanaado(Long idGanado,Date fechaDesde, Date fechaHasta) throws Exception {
        try{
            if (!ganadoRepo.existsById(idGanado)) throw new Exception("No existe este codigo ("+ idGanado +") de Ganado.");
            return vacunaRepo.findByIdGanadoBetweenFecha(idGanado,fechaDesde,fechaHasta);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existen vacunas aplicadas al ganado para estas fechas.");
        }
        catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> obtenerVacunasEnUnaFechaParaUnGanaado(Long idGanado,Date fecha) throws Exception {
        try{
            if (!ganadoRepo.existsById(idGanado)) throw new Exception("No existe este codigo ("+ idGanado +") de Ganado.");
            return vacunaRepo.findByIdGanadoAndFecha(idGanado,fecha);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existen vacunas aplicadas al ganado para esta fecha.");
        }
        catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public List<Vacuna> listarTodasLasVacunasDelSistema() {
        return null;
    }

    @Override
    public void eliminarVacunaDelSistema(Long idVacuna) throws Exception {

    }

    @Override
    public boolean existsById(Long idVacuna) {
        return vacunaRepo.existsById(idVacuna);
    }

    @Override
    public Vacuna guardar(Vacuna v) throws Exception{
        try {
            return vacunaRepo.save(v);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public void eliminarVacunaDentroDeEmpresa(Long idEmpresa, Long IdVacuna) throws Exception {
        try{
            if (!empresaRepo.existsById(idEmpresa)) throw new Exception("No existe la empresa con este codigo: "+ idEmpresa);
            vacunaRepo.deleteById(IdVacuna);
            vacunaEmpresaRepo.deleteById(idEmpresa);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }

    @Override
    public Vacuna getOneVacuna(Long id) throws Exception {
        try{
            if(!this.existsById(id)) throw new Exception("No existe este codigo de vacuna:" + id);
            return vacunaRepo.getOne(id);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de la vacuna. Error: "+ e.getMessage());
        }
    }
}
