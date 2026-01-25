package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.exceptions.EventFullException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    AGENDADO("agendado"), CANCELADO("cancelado"), DISPONIVEL("disponivel");
    private String status;

    Status(String status) {
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    @JsonCreator
    public static Status converte(String status){
        for (Status s: values()){
            if(s.getStatus().equalsIgnoreCase(status)){
                return s;
            }
        }
        throw new EventFullException("Local inválido: " + status);
    }
}
