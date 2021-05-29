package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Ganado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GanadoRepo extends JpaRepository<Ganado,Long> {

    @Query("SELECT g FROM Ganado g WHERE g.id=:id_ganado")
    Ganado getById(@Param("id_ganado") Long id_ganado);

    //----------------------------------------------------------LISTA DE GANADOS----------------------------------------------------------

    //obtener la temperatura de vacas entre ciertos grados
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo " +
            "AND gd.temperatura between :temp1 and :temp2 " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByTemperatureIsRange(@Param("id_tambo") Long idTambo,@Param("id_empresa") Long idEmpresa,
                                          @Param("temp1") Double temp1,@Param("temp2") Double temp2,
                                          @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron menos o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.pasos <=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByPasosIsBefore(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                     @Param("cant_pasos") Double cant_pasos,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.pasos >=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByPasosIsAfter(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                    @Param("cant_pasos") Double cant_pasos,
                                    @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.cantidadComio =:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByCantidadComio(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                     @Param("cant_comio") Integer cant_comio,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces o menos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.cantidadComio <=:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByCantidadComioLessOrEquals(@Param("id_tambo") Long id_tambo,
                                                 @Param("id_empresa") Long id_empresa, @Param("cant_comio") Integer cant_comio,
                                                 @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de menor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.peso >=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByPesoIsHigherThanEqual(@Param("id_tambo") Long id_tambo,
                                             @Param("id_empresa") Long id_empresa,@Param("cant_peso") Double cant_peso,
                                             @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de mayor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.peso <=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<Ganado> findByPesoIsLessThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                           @Param("cant_peso") Double cant_peso,
                                           @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);


    //----------------------------------------------------------------GANADO ESPECIFICO-----------------------------------------------------------------------

    @Query("SELECT g FROM Ganado g INNER JOIN GanadoDatos gd ON g.id = gd.ganado.id " +
            "WHERE g.id=:id_ganado AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado getDatosGanadoInFecha(@Param("id_ganado") Long id_ganado,@Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener la temperatura de vacas entre ciertos grados
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado " +
            "AND gd.temperatura between :temp1 and :temp2 " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoTemperatureIsRange(@Param("id_ganado") Long id_ganado,@Param("temp1") Double temp1,@Param("temp2") Double temp2,
                                          @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron menos o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.pasos <=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoPasosIsBefore(@Param("id_ganado") Long id_ganado,@Param("cant_pasos") Double cant_pasos,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.pasos >=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoPasosIsAfter(@Param("id_ganado") Long id_ganado,@Param("cant_pasos") Double cant_pasos,
                                    @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.cantidadComio =:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoCantidadComio(@Param("id_ganado") Long id_ganado,@Param("cant_comio") Integer cant_comio,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces o menos
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.cantidadComio <=:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoCantidadComioLessOrEquals(@Param("id_ganado") Long id_ganado,@Param("cant_comio") Integer cant_comio,
                                                 @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de menor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.peso >=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoPesoIsHigherThanEqual(@Param("id_ganado") Long id_ganado,@Param("cant_peso") Double cant_peso,
                                             @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de mayor X cantidad de peso
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.peso <=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    Ganado findByGanadoPesoIsLessThanEqual(@Param("id_ganado") Long id_ganado,@Param("cant_peso") Double cant_peso,
                                           @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);
    //--------------------------------------------SIN INNER JOIN ENTRE GANADO_DATOS--------------------------------------------------------

    //obtener las vacas de X empresa para Y tambo
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo")
    List<Ganado> findByTamboAndEmpresa(@Param("id_tambo") Long id_tambo, @Param("id_empresa") Long id_empresa);

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
    List<Ganado> findByVacunas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("id_vacuna") Long id_vacuna);

    //obtener las vacas a cargo de X empleado
    @Query("SELECT g FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "INNER JOIN Empleado em ON e.empleado.id=em.id " +
            "WHERE em.id=:id_empleado AND e.id=:id_empresa AND t.id =:id_tambo")
    List<Ganado> findByEmpleado( @Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,@Param("id_empleado") Long id_empleado);

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
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND g.cantidadServidas <:cant_servidas " )
    List<Ganado> findByCantidadServidas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                        @Param("cant_servidas") Integer cant_servidas);

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
