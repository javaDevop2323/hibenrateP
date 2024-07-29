package com.beksons.service;

import com.beksons.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RetInfoService {

    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);

    Long housesByAgencyIdAndDate(Long agencyId);
}
