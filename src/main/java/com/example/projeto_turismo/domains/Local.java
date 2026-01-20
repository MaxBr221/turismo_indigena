package com.example.projeto_turismo.domains;

public enum Local {
    ALDEIA("aldeia"), PRAIA("praia"), RIO("rio"), CENTRO("centro");
    private String local;

    Local(String local) {
        this.local = local;
    }
    public String getLocal(){
        return local;
    }

}
