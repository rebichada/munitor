package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.GanadoDatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface GanadoDatosRepo extends JpaRepository<GanadoDatos,Long> {

    //----------------------------------------------------------LISTA DE GANADOS----------------------------------------------------------

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query(value = "SELECT gd FROM ganados g INNER JOIN tambos t ON g.id_tambo=t.id_tambo " +
            "INNER JOIN empresas e ON e.id_tambo=t.id_tambo " +
                "INNER JOIN (SELECT id, COUNT(cantPasos) FROM ganado_datos " +
                "WHERE fecha_de_registro between :fecha_desde and :fecha_hasta group by id) gd ON gd.id_ganado=g.id_ganado " +
            "WHERE e.id_empresa=:id_empresa AND t.id_tambo =:id_tambo ", nativeQuery = true)
    List<GanadoDatos> findByPasosInRangeFechas(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                         @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener la temperatura de vacas entre ciertos grados
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo " +
            "AND gd.temperatura between :temp1 and :temp2 " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByTemperatureIsRange(@Param("id_tambo") Long idTambo, @Param("id_empresa") Long idEmpresa,
                                          @Param("temp1") Double temp1, @Param("temp2") Double temp2,
                                          @Param("fecha_desde") Date fechaDesde, @Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron menos o igual de x cantidad de pasos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.pasos <=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByPasosIsBefore(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                     @Param("cant_pasos") Double cant_pasos,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.pasos >=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByPasosIsAfter(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                    @Param("cant_pasos") Double cant_pasos,
                                    @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.cantidadComio =:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByCantidadComio(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                     @Param("cant_comio") Integer cant_comio,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces o menos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.cantidadComio <=:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByCantidadComioLessOrEquals(@Param("id_tambo") Long id_tambo,
                                                 @Param("id_empresa") Long id_empresa, @Param("cant_comio") Integer cant_comio,
                                                 @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de menor X cantidad de peso
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.peso >=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByPesoIsHigherThanEqual(@Param("id_tambo") Long id_tambo,
                                             @Param("id_empresa") Long id_empresa,@Param("cant_peso") Double cant_peso,
                                             @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de mayor X cantidad de peso
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE e.id=:id_empresa AND t.id =:id_tambo AND gd.peso <=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    List<GanadoDatos> findByPesoIsLessThanEqual(@Param("id_tambo") Long id_tambo,@Param("id_empresa") Long id_empresa,
                                           @Param("cant_peso") Double cant_peso,
                                           @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);


    //----------------------------------------------------------------GANADO ESPECIFICO-----------------------------------------------------------------------

    @Query("SELECT gd FROM Ganado g INNER JOIN GanadoDatos gd ON g.id = gd.ganado.id " +
            "WHERE g.id=:id_ganado AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos getDatosGanadoInFecha(@Param("id_ganado") Long id_ganado,@Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener la temperatura de vacas entre ciertos grados
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado " +
            "AND gd.temperatura between :temp1 and :temp2 " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoTemperatureIsRange(@Param("id_ganado") Long id_ganado,@Param("temp1") Double temp1,@Param("temp2") Double temp2,
                                          @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron menos o igual de x cantidad de pasos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.pasos <=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoPasosIsBefore(@Param("id_ganado") Long id_ganado,@Param("cant_pasos") Double cant_pasos,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas que dieron mas o igual de x cantidad de pasos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.pasos >=:cant_pasos " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoPasosIsAfter(@Param("id_ganado") Long id_ganado,@Param("cant_pasos") Double cant_pasos,
                                    @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND e.id=:id_empresa AND t.id =:id_tambo AND gd.cantidadComio =:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoCantidadComio(@Param("id_ganado") Long id_ganado,@Param("cant_comio") Integer cant_comio,
                                     @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //ver las vacas que comieron x cantidad de veces o menos
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.cantidadComio <=:cant_comio " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoCantidadComioLessOrEquals(@Param("id_ganado") Long id_ganado,@Param("cant_comio") Integer cant_comio,
                                                 @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de menor X cantidad de peso
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.peso >=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoPesoIsHigherThanEqual(@Param("id_ganado") Long id_ganado,@Param("cant_peso") Double cant_peso,
                                             @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

    //obtener las vacas de mayor X cantidad de peso
    @Query("SELECT gd FROM Ganado g INNER JOIN Tambo t ON g.tambo.id=t.id " +
            "INNER JOIN Empresa e ON e.tambo.id=t.id " +
            "LEFT JOIN Caravana c ON c.id=g.caravana.id " +
            "INNER JOIN GanadoDatos gd ON gd.ganado.id=g.id " +
            "WHERE g.id=:id_ganado AND gd.peso <=:cant_peso " +
            "AND gd.fechaDeRegistro between :fecha_desde and :fecha_hasta")
    GanadoDatos findByGanadoPesoIsLessThanEqual(@Param("id_ganado") Long id_ganado,@Param("cant_peso") Double cant_peso,
                                           @Param("fecha_desde")Date fechaDesde,@Param("fecha_hasta")Date fechaHasta);

}
