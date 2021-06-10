package com.munnitorbackend.Service;

import com.munnitorbackend.Model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    Usuario create(Usuario usuario);
}
