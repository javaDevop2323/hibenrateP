package com.beksons.doa;

import com.beksons.entities.House;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HouseDao {

    void createHouse(Long ownerId, House house);

    String deleteHouse(Long houseId);

    List<House> getHouseByRegion();

    Optional<House> getHouseByRegion(String region);

    List<House> getHouseByAgencyId(Long id);

    List<House> getHouseByOwnerId(Long ownerId);


}
