package com.munnitorbackend.Controller;

import com.munnitorbackend.DTO.CaravanaDTO;
import com.munnitorbackend.DTO.RequestDatosDelGanadoDTO;
import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Service.ICaravanaService;
import com.munnitorbackend.Service.ITamboService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/caravana")
public class CaravanaResource {

    @Autowired
    private ICaravanaService caravanaService;

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



}
