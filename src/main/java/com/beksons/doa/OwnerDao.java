package com.beksons.doa;

import com.beksons.entities.House;
import com.beksons.entities.Owner;

import java.util.List;

public interface OwnerDao {
//    CRUD
    void saveOwner(Owner owner);

    void saveOwnerAndHouse(Owner owner , House house);

    String assignOwnerToAgency(Long ownerId,Long agencyId);

    List<Owner>  getOwnersByAgencyID(Long agencyID);


    String deleteOwnerByID(Long ownerID);

    List<Owner> getOwnersByAgencyByID(Long agencyId);

    List<Owner> getOwnerAgeAndName();








}
