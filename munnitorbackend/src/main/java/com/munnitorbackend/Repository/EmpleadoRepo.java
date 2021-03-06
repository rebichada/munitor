package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado,Long> {
    //obtener empleados dentro de una empresa
    @Query("SELECT e FROM Empleado e INNER JOIN Tambo t ON t.id=e.tambo.id " +
            "INNER JOIN Empresa empresa ON empresa.id=t.empresa.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE e.id=:id_empresa order by e.nombre")
    List<Empleado> findByEmpresaEquals(@Param("id_empresa") Long id_empresa);

    //obtener empleado dentro de una empresa y de un tambo especifico
    @Query("SELECT e FROM Empleado e INNER JOIN Tambo t ON t.id=e.tambo.id " +
            "INNER JOIN Empresa emp ON e.id=t.empresa.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE t.id=:id_tambo")
    List<Empleado> findByIdTamboEquals(@Param("id_tambo") Long id_tambo);

    //obtener empleado por su usuario
    @Query("SELECT e FROM Empleado e INNER JOIN Usuario u ON e.usuario.id=u.id " +
           "INNER JOIN Direccion d ON d.id=e.direccion.id " +
           "WHERE u.id=:id_user")
    Empleado findByUserEquals(@Param("id_user") Long id_user);

}
