package me.dio.academia.digital.controller;


import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.entity.form.MatriculaResponse;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private AlunoServiceImpl alunoService;
    @Autowired
    private MatriculaServiceImpl matriculaService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    private List<MatriculaResponse> getMatriculaByNome(@RequestParam String nome){
        List<Matricula> matriculas = matriculaService.findByNome(nome);
        return toMatriculaResponse(matriculas);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse create(@RequestBody MatriculaForm matriculaForm){
        Aluno aluno = alunoService.get(matriculaForm.getAlunoId())
                .orElseThrow(() -> new NegocioException("O id do aluno nao foi encontrado"));
        Matricula matricula = matriculaService.create(aluno);
        MatriculaResponse matriculaResponse = toMatriculaResponse(matricula);
        return matriculaResponse;
    }

    private MatriculaResponse toMatriculaResponse(Matricula matricula){
        return modelMapper.map(matricula, MatriculaResponse.class);
    }

    private List<MatriculaResponse> toMatriculaResponse(List<Matricula> matriculas){
        return matriculas.stream()
                .map(this::toMatriculaResponse)
                .collect(Collectors.toList());
    }
}
