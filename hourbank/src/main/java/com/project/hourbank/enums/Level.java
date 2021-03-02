package com.project.hourbank.enums;

public enum Level {

    ADMIN("User Admin"),
    COMMON("User Common");


    private String description;

    Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
