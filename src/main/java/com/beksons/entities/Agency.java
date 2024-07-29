package com.beksons.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Agency {

    @Id
    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String phoneNumber;
    @ToString.Exclude
    @OneToOne( cascade = {
            PERSIST,
            MERGE,
            REMOVE}, optional = false)
    private Address address;
    @ToString.Exclude
    @ManyToMany(mappedBy = "agencies", cascade =
            {REFRESH,
                    MERGE,
                    REMOVE}, fetch = FetchType.LAZY)
    private List<Owner> owners;
    @ToString.Exclude
    @OneToMany(cascade = {
            MERGE
            , REMOVE,
            REFRESH})
    private List<RentInfo> rentInfos;

    public Agency(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
