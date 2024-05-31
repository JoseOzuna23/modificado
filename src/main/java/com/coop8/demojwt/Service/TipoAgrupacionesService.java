package com.coop8.demojwt.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.coop8.demojwt.Jwt.JwtService;
import com.coop8.demojwt.Models.TipoAgrupaciones;
import com.coop8.demojwt.Repository.TipoAgrupacionesRepository;
import com.coop8.demojwt.Request.TipoAgrupacionesRequest;
import com.coop8.demojwt.Response.Response;
import com.coop8.demojwt.Response.ResponseHeader;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Utils.ECodigosRespuestas;
import com.coop8.demojwt.Utils.Util;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoAgrupacionesService {

    @Autowired
    TipoAgrupacionesRepository tipoAgrupacionesRepository;
    
    @Autowired
    JwtService jwtService;

    public SecuredResponse newAction(@Valid @RequestBody TipoAgrupacionesRequest tipoAgrupacionesRequest) throws Exception {
        log.info("TipoAgrupacionesService | newAction");
        log.info("__tipoAgrupacionesRequest: " + Util.getJsonFromObject(tipoAgrupacionesRequest));

        // Decodificamos el dataRequest y obtenemos el Payload
        log.info("__tipoAgrupacionesRequest: " + Util.getJsonFromObject(tipoAgrupacionesRequest));

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        // Obtenemos el usuario de SecurityContextHolder
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();

        // Verificamos si es un INSERT
        if (Util.isNullOrEmpty(tipoAgrupacionesRequest.getId()) || tipoAgrupacionesRequest.getId() == 0) {
            // INSERT
            TipoAgrupaciones tipoAgrupacionesInsert = new TipoAgrupaciones();
            tipoAgrupacionesInsert.setDescripcion(tipoAgrupacionesRequest.getDescripcion().toUpperCase());
            tipoAgrupacionesInsert.setFechasys(new Date());
            tipoAgrupacionesInsert.setUsuariosys(usuariosys);

            tipoAgrupacionesRepository.save(tipoAgrupacionesInsert);

            header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
            header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.info("__response: " + Util.getJsonFromObject(response));
            log.info("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.info("Token generado: " + token);
        } else {
            // Se proporcionó un ID para una operación de creación
            header.setCodResultado(ECodigosRespuestas.ERROR.getCodigoRespuesta());
            header.setTxtResultado("Se proporcionó un ID para una operación de creación");
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.error("__response: " + Util.getJsonFromObject(response));
            log.error("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.error("Token generado: " + token);
        }

        return securedResponse;
    }
}
