package dk.cph;


import dk.cph.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("university");
        }
    }
}