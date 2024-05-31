package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoLaboralesResponseModel {


    private Long id;   
    
    private String descripcion;

    public void filterPayloadToSend(CargoLaboralesResponseModel cargoLaborales) {
        this.id= cargoLaborales.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = cargoLaborales.getDescripcion();        
    }


}
