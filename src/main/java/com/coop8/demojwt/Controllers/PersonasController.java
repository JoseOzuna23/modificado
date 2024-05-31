package com.coop8.demojwt.Controllers;

import com.coop8.demojwt.PayloadModels.PersonasResponseModel;
import com.coop8.demojwt.Request.PersonasRequest;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/personas")
@Slf4j
public class PersonasController {

    @Autowired
    PersonasService personasService;

    @PostMapping("/list")
    public ResponseEntity<?> list(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/list");
        return personasService.list(personasRequest);
    }


    @PostMapping("/edit")
    public ResponseEntity<?> edit(@Valid @RequestBody PersonasRequest personasRequest) {
        log.info("__end_point: /personas/edit");
        try {
            // Llama a getById en el servicio y retorna el ResponseEntity directamente
            ResponseEntity<?> response = personasService.getById(personasRequest);
            return response;
        } catch (Exception e) {
            // Manejo de excepciones y retorno de un error apropiado
            log.error("Error en /personas/edit", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getById(@Valid @RequestBody PersonasRequest personasRequest) {
        log.info("__end_point: /personas/getById");
        try {
            // Llama a getById en el servicio y retorna el ResponseEntity directamente
            ResponseEntity<?> response = personasService.getById(personasRequest);
            return response;
        } catch (Exception e) {
            // Manejo de excepciones y retorno de un error apropiado
            log.error("Error en /personas/getById", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/newAction")
    public ResponseEntity<PersonasResponseModel> newAction(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        return ResponseEntity.ok(personasService.save(personasRequest).getBody());
    }

    @PostMapping("/save")
    public ResponseEntity<PersonasResponseModel> save(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        return ResponseEntity.ok(personasService.save(personasRequest).getBody());
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteById(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/deleteById");
        SecuredResponse response = personasService.deleteById(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/create");
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();
        personasService.savePerson(personasRequest, usuariosys);
        return new ResponseEntity<>("Persona creada exitosamente", HttpStatus.CREATED);
    }
}
