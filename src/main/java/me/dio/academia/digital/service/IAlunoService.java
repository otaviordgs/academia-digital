package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAlunoService {

  Aluno create(Aluno aluno);

  List<Aluno> getByNome(String nome);

  List<AvaliacaoFisica> getAvaliacoes(Long alunoId);

  public List<Aluno> getAllByOrder(Sort sort);

  public List<Aluno> getByDataNascimento(LocalDate date);

  Optional<Aluno> get(Long id);

  List<Aluno> getAll();

  /**
   * Atualiza o Aluno.
   * @param id - id do Aluno que será atualizado.
   * @param formUpdate - formulário referente aos dados necessários para atualização do Aluno
   * no banco de dados.
   * @return - Aluno recém-atualizado.
   */
  void update(AlunoUpdateForm formUpdate, Long id);

  void delete(Long id);
}
