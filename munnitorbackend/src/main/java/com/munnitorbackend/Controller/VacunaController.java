package com.munnitorbackend.Controller;

import com.munnitorbackend.DTO.VacunaDTO;
import com.munnitorbackend.Model.Vacuna;
import com.munnitorbackend.Service.IEmpresaService;
import com.munnitorbackend.Service.IGanadoService;
import com.munnitorbackend.Service.ITamboService;
import com.munnitorbackend.Service.IVacunaService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/vacuna")
public class VacunaController {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private IVacunaService vacunaService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private ITamboService tamboService;

    @Autowired
    private IGanadoService ganadoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody VacunaDTO vacunaDTO){
        try{
            Vacuna vacuna = modelMapper.map(vacunaDTO,Vacuna.class);
            vacunaService.guardar(vacuna);
            return new ResponseEntity(vacuna,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(){
        try{
            return new ResponseEntity(vacunaService.listarTodasLasVacunasDelSistema(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{idVacuna}")
    public ResponseEntity<?> mostrarVacuna(@PathVariable String idVacuna){
        try{
            return new ResponseEntity(vacunaService.getOneVacuna(Long.parseLong(idVacuna)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{idVacuna}")
    public ResponseEntity<?> eliminar(@PathVariable String idVacuna){
        try{
            vacunaService.eliminarVacunaDelSistema(Long.parseLong(idVacuna));
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listar/empresa/{idEmpresa}")
    public ResponseEntity<List<?>> obtenerVacunasDentroDeEmpresa(@PathVariable String idEmpresa){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunasDentroDeEmpresa(Long.parseLong(idEmpresa)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/empresa/{idEmpresa}/tambo/{idTambo}")
    public ResponseEntity<List<?>> obtenerVacunasDentroDeUnaEmpresaEnUnTambo(@PathVariable String idEmpresa,@PathVariable String idTambo){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunasDentroDeUnaEmpresaEnUnTambo(Long.parseLong(idEmpresa),Long.parseLong(idTambo)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{idVacuna}/empresa/{idEmpresa}")
    public ResponseEntity<?> obtenerVacunaDentroDeEmpresa(@PathVariable String idVacuna,@PathVariable String idEmpresa){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunaDentroDeEmpresa(Long.parseLong(idEmpresa),Long.parseLong(idVacuna)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/ganado/{idGanado}")
    public ResponseEntity<List<?>> obtenerVacunasAplicadasAlGanado(@PathVariable String idGanado){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunasAplicadasAlGanado(Long.parseLong(idGanado)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/ganado/{idGanado}/por_fecha")
    public ResponseEntity<List<?>> obtenerVacunasEnUnRangoDeFechasParaUnGanado(@PathVariable String idGanado,@RequestParam String fechaDesde,@RequestParam String fechaHasta){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunasEnUnRangoDeFechasParaUnGanado(Long.parseLong(idGanado),dateFormat.parse(fechaDesde),dateFormat.parse(fechaHasta)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/{nombre}/empresa/{idEmpresa}")
    public ResponseEntity<List<?>> obtenerVacunasEnUnaFechaParaUnGanado(@PathVariable String idGanado,@RequestParam String fecha){
        try{
            return new ResponseEntity(vacunaService.obtenerVacunasEnUnaFechaParaUnGanado(Long.parseLong(idGanado),dateFormat.parse(fecha)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**@GetMapping("/{nombre}/empresa/{idEmpresa}")
    public ResponseEntity<?> buscarVacunaDentroDeEmpresa(@PathVariable String nombre,@PathVariable String idEmpresa){
        try{
            return new ResponseEntity(vacunaService.buscarVacunaDentroDeEmpresa(Long.parseLong(idEmpresa),nombre),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
