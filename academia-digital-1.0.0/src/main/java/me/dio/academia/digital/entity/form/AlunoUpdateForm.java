package me.dio.academia.digital.entity.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoUpdateForm {

  @NotBlank
  private String bairro;
}
