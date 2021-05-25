package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Tambo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TamboRepo extends JpaRepository<Tambo,Long> {
    //obtener los tambos de una empresa
    @Query("SELECT t FROM Tambo t INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Direccion d ON d.id=t.direccion.id " +
            "WHERE e.id=:id_empresa")
    List<Tambo> findByEmpresaEquals(@Param("id_empresa") Long id_empresa);

    //obtener un tambo especifico dentro de una empresa
    @Query("SELECT t FROM Tambo t INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Direccion d ON d.id=t.direccion.id " +
            "WHERE t.id=:id_tambo AND e.id=:id_empresa")
    Tambo findByIdEqualsAndEmpresaEquals(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa);


}
