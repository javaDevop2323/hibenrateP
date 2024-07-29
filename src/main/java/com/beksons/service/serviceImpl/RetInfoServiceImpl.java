package com.beksons.service.serviceImpl;

import com.beksons.doa.RetInfoDao;
import com.beksons.doa.daoImpl.RetInfoDaoImplImpl;
import com.beksons.entities.RentInfo;
import com.beksons.service.RetInfoService;

import java.time.LocalDate;
import java.util.List;
public class RetInfoServiceImpl implements RetInfoService {
    private final RetInfoDao retInfoDao = new RetInfoDaoImplImpl();
    @Override
    public List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2) {
        return retInfoDao.getAllRentInfoBetweenDates(checkOut1,checkOut2);
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        return retInfoDao.housesByAgencyIdAndDate(agencyId);
    }
}
