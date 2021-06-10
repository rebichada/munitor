package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Usuario;
import com.munnitorbackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //busco al usuario por email
        Usuario usuario =repo.findByEmail(email);
        //si no encuentra al usuario por email lo busco por nombre de usuario
        if(usuario ==null){
            usuario = repo.findByNombreUsuario(email);
            if(usuario ==null) {
                throw new UsernameNotFoundException("Usuario no encontrado.");
            }
        }
        User userDetail = new User(usuario.getNombreUsuario(), usuario.getPassword(),usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), usuario.getAuthorities());
        return userDetail;
    }



    @Override
    public Usuario create(Usuario usuario) throws UsernameNotFoundException {
        //si no existe el email y ese nombre de usuario ese en la bd lo guardo
        if(!repo.existsByEmail(usuario.getEmail())){
            if (!repo.existsByNombreUsuario(usuario.getNombreUsuario())){
                usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
                usuario =repo.save(usuario);
            }else{
                throw new UsernameNotFoundException("The User name : "+ usuario.getNombreUsuario() +" it is already associated with a user. Try Again with another Username");
            }
        }
        else{
            throw new UsernameNotFoundException("The email : "+ usuario.getEmail() +" it is already associated with a user. Try Again with another User");
        }
        return usuario;
    }
}
