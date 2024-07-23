package com.beksons.service.serviceImpl;

import com.beksons.doa.HouseDao;
import com.beksons.doa.daoImpl.HouseDaoImpl;
import com.beksons.entities.House;
import com.beksons.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {

    HouseDao houseDao = new HouseDaoImpl();
    @Override
    public void createHouse(Long ownerId, House house) {
        houseDao.createHouse(ownerId,house);

    }

    @Override
    public String deleteHouse(Long houseId) {
        return houseDao.deleteHouse(houseId);
    }

    @Override
    public List<House> getHouseByRegion() {
        return houseDao.getHouseByRegion();
    }

    @Override
    public House getHouseByRegion(String region) {
        return null;
    }

    @Override
    public List<House> getHouseByAgencyId(Long id) {
        return null;
    }

    @Override
    public List<House> getHouseByOwnerId(Long ownerId) {
        return null;
    }
}
