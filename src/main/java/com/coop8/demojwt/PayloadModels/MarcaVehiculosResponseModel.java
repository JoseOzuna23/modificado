package com.coop8.demojwt.PayloadModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaVehiculosResponseModel {


    private Long id;   
    
    private String descripcion;

    public void filterPayloadToSend(MarcaVehiculosResponseModel marcaVehiculos) {
        this.id = marcaVehiculos.getId(); // Ajustado al nuevo nombre del método getter
        this.descripcion = marcaVehiculos.getDescripcion();        
    }


}
