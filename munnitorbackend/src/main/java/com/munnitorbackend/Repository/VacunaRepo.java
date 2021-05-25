package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacunaRepo extends JpaRepository<Vacuna,Long> {

    //-------------------------------------------------VACUNAS DENTRO DE UNA EMPRESA------------------------------------

    //obtener una vacuna especifica dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "WHERE e.id=:id_empresa AND v.id=:id_vacuna")
    Vacuna getByIdAndEmpresaEquals(@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);

    //obtener una vacuna por nombre (LIKE) dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "WHERE e.id=:id_empresa AND v.nombre LIKE :nombre")
    List<Vacuna> findByNombreLikeAndEmpresaEquals(@Param("id_empresa") Long id_empresa,@Param("nombre") String nombre);

    //obtener una vacuna por tipo dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "WHERE e.id=:id_empresa AND v.tipo =:tipo")
    List<Vacuna> findByTipoEqualsAndEmpresaEquals(@Param("id_empresa") Long id_empresa,@Param("tipo") String tipo);

    //obtener todas la vacunas dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id WHERE e.id=:id_empresa")
    List<Vacuna> findByEmpresa(@Param("id_empresa") Long id_empresa);

    //-------------------------------------------------VACUNAS DENTRO DE UN TAMBO---------------------------------------
    //obtener una vacuna por nombre (LIKE) dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "INNER JOIN Tambo t ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.nombre LIKE :nombre")
    List<Vacuna> findByNombreLikeAndEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("nombre") String nombre);

    //obtener una vacuna por tipo dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "INNER JOIN Tambo t ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.tipo =:tipo")
    List<Vacuna> findByTipoEqualsAndEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("tipo") String tipo);

    //obtener una vacuna especifica dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "INNER JOIN Tambo t ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.id=:id_vacuna")
    Vacuna getByIdAndEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("id_vacuna") Long id_vacuna);

    //obtener todas las vacunas de una empresa para un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN Empresa e ON v.id=e.vacuna.id " +
            "INNER JOIN Tambo t ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND e.tambo.id =:id_tambo")
    List<Vacuna> findByEmpresaAndTambo(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo);
}
