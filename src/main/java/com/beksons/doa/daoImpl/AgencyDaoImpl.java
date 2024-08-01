package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.AgencyDao;
import com.beksons.entities.Address;
import com.beksons.entities.Agency;
import com.beksons.entities.RentInfo;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
            if(entityManager.isOpen()){
                entityManager.close();
            }

        }
    }


    @Override
    public List<Agency> getAllAgencies() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT a FROM  agency_entity a ", Agency.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();

    }


    @Override
    public Optional<Agency> getAgencyByID(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();
            agency = entityManager.createQuery("SELECT a FROM agency_entity a WHERE a.id = ?1", Agency.class)
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
             List<RentInfo> rentInfos = agency.getRentInfos();
             Address address = agency.getAddress();
             agency.getRentInfos().remove(rentInfos);
             entityManager.remove(address);
             entityManager.remove(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public String updateAgencyAddress(Long id, Address newAddress) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            final Agency singleResult = entityManager.createQuery("select ag from agency_entity ag where ag.id = :id", Agency.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if (singleResult != null) {
                singleResult.setAddress(newAddress);
                entityManager.merge(singleResult);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                return "Agency not found";            }

        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return "An error occurred while updating the address";

        }finally {
            if(entityManager !=null&&entityManager.isOpen()){
                entityManager.close();

            }

        }
        return "successFully updated";
    }

    @Override
    public List<Agency> getAgenciesByCity(String city) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = Collections.emptyList();
        try {
            agencies  = entityManager.createQuery
                    ("select a from agency_entity a where a.address.city = :city",Agency.class)
                    .setParameter("city",city).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null&&entityManager.isOpen()){
                entityManager.close();
            }
        }
        return agencies;
    }
}
