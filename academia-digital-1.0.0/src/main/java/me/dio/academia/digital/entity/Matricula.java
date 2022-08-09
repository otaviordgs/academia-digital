package me.dio.academia.digital.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_matriculas")
public class Matricula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Aluno aluno;

  private LocalDateTime dataDaMatricula = LocalDateTime.now();

  public Matricula(Aluno aluno) {
    this.aluno = aluno;
  }
}
