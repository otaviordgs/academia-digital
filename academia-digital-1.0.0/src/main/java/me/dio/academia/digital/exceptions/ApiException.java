package me.dio.academia.digital.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiException {
    private final String messagem;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;


    public ApiException(String messagem, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.messagem = messagem;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }
}
