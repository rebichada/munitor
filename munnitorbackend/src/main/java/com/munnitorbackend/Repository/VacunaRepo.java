package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacunaRepo extends JpaRepository<Vacuna,Long> {

    //-------------------------------------------------VACUNAS DENTRO DE UNA EMPRESA------------------------------------

    //verificar si una vacuna especifica esta dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "WHERE e.id=:id_empresa AND v.id=:id_vacuna")
    boolean existsByIdEmpresaAndIdVacuna(@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);

    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "WHERE e.id=:id_empresa AND v.id=:id_vacuna")
    Vacuna findByIdEmpresaAndIdVacuna(@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);


    //obtener una vacunas buscando por nombre_vacuna con (LIKE) dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "WHERE e.id=:id_empresa AND v.nombre LIKE :nombre")
    List<Vacuna> findByNombreLikeAndEmpresaEquals(@Param("id_empresa") Long id_empresa,@Param("nombre") String nombre);

    //obtener una vacuna por tipo dentro de una empresa por tipo de vacuna
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "WHERE e.id=:id_empresa AND v.tipo =:tipo")
    List<Vacuna> findByTipoEqualsAndEmpresaEquals(@Param("id_empresa") Long id_empresa,@Param("tipo") String tipo);

    //obtener todas la vacunas dentro de una empresa
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id WHERE e.id=:id_empresa")
    List<Vacuna> findByEmpresa(@Param("id_empresa") Long id_empresa);

    //-------------------------------------------------VACUNAS DENTRO DE UN TAMBO---------------------------------------

    //obtener una vacuna por nombre (LIKE) dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "INNER JOIN Tambo t ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.nombre LIKE :nombre")
    List<Vacuna> findByNombreLikeAndEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("nombre") String nombre);

    //obtener una vacuna por tipo dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "INNER JOIN Tambo t ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.tipo =:tipo")
    List<Vacuna> findByTipoEqualsAndEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("tipo") String tipo);

    //verificar si una vacuna especifica por id_vacuna, se encuentra dentro de una empresa y un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "INNER JOIN Tambo t ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo AND v.id=:id_vacuna")
    boolean existsByIdEmpresaAndIdTamboAndIdVacuna(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo,@Param("id_vacuna") Long id_vacuna);

    //obtener todas las vacunas de una empresa para un tambo especifico
    @Query("SELECT v FROM Vacuna v INNER JOIN VacunaEmpresa ve ON v.id=ve.vacuna.id " +
            "INNER JOIN Empresa e ON e.id=ve.empresa.id " +
            "INNER JOIN Tambo t ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo")
    List<Vacuna> findByEmpresaAndTambo(@Param("id_empresa") Long id_empresa,@Param("id_tambo") Long id_tambo);


    //VACUNAS APLICADAS AL GANADO
    @Query("SELECT v FROM Vacuna v INNER JOIN GanadoVacuna gv ON v.id=gv.vacuna.id " +
            "WHERE gv.ganado.id=:id_ganado")
    List<Vacuna> findByIdGanado(@Param("id_ganado") Long idGanado);

    @Query("SELECT v FROM Vacuna v INNER JOIN GanadoVacuna gv ON v.id=gv.vacuna.id " +
            "WHERE gv.vacuna.id=:id_vacuna and gv.ganado.id=:id_ganado")
    boolean existsByIdVacunaAndIdGanado(@Param("id_vacuna") Long idVacuna,@Param("id_ganado") Long idGanado);

    @Query("SELECT v FROM Vacuna v INNER JOIN GanadoVacuna gv ON v.id=gv.vacuna.id " +
            "WHERE gv.ganado.id=:id_ganado AND gv.fechaDeVacunacion between :fecha_desde and :fecha_hasta")
    List<Vacuna> findByIdGanadoBetweenFecha(@Param("id_ganado") Long idGanado, @Param("fecha_desde") Date fecha_desde, @Param("fecha_hasta") Date fecha_hasta);

    @Query("SELECT v FROM Vacuna v INNER JOIN GanadoVacuna gv ON v.id=gv.vacuna.id " +
            "WHERE gv.ganado.id=:id_ganado AND gv.fechaDeVacunacion=:fecha")
    List<Vacuna> findByIdGanadoAndFecha(@Param("id_ganado") Long idGanado, @Param("fecha") Date fecha);

}
