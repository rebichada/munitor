package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Direccion;
import com.munnitorbackend.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepo extends JpaRepository<Direccion,Long> {
}
