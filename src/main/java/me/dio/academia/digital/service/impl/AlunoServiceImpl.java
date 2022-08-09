package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno create(Aluno aluno) {
        alunoRepository.save(aluno);
        return aluno;
    }

    @Override
    public List<Aluno> getByNome(String nome) {
        return alunoRepository.findByNomeContains(nome);
    }

    @Override
    public List<AvaliacaoFisica> getAvaliacoes(Long alunoId) {
        return alunoRepository.findAvaliacoe(alunoId);
    }

    public List<Aluno> getByDataNascimento(LocalDate date) {
        return alunoRepository.findAllByDataDeNascimento(date);
    }

    @Override
    public Optional<Aluno> get(Long id) {
        return alunoRepository.findById(id);
    }

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @Override
    public void update(AlunoUpdateForm aluno, Long id) {
        var alunoAntigo = this.get(id)
                .orElseThrow(() -> new NegocioException("O id do aluno não foi encontrado"));
        alunoAntigo.setBairro(aluno.getBairro());
        alunoRepository.save(alunoAntigo);
    }

    @Override
    public void delete(Long id) {
        var aluno = alunoRepository.findById(id);
        if (aluno.isEmpty())
            throw new NegocioException("O id não foi encontrado");
        alunoRepository.deleteById(id);
    }

    public List<Aluno> getAllByOrder(Sort sort){
        return alunoRepository.findAll(sort);
    };


}
