package com.coop8.demojwt.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.NacionalidadesRequest;
import com.coop8.demojwt.Service.NacionalidadesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/nacionalidades")
@Slf4j
public class NacionalidadesController {
	String BaseUrlEndPoint;

	public NacionalidadesController() {
		BaseUrlEndPoint = "/referenciales/nacionalidades";
		log.info("__BASE_end_point:	" + BaseUrlEndPoint);
	}

	@Autowired
	NacionalidadesService nacionalidadesService;

	@PostMapping("/newAction")
	public ResponseEntity<?> newAction(@Valid @RequestBody NacionalidadesRequest nacionalidadesRequest) throws Exception {
		log.info("__end_point:	" + BaseUrlEndPoint + "/newAction");
		return new ResponseEntity<>(nacionalidadesService.newAction(nacionalidadesRequest), HttpStatus.OK);
	}

	
}