package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {
    @Query("select avaliacao from AvaliacaoFisica  avaliacao where avaliacao.peso <= :peso")
    List<AvaliacaoFisica> findByPesoLessThanEquals(@Param("peso") double peso);
}
