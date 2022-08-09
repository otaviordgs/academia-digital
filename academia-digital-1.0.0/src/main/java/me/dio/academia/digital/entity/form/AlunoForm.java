package me.dio.academia.digital.entity.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoForm {

  @NotBlank
  private String nome;

  @CPF
  private String cpf;

  @NotBlank
  private String bairro;

  @NotNull
  private LocalDate dataDeNascimento;
}
