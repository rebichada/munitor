package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Repository.CaravanaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaravanaServiceImplements implements ICaravanaService{

    @Autowired
    private CaravanaRepo caravanaRepo;

    @Override
    public Caravana newCaravana(Caravana caravana) throws Exception {
        return caravanaRepo.save(caravana);
    }

    @Override
    public List<Caravana> obtenerCaravanasTambo(Long idEmpresa, Long idTambo) {
        return caravanaRepo.findByEmpresaEqualsAndTamboEquals(idEmpresa,idTambo) ;
    }

    @Override
    public List<Caravana> obtenerCaravanasEmpresa(Long idEmpresa) {
        return caravanaRepo.findByEmpresaEquals(idEmpresa);
    }


    @Override
    public List<Caravana> obtenerCaravanasSinAsignarTambo(Long idEmpresa, Long idTambo) {
        return caravanaRepo.findByEmpresaEqualsAndTamboEqualsAndGanadoIsNull(idEmpresa,idTambo);

    }

    @Override
    public List<Caravana> obtenerCaravanasSinAsignarEmpresa(Long idEmpresa) {
        return caravanaRepo.findByEmpresaEqualsAndGanadoIsNull(idEmpresa);
    }

    @Override
    public List<Caravana> obtenerCaravanasAsignadasTambo(Long idEmpresa, Long idTambo) {
        return caravanaRepo.findByEmpresaEqualsAndTamboEqualsAndGanadoIsNotNull(idEmpresa,idTambo);

    }

    @Override
    public List<Caravana> obtenerCaravanasAsignadasEmpresa(Long idEmpresa) {
        return caravanaRepo.findByEmpresaEqualsAndGanadoIsNotNull(idEmpresa);

    }

    @Override
    public Caravana findById(Long idCaravana) {
        return caravanaRepo.findById(idCaravana).get();
    }

    @Override
    public void deleteCaravana(Long idCaravana) throws Exception {
         caravanaRepo.deleteById(idCaravana);
    }


}
