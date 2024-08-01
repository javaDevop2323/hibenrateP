//package com.beksons.entities;
//
//import com.beksons.enums.PaymentStatus;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Date;
//
//@Entity(name = "contract_entity")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Contract {
//    @Id
//    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
//    private Long id;
//    private Date startDate;
//    private Date endDate;
//    private String termsAndConditions;
//    private BigDecimal paymentAmount;
//    private PaymentStatus paymentStatus;
//
//
//}
