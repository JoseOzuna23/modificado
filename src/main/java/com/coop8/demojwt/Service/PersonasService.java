package com.coop8.demojwt.Service;

import com.coop8.demojwt.Jwt.JwtService;
import com.coop8.demojwt.Models.EstadoCiviles;
import com.coop8.demojwt.Models.Ciudad;
import com.coop8.demojwt.Models.Nacionalidades;
import com.coop8.demojwt.Models.Personas;
import com.coop8.demojwt.Models.TipoDocumentos;
import com.coop8.demojwt.Models.TipoPersonas;
import com.coop8.demojwt.PayloadModels.PersonasResponseModel;
import com.coop8.demojwt.Repository.PersonasRepository;
import com.coop8.demojwt.Request.PaginacionRequest;
import com.coop8.demojwt.Request.PersonasRequest;
import com.coop8.demojwt.Response.PaginacionResponse;
import com.coop8.demojwt.Response.PersonasResponse;
import com.coop8.demojwt.Response.Response;
import com.coop8.demojwt.Response.ResponseHeader;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Utils.DateUtil;
import com.coop8.demojwt.Utils.ECodigosRespuestas;
import com.coop8.demojwt.Utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@Slf4j
public class PersonasService {

    @Autowired
    PersonasRepository personasRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> list(@Valid PersonasRequest personasRequest) throws Exception {
        log.info("PersonasService | list");
        log.info("__personasRequest: " + Util.getJsonFromObject(personasRequest));

        // Obtener el UserDetails del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new Exception("Usuario no autenticado");
        }

        Object principal = authentication.getPrincipal();
        UserDetails userDetails;

        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        } else if (principal instanceof String) {
            String username = (String) principal;
            // Aquí debes cargar los detalles del usuario manualmente si el principal es un String
            userDetails = loadUserByUsername(username);
        } else {
            throw new ClassCastException("El principal de autenticación no es una instancia de UserDetails ni un String");
        }

        List<Personas> personasList;

        // Verificación y asignación predeterminada si paginacion es null
        int pagina = 0;
        int cantidad = 9; // Cambiar cantidad a 5 registros por página
        if (personasRequest.getPaginacion() != null) {
            pagina = Math.max(personasRequest.getPaginacion().getPagina() - 1, 0);
            cantidad = Math.max(personasRequest.getPaginacion().getCantidad(), 1); // Asegura que la cantidad sea al menos 1
        }
        Pageable paging = PageRequest.of(pagina, cantidad);

        Page<Personas> pagePersonas;
        if (Util.isNullOrEmpty(personasRequest.getNroDocumento())) {
            pagePersonas = personasRepository.findAllByOrderByNroDocumentoAsc(paging);
        } else {
            pagePersonas = personasRepository.findByNroDocumentoContainingIgnoreCaseOrderByNroDocumentoAsc(
                    personasRequest.getNroDocumento(), paging);
        }

        personasList = pagePersonas.getContent();
        List<PersonasResponseModel> personasResponseModels = new ArrayList<>();
        for (Personas personas : personasList) {
            PersonasResponseModel eu = new PersonasResponseModel();
            eu.filterPayloadToSend(personas);
            personasResponseModels.add(eu);
        }

        PersonasResponse personasResponse = new PersonasResponse();
        personasResponse.setPersonas(personasResponseModels);
        PaginacionResponse pageableResponse = new PaginacionResponse();

        pageableResponse.setTotalItems(pagePersonas.getTotalElements());
        pageableResponse.setTotalPages(pagePersonas.getTotalPages());
        pageableResponse.setCurrentPages(pagePersonas.getNumber() + 1);
        personasResponse.setPaginacion(pageableResponse);

        // Crear una nueva respuesta que contenga los datos de las personas
        return new ResponseEntity<>(personasResponse, HttpStatus.OK);
    }

    // Método auxiliar para cargar UserDetails manualmente
    private UserDetails loadUserByUsername(String username) {
        // Aquí deberías implementar la lógica para cargar un UserDetails a partir del nombre de usuario.
        // Por ejemplo, podrías usar un servicio de usuario:
        // return userDetailsService.loadUserByUsername(username);

        // Ejemplo básico (deberías reemplazar esto con tu implementación real)
        return new User(username, "", new ArrayList<>());
    }


