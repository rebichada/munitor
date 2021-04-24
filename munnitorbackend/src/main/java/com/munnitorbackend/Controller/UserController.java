package com.munnitorbackend.Controller;


import com.munnitorbackend.DTO.UserLoginDTO;
import com.munnitorbackend.DTO.UserNewDTO;
import com.munnitorbackend.Model.User;
import com.munnitorbackend.Service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Qualifier("userServiceImplement")
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new UserNewDTO());
        return "users/new";
    }

    @PostMapping("/new")
    public String regist(@ModelAttribute UserNewDTO userDTO, Model model){
        try{
            User user = modelMapper.map(userDTO, User.class);
            user.setBirthDate(userDTO.getBirthDateInDateConverted());
            userService.create(user);
            return "/users/login";
        }catch(UsernameNotFoundException errorU){
            model.addAttribute("error",errorU.getMessage());
            return "/error";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "/error";
        }
    }

    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new UserLoginDTO());
        return "/users/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginDTO user, Model model) {
        try {
            userService.loadUserByUsername(user.getEmail());
            return "/home";
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error",ex.getMessage());
            return "/error";

        }
    }
}
