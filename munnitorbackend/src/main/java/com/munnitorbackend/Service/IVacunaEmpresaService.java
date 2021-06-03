package com.munnitorbackend.Service;

import com.munnitorbackend.Model.VacunaEmpresa;

import java.util.List;

public interface IVacunaEmpresaService {
    VacunaEmpresa guardar(VacunaEmpresa vacunaEmpresa);
    List<VacunaEmpresa> listar();
    List<VacunaEmpresa> listarVacunasDeEmpresa(Long idEmpresa);
}
