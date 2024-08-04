package com.turismoapp.turismo_app.repository;

import com.turismoapp.turismo_app.model.Usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
