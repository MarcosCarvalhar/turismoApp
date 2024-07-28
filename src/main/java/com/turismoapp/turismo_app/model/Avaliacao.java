package com.turismoapp.turismo_app.model;

import java.util.GregorianCalendar;

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
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAvaliacao;
    private int idUsuario;
    private int idLocal;
    private int nota;
    private String comentario;
    private GregorianCalendar dataAvaliacao;

}
