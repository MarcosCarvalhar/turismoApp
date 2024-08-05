package com.turismoapp.turismo_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turismoapp.turismo_app.model.Avaliacao;
import com.turismoapp.turismo_app.model.Local;
import com.turismoapp.turismo_app.model.Usuario;
import com.turismoapp.turismo_app.repository.AvaliacaoRepository;
import com.turismoapp.turismo_app.repository.LocalRepository;
import com.turismoapp.turismo_app.repository.UsuarioRepository;


@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalUpdateService localUpdateService;

    public String avaliacaoAddUpdate(Avaliacao avaliacao, String acao) {

        List<Avaliacao> existente = avaliacaoRepository.findByUsuarioIdAndLocalId(
            Long.valueOf(avaliacao.getUsuario().getId()), Long.valueOf(avaliacao.getLocal().getId()));


        if (acao.equals("cadastrar") && !existente.isEmpty()) {
    
            new RuntimeException("O usuário já fez uma avaliação para este local.");  
        } 
        
        if (acao.equals("alterar") && existente.isEmpty()) {

            new RuntimeException("Avaliação não encontrada.");
        } 

        int localId = avaliacao.getLocal().getId();
        int usuarioId = avaliacao.getUsuario().getId();

        // Buscar o Local e o Usuario existentes
        Local local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));
        local.setLocalUpdateService(localUpdateService);

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Setar o Local e o Usuario persistidos na Avaliacao
        avaliacao.setLocal(local);
        avaliacao.setUsuario(usuario);

        if (acao.equals("cadastrar")) {
            avaliacao.addObserver(local);
            new ResponseEntity<Avaliacao>(avaliacaoRepository.save(avaliacao), HttpStatus.CREATED);
            avaliacao.notifyObservers();
        } 

        if (acao.equals("alterar")){
            avaliacao.addObserver(local);
            new ResponseEntity<Avaliacao>(avaliacaoRepository.save(avaliacao), HttpStatus.OK);
            avaliacao.notifyObservers();
        }

        return String.valueOf(avaliacao.getId());

    }

    public Optional<Avaliacao> getAvaliacao(int idAvaliacao) {
        return avaliacaoRepository.findById(idAvaliacao);
    }

    public String deleteAvaliacao(String idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(Integer.valueOf(idAvaliacao))
            .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        Local local = avaliacao.getLocal();

        // Configurar o serviço de atualização no Local
        local.setLocalUpdateService(localUpdateService);

        // Adicionar observadores
        avaliacao.addObserver(local);

        // Remover a avaliação
        avaliacaoRepository.delete(avaliacao);

        // Notificar observadores após a remoção
        avaliacao.notifyObservers();

        return idAvaliacao;
    }

    public Iterable<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Iterable<Avaliacao> listarAvaliacoesPorLocal(String localId) {
        return avaliacaoRepository.findByLocalId(Long.parseLong(localId));
    }

    public List<Local> getTopLocaisPorNotaMedia() {
        List<Object[]> resultados = avaliacaoRepository.findTopLocaisPorNotaMedia(PageRequest.of(0, 3));
        return resultados.stream()
                .map(result -> (Local) result[0])
                .collect(Collectors.toList());
    }

    public List<Avaliacao> findAvaliacoesByLocalNome(String nome) {
        return avaliacaoRepository.findByLocalNomeContaining(nome);
    }

    public Iterable<Avaliacao> listarAvaliacoesPorUsuario(String usuarioId) {
        return avaliacaoRepository.findByUsuarioId(Long.parseLong(usuarioId));
    } 

}
