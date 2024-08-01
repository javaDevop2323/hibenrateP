package com.beksons.entities;

import com.beksons.enums.FamilyStatus;
import com.beksons.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDeletes;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity(name = "customer_entity")
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer {
    @Id
    @GeneratedValue(generator = "customer_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;
    @ToString.Exclude
    @Size(min = 5,max = 10)
    @Column(name = "first_name")
    private String firstName;
    @ToString.Exclude
    @Size(min = 5,max = 15)
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade =
            {REFRESH, DETACH})
    public List<RentInfo> rentInfos;

    public Customer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;

    }


}
