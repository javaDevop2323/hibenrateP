package com.beksons.doa.daoImpl;

import com.beksons.config.HibernateUtil;
import com.beksons.doa.CustomerDao;
import com.beksons.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();


    @Override
    public String saveCustomer(Customer customer) {
        try (final EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            return e.getMessage();
        }

        return "Success";
    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            House house = entityManager.find(House.class, houseId);
            if (!checkHouseRent(entityManager,houseId, checkIn, checkOut)){
                entityManager.getTransaction().rollback();
                return "error";
            }
            entityManager.persist(customer);
            RentInfo rentInfo = new RentInfo();
            rentInfo.setCustomer(customer);
            rentInfo.setHouse(house);
            rentInfo.setAgency(agency);
            rentInfo.setCheckIn(checkIn);
            rentInfo.setCheckOut(checkOut);
            entityManager.persist(rentInfo);
            entityManager.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            entityManager.close();

        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = null;
        try {
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from  Customer c ", Customer.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, customerId);
            entityManager.getTransaction().commit();
            return Optional.of(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert customer != null;
        return Optional.of(customer);
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomer) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.createQuery("update Customer c set c.firstName = :firstName," +
                            "  c.lastName = :lastName, " +
                            "c.email = :email ,c.nationality = :nationality,c.dateOfBirth " +
                            "= :date,c.gender = :gender,c.familyStatus = :fam where c.id = :customerId")
                    .setParameter("firstName", newCustomer.getFirstName())
                    .setParameter("lastName", newCustomer.getLastName())
                    .setParameter("email", newCustomer.getEmail())
                    .setParameter("nationality", newCustomer.getNationality())
                    .setParameter("date", newCustomer.getDateOfBirth())
                    .setParameter("gender", newCustomer.getGender())
                    .setParameter("fam", newCustomer.getFamilyStatus())
                    .setParameter("customerId", customerId).executeUpdate();
            entityManager.getTransaction().commit();

            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteCustomer(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            List<RentInfo> rentInfos = customer.getRentInfos();
            if (!rentInfos.isEmpty()) {
                for (RentInfo rentInfo : rentInfos) {
                    if (rentInfo.getCheckOut().isAfter(LocalDate.now())) {
                        entityManager.getTransaction().rollback();
                        return "customer has active rentInfo";
                    }
                    House house = rentInfo.getHouse();

                    Owner owner = rentInfo.getOwner();
                    owner.getRentInfos().remove(rentInfo);
                    Agency agency = rentInfo.getAgency();
                    agency.getRentInfos().remove(rentInfo);
                    entityManager.remove(rentInfo);
                }
            }
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            return "successfully deleted";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            entityManager.close();

        }
    }

    private boolean checkHouseRent(EntityManager entityManager, Long houseId, LocalDate checkIn, LocalDate checkOut) {
        Long count = entityManager.createQuery(
                        "select count(r) from RentInfo r where r.house.id = :houseId " +
                                "and (r.checkIn <= :checkOut and r.checkOut >= :checkIn)", Long.class)
                .setParameter("houseId", houseId)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .getSingleResult();
        return count == 0;
    }

}
