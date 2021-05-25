package com.munnitorbackend.Repository;

import com.munnitorbackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Julito
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
   //para chequear y no tener usuarios con el mismo nombre o email
    boolean existsByEmail(String email);
    User findByPassword(String password);
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u INNER JOIN Empleado e ON u.id=e.user.id " +
            "WHERE u.id=:id_user")
    User findByIdEmpleado(@Param("id_user") Long id_user);
}
