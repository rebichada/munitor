package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Repository.CaravanaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CaravanaServiceImplements implements ICaravanaService{

    @Autowired
    private CaravanaRepo caravanaRepo;

    @Override
    public Caravana newCaravana(Caravana caravana) throws Exception {
        try{
            return caravanaRepo.save(caravana);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicio de las Caravanas.");
        }
    }

    @Override
    public List<Caravana> obtenerCaravanasTambo(Long idEmpresa, Long idTambo) throws Exception {
        try{
            return caravanaRepo.findByEmpresaEqualsAndTamboEquals(idEmpresa,idTambo);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }
    }

    @Override
    public List<Caravana> obtenerCaravanasEmpresa(Long idEmpresa) throws Exception {
        try{
            return caravanaRepo.findByEmpresaEquals(idEmpresa);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }
    }

    @Override
    public List<Caravana> obtenerCaravanasSinAsignarTambo(Long idEmpresa, Long idTambo) throws Exception {
        try{
            return caravanaRepo.findByEmpresaEqualsAndTamboEqualsAndGanadoIsNull(idEmpresa,idTambo);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }

    }

    @Override
    public List<Caravana> obtenerCaravanasSinAsignarEmpresa(Long idEmpresa) throws Exception {
        try{
            return caravanaRepo.findByEmpresaEqualsAndGanadoIsNull(idEmpresa);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }
    }

    @Override
    public List<Caravana> obtenerCaravanasAsignadasTambo(Long idEmpresa, Long idTambo)  throws Exception{
        try{
            return caravanaRepo.findByEmpresaEqualsAndTamboEqualsAndGanadoIsNotNull(idEmpresa,idTambo);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }

    }

    @Override
    public List<Caravana> obtenerCaravanasAsignadasEmpresa(Long idEmpresa) throws Exception {
        try{
            return caravanaRepo.findByEmpresaEqualsAndGanadoIsNotNull(idEmpresa);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }


    }

    @Override
    public Caravana findById(Long idCaravana) throws Exception{
        try{
            return caravanaRepo.findById(idCaravana).get();
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }
    }

    @Override
    public void deleteCaravana(Long idCaravana) throws Exception {
        try{
            caravanaRepo.deleteById(idCaravana);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }
    }

    @Override
    public Caravana guardar(Caravana caravana) throws Exception {
        try{
            return caravanaRepo.save(caravana);
        }catch (Exception e){
            throw new Exception("Ocurrio un error en el servicios de las caravanas. Error :" + e.getMessage());
        }

    }

    @Override
    public boolean existsBtId(Long idCaravana) {
        return caravanaRepo.existsById(idCaravana);
    }


}
