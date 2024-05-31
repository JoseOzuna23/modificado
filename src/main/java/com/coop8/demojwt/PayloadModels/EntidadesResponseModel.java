package com.coop8.demojwt.PayloadModels;

import java.io.Serializable;

import com.coop8.demojwt.Models.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadesResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String descripcion;

    public void filterPayloadToSend(Entidades entidades) {
        this.id = entidades.getId();
        this.descripcion = entidades.getDescripcion();
        // Incluir otros campos que desees enviar en el response aquí
    }

}