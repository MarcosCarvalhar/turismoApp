package com.turismoapp.turismo_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turismoapp.turismo_app.model.Endereco;
import com.turismoapp.turismo_app.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public String enderecoAddUpdate(Endereco endereco, String acao) {

        if (acao.equals("cadastrar")) {
            new ResponseEntity<Endereco>(enderecoRepository.save(endereco), HttpStatus.CREATED);
            return String.valueOf(endereco.getId());

        } else {
            new ResponseEntity<Endereco>(enderecoRepository.save(endereco), HttpStatus.OK);
            return String.valueOf(endereco.getId());
        }
    }

    public Optional<Endereco> getEndereco(int idEndereco) {
        return enderecoRepository.findById(idEndereco);
    }

    public String deleteEndereco(String idEndereco) {
        enderecoRepository.deleteById(Integer.valueOf(idEndereco));
        return idEndereco;
    }

    public Iterable<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }
}
