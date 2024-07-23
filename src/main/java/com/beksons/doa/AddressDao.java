package com.beksons.doa;

import com.beksons.entities.Address;
import com.beksons.entities.Agency;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AddressDao {
///system.out.println("hello world");
    Optional<Address> getAddressById(Long addressId);


    Map<Address,Agency> getAllAddressWithAgency();


    int getCountAgenciesByCity(String city);


    Map<String, List<Agency>> getAllRegionWithAgency();


    String updateAddress(Long oldAddressId, Address newAddress);



}
