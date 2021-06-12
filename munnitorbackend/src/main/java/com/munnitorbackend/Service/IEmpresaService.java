package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empresa;
import org.aspectj.weaver.patterns.ExactTypePattern;

import java.util.List;

public interface IEmpresaService {
    Empresa obtenerEmpresaPorIdTambo(Long idTambo) throws Exception;
    Empresa obtenerEmpresaPorIdEmpleado(Long idEmpleado) throws Exception;
    List<Empresa> listarEmpresas();
    Empresa findById(Long idEmpresa) throws Exception;
    void deleteEmpresa(Long idEmpresa) throws Exception;
    Empresa guardar(Empresa e) throws Exception;
    boolean existsById(Long id);
    boolean existsByCuit(String cuit);
    boolean existsByRazonSocial(String razonSocial);
    boolean existsByEmail(String email);

}
