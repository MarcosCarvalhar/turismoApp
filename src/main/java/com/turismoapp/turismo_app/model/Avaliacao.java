package com.turismoapp.turismo_app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.turismoapp.turismo_app.observer.Observer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Avaliacao")
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idLocal", nullable = false)
    private Local local;

    @Transient
    private List<Observer> observers = new ArrayList<>();

    private Double nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao() {

    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.atualizar(this);
        }
    }

}
