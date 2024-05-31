package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoVinculosResponseModel {

    private Long id;   
    
    private String descripcion;


    public void filterPayloadToSend(TipoVinculosResponseModel tipoVinculos) {
        this.id = tipoVinculos.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = tipoVinculos.getDescripcion();        
    }



}
