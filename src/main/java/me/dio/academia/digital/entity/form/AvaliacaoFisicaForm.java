package me.dio.academia.digital.entity.form;

import lombok.Getter;
import lombok.Setter;
import me.dio.academia.digital.entity.Aluno;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class AvaliacaoFisicaForm {

  @NotNull
  private Long alunoId;

  @Min(1)
  private double peso;

  @Positive
  private double altura;
}
