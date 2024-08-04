package com.turismoapp.turismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turismoapp.turismo_app.model.Local;
import com.turismoapp.turismo_app.repository.LocalRepository;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public String localAddUpdate(Local local, String acao) {

        if (acao.equals("cadastrar")) {
            new ResponseEntity<Local>(localRepository.save(local), HttpStatus.CREATED);
            return String.valueOf(local.getId());

        } else {
            new ResponseEntity<Local>(localRepository.save(local), HttpStatus.OK);
            return String.valueOf(local.getId());
        }
    }

    public Optional<Local> getLocal(int idLocal) {
        return localRepository.findById(idLocal);
    }

    public String deleteLocal(String idLocal) {
        localRepository.deleteById(Integer.valueOf(idLocal));
        return idLocal;
    }

    public Iterable<Local> getAllLocais() {
        return localRepository.findAll();
    }

    public List<Local> getLocaisPorCategoriaId(Long categoriaId) {
        return localRepository.findByCategoriaId(categoriaId);
    }

    public List<Local> getLocaisPorEstado(String estado) {
        return localRepository.findByEstado(estado);
    }
}
