package com.coop8.demojwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.SucursalesRequest;
import com.coop8.demojwt.Service.SucursalesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/sucursales")
@Slf4j
public class SucursalesController {

    String BaseUrlEndPoint;

    public SucursalesController(){
        BaseUrlEndPoint = "/referenciales/sucursales";
        log.info("__BASE_end_point:    " + BaseUrlEndPoint);
    }

    @Autowired
    SucursalesService sucursalesService;


    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody SucursalesRequest sucursalesRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(sucursalesService.newAction(sucursalesRequest), HttpStatus.OK);
    }

}
