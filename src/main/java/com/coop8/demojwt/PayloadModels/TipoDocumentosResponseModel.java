package com.coop8.demojwt.PayloadModels;

import java.io.Serializable;

import com.coop8.demojwt.Models.TipoDocumentos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentosResponseModel implements Serializable {

	private static final long serialVersionUID = 3481114364879410493L;

	private Integer id;
	private String descripcion;

	public void filterPayloadToSend(TipoDocumentos tipoDocumentos) {
		this.id = tipoDocumentos.getId();
		this.descripcion = tipoDocumentos.getDescripcion();
	}
}