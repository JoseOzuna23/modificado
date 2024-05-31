package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoSolicitudesResponseModel {
    private Long id;   
    
    private String descripcion;


    public void filterPayloadToSend(TipoSolicitudesResponseModel tipoSolicitudes) {
        this.id = tipoSolicitudes.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = tipoSolicitudes.getDescripcion();        
    }

}
