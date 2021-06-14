package com.munnitorbackend.Repository;
import com.munnitorbackend.Model.GanadoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GanadoVacunaRepo extends JpaRepository<GanadoVacuna,Long> {
    @Transactional
    @Query("DELETE FROM GanadoVacuna WHERE vacuna.id=:id_vacuna and ganado.id=:id_ganado")
    void deleteByIdEmpresaAndIdVacuna(@Param("id_vacuna")Long idVacuna, @Param("id_ganado")Long idGanado);
}
