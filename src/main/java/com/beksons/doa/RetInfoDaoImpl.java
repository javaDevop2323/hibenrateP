package com.beksons.doa;

import com.beksons.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RetInfoDaoImpl {
    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);

     Long housesByAgencyIdAndDate(Long agencyId);
}
