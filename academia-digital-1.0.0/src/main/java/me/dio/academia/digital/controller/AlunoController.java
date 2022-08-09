package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exceptions.NegocioException;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoService ;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public List<AlunoForm> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<Aluno> alunos = alunoService.getAllByOrder(sort);
        return toAlunoForm(alunos);
    }

    @GetMapping("/{alunoId}")
    public AlunoForm getAlunoById(@PathVariable Long alunoId){
        return alunoService.get(alunoId)
                .map(this::toAlunoForm)
                .orElseThrow(() -> new NegocioException("O id do aluno não foi encontrado"));
    }

    @GetMapping("/filtros")
    public List<AlunoForm> getAlunoByNome(@RequestParam(value = "nome", required = false) String nome,
                                      @RequestParam(value = "nascimento", required = false)LocalDate nascimento){
        if(nome != null)
            return toAlunoForm(alunoService.getByNome(nome));
        return toAlunoForm(alunoService.getByDataNascimento(nascimento));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoForm create(@RequestBody @Valid AlunoForm alunoForm){
        Aluno aluno = toAluno(alunoForm);
        return toAlunoForm(alunoService.create(aluno));
    }

    @PutMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody @Valid AlunoUpdateForm alunoUpdateForm, @PathVariable Long alunoId){
        alunoService.update(alunoUpdateForm, alunoId);
    }

    @DeleteMapping("/{alunoId}")
    public void delete(@PathVariable Long alunoId){
        alunoService.delete(alunoId);
    }

    private AlunoForm toAlunoForm(Aluno aluno){
        return modelMapper.map(aluno, AlunoForm.class);
    }

    private List<AlunoForm> toAlunoForm(List<Aluno> alunos){
        return alunos.stream()
                .map(this::toAlunoForm)
                .collect(Collectors.toList());
    }

    private Aluno toAluno(AlunoForm alunoForm){
        return modelMapper.map(alunoForm, Aluno.class);
    }

    private Aluno toAluno(AlunoUpdateForm alunoUpdateForm){
        return modelMapper.map(alunoUpdateForm, Aluno.class);
    }
}
