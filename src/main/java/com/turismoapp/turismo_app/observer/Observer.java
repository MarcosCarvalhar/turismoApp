package com.turismoapp.turismo_app.observer;

import org.springframework.stereotype.Component;

import com.turismoapp.turismo_app.model.Avaliacao;

@Component
public interface Observer {
    void atualizar(Avaliacao avaliacao);
}
