package com.beksons.enums;

public enum HouseType {

    DETACHED("Detached House"),
    SEMI_DETACHED("Semi-Detached House"),
    TERRACED("Terraced House"),
    BUNGALOW("Bungalow"),
    APARTMENT("Apartment"),
    COTTAGE("Cottage"),
    VILLA("Villa");

    private final String description;

    HouseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

