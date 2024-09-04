package dk.cph.dao;
import dk.cph.config.HibernateConfig;
import dk.cph.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
class StudentDaoImplTest {

    private static EntityManagerFactory emf;
    private static StudentDaoImpl studentDao;

    Student s1, s2, s3;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTestMode(true);
        emf = HibernateConfig.getEntityManagerFactory();
        studentDao = new StudentDaoImpl(emf);
    }

    @AfterAll
    static void tearDownAll() {
        HibernateConfig.setTestMode(false);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        s1 = Student.builder()
                .name("John")
                .email("john.doe@example.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        s2 = Student.builder()
                .name("Jane")
                .email("jane.smith@example.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        s3 = Student.builder()
                .name("Alice")
                .email("alice.johnson@example.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();




        em.getTransaction().begin();
        em.createQuery("DELETE FROM Student").executeUpdate();
        em.createQuery("DELETE FROM Course ").executeUpdate();
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.getTransaction().commit();
    }
    @Test
    void findAll() {
    }

    @Test
    void persistEntity() {
    }

    @Test
    void removeEntity() {
    }

    @Test
    void findEntity() {
        Student expected = s1;
        Student actual = studentDao.findEntity(1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    void updateEntity() {
    }
}