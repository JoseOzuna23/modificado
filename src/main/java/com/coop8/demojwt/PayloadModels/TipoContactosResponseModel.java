package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoContactosResponseModel {

    private Long id;   
    
    private String descripcion;

      public void filterPayloadToSend(TipoContactosResponseModel tipoContactos) {
        this.id = tipoContactos.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = tipoContactos.getDescripcion();        
    }

}
