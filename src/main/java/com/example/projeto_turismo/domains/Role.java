package com.example.projeto_turismo.domains;

import com.example.projeto_turismo.exceptions.EventFullException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    USER("user"), ADMIN("admin"), GUIDE("guide");
    private String role;

    Role (String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
    @JsonCreator
    public static String converter(String role){
        for (Role r: values()){
            if (r.role.equalsIgnoreCase(role)){
                return role;
            }
        }throw new EventFullException("Role não indetificado");
    }
}
