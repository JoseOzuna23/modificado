package com.coop8.demojwt.PayloadModels;

import com.coop8.demojwt.Models.Personas;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonasResponseModel {
    private int id;
    private String nroDocumento;
    private String nombre1;
    private String nombre2;
    private String nombre3;
    private String apellido1;
    private String apellido2;
    private String apellido3;
    private String fechaNacimiento;
    private String sexo;
    private String tipoDocumento; // Cambiado a String para descripción
    private String tipoPersona; // Cambiado a String para descripción
    private String estadoCivil; // Cambiado a String para descripción
    private String ciudad;  // Cambiado a String para descripción
    private String nacionalidad; // Cambiado a String para descripción

    public void filterPayloadToSend(Personas personas) {
        this.id = personas.getId();
        this.nroDocumento = personas.getNroDocumento();
        this.nombre1 = personas.getNombre1();
        this.nombre2 = personas.getNombre2();
        this.nombre3 = personas.getNombre3();
        this.apellido1 = personas.getApellido1();
        this.apellido2 = personas.getApellido2();
        this.apellido3 = personas.getApellido3();
        this.fechaNacimiento = personas.getFechaNacimiento() != null ? personas.getFechaNacimiento().toString() : null;
        this.sexo = personas.getSexo();
        this.tipoDocumento = personas.getTipoDocumento() != null ? personas.getTipoDocumento().getDescripcion() : null;
        this.tipoPersona = personas.getTipoPersona() != null ? personas.getTipoPersona().getDescripcion() : null;
        this.estadoCivil = personas.getEstadoCivil() != null ? personas.getEstadoCivil().getDescripcion() : null;
        this.ciudad = personas.getCiudad() != null ? personas.getCiudad().getDescripcion() : null;
        this.nacionalidad = personas.getNacionalidad() != null ? personas.getNacionalidad().getDescripcion() : null;
    }
}
