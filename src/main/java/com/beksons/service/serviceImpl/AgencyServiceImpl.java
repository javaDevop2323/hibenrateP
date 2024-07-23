package com.beksons.service.serviceImpl;

import com.beksons.doa.AgencyDao;
import com.beksons.doa.daoImpl.AgencyDaoImpl;
import com.beksons.entities.Address;
import com.beksons.entities.Agency;
import com.beksons.service.AgencyService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    final AgencyDao agencyDao  = new AgencyDaoImpl();

    @Override
    public void saveAgency(Agency agency, Address address) {
        agencyDao.saveAgency(agency,address);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyDao.getAllAgencies();
    }

    @Override
    public Agency getAgencyByID(Long id) {
        return agencyDao.getAgencyByID(id).orElseThrow(()-> new  RuntimeException("not found"));
    }

    @Override
    public String updateAgencyByID(Long id, Agency newAgency) {
        return agencyDao.updateAgencyByID(id,newAgency);
    }

    @Override
    public void deleteAgency(Long id) {
        agencyDao.deleteAgency(id);

    }
}
