package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionesResponseModel {


    private Long id;   
    
    private String descripcion;


    public void filterPayloadToSend(ProfesionesResponseModel Profesiones) {
        this.id = Profesiones.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = Profesiones.getDescripcion();        
    }

}
