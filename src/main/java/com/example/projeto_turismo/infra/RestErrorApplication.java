package com.example.projeto_turismo.infra;

import com.example.projeto_turismo.exceptions.EventFullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestErrorApplication {

    @ExceptionHandler
    public ResponseEntity<RestMenssage> exception(EventFullException e){
        RestMenssage restMenssage = new RestMenssage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restMenssage);
    }
}
