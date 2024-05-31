package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaSociosResponseModel {

    private Long id;   
    
    private String descripcion;

      public void filterPayloadToSend(CategoriaSociosResponseModel categoriaSocio) {
        this.id = categoriaSocio.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = categoriaSocio.getDescripcion();        
    }

}
