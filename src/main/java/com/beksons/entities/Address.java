package com.beksons.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Address {
    @Id
    @GeneratedValue(generator = "address_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "address_gen",sequenceName = "address_seq",allocationSize = 1,initialValue = 5)
    private Long id;
    private String city;
    private String region;
    private String street;
    @OneToOne
    private Agency agency;


    @OneToOne
    private House house;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }
}
