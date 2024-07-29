package com.beksons.service.serviceImpl;

import com.beksons.doa.OwnerDao;
import com.beksons.doa.daoImpl.OwnerDaoImpl;
import com.beksons.entities.House;
import com.beksons.entities.Owner;
import com.beksons.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public String saveOwner(Owner owner) {
        return ownerDao.saveOwner(owner);
    }

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {
        return ownerDao.saveOwnerWithHouse(owner,house);
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return ownerDao.assignOwnerToAgency(ownerId,agencyId);
    }

    @Override
    public String deleteOwner(Long ownerId) {
        return ownerDao.deleteOwner(ownerId);
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        return ownerDao.getOwnersByAgencyId(agencyId);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.getAllOwners();
    }

    @Override
    public String updateOwner(Long ownerId, Owner newOwner) {
        return ownerDao.updateOwner(ownerId,newOwner);
    }
}
