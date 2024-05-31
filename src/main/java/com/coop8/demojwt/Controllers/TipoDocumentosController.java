package com.coop8.demojwt.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop8.demojwt.Request.TipoDocumentosRequest;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Service.TipoDocumentosService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/referenciales/tipoDocumentos")
@Slf4j
public class TipoDocumentosController {
	String BaseUrlEndPoint;

	public TipoDocumentosController() {
		BaseUrlEndPoint = "/referenciales/tipoDocumentos";
		log.info("__BASE_end_point:	" + BaseUrlEndPoint);
	}

	@Autowired
	TipoDocumentosService tipoDocumentosService;

	
	@PostMapping("/newAction")
	public ResponseEntity<SecuredResponse> newAction(@Valid @RequestBody TipoDocumentosRequest tipoDocumentosRequest) {
        try {
            SecuredResponse response = tipoDocumentosService.newAction(tipoDocumentosRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error en newAction", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}