package com.coop8.demojwt.PayloadModels;

import java.io.Serializable;

import com.coop8.demojwt.Models.Ciudad;
import com.coop8.demojwt.Models.Departamento; // Importa la clase Departamento si aún no está importada

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CiudadResponseModel implements Serializable {

    private static final long serialVersionUID = 3481114364879410493L;

    private Integer id; // Cambiado de Long a Integer
    private String descripcion;
    private String departamento; // Cambiado de Long a String

    public void filterPayloadToSend(Ciudad ciudad) {
        this.id = ciudad.getCodCiudad(); // Ajustado al nuevo nombre del método getter
        this.descripcion = ciudad.getDescripcion();
        this.departamento = ciudad.getDistrito().getDescripcion(); // Ajustado al nuevo nombre del método getter
    }
}
