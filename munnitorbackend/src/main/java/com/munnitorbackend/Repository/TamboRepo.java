package com.munnitorbackend.Repository;


import com.munnitorbackend.Model.Tambo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TamboRepo extends JpaRepository<Tambo,Long> {
    @Override
    List<Tambo>findAll();

    @Override
    Tambo getOne(Long id);

    //obtener los tambos de una empresa
    @Query("SELECT t FROM Tambo t INNER JOIN Empresa e ON e.id=t.empresa.id " +
            "INNER JOIN Direccion d ON d.id=t.direccion.id " +
            "WHERE e.id=:id_empresa")
    List<Tambo> findByEmpresaEquals(@Param("id_empresa") Long id_empresa);

    @Query("SELECT t FROM Tambo t INNER JOIN Empleado e on t.id=e.tambo.id " +
            "WHERE e.id=:id_empleado")
    Tambo findByEmpleado(@Param("id_empleado")Long id_empleado);
}
