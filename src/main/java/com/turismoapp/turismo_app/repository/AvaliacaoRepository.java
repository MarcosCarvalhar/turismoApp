package com.turismoapp.turismo_app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turismoapp.turismo_app.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Integer> {
    
    List<Avaliacao> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);

    List<Avaliacao> findByLocalId(Long localId);

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.local.id = :localId")
    long countByLocalId(@Param("localId") Long localId);

    @Query("SELECT SUM(a.nota) FROM Avaliacao a WHERE a.local.id = :localId")
    double sumNotasByLocalId(@Param("localId") Long localId);

    @Query("SELECT a.local, AVG(a.nota) as media FROM Avaliacao a GROUP BY a.local ORDER BY media DESC")
    List<Object[]> findTopLocaisPorNotaMedia(Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.local.nome LIKE %:nome%")
    List<Avaliacao> findByLocalNomeContaining(@Param("nome") String nome);
    
}
