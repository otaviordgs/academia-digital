package me.dio.academia.digital.entity.form;


import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
public class AvaliacaoFisicaUpdateForm {

  @Min(1)
  private double peso;

  @Positive
  private double altura;
}
