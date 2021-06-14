package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Model.VacunaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VacunaEmpresaRepo extends JpaRepository<VacunaEmpresa,Long> {

    @Transactional
    @Query("DELETE FROM VacunaEmpresa WHERE empresa.id=:id_empresa and vacuna.id=:id_vacuna")
    void deleteByIdEmpresaAndIdVacuna(@Param("id_empresa")Long idEmpresa, @Param("id_vacuna")Long idVacuna);
}
