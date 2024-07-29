package com.beksons.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Gender {
    @Enumerated(EnumType.STRING)
    MALE,
    @Enumerated(EnumType.STRING)
    FEMALE;
}
