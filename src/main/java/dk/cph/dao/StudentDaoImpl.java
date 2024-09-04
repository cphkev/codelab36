package dk.cph.dao;

import dk.cph.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDaoImpl implements  GenericDAO<Student, Integer> {

    private static StudentDaoImpl instance;
    private static EntityManagerFactory emf;

    public StudentDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public static StudentDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Student> findAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        }
    }

    @Override
    public void persistEntity(Student student) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student found = em.find(Student.class, id);
            if(found == null) {
                throw new EntityNotFoundException();
            }
            em.remove(found);
            em.getTransaction().commit();
        }

    }

    @Override
    public Student findEntity(Integer id) {
        Student found;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            found = em.find(Student.class, id);
            if (found == null) {
                throw new EntityNotFoundException();
            }
            em.getTransaction().commit();
        }
        return found;
    }

    @Override
    public Student updateEntity(Student student, Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Student found;
            em.getTransaction().begin();
            found = em.find(Student.class, id);
            if (found == null) {
                throw new EntityNotFoundException();
            }
            em.merge(student);
            em.getTransaction().commit();
        }
        return student;
    }
}
