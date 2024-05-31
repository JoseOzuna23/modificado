package com.coop8.demojwt.PayloadModels;

import com.coop8.demojwt.Models.TipoPersonas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoPersonasResponseModel {

	private Integer id;
	private String descripcion;

	public void filterPayloadToSend(TipoPersonas tipoPersonas) {
		this.id = tipoPersonas.getId();
		this.descripcion = tipoPersonas.getDescripcion();
	}
}