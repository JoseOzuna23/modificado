package com.coop8.demojwt.PayloadModels;

import com.coop8.demojwt.Models.TipoAgrupaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoAgrupacionesResponseModel {


    private Long id;
    private String descripcion;

    public void filterPayloadToSend(TipoAgrupaciones tipoAgrupaciones) {
        this.id = tipoAgrupaciones.getId();
        this.descripcion = tipoAgrupaciones.getDescripcion();
        // Incluir otros campos que desees enviar en el response aquí
    }

}