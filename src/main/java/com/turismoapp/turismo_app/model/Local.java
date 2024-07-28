package com.turismoapp.turismo_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Avaliacao")
@Getter
@Setter

public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocal;

    private String CNPJ;
    private String nome;
    private int idEndereco;
    private float notaMedia;
    private int idCategoria;
    private String horarioDeAtendimento;
    private String telefone;

}
