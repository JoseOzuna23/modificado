package com.coop8.demojwt.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "tipo_documentos")
public class TipoDocumentos extends DescripcionFijo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_documentos",columnDefinition = "SERIAL")
    private Integer id;

// Constructor sin parámetros
    public TipoDocumentos() {
        super();
    }

    // Constructor con parámetros
    public TipoDocumentos(Integer id, String descripcion, String usuariosys) {
        super(descripcion, usuariosys);
        this.id = id;
    }
}