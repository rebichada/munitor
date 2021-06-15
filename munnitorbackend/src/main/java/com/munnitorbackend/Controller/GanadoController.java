package com.munnitorbackend.Controller;

import com.munnitorbackend.DTO.RequestDatosDelGanadoDTO;
import com.munnitorbackend.DTO.ResponseDatosDelGanadoDTO;
import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Model.Ganado;
import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.net.www.http.HttpCaptureInputStream;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;




@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/ganado")
public class GanadoController {

    @Autowired
    private IGanadoService ganadoService;

    @Autowired
    private IGanadoDatosService ganadoDatosService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private ITamboService tamboService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/new")
    public ResponseEntity<?> crear(@RequestBody Ganado ganado){
        try{
            return new ResponseEntity(ganadoService.guardar(ganado),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idGanado}")
    public ResponseEntity<?> eliminar(@PathVariable String idGanado){
        try{
            return new ResponseEntity(ganadoService.eliminarById(Long.parseLong(idGanado)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idGanado}")
    public ResponseEntity<?> getGanado(@PathVariable(value = "idGanado") String idGanado){
        try{
            if (ganadoService.obtenerPorId(Long.parseLong(idGanado))!= null){
                return ResponseEntity.ok(ganadoService.obtenerPorId(Long.parseLong(idGanado)));
            }
            return new ResponseEntity("No se encontro este ganado.", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //LISTADO DE TODO GANADO QUE PERTENECE A UNA EMPRESA
    @GetMapping("/verGanadoEmpresa")
    public ResponseEntity<List<?>> getGanadoEMpresa(@PathVariable(value = "idEmpresa") @Validated String idEmpresa){
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresa(Long.parseLong(idEmpresa)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //LISTADO DE TODO GANADO QUE PERTENECE A UNA EMPRESA PARA DICHO TAMBO
    @GetMapping("/listar")
    public ResponseEntity<List<Ganado>> getGanadoEmpresaTambo(@RequestParam(value = "idEmpresa") String idEmpresa,
                                                              @RequestParam(value = "idTambo") String idTambo) {
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresaTambo(Long.parseLong(idTambo),Long.parseLong(idEmpresa)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/datosSensor")
    public String RequestDatosGanado(){
        return "/datosSensor";
    }

    @PostMapping ("/datosSensor")
    public ResponseEntity<?> guardarDatosGanado(@RequestBody RequestDatosDelGanadoDTO datosDelGanado){
        try{
            if (!ganadoService.existById(datosDelGanado.getIdGanado())) new ResponseEntity<>("No existe este codigo de ganado: " + datosDelGanado.getIdGanado(), HttpStatus.INTERNAL_SERVER_ERROR);

            GanadoDatos datos= modelMapper.map(datosDelGanado, GanadoDatos.class);


            datos.setFechaDeRegistro(new Date());
            datos = ganadoDatosService.guardar(datos);
            if (datos.getTemperatura() > 40 || datos.getTemperatura() < 35) {
                //crear notificacion;
            }
            return new ResponseEntity(datos,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping ("/datosGanado")
    public ResponseEntity<GanadoDatos> guardarDatosGanadoPorUsuario(@RequestBody RequestDatosDelGanadoDTO datosDelGanado){
        try{
            GanadoDatos datos= modelMapper.map(datosDelGanado, GanadoDatos.class);
            datos.setFechaDeRegistro(new Date());
            GanadoDatos ganadoDatos = ganadoDatosService.guardar(datos);
            if (datos.getTemperatura() > 40 || datos.getTemperatura() < 35){
                return ResponseEntity.created(new URI("/notificarTemperatura/"+ ganadoDatos.getId())).body(ganadoDatos);
            }else{
                return ResponseEntity.created(new URI("/datosSensor")).body(ganadoDatos);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/principal")
    public ResponseEntity<List<?>> getGanadoMasTemperaturaMasCantPasos(@RequestParam(value = "idTambo") String idTambo, @RequestParam(value = "idEmpresa") String idEmpresa){
        List<GanadoDatos> ganadoDatosReales;
        try {
            //obtengo toodos los ganados de esta empresa y tambo con su ultima temperatura en su ultimo registro
            if(!empresaService.existsById(Long.parseLong(idEmpresa))) return new ResponseEntity("Esta empresa con este codigo: " + idEmpresa + " no se fue encontrado. "  ,HttpStatus.OK);
            if(!tamboService.existsById(Long.parseLong(idTambo))) return new ResponseEntity("Esta Tambo con codigo: " + idTambo + " no fue encontrado.",HttpStatus.OK);
            //obtengo todos los GanadoDatos con la cantidad de pasos en las ultimas 24 hs
            List<GanadoDatos> ganadoDatos= ganadoDatosService.cantidadDePasosInRangeFecha(Long.parseLong(idTambo),Long.parseLong(idEmpresa));
            List<GanadoDatos> ganadoDatosUltimasTemperaturasRegistradas= ganadoDatosService.findByUltimaTemperatura(Long.parseLong(idTambo),Long.parseLong(idEmpresa));
            //filtro por el object Ganado y mapeo para solo enviar los datos necesarios
<<<<<<< HEAD
            List<ResponseDatosDelGanadoDTO> resultadoGanado;
             ganadoDatos = ganadoDatos.stream()
                    .filter(l1 -> (ganadoDatosUltimasTemperaturasRegistradas.stream().anyMatch(l2 -> l2.getGanado().getId().equals(l1.getGanado().getId())))).collect(Collectors.toList());
                            //.count())<1)

            //Predicate<String> notIn2 = s -> ! list2.stream().anyMatch(mc -> s.equals(mc.str));
            //List<String> list3 = list1.stream().filter(notIn2).collect(Collectors.toList());
            resultadoGanado= ganadoDatos.stream()
=======

            ganadoDatosReales = ganadoDatos.stream()
                    .filter(l1 -> (ganadoDatosUltimasTemperaturasRegistradas.stream()
                            .filter(l2 -> l1.getGanado().getId().equals(l1.getGanado().getId()))
                            .count())<1)
                    .collect(Collectors.toList());

            ganadoDatosReales= ganadoDatosReales.stream().map(l1 -> {
                for (GanadoDatos ganadoDatos1:
                        ganadoDatosUltimasTemperaturasRegistradas) {
                    l1.setTemperatura(ganadoDatos1.getTemperatura());
                }
                return l1;
            }).collect(Collectors.toList());

            ganadoDatosReales= ganadoDatosReales.stream().map(l1 -> {
                for (GanadoDatos ganadoDatos2:
                        ganadoDatos) {
                    l1.setPasos(ganadoDatos2.getPasos());
                }
                return l1;
            }).collect(Collectors.toList());

            //Predicate<String> notIn2 = s -> ! list2.stream().anyMatch(mc -> s.equals(mc.str));
            //List<String> list3 = list1.stream().filter(notIn2).collect(Collectors.toList());
            List<ResponseDatosDelGanadoDTO> resultadoGanado= ganadoDatosReales.stream()
>>>>>>> developJulito1998
                    .map(ganadoDatos1 -> modelMapper.map(ganadoDatos1, ResponseDatosDelGanadoDTO.class))
                    .collect(Collectors.toList());

            for (GanadoDatos gd:ganadoDatosReales) {
                for (ResponseDatosDelGanadoDTO responseDatosDelGanadoDTO:resultadoGanado) {
                    if (gd.getId()==responseDatosDelGanadoDTO.getId()){
                        responseDatosDelGanadoDTO.setCuig(gd.getGanado().getCaravana().getCUIG());
                    }
                }
            }

            return ResponseEntity.ok(resultadoGanado);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //listarUltimaTemperaturaCantPasosEnUnDia
    //cantidadDePasosInRangeFecha

    @GetMapping(path = "/VerGanadoPorCaravana/{idCaravana}")
    public ResponseEntity<Ganado> getGanadoByIdCaravana(@PathVariable String idCaravana) {
        try{
            return ResponseEntity.ok(ganadoService.obtenerGanadoPorIdCaravana(Long.parseLong(idCaravana)));
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
    public ResponseEntity<List<Ganado>> listarGanadoPorEmpleado(@RequestParam(value = "idEmpresa") String idEmpresa,
                                                                @RequestParam(value = "idTambo") String idTambo,
                                                                @RequestParam(value = "idEmpleado") String idEmpleado) {
        try{
            return ResponseEntity.ok(ganadoService.listarPorEmpresaTamboEmpleado(Long.parseLong(idTambo),Long.parseLong(idEmpresa),Long.parseLong(idEmpleado)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @GetMapping("/listadoPorTemperatura")
    public ResponseEntity<ArrayList<Ganado>> listarPorRangoTemperatura(@RequestParam(value = "idTambo") String idTambo,
                                                                        @RequestParam(value = "idEmpresa") String idEmpresa,
                                                                        @RequestParam(value = "temp1") Double temp1,
                                                                        @RequestParam(value = "temp2") Double temp2,
                                                                        @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                        @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
              return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorRangoTemperatura(Long.parseLong(idTambo),Long.parseLong(idEmpresa),temp1,temp2,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @GetMapping("/listadoPorCantPasosMenorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPasosMenor(@RequestParam(value = "idTambo") String idTambo, @RequestParam(value = "idEmpresa") String idEmpresa,
                                                                       @RequestParam(value = "cantPasos") Double cantPasos,
                                                                       @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                       @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCantPasosMenorIgual(Long.parseLong(idTambo),Long.parseLong(idEmpresa),cantPasos,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/listadoPorCantPasosMayorIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorPasosMayor(@RequestParam(value = "idTambo") String idTambo,
                                                                       @RequestParam(value = "idEmpresa") String idEmpresa,
                                                                       @RequestParam(value = "cantPasos") Double cantPasos,
                                                                       @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                       @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCantPasosMayorIgual(Long.parseLong(idTambo),Long.parseLong(idEmpresa),cantPasos,fechaDesde,fechaHasta)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorCUIG")
    public ResponseEntity<ArrayList<Ganado>> listarPorCUIG(@RequestParam(value = "idTambo") String idTambo,
                                                            @RequestParam(value = "idEmpresa") String idEmpresa,
                                                            @RequestParam(value = "cuig") String cuig){
        try{
            return ResponseEntity.ok(new ArrayList(ganadoService.filtrarPorCUIGCaravana(Long.parseLong(idTambo),Long.parseLong(idEmpresa),cuig)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listadoPorCantidadComieronIgual")
    public ResponseEntity<ArrayList<Ganado>> listarPorCantComieronIgual(@RequestParam(value = "idTambo") String idTambo,
                                                                         @RequestParam(value = "idEmpresa") String idEmpresa,
                                                                         @RequestParam(value = "cantComieron") Integer cantComieron,
                                                                         @RequestParam(value = "fechaDesde") Date fechaDesde,
                                                                         @RequestParam(value = "fechaHasta") Date fechaHasta){
        try{
            return ResponseEntity.ok(new ArrayList<>(ganadoService.filtrarPorCantComieronIgual(Long.parseLong(idTambo),Long.parseLong(idEmpresa),cantComieron,fechaDesde,fechaHasta)));
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
