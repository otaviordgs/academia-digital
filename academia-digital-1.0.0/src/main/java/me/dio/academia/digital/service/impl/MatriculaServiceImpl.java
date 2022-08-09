package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.IMatriculaService;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MatriculaServiceImpl implements IMatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoServiceImpl alunoService;

    @Override
    public Matricula create(Aluno aluno) {
        Matricula matricula = new Matricula(aluno);
        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> findByNome(String nome) {
        return matriculaRepository.findByAlunoNomeContaining(nome);
    }

    @Override
    public Matricula get(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Matricula nao foi encontrada"));
    }

    @Override
    public List<Matricula> getAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Matricula matricula = this.get(id);
        matriculaRepository.delete(matricula);
    }
}
