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
    @Query("SELECT e FROM Tambo t INNER JOIN Empresa e ON e.id=t.empresa.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE t.id=:id_tambo")
    Empresa findByTamboEquals(@Param("id_tambo") Long id_tambo);

    //obtener la empresa de un empleado especifico
    @Query("SELECT e FROM Empresa e INNER JOIN Tambo t on e.id=t.empresa.id " +
            "INNER JOIN Empleado empleado ON empleado.tambo.id=t.id " +
            "INNER JOIN Direccion d ON d.id=e.direccion.id " +
            "WHERE empleado.id=:id_empleado")
    Empresa findByEmpleadoEquals(@Param("id_empleado") Long id_empleado);


    boolean existsByCuit(String cuit);
    boolean existsByEmail(String email);
    boolean existsByRazonSocial(String razonSocial);

}
