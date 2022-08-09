package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContains(String nome);
    List<Aluno> findAllByDataDeNascimento(LocalDate dataDeNascimento);

    @Query("SELECT f from AvaliacaoFisica f JOIN FETCH f.aluno where f.aluno.id = :id")
    List<AvaliacaoFisica> findAvaliacoe(@Param("id") Long alunoId);

    @Override
    List<Aluno> findAll(Sort sort);
}
