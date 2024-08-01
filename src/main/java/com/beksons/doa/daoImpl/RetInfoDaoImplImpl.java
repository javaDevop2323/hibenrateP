package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.RetInfoDao;
import com.beksons.entities.RentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RetInfoDaoImplImpl implements RetInfoDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RentInfo> rentInfos = new ArrayList<>();
        try {
            rentInfos = entityManager.createQuery("select r from rent_info_entity r where r.checkOut between :checkOut1 and :checkOut2", RentInfo.class)
                    .setParameter("checkOut1", checkOut1)
                    .setParameter("checkOut2", checkOut2).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
          if(entityManager.isOpen()){
              entityManager.close();
          }
        }
        return rentInfos;
    }

    public Long housesByAgencyIdAndDate(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Long countHouse = 0L;
        try {
            entityManager.getTransaction().begin();
            countHouse = entityManager.createQuery("""
                            select count(r.id) from rent_info_entity r
                            where r.agency.id =:agencyId and r.checkIn <=:currentDate
                            and r.checkOut >=:currentDate
                            """, Long.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }
        }
        return countHouse;
    }

    @Override
    public Optional<RentInfo> getRentInfoById(Long rentInfoId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        RentInfo rentInfo = null;
        try {

            rentInfo = entityManager.find(RentInfo.class, rentInfoId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return Optional.ofNullable(rentInfo);
    }

    @Override
    public List<RentInfo> getRentInfoByAgencyIdAndBookingDate(Long agencyId, LocalDate bookingDate) {
        EntityManager entityManager = null;
        List<RentInfo> rentInfoList = Collections.emptyList();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            rentInfoList = entityManager.createQuery(
                            "select r from rent_info_entity r where r.agency.id = :agencyId AND r.checkIn = :bookingDate", RentInfo.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("bookingDate", bookingDate)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return rentInfoList;
    }




    @Override
    public Long countRentedHousesBetweenDates(LocalDate startDate, LocalDate endDate) {
      EntityManager   entityManager = entityManagerFactory.createEntityManager();
        Long count = 0L;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            count = entityManager.createQuery(
                            "select count (r) from rent_info_entity r where r.checkIn between :startDate and :endDate", Long.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return count;
    }

    @Override
    public List<RentInfo> getRentInfoByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}

