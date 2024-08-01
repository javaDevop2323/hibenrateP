package com.beksons.entities;

import com.beksons.enums.HouseType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.*;

@Entity(name = "house_entity")
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House {
    @Id
    @GeneratedValue(generator = "house_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    @Lob
    private String description;
    private int room;
    private boolean furniture;
    @ManyToOne(cascade = {
            PERSIST,
            MERGE,
            REFRESH,
            DETACH})
    private Owner owner;
    @OneToOne(cascade =
            {REMOVE,
            MERGE,
            REFRESH})
    private Address address;



    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture, RentInfo rentInfo) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;

    }

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture, Address address, RentInfo rentInfo) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
        this.address = address;
    }

}