public ResponseEntity<?> getById(@Valid PersonasRequest personasRequest) throws Exception {
    log.info("PersonasService | getById");
    log.info("__personasRequest: " + Util.getJsonFromObject(personasRequest));

    // Obtener el UserDetails del contexto de seguridad
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
        throw new Exception("Usuario no autenticado");
    }

    Object principal = authentication.getPrincipal();
    UserDetails userDetails;

    if (principal instanceof UserDetails) {
        userDetails = (UserDetails) principal;
    } else if (principal instanceof String) {
        String username = (String) principal;
        // Aquí debes cargar los detalles del usuario manualmente si el principal es un String
        userDetails = loadUserByUsername(username);
    } else {
        throw new ClassCastException("El principal de autenticación no es una instancia de UserDetails ni un String");
    }

    PersonasResponse personasResponse = new PersonasResponse();
    long idPersona = personasRequest.getId();
    Optional<Personas> persona = personasRepository.findById(idPersona);

    if (persona.isPresent()) {
        List<PersonasResponseModel> personasResponseModels = new ArrayList<>();
        PersonasResponseModel eu = new PersonasResponseModel();
        eu.filterPayloadToSend(persona.get());
        personasResponseModels.add(eu);

        personasResponse.setPersonas(personasResponseModels);

        PaginacionResponse pageableResponse = new PaginacionResponse();
        pageableResponse.setTotalItems(1);
        pageableResponse.setTotalPages(1);
        pageableResponse.setCurrentPages(1);
        personasResponse.setPaginacion(pageableResponse);

        log.info("__PersonasResponse: " + Util.getJsonFromObject(personasResponse));
        return new ResponseEntity<>(personasResponse, HttpStatus.OK);
    } else {
        log.error("Persona no encontrada con el id: " + idPersona);
        return new ResponseEntity<>("Persona no encontrada", HttpStatus.NOT_FOUND);
    }
  }
    

    public ResponseEntity<PersonasResponseModel> save(PersonasRequest personasRequest) {
        try {
            String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("Usuario del sistema: " + usuariosys);

            // Si la persona tiene ID, intentar actualizar
            if (personasRequest.getId() != 0) {
                log.info("Intentando actualizar persona con ID: " + personasRequest.getId());
                // Convertir el id de int a long
                long personaId = personasRequest.getId();
                Optional<Personas> optionalPersona = personasRepository.findById(personaId);
                if (optionalPersona.isPresent()) {
                    Personas personaUpdate = optionalPersona.get();
                    mapPersonasRequestToEntity(personasRequest, personaUpdate, usuariosys);
                    personasRepository.save(personaUpdate);
                    log.info("Persona actualizada con éxito: " + personaUpdate);

                    // Crear una respuesta que contenga los datos de la persona actualizada
                    PersonasResponseModel personaResponse = new PersonasResponseModel();
                    personaResponse.filterPayloadToSend(personaUpdate);
                    return ResponseEntity.status(HttpStatus.OK).body(personaResponse);
                } else {
                    log.error("Persona no encontrada con ID: " + personasRequest.getId());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PersonasResponseModel());
                }
            } else {
                // Si la persona no tiene ID, insertar nueva persona
                log.info("Insertando nueva persona");
                Personas personaInsert = new Personas();
                mapPersonasRequestToEntity(personasRequest, personaInsert, usuariosys);
                personasRepository.save(personaInsert);
                log.info("Persona insertada con éxito: " + personaInsert);

                // Crear una respuesta que contenga los datos de la persona insertada
                PersonasResponseModel personaResponse = new PersonasResponseModel();
                personaResponse.filterPayloadToSend(personaInsert);
                return ResponseEntity.status(HttpStatus.CREATED).body(personaResponse);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver una respuesta de error
            log.error("Error al procesar la solicitud: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PersonasResponseModel());
        }
    }

   private void mapPersonasRequestToEntity(PersonasRequest request, Personas entity, String usuariosys) {
    entity.setNombre1(request.getNombre1());
    entity.setNombre2(request.getNombre2());
    entity.setNombre3(request.getNombre3());
    entity.setApellido1(request.getApellido1());
    entity.setApellido2(request.getApellido2());
    entity.setApellido3(request.getApellido3());
    entity.setSexo(request.getSexo());
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fechaActualStr = sdf.format(new Date());
    entity.setFechasys(java.sql.Date.valueOf(fechaActualStr));
    entity.setUsuariosys(usuariosys);
    entity.setNroDocumento(request.getNroDocumento());

    // Convertir la fecha de nacimiento al formato "yyyy-MM-dd"
    SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fechaNacimientoStr = dbDateFormat.format(request.getFechaNacimiento());
    entity.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimientoStr));

    Integer tipoPersona = Integer.valueOf(request.getTipoPersonas());
    Integer tipoDocumento = Integer.valueOf(request.getTipoDocumentos());
    Integer estadoCivil = Integer.valueOf(request.getEstadoCiviles());
    Integer ciudad = Integer.valueOf(request.getCiudades());
    Integer nacionalidad = Integer.valueOf(request.getNacionalidades());

    entity.setTipoDocumento(new TipoDocumentos(tipoDocumento, null, usuariosys));
    entity.setTipoPersona(new TipoPersonas(tipoPersona, null, usuariosys));
    entity.setEstadoCivil(new EstadoCiviles(estadoCivil, null, usuariosys));
    entity.setCiudad(new Ciudad(ciudad, null, null, usuariosys));
    entity.setNacionalidad(new Nacionalidades(nacionalidad, null, usuariosys));
}

    public void savePerson(PersonasRequest personasRequest, String usuariosys) {
        Personas persona = new Personas();
        mapPersonasRequestToEntity(personasRequest, persona, usuariosys);
        personasRepository.save(persona);
    }



public SecuredResponse deleteById(@Valid PersonasRequest personasRequest) throws Exception {
    log.info("PersonasService | deleteById");
    log.info("__personasRequest: " + Util.getJsonFromObject(personasRequest));

    long idPersona = personasRequest.getId();
    ResponseHeader header = new ResponseHeader();
    Response response = new Response();
    SecuredResponse securedResponse = new SecuredResponse();

    personasRepository.deleteById(idPersona);
    header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
    header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
    response.setHeader(header);
    response.setData(null);

    securedResponse.setData(jwtService.getDataFromPayload(response));
    log.info("__response: " + Util.getJsonFromObject(response));
    log.info("__securedResponse: " + Util.getJsonFromObject(securedResponse));

    return securedResponse;
}


private SecuredResponse handleMissingToken() {
    ResponseHeader header = new ResponseHeader();
    Response response = new Response();
    SecuredResponse securedResponse = new SecuredResponse();

    header.setCodResultado(ECodigosRespuestas.ERROR.getCodigoRespuesta());
    header.setTxtResultado("JWT String argument cannot be null or empty.");
    response.setHeader(header);
    response.setData(null);

    try {
        securedResponse.setData(jwtService.getDataFromPayload(response));
    } catch (Exception ex) {
        log.error("Error handling missing token", ex);
    }
    return securedResponse;
}
}