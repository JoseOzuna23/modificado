package com.coop8.demojwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.TipoSolicitudesRequest;
import com.coop8.demojwt.Service.TipoSolicitudesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/tipoSolicitudes")
@Slf4j
public class TipoSolicitudesController {


    String BaseUrlEndPoint;

    public TipoSolicitudesController() {
        BaseUrlEndPoint = "/referenciales/tipoSolicitudes";
        log.info("__BASE_end_point:    " + BaseUrlEndPoint);
    }

    @Autowired
    TipoSolicitudesService tipoSolicitudesService;



    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody TipoSolicitudesRequest tipoSolicitudesRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(tipoSolicitudesService.newAction(tipoSolicitudesRequest), HttpStatus.OK);
    }



}
