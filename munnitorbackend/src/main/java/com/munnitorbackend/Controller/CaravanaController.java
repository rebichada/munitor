package com.munnitorbackend.Controller;

import com.munnitorbackend.Model.Caravana;
import com.munnitorbackend.Service.ICaravanaService;
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

    @PostMapping("/new")
    public ResponseEntity<?> crear(@RequestBody Caravana caravana){
        try{
            return new ResponseEntity(caravanaService.guardar(caravana),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
