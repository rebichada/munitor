package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Caravana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaravanaRepo extends JpaRepository<Caravana,Long> {

    //------------------------------CARAVANAS DE UNA EMPRESA Y UN TAMBO ESPECIFICO-------------------------------------------------

    //obtener caravanas para una empresa y un tambo (tanto asignadas como no asignadas a un ganado)
    @Query("SELECT c FROM Caravana c " +
            "INNER JOIN Empresa e ON e.id=c.empresa.id " +
            "INNER JOIN Tambo t ON t.empresa.id=e.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo")
    List<Caravana> findByEmpresaEqualsAndTamboEquals(@Param("id_empresa") Long id_empresa, @Param("id_tambo") Long id_tambo);

    //obtener caravanas dentro de una empresa y un tambo especifico con caravanas SIN ASIGNAR a un ganado
    @Query("SELECT c FROM Caravana c INNER JOIN Empresa e ON e.id=c.empresa.id " +
            "INNER JOIN Tambo t ON t.empresa.id=e.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo " +
            "AND c.id not IN (SELECT g.caravana.id FROM Ganado g WHERE g.caravana is not null)")
    List<Caravana> findByEmpresaEqualsAndTamboEqualsAndGanadoIsNull(@Param("id_empresa") Long id_empresa, @Param("id_tambo") Long id_tambo);

    //obtener caravanas dentro de una empresa y un tambo especifico asignadas a un ganado
    @Query("SELECT c FROM Caravana c INNER JOIN Ganado g ON c.id=g.caravana.id " +
            "INNER JOIN Tambo t ON t.id=g.tambo.id " +
            "INNER JOIN Empresa e ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa AND t.id=:id_tambo")
    List<Caravana> findByEmpresaEqualsAndTamboEqualsAndGanadoIsNotNull(@Param("id_empresa") Long id_empresa, @Param("id_tambo") Long id_tambo);

    //------------------------------CARAVANAS DE UNA EMPRESA -------------------------------------------------

    //obtener caravanas dentro de una empresa asignadas a un GANADO
    @Query("SELECT c FROM Caravana c INNER JOIN Ganado g ON c.id=g.caravana.id " +
            "INNER JOIN Tambo t ON t.id=g.tambo.id " +
            "INNER JOIN Empresa e ON e.id=t.empresa.id " +
            "WHERE e.id=:id_empresa")
    List<Caravana> findByEmpresaEqualsAndGanadoIsNotNull(@Param("id_empresa") Long id_empresa);

    //obtener caravanas dentro de una empresa sin asignar a un GANADO
    @Query("SELECT c FROM Caravana c " +
            "INNER JOIN Empresa e ON e.id=c.empresa.id " +
            "WHERE e.id=:id_empresa " +
            "AND c.id not IN (SELECT g.caravana.id FROM Ganado g WHERE g.caravana is not null)")
    List<Caravana> findByEmpresaEqualsAndGanadoIsNull(@Param("id_empresa") Long id_empresa);

    //obtener caravanas dentro de una empresa ASIGNADAS Y NO ASIGNADAS A UN GANADO
    @Query("SELECT c FROM Caravana c " +
            "INNER JOIN Empresa e ON e.id=c.empresa.id " +
            "WHERE e.id=:id_empresa ")
    List<Caravana> findByEmpresaEquals(@Param("id_empresa") Long id_empresa);


}
