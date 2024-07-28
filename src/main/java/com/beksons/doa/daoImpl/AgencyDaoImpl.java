package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.AgencyDao;
import com.beksons.entities.Address;
import com.beksons.entities.Agency;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public void saveAgency(Agency agency, Address address) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean isAgencyExists = false;

        try {
            for (Agency existingAgency : getAllAgencies()) {
                if (existingAgency.getName().equals(agency.getName())) {
                    isAgencyExists = true;
                    break;
                }
            }

            if (isAgencyExists) {
                throw new IllegalArgumentException("Agency with this name already exists.");
            }
            if (!agency.getPhoneNumber().startsWith("+996") || agency.getPhoneNumber().length() != 13) {
                throw new IllegalArgumentException("Invalid phone number format. It should start with +996 and be 13 characters long.");
            }
            entityManager.getTransaction().begin();
            agency.setAddress(address);
            address.setAgency(agency);
            entityManager.persist(agency);
            entityManager.getTransaction().commit();

        } catch (IllegalArgumentException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();

        }
    }


    @Override
    public List<Agency> getAllAgencies() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT a FROM Agency a ", Agency.class).getResultList();

    }


    @Override
    public Optional<Agency> getAgencyByID(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();
            agency = entityManager.createQuery("SELECT a FROM Agency a WHERE a.id = ?1", Agency.class)
                    .setParameter(1, id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            entityManager.getTransaction().rollback();

        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(agency);
    }


    @Override
    public String updateAgencyByID(Long id, Agency newAgency) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency existingAgency = entityManager.find(Agency.class, id);
            if (existingAgency == null) {
                throw new IllegalArgumentException("Agency with this ID does not exist");
            }
            existingAgency.setName(newAgency.getName());
            existingAgency.setPhoneNumber(newAgency.getPhoneNumber());
            entityManager.merge(existingAgency);
            entityManager.getTransaction().commit();

            return "Agency updated successfully.";
        } catch (IllegalArgumentException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void deleteAgency(Long id) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            final Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
