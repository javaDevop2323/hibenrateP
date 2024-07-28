package com.beksons.dto;

import lombok.Data;

@Data
public class OwnerResponse {
    private int age;
    private String fullName;

    public OwnerResponse(int age, String fullName) {
        this.age = age;
        this.fullName = fullName;
    }
}

