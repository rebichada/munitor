package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Model.VacunaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaEmpresaRepo extends JpaRepository<VacunaEmpresa,Long> {
}
