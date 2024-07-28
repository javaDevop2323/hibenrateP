package com.beksons.entities;

import com.beksons.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Owner {

    @Id
    @GeneratedValue(generator = "owner_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "owner_gen", sequenceName = "owner_seq", allocationSize = 1, initialValue = 3)
    private Long id;
    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;
    @ToString.Exclude
    @Column(name = "first_name")
    private String firstName;
    @ToString.Exclude
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;


    @ToString.Exclude
    @ManyToMany()
    private List<Agency> agencies;
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = {
            PERSIST,
            REFRESH,
            DETACH})
    private List<House> houses;
    @ToString.Exclude
    @OneToMany(mappedBy = "owner",cascade = {REFRESH,DETACH,MERGE})
    private List<RentInfo> rentInfos;

    public Owner(String firstName, String lastName, String email, String fullName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Owner(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}


