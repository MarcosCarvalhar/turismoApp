package com.turismoapp.turismo_app.service;

import com.turismoapp.turismo_app.model.Local;
import com.turismoapp.turismo_app.repository.AvaliacaoRepository;
import com.turismoapp.turismo_app.repository.LocalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LocalUpdateService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private LocalRepository localRepository;

    public void atualizarNotaMedia(Local local) {
        long quantidadeAvaliacoes = avaliacaoRepository.countByLocalId(Long.valueOf(local.getId()));
        double somaNotasAvaliacoes = avaliacaoRepository.sumNotasByLocalId(Long.valueOf(local.getId()));

        if (quantidadeAvaliacoes > 0) {
            BigDecimal mediaNotas = BigDecimal.valueOf(somaNotasAvaliacoes)
                .divide(BigDecimal.valueOf(quantidadeAvaliacoes), 2, RoundingMode.HALF_UP);
            local.setNotaMedia(mediaNotas.doubleValue());
        } else {
            local.setNotaMedia(0.0);
        }

        new ResponseEntity<Local>(localRepository.save(local), HttpStatus.OK);
    }
}
