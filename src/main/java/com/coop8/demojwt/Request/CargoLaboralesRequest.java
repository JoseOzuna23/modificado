package com.coop8.demojwt.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoLaboralesRequest {

    private Long id;
	private String descripcion;

    

}
