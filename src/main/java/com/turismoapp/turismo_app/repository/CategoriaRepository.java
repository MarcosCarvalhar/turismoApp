package com.turismoapp.turismo_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turismoapp.turismo_app.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    
}
