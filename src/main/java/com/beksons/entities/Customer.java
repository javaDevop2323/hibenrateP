package com.beksons.entities;

import com.beksons.enums.FamilyStatus;
import com.beksons.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDeletes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
@Id
    @GeneratedValue(generator = "customer_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private  String email;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String nationality;
    private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer")
    public List<RentInfo>rentInfos;
}
