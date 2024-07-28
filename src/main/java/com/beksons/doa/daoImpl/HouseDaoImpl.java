package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.HouseDao;
import com.beksons.entities.House;
import com.beksons.entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;

import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public void createHouse(Long ownerId, House house) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner foundOwner = entityManager.find(Owner.class, ownerId);

            if (foundOwner != null) {
                house.setOwner(foundOwner);
                entityManager.persist(house);
                foundOwner.getHouses().add(house);
                entityManager.merge(foundOwner);
            } else {
                System.out.println("Owner with ID " + ownerId + " not found.");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteHouse(Long houseId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from House h where h.id = :houseId")
                    .setParameter("houseId", houseId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            return e.getMessage();
        }
        return "success";

    }

    @Override
    public List<House> getAllHouseByRegion(String region) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<House> query = entityManager.createQuery("SELECT h FROM House h JOIN h.address a WHERE a.region = :region", House.class);
        query.setParameter("region", region);
        return query.getResultList();
    }

    @Override
    public List<House> getHouseByAgencyId(Long id) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select h from  House h  join h.address a join a.agency ag where ag.id= :id ",House.class)
                .setParameter("id",id)
                .getResultList();

    }

    @Override
    public List<House> getHouseByOwnerId(Long ownerId) {
        return List.of();
    }
}
