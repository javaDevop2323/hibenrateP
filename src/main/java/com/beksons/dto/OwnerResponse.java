package com.beksons.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Data
@NoArgsConstructor
@Getter
@Setter
public class OwnerResponse {
    @Formula("concat(first_name, ' ', last_name)")
    private int fullName;
    @Formula("year(current_date) - year(date_of_birth)")
    private int age;

    public OwnerResponse(int fullNmae, int age) {
        this.fullName = fullNmae;
        this.age = age;
    }
}


