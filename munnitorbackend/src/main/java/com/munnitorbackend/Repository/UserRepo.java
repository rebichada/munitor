package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Julito
 */
@Repository
public interface UserRepo extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
    Usuario findByNombreUsuario(String nombreUsuario);
   //para chequear y no tener usuarios con el mismo nombre o email
    boolean existsByEmail(String email);
    boolean existsByNombreUsuario(String nombreUsuario);

    Usuario findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM Usuario u INNER JOIN Empleado e ON u.id=e.usuario.id " +
            "WHERE u.id=:id_user")
    Usuario findByIdEmpleado(@Param("id_user") Long id_user);
}
