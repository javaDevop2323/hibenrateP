package com.beksons.service.serviceImpl;

import com.beksons.doa.AddressDao;
import com.beksons.doa.daoImpl.AddressDaoImpl;
import com.beksons.entities.Address;
import com.beksons.entities.Agency;
import com.beksons.service.AddressService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao = new AddressDaoImpl();

    @Override
    public Optional<Address> getAddressById(Long addressId) {
        return addressDao.getAddressById(addressId);
    }

    @Override
    public Map<Address, Agency> getAllAddressWithAgency() {
        return addressDao.getAllAddressWithAgency();
    }

    @Override
    public int getCountAgenciesByCity(String city) {
        return addressDao.getCountAgenciesByCity(city);
    }

    @Override
    public Map<String, List<Agency>> getAllRegionWithAgency() {
        return addressDao.getAllRegionWithAgency();
    }

    @Override
    public String updateAddress(Long oldAddressId, Address newAddress) {
        return addressDao.updateAddress(oldAddressId,newAddress);
    }
}