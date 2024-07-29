package com.beksons.service;

import com.beksons.entities.House;
import com.beksons.entities.Owner;

import java.util.List;

public interface OwnerService {
    String saveOwner(Owner owner);


    String saveOwnerWithHouse(Owner owner, House house);


    String assignOwnerToAgency(Long ownerId, Long agencyId);


    String deleteOwner(Long ownerId);


     List<Owner> getOwnersByAgencyId(Long agencyId);


    List<Owner> getAllOwners();


    String updateOwner(Long ownerId, Owner newOwner);


}
