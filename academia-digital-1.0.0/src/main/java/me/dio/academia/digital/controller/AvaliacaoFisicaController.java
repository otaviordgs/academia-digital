package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaResponse;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlunoServiceImpl alunoService;
    @Autowired
    private AvaliacaoFisicaServiceImpl avaliacaoFisicaService;


    @GetMapping()
    private List<AvaliacaoFisicaResponse> getAll(){
        return avaliacaoFisicaService.getAll().stream()
                .map(this::toAvaliacaoFisicaResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{avaliacaoId}")
    private AvaliacaoFisicaResponse getAvaliacaoById(@PathVariable Long avaliacaoId){
        var avaliacao = avaliacaoFisicaService.get(avaliacaoId);
        return toAvaliacaoFisicaResponse(avaliacao);
    }

    @GetMapping("/{alunoId}/")
    private List<AvaliacaoFisicaResponse> getAvaliacoesFromAluno(@PathVariable Long alunoId){
        Aluno aluno = alunoService.get(alunoId)
                .orElseThrow(()-> new NegocioException("Id do aluno não encontrado"));
        List<AvaliacaoFisica> avaliacoes = alunoService.getAvaliacoes(alunoId);
        return toAvaliacaoFisicaResponse(avaliacoes);
    }

    @GetMapping("/filtrar")
    public List<AvaliacaoFisicaResponse> getAvaliacaoByPeso(@RequestParam(value = "peso",required = false) double weight){
        List<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaService.getAvaliacaoByPeso(weight);
        return toAvaliacaoFisicaResponse(avaliacaoFisica);
    }


    @PostMapping
    private AvaliacaoFisica create(@RequestBody @Valid AvaliacaoFisicaForm avaliacaoFisicaForm){
        Aluno aluno = alunoService.get(avaliacaoFisicaForm.getAlunoId())
                .orElseThrow(()-> new NegocioException("Id do aluno não encontrado"));
        AvaliacaoFisica avaliacaoFisica = toAvaliacaoFisica(avaliacaoFisicaForm);
        return avaliacaoFisicaService.create(avaliacaoFisica, aluno);
    }

    @PutMapping("/{avaliacaoId}")
    private AvaliacaoFisicaResponse updateAvaliacao(@RequestBody @Valid AvaliacaoFisicaUpdateForm avaliacaoFisicaForm,
                                                @PathVariable Long avaliacaoId){
       AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaService.update(avaliacaoFisicaForm, avaliacaoId);
       return toAvaliacaoFisicaResponse(avaliacaoFisica);
    }

    @DeleteMapping("/{avaliacaoId}")
    private String deleteAvaliacao(@PathVariable Long avaliacaoId){
        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaService.get(avaliacaoId);
        avaliacaoFisicaService.delete(avaliacaoId);
        return "Deletado com sucesso";
    }

    private AvaliacaoFisica toAvaliacaoFisica(AvaliacaoFisicaForm fisicaForm){
        return modelMapper.map(fisicaForm, AvaliacaoFisica.class);
    }

    private AvaliacaoFisicaForm toAvaliacaoFisicaForm(AvaliacaoFisica avaliacaoFisica){
        return modelMapper.map(avaliacaoFisica, AvaliacaoFisicaForm.class);
    }

    private List<AvaliacaoFisicaForm> toAvaliacaoFisicaForm(List<AvaliacaoFisica> avaliacaoFisica){
        return avaliacaoFisica.stream()
                .map(this::toAvaliacaoFisicaForm)
                .collect(Collectors.toList());
    }

    private List<AvaliacaoFisicaResponse> toAvaliacaoFisicaResponse(List<AvaliacaoFisica> avaliacaoFisicas){
        return avaliacaoFisicas.stream()
                .map(this::toAvaliacaoFisicaResponse)
                .collect(Collectors.toList());
    }

    private AvaliacaoFisicaResponse toAvaliacaoFisicaResponse(AvaliacaoFisica avaliacaoFisica){
        return modelMapper.map(avaliacaoFisica, AvaliacaoFisicaResponse.class);
    }
}
