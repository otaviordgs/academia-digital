package me.dio.academia.digital.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex){
        //criar um payload com os detalhes da exception
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(ex.getMessage(), status, LocalDateTime.now());
        return new ResponseEntity<>(exception, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var erros = new HashMap<String, String>();
        for(FieldError erro : ex.getBindingResult().getFieldErrors()){
            erros.put(erro.getField(), erro.getDefaultMessage());
        }
        return new ResponseEntity<>(erros, status);
    }
}
