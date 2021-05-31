package com.munnitorbackend.Controller;

import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ganado")
public class GanadoController {

    @Autowired
    private IGanadoService ganadoService;

    @Autowired
    private IGanadoDatosService ganadoDatosService;


    /**POST PARA ALMACENAR LOS DATOS DEL SENSOR, EL JSON DEBE SER:
    {
     "idGanadoDatos:null",
     "IdGanado:Long (not null)",
     "temperatura":Double(permite null),
     "pasos":Integer(permite null),
     "cantidadComio":Integer(permite null),
     "peso":Double(permite null),
     "comio":boolean (permite null),
     "fechaDeRegistro:null"
     }
     **/
    @PostMapping("/datosSensor")
    public ResponseEntity<GanadoDatos> guardarDatosGanado(@RequestBody GanadoDatos datos){
        try{
            datos.setFechaDeRegistro(new Date());
            GanadoDatos ganadoDatos = ganadoDatosService.guardar(datos);
            return ResponseEntity.created(new URI("/datos/"+ ganadoDatos.getId())).body(ganadoDatos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/VerGanadoPorCaravana")
    public ResponseEntity<Ganado> getGanadoByIdCaravana(@RequestParam(value = "idCaravana") Long idCaravana) {
        try{
            return ResponseEntity.ok(ganadoService.obtenerGanadoPorIdCaravana(idCaravana));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

   @GetMapping("/verGanado")
   public ResponseEntity<Ganado> getGanado(@RequestParam(value = "idGanado") Long idGanado){
        try{
            return ResponseEntity.ok(ganadoService.obtenerPorId(idGanado));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
   }

    //LISTADO DE TODO GANADO QUE PERTENECE A UNA EMPRESA
    @GetMapping("/verGanadoEmpresa")
    public ResponseEntity<List<Ganado>> getGanadoEMpresa(@RequestParam(value = "idEmpresa") Long idEmpresa){
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresa(idEmpresa));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

   //LISTADO DE TODO GANADO QUE PERTENECE A UNA EMPRESA PARA DICHO TAMBO
    @GetMapping("/listar")
    public ResponseEntity<List<Ganado>> getGanadoEmpresaTambo(@RequestParam(value = "idEmpresa") Long idEmpresa,
                                                  @RequestParam(value = "idTambo") Long idTambo) {
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresaTambo(idTambo,idEmpresa));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //LISTADO DE TODO GANADO DE TODAS LAS EMPRESAS
    @GetMapping("/list-all")
    public ResponseEntity<List<Ganado>> listAllGanado() {
        try{
            return ResponseEntity.ok(ganadoService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //------------------------------------------------- INICIO DE LISTADOS POR FILTROS -------------------------------------------------



    @GetMapping("/listarPorEmpleado")
    public ResponseEntity<List<Ganado>> listarGanadoPorEmpleado(@RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                @RequestParam(value = "idTambo") Long idTambo,
                                                                @RequestParam(value = "idEmpleado") Long idEmpleado) {
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresaTamboEmpleado(idTambo,idEmpresa,idEmpleado));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @GetMapping("/listadoPorTemperatura")
    public ResponseEntity<ArrayList<Ganado>> listarPorRangoTemperatura(@RequestParam(value = "idTambo") Long idTambo,
                                                                        @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                        @RequestParam(value = "temp1") Double temp1,
                                                                        @RequestParam(value = "temp2") Double temp2,
                                                                        @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                        @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
              return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorRangoTemperatura(idTambo,idEmpresa,temp1,temp2,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @GetMapping("/listadoPorCantPasosMenorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPasosMenor(@RequestParam(value = "idTambo") Long idTambo, @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                       @RequestParam(value = "cantPasos") Double cantPasos,
                                                                       @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                       @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCantPasosMenorIgual(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/listadoPorCantPasosMayorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPasosMayor(@RequestParam(value = "idTambo") Long idTambo,
                                                                       @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                       @RequestParam(value = "cantPasos") Double cantPasos,
                                                                       @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                       @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCantPasosMayorIgual(idTambo,idEmpresa,cantPasos,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorCUIG")
    public ResponseEntity<ArrayList<Ganado>> listarPorCUIG(@RequestParam(value = "idTambo") Long idTambo,
                                                            @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                            @RequestParam(value = "cuig") String cuig){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCUIGCaravana(idTambo,idEmpresa,cuig)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorCantidadComieronIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorCantComieronIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                         @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                         @RequestParam(value = "cantComieron") Integer cantComieron,
                                                                         @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                         @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList<>(ganadoService.filtrarPorCantComieronIgual(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorCantidadComieronMenorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorCantComieronMenorIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                              @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                              @RequestParam(value = "cantComieron") Integer cantComieron,
                                                                              @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                              @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList<>(ganadoService.filtrarPorCantComieronMenorIgual(idTambo,idEmpresa,cantComieron,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorPesoMenorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPesoMenorIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                      @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                      @RequestParam(value = "peso") Double peso,
                                                                      @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                      @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList<>(ganadoService.filtrarPorPesoMenorIgual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorPesoMayorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPesoMayorIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                      @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                                      @RequestParam(value = "peso") Double peso,
                                                                      @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                      @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList<>(ganadoService.filtrarPorPesoMayorIgual(idTambo,idEmpresa,peso,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorFechaNacimientoMenorIgual")
    public ResponseEntity<List<Ganado>> listarPorFechaNacimientoMenorIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                              @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                                              @RequestParam(value = "fecha") Date fecha){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorFechaNacimientoMenorIgual(idTambo,idEmpresa,fecha));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("/listadoPorFechaNacimientoMayorIgual")
    public ResponseEntity<List<Ganado>> listarrPorFechaNacimientoMayorIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                                              @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                                              @RequestParam(value = "fecha") Date fecha){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorFechaNacimientoMayorIgual(idTambo,idEmpresa,fecha));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/listadoPorFechaNacimientoInRange")
    public ResponseEntity<List<Ganado>> listarPorFechaNacimientoInRange(@RequestParam(value = "idTambo") Long idTambo,
                                                                           @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                                           @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                           @RequestParam(value = "fechaHasta")Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorRangoFechaNacimiento(idTambo,idEmpresa,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorVacunaDistinta")
    public ResponseEntity<List<Ganado>> listarPorVacunaDistinta(@RequestParam(value = "idTambo") Long idTambo,
                                                                @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                                @RequestParam(value = "idVacuna") Long idVacuna){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorEmpresaTamboVacunaDistinta(idTambo,idEmpresa,idVacuna));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorVacunaIgual")
    public ResponseEntity<List<Ganado>> listarPorVacunaIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                             @RequestParam(value = "idEmpresa") Long idEmpresa,
                                                             @RequestParam(value = "idVacuna") Long idVacuna){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorEmpresaTamboVacunaIgual(idTambo,idEmpresa,idVacuna));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("listadoPorSexoIgual")
    public ResponseEntity<List<Ganado>> listarPorSexoIgual(@RequestParam(value = "idTambo") Long idTambo,
                                                           @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                           @RequestParam(value = "sexo") String sexo){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorSexo(idTambo,idEmpresa,sexo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("listadoPorCantPartosMenor")
    public ResponseEntity<List<Ganado>> listarPorCantPartosMenor(@RequestParam(value = "idTambo") Long idTambo,
                                                           @RequestParam(value = "idEmpresa")Long idEmpresa,
                                                           @RequestParam(value = "cantPartos") Integer cantPartos){
        try{
            return ResponseEntity.ok(ganadoService.filtrarPorCantPartosMenor(idTambo,idEmpresa,cantPartos));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //------------------------------------------------- INICIO DE NOTIFICACIONES -------------------------------------------------

    @GetMapping("/notificarPorRangoTemperatura")
    public ResponseEntity<Ganado> notificarPorRangoTemperartura(@RequestParam(value = "idGanado") Long idGanado,
                                                                @RequestParam(value = "temp1") Double temp1,
                                                                @RequestParam(value = "temp2") Double temp2,
                                                                @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorRangoTemperatura(idGanado,temp1,temp2,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorCantPasosMayorIgual")
    public ResponseEntity<Ganado> notificarPorCantPasosMayorIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                                  @RequestParam(value = "cantPasos") Double cantPasos,
                                                                  @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                  @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorCantPasosMayorIgual(idGanado,cantPasos,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorCantPasosMenorIgual")
    public ResponseEntity<Ganado> notificarPorCantPasosMenorIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                                  @RequestParam(value = "cantPasos") Double cantPasos,
                                                                  @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                  @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorCantPasosMenorIgual(idGanado,cantPasos,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorCantComieronIgual")
    public ResponseEntity<Ganado> notificarPorCantComieronIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                                @RequestParam(value = "cantComio") Integer cantComio,
                                                                @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorCantComieronIgual(idGanado,cantComio,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorCantComieronMenorIgual")
    public ResponseEntity<Ganado> notificarPorCantComieronMenorIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                                     @RequestParam(value = "cantComio") Integer cantComio,
                                                                     @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                     @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorCantComieronMenorIgual(idGanado,cantComio,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorPesoMenorIgual")
    public ResponseEntity<Ganado> notificarPorPesoMenorIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                             @RequestParam(value = "peso") Double peso,
                                                             @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                             @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorPesoMenorIgual(idGanado,peso,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/notificarPorPesoMayorIgual")
    public ResponseEntity<Ganado> notificarPorPesoMayorIgual(@RequestParam(value = "idGanado") Long idGanado,
                                                             @RequestParam(value = "peso") Double peso,
                                                             @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                             @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(ganadoService.filtrarGanadoPorPesoMayorIgual(idGanado,peso,fechaDesde,fechaHasta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}
