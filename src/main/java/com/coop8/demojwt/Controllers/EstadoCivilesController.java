package com.coop8.demojwt.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.EstadoCivilesRequest;
import com.coop8.demojwt.Service.EstadoCivilesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/referenciales/estadoCiviles")
@Slf4j
public class EstadoCivilesController {
	String BaseUrlEndPoint;

	public EstadoCivilesController() {
		BaseUrlEndPoint = "/referenciales/estadoCiviles";
		log.info("__BASE_end_point:	" + BaseUrlEndPoint);
	}

	@Autowired
    EstadoCivilesService estadoCivilesService;

    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody EstadoCivilesRequest estadoCivilesRequest) throws Exception {
        log.info("__end_point:    " + BaseUrlEndPoint + "/newAction");
        return new ResponseEntity<>(estadoCivilesService.newAction(estadoCivilesRequest), HttpStatus.OK);
    }

}