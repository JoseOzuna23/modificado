package com.coop8.demojwt.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.coop8.demojwt.Jwt.JwtService;
import com.coop8.demojwt.Models.TipoVehiculos;
import com.coop8.demojwt.Repository.TipoVehiculosRepository;
import com.coop8.demojwt.Request.TipoVehiculosRequest;
import com.coop8.demojwt.Response.Response;
import com.coop8.demojwt.Response.ResponseHeader;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Utils.ECodigosRespuestas;
import com.coop8.demojwt.Utils.Util;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoVehiculosService {

    @Autowired
    TipoVehiculosRepository tipoVehiculosRepository;
    
    @Autowired
    JwtService jwtService;

    public SecuredResponse newAction(@Valid @RequestBody TipoVehiculosRequest tipoVehiculosRequest) throws Exception {
        log.info("TipoVehiculosService | newAction");
        log.info("__tipoVehiculosRequest: " + Util.getJsonFromObject(tipoVehiculosRequest));

        // Decodificamos el dataRequest y obtenemos el Payload
        log.info("__tipoVehiculosRequest: " + Util.getJsonFromObject(tipoVehiculosRequest));

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        // Obtenemos el usuario de SecurityContextHolder
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();

        // Verificamos si es un INSERT
        if (Util.isNullOrEmpty(tipoVehiculosRequest.getId()) || tipoVehiculosRequest.getId() == 0) {
            // INSERT
            TipoVehiculos tipoVehiculoInsert = new TipoVehiculos();
            tipoVehiculoInsert.setDescripcion(tipoVehiculosRequest.getDescripcion().toUpperCase());
            tipoVehiculoInsert.setFechasys(new Date());
            tipoVehiculoInsert.setUsuariosys(usuariosys);

            tipoVehiculosRepository.save(tipoVehiculoInsert);

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


