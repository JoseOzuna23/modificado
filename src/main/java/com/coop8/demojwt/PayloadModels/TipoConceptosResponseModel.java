package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoConceptosResponseModel {


    private Long id;   
    
    private String descripcion;

    public void filterPayloadToSend(TipoConceptosResponseModel tipoConceptos) {
        this.id = tipoConceptos.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = tipoConceptos.getDescripcion();        
    }


}
