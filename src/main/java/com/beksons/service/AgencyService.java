package com.beksons.service;

import com.beksons.entities.Address;
import com.beksons.entities.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    void saveAgency(Agency agency, Address address);

    List<Agency> getAllAgencies();

   Agency getAgencyByID(Long id);

    String updateAgencyByID(Long id,Agency newAgency);

    void deleteAgency(Long id);

}
