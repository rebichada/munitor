package com.munnitorbackend.Service;

import com.munnitorbackend.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User create(User user);
}
