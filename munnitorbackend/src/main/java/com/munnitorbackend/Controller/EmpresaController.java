package com.munnitorbackend.Controller;

import com.munnitorbackend.Model.Empleado;
import com.munnitorbackend.Model.Empresa;
import com.munnitorbackend.Service.EmpleadoServiceImplements;
import com.munnitorbackend.Service.EmpresaServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaServiceImplements empresaServiceImplements;

    @Autowired
    private EmpleadoServiceImplements empleadoServiceImplements;



    @GetMapping("/listar")
    public ResponseEntity<List<?>> listarEmpresas() {
        try{
            List<Empresa> empresas =empresaServiceImplements.listarEmpresas();
            return new ResponseEntity(empresas, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<?> verEmpresa(@PathVariable(value = "idEmpreas")String idEmpresa) {
        try{
            Empresa empresa =empresaServiceImplements.findById(Long.parseLong(idEmpresa));
            if (empresa== null){
                return new ResponseEntity("Esta empresa no existe.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(empresa, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){

        try{
            Long idEmpresa=Long.parseLong(id);
            if(!empresaServiceImplements.existsById(idEmpresa)) return new ResponseEntity("no existe la empresa.", HttpStatus.BAD_REQUEST);
            List<Empleado> empleados=empleadoServiceImplements.obtenerEmpleadosEmpresa(idEmpresa);
            if (empleados!= null){
                return new ResponseEntity("Esta empresa tiene registrado a los siguientes empleados: "+
                        empleados.stream().map(empleado -> empleado.getNombre() + " " + empleado.getApellido()).toString() +
                        ". Debe eliminar estos empleados previamente.", HttpStatus.BAD_REQUEST);
            }
            empresaServiceImplements.deleteEmpresa(idEmpresa);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Empresa eliminada.", HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> update(@RequestBody Empresa empresa){

        try{
            if(!empresaServiceImplements.existsByEmail(empresa.getEmail())) return new ResponseEntity("Ya existe este mail asignado a otra empresa.", HttpStatus.BAD_REQUEST);
            if(!empresaServiceImplements.existsByCuit(empresa.getCuit())) return new ResponseEntity("Ya existe este cuit asignado a otra empresa.", HttpStatus.BAD_REQUEST);
            if(!empresaServiceImplements.existsByRazonSocial(empresa.getRazonSocial())) return new ResponseEntity("Ya existe esta razon social asignada a otra empresa.", HttpStatus.BAD_REQUEST);
            empresaServiceImplements.guardar(empresa);
            return new ResponseEntity("Empresa registrada.", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
