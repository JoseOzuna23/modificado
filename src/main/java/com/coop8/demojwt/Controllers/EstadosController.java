package com.coop8.demojwt.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.EstadosRequest;
import com.coop8.demojwt.Service.EstadosService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "http://localhost:3000")@RestController
@RequestMapping("/referenciales/estados")
@Slf4j
public class EstadosController {
	String BaseUrlEndPoint;

	public EstadosController() {
		BaseUrlEndPoint = "/referenciales/estados";
		log.info("__BASE_end_point:	" + BaseUrlEndPoint);
	}

	@Autowired
	EstadosService estadosService;


	@PostMapping("/newAction")
	public ResponseEntity<?> newAction(@Valid @RequestBody EstadosRequest estadosRequest) throws Exception {
		log.info("__end_point:	" + BaseUrlEndPoint + "/newAction");
		return new ResponseEntity<>(estadosService.newAction(estadosRequest), HttpStatus.OK);
	}

	
}