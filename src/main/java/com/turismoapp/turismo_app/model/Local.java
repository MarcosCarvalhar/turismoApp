package com.turismoapp.turismo_app.model;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.turismoapp.turismo_app.observer.Observer;
import com.turismoapp.turismo_app.service.LocalUpdateService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Local")
@Getter
@Setter
public class Local implements Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    private String CNPJ;
    private String nome;
    private Double notaMedia;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String telefone;
    
    @OneToOne
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @Transient
    @Autowired
    private LocalUpdateService localUpdateService;

    public Local() {
        
    }

    public void setLocalUpdateService(LocalUpdateService localUpdateService) {
        this.localUpdateService = localUpdateService;
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {

        if (localUpdateService != null) {
            localUpdateService.atualizarNotaMedia(this);
        } else {
            System.err.println("localUpdateService não está injetado corretamente");
        }
        
    }
}
