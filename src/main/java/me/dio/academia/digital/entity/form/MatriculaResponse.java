package me.dio.academia.digital.entity.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatriculaResponse {
    String alunoNome;
    LocalDateTime dataDaMatricula;
}
