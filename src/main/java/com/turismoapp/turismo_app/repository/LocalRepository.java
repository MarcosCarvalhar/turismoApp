package com.turismoapp.turismo_app.repository;

import com.turismoapp.turismo_app.model.Local;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends CrudRepository<Local, Integer>  {
    List<Local> findByCategoriaId(Long idCategoria);

    @Query("SELECT l FROM Local l JOIN l.endereco e WHERE e.estado = :estado")
    List<Local> findByEstado(@Param("estado") String estado);
}
