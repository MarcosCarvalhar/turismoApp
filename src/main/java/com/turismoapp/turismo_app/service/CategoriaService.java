package com.turismoapp.turismo_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turismoapp.turismo_app.model.Categoria;
import com.turismoapp.turismo_app.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public String categoriaAddUpdate(Categoria categoria, String acao) {

        if (acao.equals("cadastrar")) {
            new ResponseEntity<Categoria>(categoriaRepository.save(categoria), HttpStatus.CREATED);
            return String.valueOf(categoria.getId());

        } else {
            new ResponseEntity<Categoria>(categoriaRepository.save(categoria), HttpStatus.OK);
            return String.valueOf(categoria.getId());
        }
    }

    public Optional<Categoria> getCategoria(int idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }
    
    public String deleteCategoria(String idCategoria) {
        categoriaRepository.deleteById(Integer.valueOf(idCategoria));
        return idCategoria;
    }

    public Iterable<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }
}
