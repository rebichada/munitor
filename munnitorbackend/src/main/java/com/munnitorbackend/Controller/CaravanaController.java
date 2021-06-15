package com.munnitorbackend.Controller;

import java.util.*;
import com.munnitorbackend.DTO.CaravanaDTO;
import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Service.ICaravanaService;
import com.munnitorbackend.Service.IEmpresaService;
import com.munnitorbackend.Service.ITamboService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/caravana")
public class CaravanaController {

    @Autowired
    private ICaravanaService caravanaService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private ITamboService tamboService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/cargarCaravana")
    public ResponseEntity<Caravana> guardarDatosCaravana(@RequestBody CaravanaDTO caravanaDTO){
        try{
            Caravana datos= modelMapper.map(caravanaDTO, Caravana.class);
            datos.setFechaImpresion(caravanaDTO.getFechaImpresionInDateConverted());
            Caravana caravana = caravanaService.newCaravana(datos);
            return ResponseEntity.status(HttpStatus.OK).body(caravana);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{ìdCaravana}")
    public ResponseEntity<?> verCaravana(@PathVariable String idCaravana){
        try{
            return new ResponseEntity(caravanaService.findById(Long.parseLong(idCaravana)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> verCaravanas(){
        try{
            return new ResponseEntity(caravanaService.listaAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/borrar/{idCaravana}")
    public ResponseEntity<List<?>> borrarCaravana(@PathVariable String idCaravana){
        try{
            if (!caravanaService.existsBtId(Long.parseLong(idCaravana))) return new ResponseEntity("No existe este codigo de caravana: "+ idCaravana+ ".",HttpStatus.OK);
            caravanaService.deleteCaravana(Long.parseLong(idCaravana));
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}/tambo/{idTambo}")
    public ResponseEntity<List<?>> verCaravanasDeEmpresaEnUnTambo(@PathVariable String idEmpresa, @PathVariable String idTambo){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            if(!tamboService.existsById(Long.parseLong(idTambo))) new ResponseEntity<>("Este codigo " + idTambo + " de tambo no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasAsignadasTambo(Long.parseLong(idEmpresa),Long.parseLong(idTambo)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<?>> verCaravanasDeEmpresa(@PathVariable String idEmpresa){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasAsignadasEmpresa(Long.parseLong(idEmpresa)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}/tambo/{idTambo}/sin_ganado")
    public ResponseEntity<List<?>> verCaravanasDeEmpresaEnUnTamboSinGanadoAsiganado(@PathVariable String idEmpresa, @PathVariable String idTambo){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            if(!tamboService.existsById(Long.parseLong(idTambo))) new ResponseEntity<>("Este codigo " + idTambo + " de tambo no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasSinAsignarTambo(Long.parseLong(idEmpresa),Long.parseLong(idTambo)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}/sin_ganado")
    public ResponseEntity<List<?>> verCaravanasDeEmpresaSinGanadoAsiganado(@PathVariable String idEmpresa){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasSinAsignarEmpresa(Long.parseLong(idEmpresa)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}/con_sin_ganado")
    public ResponseEntity<List<?>> verTodasLasCaravanasDeLaEmpresa(@PathVariable String idEmpresa){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasEmpresa(Long.parseLong(idEmpresa)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{idEmpresa}/con_ganado")
    public ResponseEntity<List<?>> verTodasLasCaravanasDeLaEmpresaParaUnTambo(@PathVariable String idEmpresa, @PathVariable String idTambo){
        try{
            if (!empresaService.existsById(Long.parseLong(idEmpresa))) new ResponseEntity<>("Este codigo "+ idEmpresa +" de empresa no existe.",HttpStatus.OK);
            if(!tamboService.existsById(Long.parseLong(idTambo))) new ResponseEntity<>("Este codigo " + idTambo + " de tambo no existe.",HttpStatus.OK);
            return new ResponseEntity(caravanaService.obtenerCaravanasTambo(Long.parseLong(idEmpresa),Long.parseLong(idTambo)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
