package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Ganado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface GanadoRepo extends JpaRepository<Ganado,Long> {
    //obtener un ganado especifico, no importa el tambo ni la empresa, no puede haber id_ganado repetidos
    @Query("SELECT g FROM Ganado g WHERE g.id=:id_ganado")
    Ganado getById(@Param("id_ganado") Long id_ganado);

    //obtener la temperatura de vacas entre ciertos grados
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo " +
            "AND g.temperatura between :temp1 and :temp2")
    List<Ganado> findByTemperatureIsRange(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("temp1") Double temp1,@Param("temp2") Double temp2);

    //obtener las vacas que dieron menos o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.pasos <=:cant_pasos ")
    List<Ganado> findByPasosIsBefore(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_pasos") Double cant_pasos);

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.pasos >=:cant_pasos ")
    List<Ganado> findByPasosIsAfter(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_pasos") Double cant_pasos);

    //ver las vacas que comieron x cantidad de veces
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.cantidadComio =:cant_comio ")
    List<Ganado> findByCantidadComio(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_comio") Double cant_comio);

    //ver las vacas que comieron x cantidad de veces o menos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.cantidadComio <=:cant_comio")
    List<Ganado> findByCantidadComioLessOrEquals(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa, @Param("cant_comio") Double cant_comio);

    //obtener las vacas de X empresa para Y tambo
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo")
    List<Ganado> findByTamboAndEmpresa(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa);

    //obtener las vacas que no tengan X vacuna
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN GanadoVacuna gv ON gv.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gv.vacuna.id <> :id_vacuna")
    List<Ganado> findByNotVacunas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);

    //obtener el ganado que tienen X vacuna
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN GanadoVacuna gv ON gv.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gv.vacuna.id = :id_vacuna")
    Ganado findByVacunas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);

    //obtener las vacas a cargo de X empleado
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Empleado em ON e.empleado.id=em.id " +
            "WHERE em.id=:id_emepleado AND e.id=:id_empresa AND t.id =:id_tambo")
    List<Ganado> findByEmpleado( @Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("id_empleado") Long id_empleado);

    //obtener las vacas de menor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.peso >=:cant_peso ")
    List<Ganado> findByPesoIsHigherThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_peso") Double cant_peso);

    //obtener las vacas de mayor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.peso <=:cant_peso ")
    List<Ganado> findByPesoIsLessThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_peso") Double cant_peso);

    //obtener las vacas de X sexo (toros o vacas)
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.sexo =:tipo_sexo")
    List<Ganado> findBySexoEquals(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("tipo_sexo") String tipo_sexo);

    //obtener las vacas donde la edad =< X (por ejemplo para saber si son terneros, novillos, vacas, toros o animales viejos)
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.fechaDeNacimiento <=:edad")
    List<Ganado> findByFechaDeNacimientoIsLessThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("edad") Date edad);

    //obtener las vacas donde la fecha >= X (por ejemplo para saber si son terneros, novillos, vacas, toros o animales viejos)
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.fechaDeNacimiento >=:fecha")
    List<Ganado> findByFechaDeNacimientoIsHigherThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("fecha") Date edad);

    //obtener las vacas de X fecha_desde e Y fecha_hasta
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.fechaDeNacimiento between :fecha_desde and :fecha_hasta")
    List<Ganado> findByRangeFechaDeNacimiento(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("fecha_desde") Date edad_desde, @Param("fecha_hasta") Date edad_hasta);

    //obtener un listado de las vacas con X partos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.cantidadServidas <:cant_servidas ")
    List<Ganado> findByCantidadServidas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("cant_servidas") Integer cant_servidas);

    //obtener las vacas de X empresa para Y tambo e Z codigo de caravana
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo " +
            "AND c.id=:codigo_caravana")
    Ganado findByCaravanaEquals(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("codigo_caravana") Long codigo_caravana);

    //obtener las vacas de X empresa para Y tambo e Z codigo de caravana (CUIG)
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo " +
            "AND c.CUIG LIKE :CUIG")
    List<Ganado> findByCUIDCaravanaLike(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("CUIG") String CUIG);



}
