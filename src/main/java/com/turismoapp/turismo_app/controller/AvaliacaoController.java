package com.turismoapp.turismo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismoapp.turismo_app.model.Avaliacao;
import com.turismoapp.turismo_app.model.Local;
import com.turismoapp.turismo_app.service.AvaliacaoService;

@RestController
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/cadastrarAvaliacao")
    public String addAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.avaliacaoAddUpdate(avaliacao, "cadastrar");
    }

    @PutMapping("/alterarAvaliacao")
    public String updateAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.avaliacaoAddUpdate(avaliacao, "alterar");
    }

    @DeleteMapping("/removerAvaliacao/{idAvaliacao}")
    public String deleteAvaliacao(@PathVariable String idAvaliacao) {
        return avaliacaoService.deleteAvaliacao(idAvaliacao);
    }

    @GetMapping("/listarAvaliacoes")
    public Iterable<Avaliacao> getAllAvaliacoes() {
        return avaliacaoService.getAllAvaliacoes();
    }

    @GetMapping("/melhoresLugares")
    public ResponseEntity<List<Local>> getTopLocaisPorNotaMedia() {
        List<Local> locais = avaliacaoService.getTopLocaisPorNotaMedia();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/listarAvaliacoes/{nome}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByNome(@PathVariable String nome) {
        List<Avaliacao> avaliacoes = avaliacaoService.findAvaliacoesByLocalNome(nome);
        return ResponseEntity.ok(avaliacoes);
    }
}
