package com.munnitorbackend.Service;

import com.munnitorbackend.Model.User;
import com.munnitorbackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserDetailsService,IUserService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //si no encuentro al usuario al menos debo retornar algo de tipo User
        User user=repo.findByEmail(email);
        //si encuentra al usuario lo retorno
        if(user==null){
            throw new UsernameNotFoundException("error");
        }
        return user;
    }

    @Override
    public User create(User user) throws UsernameNotFoundException {
        //si no existe el email ese en la bd lo guardo

        if(!repo.existsByEmail(user.getEmail())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user=repo.save(user);
        }
        else{
            throw new UsernameNotFoundException("The email : "+ user.getEmail() +" it is already associated with a user. Try Again with another User");
        }
        return user;
    }
}
