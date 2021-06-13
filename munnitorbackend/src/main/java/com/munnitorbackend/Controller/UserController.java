package com.munnitorbackend.Controller;


import com.munnitorbackend.DTO.UserLoginDTO;
import com.munnitorbackend.DTO.UserNewDTO;
import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Usuario;
import com.munnitorbackend.Service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
    public ResponseEntity<?> userNew(){
        return new ResponseEntity("Ingreso a la creación de usuarios.",HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('LECTOR,USUARIO,ADMINISTRADOR,ROOT')")
    @PostMapping("/new")
    public ResponseEntity<?> regist(@RequestBody UserNewDTO usuarioNuevo) throws URISyntaxException {

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
            return new ResponseEntity(errorU.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/login")
    public ResponseEntity<?> userLogin(){
        return new ResponseEntity("Ingreso al Login.",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@AuthenticationPrincipal @RequestBody UserLoginDTO userLoginDTO) {
        try {
            Usuario usuario = modelMapper.map(userLoginDTO, Usuario.class);
            usuario=(Usuario) userDetailService.loadUserByUsername(usuario.getEmail());
            if (usuario.getNombreUsuario().equals("root")){
                return new ResponseEntity(userLoginDTO,HttpStatus.OK);
            }
            else{
                Empleado empleado = empleadoService.obtenerEmpleadoPorIdUser(usuario.getId());
                return new ResponseEntity(empleado,HttpStatus.OK);
            }
        } catch (UsernameNotFoundException e1) {
            return new ResponseEntity(e1.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e2){
            return new ResponseEntity("Usuario sin empleado asignado.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if(!userService.existsById(id)) return new ResponseEntity("No existe el usuario.", HttpStatus.NOT_FOUND);
        try{
            Empleado empleado=empleadoService.obtenerEmpleadoPorIdUser(Long.parseLong(id));
            if (empleado!= null){
                return new ResponseEntity("Este usuario esta siendo utilizado por el empleado "+ empleado.getNombre() + ", dni: " +
                         empleado.getDni() + ". Debe eliminar este empleado previamente.", HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(id);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("usuario eliminado.", HttpStatus.OK);
    }


}
