package com.beksons.service.serviceImpl;

import com.beksons.doa.AddressDao;
import com.beksons.doa.daoImpl.AddressDaoImpl;
import com.beksons.entities.Address;
import com.beksons.entities.Agency;
import com.beksons.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
   final AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Address getAddressAndAgency(Long id) {
        return null;
    }

    @Override
    public int countAgency(String word) {
        return addressDao.countAgency(word);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return null;
    }
}
