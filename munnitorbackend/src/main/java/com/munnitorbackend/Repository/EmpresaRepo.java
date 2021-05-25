package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepo extends JpaRepository<Empresa,Long> {
    //obtener la empresa de un tambo especifico
    @Query("SELECT e FROM Tambo t INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE e.tambo.id=:id_tambo")
    Empresa findByTamboEquals(@Param("id_tambo") Long id_tambo);

    //obtener la empresa de un empleado especifico
    @Query("SELECT e FROM Empleado emp INNER JOIN Empresa e ON e.empleado.id=emp.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE emp.id=:id_empleado")
    Empresa findByEmpleadoEquals(@Param("id_empleado") Long id_empleado);

}
