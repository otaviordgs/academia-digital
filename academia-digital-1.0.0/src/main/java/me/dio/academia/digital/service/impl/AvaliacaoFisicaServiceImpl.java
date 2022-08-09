package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {
    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisica avaliacaoFisica, Aluno aluno) {
        avaliacaoFisica.setDataDaAvaliacao(LocalDateTime.now());
        avaliacaoFisica.setId(null);
        avaliacaoFisica.setAluno(aluno);
        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        return avaliacaoFisicaRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Id "+id+" não encontrado"));
    }

    @Override
    public List<AvaliacaoFisica> getAvaliacaoByPeso(double peso) {
        return avaliacaoFisicaRepository.findByPesoLessThanEquals(peso);
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(AvaliacaoFisica avaliacaoFisica) {
        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica update(AvaliacaoFisicaUpdateForm updateForm, Long avaliacaoId) {
        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(avaliacaoId)
                .orElseThrow(() -> new NegocioException("Id "+avaliacaoId+" não encontrado"));
        avaliacaoFisica.setPeso(updateForm.getPeso());
        avaliacaoFisica.setAltura(updateForm.getAltura());
        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public void delete(Long id) {
        avaliacaoFisicaRepository.deleteById(id);
    }
}
