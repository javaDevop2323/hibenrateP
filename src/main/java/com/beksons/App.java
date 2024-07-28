package com.beksons;

import com.beksons.entities.*;
import com.beksons.enums.FamilyStatus;
import com.beksons.enums.Gender;
import com.beksons.enums.HouseType;
import com.beksons.service.*;
import com.beksons.service.serviceImpl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AgencyService agencyService = new AgencyServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();

        boolean isTrue = true;
        while (isTrue) {
            System.out.println("""
                    1.Agency
                    2.Address
                    3.Customer
                    4.House
                    5.Owner
                    6.RetInfo
                    """);
            String choice = new Scanner(System.in).nextLine();
            switch (choice) {
                case "1", "First" -> {
                    boolean agencyExit = true;
                    while (agencyExit) {
                        System.out.println("""
                                1.saveAgency
                                2.getAllAgencies
                                3.getAgencyByID
                                4.updateAgencyByID
                                5.deleteAgency
                                6.Exit
                                """);
                        try {
                            switch (new Scanner(System.in).nextInt()) {
                                case 1 -> {
                                    Address address = Address.builder()
                                            .city("Bishkek")
                                            .region("osh")
                                            .street("grajadnskaya")
                                            .build();
                                    Agency agency = Agency.builder()
                                            .name("ds")
                                            .phoneNumber("+996121231111")
                                            .address(address)
                                            .build();
                                    agencyService.saveAgency(agency, address);
                                }
                                case 2 -> {
                                    System.out.println(agencyService.getAllAgencies());
                                }
                                case 3 -> {
                                    System.out.println(agencyService.getAgencyByID(new Scanner(System.in).nextLong()));
                                }
                                case 4 -> {
                                    System.out.println(agencyService.updateAgencyByID(1L, new Agency("sultanIsaevs", "931231")));
                                }
                                case 5 -> {
                                    agencyService.deleteAgency(4L);
                                }
                                case 6 -> {
                                    agencyExit = false;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("e.getMessage() = " + e.getMessage());
                        }
                    }
                }
                case "2" -> {
                    boolean addressExit = true;
                    while (addressExit) {
                        System.out.println("""
                                1.getAddressById
                                2.getAllAddressWithAgency
                                3.getCountAgenciesByCity
                                4.getAllRegionWithAgency
                                5.updateAddress
                                6.Exit
                                """);
                        try {
                            switch (new Scanner(System.in).nextInt()) {
                                case 1 -> {
                                    System.out.println(addressService.getAddressById(3L));
                                }
                                case 2 -> {
                                    System.out.println(addressService.getAllAddressWithAgency());
                                }
                                case 3 -> {
                                    System.out.println(addressService.getCountAgenciesByCity("Bishkek"));
                                }
                                case 4 -> {
                                    System.out.println(addressService.getAllRegionWithAgency());
                                }
                                case 5 -> {
                                    System.out.println(addressService.updateAddress(3L, new Address("London", "Us", "gfg")));
                                }
                                case 6 -> {
                                    addressExit = false;
                                }

                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case "3" -> {
                    boolean customerExit = true;
                    while (customerExit) {
                        System.out.println("""
                                1.saveCustomer
                                2.saveCustomerWithRentInfo
                                3.assignRentInfoToCustomer
                                4.getAllCustomers
                                5.getCustomerById
                                6.updateCustomer
                                7.deleteCustomer
                                8.Exit
                                """);
                        switch (new Scanner(System.in).nextInt()) {
                            case 1 -> {
                                System.out.println(customerService.saveCustomer(new Customer("Jomart", "Isenbaev", "jomart@gamil.com", LocalDate.of(2003, 2, 2), Gender.MALE, "Kg", FamilyStatus.SINGLE)));
                            }
                            case 2 -> {
                                Customer customer = Customer.builder()
                                        .firstName("Sultan")
                                        .lastName("Isaev")
                                        .email("sultan@gmail.com")
                                        .nationality("Kg")
                                        .familyStatus(FamilyStatus.SINGLE)
                                        .gender(Gender.MALE)
                                        .build();
                                System.out.println(customerService.saveCustomerWithRentInfo(customer, 1L, 1L, LocalDate.of(2020, 2, 2), LocalDate.of(2024, 2, 2)));
                            }
                            case 3 -> {
                                customerService.assignRentInfoToCustomer(new RentInfo(), 1L, 1L, 1L);
                            }
                            case 4 -> {
                                System.out.println(customerService.getAllCustomers());
                            }
                            case 5 -> {
                                System.out.println(customerService.getCustomerById(1L));
                            }
                            case 6 -> {
                                customerExit = false;
                            }
                        }
                    }
                }
                case "4" -> {
                    boolean houseExit = true;
                    while (houseExit) {
                        System.out.println("""
                                1.createHouse
                                2.deleteHouse
                                3.getHouseByRegion
                                4.getHouseByAgencyId
                                5.getHouseByOwnerId
                                6.Exit
                                """);
                        switch (new Scanner(System.in).nextInt()) {
                            case 1 -> {
                                House house = House.builder()
                                        .houseType(HouseType.APARTMENT)
                                        .price(BigDecimal.valueOf(500000000))
                                        .description("good house")
                                        .rating(5.2)
                                        .room(3)
                                        .furniture(true)
                                        .build();
                                houseService.createHouse(1L, house);
                            }
                            case 2 -> {
                                System.out.println(houseService.deleteHouse(1L));
                            }
                            case 3 -> {
                                houseService.getHouseByRegion("reg");
                            }
                            case 4 -> {
                                System.out.println(houseService.getHouseByAgencyId(1L));
                            }
                            case 5 -> {
                                System.out.println(houseService.getHouseByOwnerId(1L));
                            }
                            case 6 -> {
                                houseExit = false;
                            }
                        }
                    }
                }
                case "5" -> {
                    boolean exitOwner = true;
                    while (exitOwner) {
                        System.out.println("""
                                  1.saveOwner
                                  2.saveOwnerWithHouse
                                  3.assignOwnerToAgency
                                  4.getOwnersByAgencyID
                                  5.deleteOwnerByID
                                  6.getOwnersByAgencyByID
                                  7.getOwnerAgeAndName
                                8.Exit
                                  """);
                        switch (new Scanner(System.in).nextInt()) {
                            case 1 -> {
                                Owner owner = null;
                                 owner = new Owner("joomart", "Ishenbaev", "jomart@gmail.com",LocalDate.of(2003, 3, 3), Gender.MALE);
                                 ownerService.saveOwner(owner);

                            }
                            case 2 -> {
                                Owner owner = null;
                                 owner = new Owner("joomart", "Ishenbaev", "jomart@gmail.com", owner.getFirstName()+' '+ owner.getLastName(),LocalDate.of(2003, 3, 3), Gender.MALE);
                                House house = new House(HouseType.DETACHED, BigDecimal.valueOf(6000), 5.2, "good house", 2, false);
                                System.out.println(ownerService.saveOwnerWithHouse(owner, house));
                            }
                            case 3 -> {
                                System.out.println(ownerService.assignOwnerToAgency(1L, 2L));

                            }case 4->{
                                System.out.println(ownerService.getOwnersByAgencyId(2L));
                            }case 5->{
                                System.out.println(ownerService.getAllOwners());
                            }case 6->{

                            }
                        }
                    }

                }
            }
        }
    }
}
