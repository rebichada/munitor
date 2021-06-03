package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Model.VacunaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaEmpresaRepo extends JpaRepository<VacunaEmpresa,Long> {
}
