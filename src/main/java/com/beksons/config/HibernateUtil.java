package com.beksons.config;


import com.beksons.entities.*;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
        private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/root";
        private static final String JDBC_USER = "postgres";
        private static final String JDBC_PASSWORD = "1234";
        private static final String JDBC_DRIVER = "org.postgresql.Driver";
        private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
        private static final String HBM2DDL_AUTO = "create-drop";
        private static final String SHOW_SQL = "true";

        private static SessionFactory sessionFactory;

        private static SessionFactory buildSessionFactory() {
            try {
                Properties properties = new Properties();
                properties.put(Environment.JAKARTA_JDBC_URL, JDBC_URL);
                properties.put(Environment.JAKARTA_JDBC_USER, JDBC_USER);
                properties.put(Environment.JAKARTA_JDBC_PASSWORD, JDBC_PASSWORD);
                properties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
                properties.put(Environment.JAKARTA_JDBC_DRIVER, JDBC_DRIVER);
                properties.put(Environment.DIALECT, DIALECT);
                properties.put(Environment.SHOW_SQL, SHOW_SQL);

                Configuration configuration = new Configuration();
                configuration.addProperties(properties);
                addAnnotatedClasses(configuration);

                return configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("There was an error building the SessionFactory", e);
            }
        }

        private static void addAnnotatedClasses(Configuration configuration) {
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Agency.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(House.class);
            configuration.addAnnotatedClass(Owner.class);
            configuration.addAnnotatedClass(RentInfo.class);
        }

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                synchronized (HibernateUtil.class) {
                    if (sessionFactory == null) {
                        sessionFactory = buildSessionFactory();
                    }
                }
            }
            return sessionFactory;
        }
        public static EntityManagerFactory getEntityManagerFactory() {
            return getSessionFactory().unwrap(EntityManagerFactory.class);
        }
    }



