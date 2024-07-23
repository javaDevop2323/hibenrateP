package com.beksons.service;

import com.beksons.entities.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {

    void createHouse(Long ownerId, House house);

    String deleteHouse(Long houseId);

    List<House> getHouseByRegion();

    House getHouseByRegion(String region);

    List<House> getHouseByAgencyId(Long id);

    List<House> getHouseByOwnerId(Long ownerId);


}
