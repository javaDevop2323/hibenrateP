package com.beksons.doa;

import com.beksons.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RetInfoDao {
    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);

     Long housesByAgencyIdAndDate(Long agencyId);

    Optional<RentInfo> getRentInfoById(Long rentInfoId);

    List<RentInfo> getRentInfoByAgencyIdAndBookingDate(Long agencyId, LocalDate bookingDate);



    Long countRentedHousesBetweenDates(LocalDate startDate, LocalDate endDate);

    List<RentInfo> getRentInfoByCustomerId(Long customerId);

}
