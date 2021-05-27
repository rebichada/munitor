package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Repository.VacunaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaServiceImplements implements IVacunaService{

    @Autowired
    private VacunaRepo vacunaRepo;

    @Override
    public Vacuna verificarVacunaDemtroDeEmpresa(Long idEmpresa, Long idVacuna) throws Exception{
        Vacuna v;
        try{
            v=vacunaRepo.getByIdAndEmpresaEquals(idEmpresa,idVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }

    @Override
    public List<Vacuna> buscarVacunaDentroDeEmpresa(Long idEmpresa, String nombreVacuna) throws Exception{
        List<Vacuna> v;
        try{
            v=vacunaRepo.findByNombreLikeAndEmpresaEquals(idEmpresa,nombreVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }

    @Override
    public List<Vacuna> buscarTipoVacunaDentroDeEmpresa(Long idEmpresa, String tipoVacuna) throws Exception{
        List<Vacuna> v;
        try{
            v=vacunaRepo.findByTipoEqualsAndEmpresaEquals(idEmpresa,tipoVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }

    @Override
    public List<Vacuna> obtenerVacunasDentroDeEmpresa(Long idEmpresa) throws Exception{
        List<Vacuna> v;
        try{
            v=vacunaRepo.findByEmpresa(idEmpresa);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }

    @Override
    public List<Vacuna> buscarVacunaDentroDeEmpresaTambo(Long idEmpresa, Long idTambo, String nombreVacuna) throws Exception{
        List<Vacuna> v;
        try{
            v=vacunaRepo.findByNombreLikeAndEmpresaEqualsAndTamboEquals(idEmpresa,idTambo,nombreVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }

    @Override
    public List<Vacuna> buscarTipoDentroDeEmpresaTambo(Long idEmpresa, Long idTambo, String tipoVacuna) throws Exception{
        List<Vacuna> v;
        try{
            v=vacunaRepo.findByTipoEqualsAndEmpresaEqualsAndTamboEquals(idEmpresa,idTambo,tipoVacuna);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return v;
    }
}
