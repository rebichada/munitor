package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Empresa;
import org.aspectj.weaver.patterns.ExactTypePattern;

import java.util.List;

public interface IEmpresaService {
    Empresa obtenerEmpresaPorIdTambo(Long idTambo);
    Empresa obtenerEmpresaPorIdEmpleado(Long idEmpleado);
    List<Empresa> listarEmpresas();
    Empresa findById(Long idEmpresa);
    void deleteEmpresa(Long idEmpresa) throws Exception;
    Empresa guardar(Empresa e) throws Exception;
}
