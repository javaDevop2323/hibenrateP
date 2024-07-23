package com.beksons.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.crypto.Mac;
import java.time.LocalDate;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "rentInfo")
    private List<Owner> owners;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
