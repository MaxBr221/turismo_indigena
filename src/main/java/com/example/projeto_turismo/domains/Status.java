package com.example.projeto_turismo.domains;

public enum Status {
    AGENDADO("agendado"), CANCELADO("cancelado"), DISPONIVEL("disponivel");
    private String status;

    Status(String status) {
        this.status = status;
    }
    String getStatus(String status){
        return this.status;
    }
}
