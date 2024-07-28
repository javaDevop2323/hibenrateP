package com.beksons.config;


import com.beksons.entities.*;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/root");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "1234");
        properties.put(Environment.HBM2DDL_AUTO,"create-drop");
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");


        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Agency.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(House.class);
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(RentInfo.class);
        configuration.addAnnotatedClass(House.class);


        return configuration.buildSessionFactory();

    }
    public static EntityManagerFactory getEntityManagerFactory(){
        return getSessionFactory().unwrap(EntityManagerFactory.class);
    }
}


