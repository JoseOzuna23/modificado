package com.coop8.demojwt.PayloadModels;

import com.coop8.demojwt.Models.Sucursales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SucursalesResponseModel {

    private int  id;
    private String descripcion;

    public void filterPayloadToSend(Sucursales sucursales) {
        this.id = sucursales.getId();
        this.descripcion = sucursales.getDescripcion();
        // Incluir otros campos que desees enviar en el response aquí
    }

}