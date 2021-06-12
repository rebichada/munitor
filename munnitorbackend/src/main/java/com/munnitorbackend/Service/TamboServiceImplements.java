package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Tambo;
import com.munnitorbackend.Repository.TamboRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TamboServiceImplements implements ITamboService{

    @Autowired
    private TamboRepo tamboRepo;

    @Override
    public List<Tambo> obtenerTodosLosTambosDeUnaEmpresa(Long idEmpresa) throws Exception {
        try{
            return tamboRepo.findByEmpresaEquals(idEmpresa);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existen registros de tambos para esta empresa : "+ idEmpresa);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Tambo guardar(Tambo t) throws Exception {
        try{
            t= tamboRepo.save(t);
        }catch (Exception e){
            throw new Exception("Ocurrio un error mediante el guardado del Tambo.");
        }
        return t;
    }

    @Override
    public Tambo obtenerTamboPorEmpleado(Long idEmpleado) throws Exception{
        try{
            return tamboRepo.findByEmpleado(idEmpleado);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existen registros de este empleado: " + idEmpleado + " para ningun tambo.");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        try{
            tamboRepo.deleteById(id);
        }catch (Exception e){
            throw new Exception("Ocurrio un error al intentar eliminar el Tambo: " + id);
        }
    }

    @Override
    public Tambo findById(Long id) throws Exception {
        try{
            return tamboRepo.findById(id).get();
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("No existe este tambo: "+ id);
        }
        catch (Exception e){
            throw new Exception("Ocurrio un error al intentar obtener el tambo: "+id);
        }
    }

    @Override
    public List<Tambo> listAll() {
        return tamboRepo.findAll();
    }

    @Override
    public boolean existsById(Long idTambo) {
        return tamboRepo.existsById(idTambo);
    }

}
