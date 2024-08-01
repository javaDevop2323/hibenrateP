package com.beksons.doa;

import com.beksons.entities.House;
import com.beksons.entities.Owner;

import java.util.List;

public interface OwnerDao {
    //    CRUD

    String saveOwner(Owner owner);


    String saveOwnerWithHouse(Owner owner, House house);


    String assignOwnerToAgency(Long ownerId, Long agencyId);


    String deleteOwner(Long ownerId);


     List<Owner> getOwnersByAgencyId(Long agencyId);


    List<Owner> getAllOwners();


    String updateOwner(Long ownerId, Owner newOwner);

    List<Owner>sortByName(String ascOrDesc);





}
