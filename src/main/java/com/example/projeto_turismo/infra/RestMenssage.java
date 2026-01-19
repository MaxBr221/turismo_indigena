package com.example.projeto_turismo.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestMenssage {
    private HttpStatus status;
    private String message;
}
