package com.beksons.doa;

import com.beksons.entities.House;

import java.util.List;

public interface HouseDao {

    void createHouse(Long ownerId, House house);

    String deleteHouse(Long houseId);

    List<House> getAllHouseByRegion(String region);

    List<House> getHouseByAgencyId(Long id);

    List<House> getHouseByOwnerId(Long ownerId);


}
