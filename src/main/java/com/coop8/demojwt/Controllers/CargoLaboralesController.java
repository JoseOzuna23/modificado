package com.coop8.demojwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.CargoLaboralesRequest;
import com.coop8.demojwt.Service.CargoLaboralesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/cargoLaborales")
@Slf4j
public class CargoLaboralesController {


    String BaseUrlEndPoint;


    public CargoLaboralesController() {
        BaseUrlEndPoint = "/referenciales/cargoLaborales";
        log.info("__BASE_end_point:    " + BaseUrlEndPoint);
    }
    

     @Autowired
    CargoLaboralesService cargoLaboralesService;


    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody CargoLaboralesRequest cargoLaboralesRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(cargoLaboralesService.newAction(cargoLaboralesRequest), HttpStatus.OK);
    }


}
