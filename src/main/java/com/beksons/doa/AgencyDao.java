package com.beksons.doa;

import com.beksons.entities.Address;
import com.beksons.entities.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyDao {

    void saveAgency(Agency agency, Address address);


    List<Agency> getAllAgencies();


    Optional<Agency> getAgencyByID(Long id);


    String updateAgencyByID(Long id, Agency newAgency);

    void deleteAgency(Long id);


    String updateAgencyAddress(Long id, Address newAddress);

    List<Agency> getAgenciesByCity(String city);



}
