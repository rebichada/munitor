package com.munnitorbackend.Controller;


import com.munnitorbackend.DTO.UserLoginDTO;
import com.munnitorbackend.DTO.UserNewDTO;
import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Empresa;
import com.munnitorbackend.Model.Tambo;
import com.munnitorbackend.Model.Usuario;
import com.munnitorbackend.Service.*;
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
    private TamboServiceImplements tamboService;

    @Autowired
    private EmpleadoServiceImplements empleadoService;

    @Autowired
    private EmpresaServiceImplements empresaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/new")
    public String userNew(){
        return "users/new";
    }

    //@PreAuthorize("hasRole('LECTOR,USUARIO,ADMINISTRADOR,ROOT')")
    @PostMapping("/new")
    public ResponseEntity<Usuario> regist(@RequestBody UserNewDTO usuarioNuevo) throws URISyntaxException {

        try{
            //el rol lo obtengo como un STRING
            //rol "1" = lector
            //rol "2" = LECTOR Y USUARIO
            //rol "3" =LECTOR, USUARIO y ADMINISTRADOR
            //rol "4" =LECTOR, USUARIO, ADMINISTRADOR y ROOT (control de todo el sistema)
            Usuario usuario = modelMapper.map(usuarioNuevo,Usuario.class);
            usuario.setRol(Byte.parseByte(usuarioNuevo.getRol()));
            //por defecto activo los usuarios para se puedan utilizar
            usuario = userService.create(usuario);
            return ResponseEntity.created(new URI("/principal/")).body(usuario);
        }catch(UsernameNotFoundException errorU){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("/login")
    public String userLogin(){
        return "/users/login";
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@AuthenticationPrincipal @RequestBody UserLoginDTO userLoginDTO) throws URISyntaxException {
        try {
            Usuario usuario = modelMapper.map(userLoginDTO, Usuario.class);
            usuario=(Usuario) userDetailService.loadUserByUsername(usuario.getEmail());
            if (usuario!= null) {
                Empleado empleado = empleadoService.findById(usuario.getId());
                Tambo tambo = tamboService.obtenerTamboPorEmpleado(empleado.getId());
                Empresa empresa = empresaService.obtenerEmpresaPorIdEmpleado(empleado.getId());
                return ResponseEntity.created(new URI("ganado/principal/?idTambo=" + tambo.getId() + "&idEmpresa=" + empresa.getId())).body(userLoginDTO);
            }
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
