package com.turismoapp.turismo_app.repository;

import com.turismoapp.turismo_app.model.Endereco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
    
}
