package com.munnitorbackend.Controller;


import com.munnitorbackend.DTO.UserLoginDTO;
import com.munnitorbackend.DTO.UserNewDTO;
import com.munnitorbackend.Model.Usuario;
import com.munnitorbackend.Service.UserServiceImplement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
public class UserController {

    @Autowired
    private UserServiceImplement userService;

    @Qualifier("userServiceImplement")
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/new")
    public String userNew(){
        return "users/new";
    }

    //@PreAuthorize("hasRole('LECTOR,USUARIO,ADMINISTRADOR,ROOT')")
    @PostMapping("/new")
    public ResponseEntity<Usuario> regist(@RequestBody UserNewDTO usuarioNuevo) throws URISyntaxException {
        Usuario usuario = modelMapper.map(usuarioNuevo,Usuario.class);
        try{
            //el rol lo obtengo como un STRING
            //rol "1" = lector
            //rol "2" = LECTOR Y USUARIO
            //rol "3" =LECTOR, USUARIO y ADMINISTRADOR
            //rol "4" =LECTOR, USUARIO, ADMINISTRADOR y ROOT (control de todo el sistema)
            usuario.setRol(Byte.parseByte(usuarioNuevo.getRol()));
            //por defecto activo los usuarios para se puedan utilizar
            usuario.setActivo(true);
            usuario = userService.create(usuario);
        }catch(UsernameNotFoundException errorU){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.created(new URI("/principal/")).body(usuario);
    }

    @GetMapping("/usuarioActual")
    public String userActual(@AuthenticationPrincipal UserLoginDTO userLoginDTO){
        return "/users/login"+userLoginDTO.getEmail();
    }

    @GetMapping("/login")
    public String userLogin(){
        return "/users/login";
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@AuthenticationPrincipal @RequestBody UserLoginDTO userLoginDTO) {
        try {
            Usuario usuario = modelMapper.map(userLoginDTO, Usuario.class);
            userDetailService.loadUserByUsername(usuario.getEmail());
            return ResponseEntity.created(new URI("/principal/")).body(userLoginDTO);
        } catch (UsernameNotFoundException | URISyntaxException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
