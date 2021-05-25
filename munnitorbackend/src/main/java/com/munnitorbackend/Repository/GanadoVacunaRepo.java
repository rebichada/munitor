package com.munnitorbackend.Repository;
import com.munnitorbackend.Model.GanadoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GanadoVacunaRepo extends JpaRepository<GanadoVacuna,Long> {

}
