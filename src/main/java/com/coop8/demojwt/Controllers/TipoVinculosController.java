package com.coop8.demojwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.coop8.demojwt.Service.TipoVinculosService;

import com.coop8.demojwt.Request.TipoVinculosRequest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/tipoVinculos")
@Slf4j
public class TipoVinculosController {


    String BaseUrlEndPoint;


    public TipoVinculosController() {
        BaseUrlEndPoint = "/referenciales/tipoVinculos";
        log.info("__BASE_end_point:    " + BaseUrlEndPoint);
    }


     @Autowired
    TipoVinculosService tipoVinculosService;

 @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody TipoVinculosRequest tipoVinculosRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(tipoVinculosService.newAction(tipoVinculosRequest), HttpStatus.OK);
    }
    

}
