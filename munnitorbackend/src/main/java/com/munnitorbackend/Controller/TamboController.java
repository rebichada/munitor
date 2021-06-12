package com.munnitorbackend.Controller;

import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Empresa;
import com.munnitorbackend.Model.Tambo;
import com.munnitorbackend.Service.EmpleadoServiceImplements;
import com.munnitorbackend.Service.TamboServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/tambo")
public class TamboController {

    @Autowired
    private TamboServiceImplements tamboServiceImplements;

    @Autowired
    private EmpleadoServiceImplements empleadoServiceImplements;

    @GetMapping("/{idTambo}")
    public ResponseEntity<?> getOneTambo(@PathVariable String idTambo){
        try{
            return new ResponseEntity(tamboServiceImplements.findById(Long.parseLong(idTambo)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listarTambos(){
        try{
            return new ResponseEntity(tamboServiceImplements.listAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/empleado/{idEmpleado}")
    public ResponseEntity<?> getOneTamboPorIdEmpleado(@PathVariable String idEmpleado){
        try{
            return new ResponseEntity(tamboServiceImplements.obtenerTamboPorEmpleado(Long.parseLong(idEmpleado)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/empresa/{idEmpresa}")
    public ResponseEntity<List<?>> listarTambosPorEmpresa(@PathVariable String idEmpresa){
        try{
            return new ResponseEntity(tamboServiceImplements.obtenerTodosLosTambosDeUnaEmpresa(Long.parseLong(idEmpresa)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            Long idTambo=Long.parseLong(id);
            if(!tamboServiceImplements.existsById(idTambo)) return new ResponseEntity("no existe el tambo.", HttpStatus.BAD_REQUEST);
            List<Empleado> empleados=empleadoServiceImplements.obtenerEmpleadosTambo(idTambo);
            if (empleados!= null){
                return new ResponseEntity("Este tambo tiene registrado a los siguientes empleados: "+
                        empleados.stream().map(empleado -> empleado.getNombre() + " " + empleado.getApellido()).toString() +
                        ". Debe eliminar estos empleados previamente.", HttpStatus.BAD_REQUEST);
            }
            tamboServiceImplements.eliminar(idTambo);
            return new ResponseEntity("Tambo eliminado.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Tambo tambo){
        try{
            tamboServiceImplements.guardar(tambo);
            return new ResponseEntity("Tambo registrado.", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@RequestBody Tambo tambo){
        try{
            tamboServiceImplements.guardar(tambo);
            return new ResponseEntity("Tambo Actualizado.", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
