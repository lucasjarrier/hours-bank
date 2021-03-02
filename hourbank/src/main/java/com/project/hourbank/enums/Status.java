package com.project.hourbank.enums;

public enum Status {

    PENDING("Protocolo em andamento."),
    CLOSED("Protocolo fechado."),
    EXPIRED("Protocolo Expirado.");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
