package com.munnitorbackend.Controller;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Model.User;
import com.munnitorbackend.Service.*;
import org.apache.catalina.connector.Response;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ganado")
public class GanadoController {

    @Autowired
    private IGanadoService ganadoService;

    @Autowired
    private IGanadoDatosService ganadoDatosService;

    @GetMapping("/list")
    public ResponseEntity<List<Ganado>> getGanado(@RequestParam(value = "idEmpresa") Long idEmpresa,@RequestParam(value = "idTambo") Long idTambo ) throws Exception {
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresaTambo(idEmpresa,idTambo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Ganado>> listAllGanado() {
        try{
            return ResponseEntity.ok(ganadoService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/datos")
    public ResponseEntity<GanadoDatos> guardarDatosGanado(@RequestBody GanadoDatos datos){
        try{
            GanadoDatos ganadoDatos = ganadoDatosService.guardar(datos);
            return ResponseEntity.created(new URI("/datos/"+ ganadoDatos.getId())).body(ganadoDatos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
