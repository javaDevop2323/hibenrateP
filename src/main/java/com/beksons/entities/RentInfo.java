package com.beksons.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.crypto.Mac;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "rent_info_entity")
@Table()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentInfo {
    @Id
    @GeneratedValue(generator = "rent_info_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rent_info_gen", sequenceName = "rent_info_seq", allocationSize = 1)
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private Agency agency;

    @OneToOne
    private House house;

}
