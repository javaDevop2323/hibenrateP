package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.HouseDao;
import com.beksons.entities.House;
import com.beksons.entities.Owner;
import com.beksons.entities.RentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.*;

public class HouseDaoImpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public void createHouse(Long ownerId, House house) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            final Owner owner = entityManager.find(Owner.class, ownerId);
            owner.getHouses().add(house);
            house.setOwner(owner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }


    }

    @Override
    public String deleteHouse(Long houseId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        House house = entityManager.find(House.class, houseId);
        if (house != null) {
            RentInfo rentInfo = house.getRentInfo();
            if (rentInfo != null && rentInfo.getCheckIn() != null && rentInfo.getCheckIn().isBefore(LocalDate.now())) {
                entityManager.remove(house);
                entityManager.getTransaction().commit();
                entityManager.close();
                return "House deleted successfully";
            } else {
                entityManager.getTransaction().rollback();
                entityManager.close();
                return "ге";
            }
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "House not found with id: " + houseId;
        }
    }

    @Override
    public List<House> getHouseByRegion() {
        return null;
    }

    @Override
    public Optional<House> getHouseByRegion(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<House> query = entityManager.createQuery(
                    "SELECT a FROM Address a WHERE a.region = :region", House.class);
            query.setParameter("region", region);
            House house = query.getSingleResult();
            entityManager.getTransaction().commit();
            return Optional.ofNullable(house);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }





    @Override
    public List<House> getHouseByAgencyId(Long agencyId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("SELECT a FROM Agency a WHERE a.id = :agencyId", House.class)
                    .setParameter("agencyId", agencyId).getResultList();
            if (houses.isEmpty()) {
                throw new NoSuchElementException("null");
            }
            return houses;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
return new ArrayList<>();
    }

    @Override
    public List<House> getHouseByOwnerId(Long ownerId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
           return   entityManager.createQuery
                            ("SELECT h FROM  Owner o WHERE  o.id = :ownerId", House.class)
                    .setParameter("ownerId", ownerId)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;

    }
}

