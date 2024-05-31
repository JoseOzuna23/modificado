package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadEconomicasResponseModel {


    private Long id;   
    
    private String descripcion;

    public void filterPayloadToSend(ActividadEconomicasResponseModel actividadEconomicas) {
        this.id= actividadEconomicas.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = actividadEconomicas.getDescripcion();        
    }

}
