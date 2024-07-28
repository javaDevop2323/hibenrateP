package com.beksons.service.serviceImpl;

import com.beksons.doa.HouseDao;
import com.beksons.doa.daoImpl.HouseDaoImpl;
import com.beksons.entities.House;
import com.beksons.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {

private final HouseDao houseDao = new HouseDaoImpl();
    @Override
    public void createHouse(Long ownerId, House house) {
        houseDao.createHouse(ownerId,house);

    }

    @Override
    public String deleteHouse(Long houseId) {
        return houseDao.deleteHouse(houseId);
    }

    @Override
    public List<House> getHouseByRegion(String region) {
        return houseDao.getAllHouseByRegion(region);
    }

    @Override
    public List<House> getHouseByAgencyId(Long id) {
        return houseDao.getHouseByAgencyId(id);
    }

    @Override
    public List<House> getHouseByOwnerId(Long ownerId) {
        return List.of();
    }
}
