package com.beksons.entities;

import com.beksons.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Entity(name = "owner_entity")
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
    @Size(min = 5,max = 10)
    private String firstName;
    @ToString.Exclude
    @Column(name = "last_name")
    @Size(min = 5,max = 10)
    private String lastName;
    @Email(message = "Invalid email address")
    @Column(name = "user_name")
    private String email;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @ToString.Exclude
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH})
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

    public Owner(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;


    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
}}


