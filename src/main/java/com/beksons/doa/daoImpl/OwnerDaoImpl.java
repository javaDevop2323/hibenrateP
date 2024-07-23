package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.OwnerDao;
import com.beksons.entities.Agency;
import com.beksons.entities.House;
import com.beksons.entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();


    @Override
    public void saveOwner(Owner owner) {


    }

    @Override
    public void saveOwnerAndHouse(Owner owner, House house) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            int age = LocalDate.now().getYear() - owner.getDateOfBirth().getYear();
            if (age < 18) {
                throw new IllegalArgumentException(" owner < 18" + owner.getDateOfBirth());
            }
            owner.getHouses().add(house);
            house.setOwner(owner);
            entityManager.persist(owner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        final  EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final Owner owner = entityManager.find(Owner.class, ownerId);
        final Agency agency = entityManager.find(Agency.class,agencyId);
        agency.getOwners().add(owner);
        owner.getAgencies().add(agency);
        entityManager.merge(owner);
        entityManager.merge(agency);
        return "success";
    }

    @Override
    public List<Owner> getOwnersByAgencyID(Long agencyID) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

return null;
    }

    @Override
    public String deleteOwnerByID(Long ownerID) {
        return "";
    }

    @Override
    public List<Owner> getOwnersByAgencyByID(Long agencyId) {
        return null;
    }

    @Override
    public List<Owner> getOwnerAgeAndName() {
        return null;
    }
}
