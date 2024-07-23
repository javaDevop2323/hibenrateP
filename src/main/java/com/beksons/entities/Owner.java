package com.beksons.entities;

import com.beksons.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
@Id
    @GeneratedValue(generator = "owner_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "owner_gen",sequenceName = "owner_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;

    @ManyToMany
    private List<Agency> agencies;

    @OneToMany(mappedBy = "owner")
    private List<House>houses;
    @ManyToOne
    private RentInfo rentInfo;






}
