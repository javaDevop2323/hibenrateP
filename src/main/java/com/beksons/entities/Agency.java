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
@ToString(exclude = "address")
public class Agency {

    @Id
    @GeneratedValue(generator = "agency_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen",sequenceName = "agency_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String phoneNumber;
    @OneToOne(mappedBy = "agency",cascade = {PERSIST,DETACH,REMOVE})
    private Address address;
    @ManyToMany(mappedBy = "agencies",fetch = FetchType.EAGER,cascade = {REFRESH,DETACH,MERGE})
    private List<Owner>owners;
    @OneToMany(fetch = FetchType.EAGER)
    private List<RentInfo>rentInfos;

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
