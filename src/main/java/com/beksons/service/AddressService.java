package com.beksons.service;

import com.beksons.entities.Address;
import com.beksons.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressService {
    Address getAddressAndAgency(Long id);

    int  countAgency(String word);

    Map<String, List<Agency>> groupByRegion();
}
