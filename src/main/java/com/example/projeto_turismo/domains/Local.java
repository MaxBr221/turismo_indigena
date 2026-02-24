package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.exceptions.EventFullException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Local {
    ALDEIA("aldeia"), PRAIA("praia"), RIO("rio"), CENTRO("centro");
    private String local;

    Local(String local) {
        this.local = local;
    }
    public String getLocal(){
        return local;
    }

    @JsonCreator
    public static String converte(String local){
        for (Local l: values()){
            if(l.local.equalsIgnoreCase(local)){
                return local;
            }
        }
        throw new EventFullException("Local inválido: " + local);
    }

}
