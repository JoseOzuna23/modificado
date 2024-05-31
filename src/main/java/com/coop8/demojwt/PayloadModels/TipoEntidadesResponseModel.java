package com.coop8.demojwt.PayloadModels;

import com.coop8.demojwt.Models.TipoEntidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoEntidadesResponseModel  {
    
    private Long id;
    private String descripcion;

    public void filterPayloadToSend(TipoEntidades tipoEntidades) {
        this.id = tipoEntidades.getId();
        this.descripcion = tipoEntidades.getDescripcion();
        // Incluir otros campos que desees enviar en el response aquí
    }

}