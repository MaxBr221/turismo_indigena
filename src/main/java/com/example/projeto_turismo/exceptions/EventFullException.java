package com.example.projeto_turismo.exceptions;

public class EventFullException extends RuntimeException{
    public EventFullException (){
        super("evento já está cheio");

    }
    public EventFullException (String messagem){
        super(messagem);
    }
}
