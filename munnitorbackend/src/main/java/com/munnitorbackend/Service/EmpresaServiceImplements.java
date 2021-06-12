package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empresa;
import com.munnitorbackend.Repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImplements implements IEmpresaService {

    @Autowired
    private EmpresaRepo empresaRepo;

    @Override
    public Empresa obtenerEmpresaPorIdTambo(Long idTambo) {
        return empresaRepo.findByTamboEquals(idTambo);
    }

    @Override
    public Empresa obtenerEmpresaPorIdEmpleado(Long idEmpleado) {
        return empresaRepo.findByEmpleadoEquals(idEmpleado);
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return empresaRepo.findAll();
    }

    @Override
    public Empresa findById(Long idEmpresa) {
        return empresaRepo.getOne(idEmpresa);
    }

    @Override
    public void deleteEmpresa(Long idEmpresa) throws Exception {
        try{
            empresaRepo.deleteById(idEmpresa);
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new Exception("Empresa no encontrada.");
        }
        catch (Exception e){
            throw new Exception("Error al intentar eliminar la empresa en el servicio.");
        }
    }

    @Override
    public Empresa guardar(Empresa e) throws Exception {
        return empresaRepo.save(e);
    }

    @Override
    public boolean existsById(Long id) {
        return empresaRepo.existsById(id);
    }

    @Override
    public boolean existsByCuit(String cuit) {
        return empresaRepo.existsByCuit(cuit);
    }

    @Override
    public boolean existsByRazonSocial(String razonSocial) {
        return empresaRepo.existsByRazonSocial(razonSocial);
    }

    @Override
    public boolean existsByEmail(String email) {
        return empresaRepo.existsByEmail(email);
    }


}

