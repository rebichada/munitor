package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Tambo;

import java.util.List;

public interface ITamboService {
    List<Tambo> obtenerTodosLosTambosDeUnaEmpresa(Long idEmpresa);
    Tambo obtenerTamboDentroDeUnaEmpresa(Long idTambo, Long idEmpresa);
    Tambo guardar(Tambo t) throws Exception;
}
