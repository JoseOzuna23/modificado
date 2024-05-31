package com.coop8.demojwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.EntidadesRequest;
import com.coop8.demojwt.Service.EntidadesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/entidades")
@Slf4j
public class EntidadesController {

    String BaseUrlEndPoint;

    public EntidadesController(){
        BaseUrlEndPoint = "/referenciales/entidades";
        log.info("__BASE_end_point:    " + BaseUrlEndPoint);
    }

    @Autowired
    EntidadesService entidadesService;

    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody EntidadesRequest entidadesRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(entidadesService.newAction(entidadesRequest), HttpStatus.OK);
    }

}
