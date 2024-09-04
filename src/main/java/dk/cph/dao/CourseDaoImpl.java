package dk.cph.dao;

import dk.cph.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDaoImpl implements GenericDAO<Course, Integer> {

    private static CourseDaoImpl instance;
    private static EntityManagerFactory emf;

    public static CourseDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseDaoImpl();
        }
        return instance;
    }


    @Override
    public List<Course> findAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);

            return query.getResultList().stream().collect(Collectors.toList());
        }
    }

    @Override
    public void persistEntity(Course entity) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }

    }

    @Override
    public void removeEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Course course = em.find(Course.class, id);
            em.remove(course);
            em.getTransaction().commit();
        }

    }

    @Override
    public Course findEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Course.class, id);
        }
    }

    @Override
    public Course updateEntity(Course entity, Integer id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Course course = em.find(Course.class, id);
            course.setCourseName(entity.getCourseName());
            course.setDescription(entity.getDescription());
            course.setStartDate(entity.getStartDate());
            course.setEndDate(entity.getEndDate());
            em.getTransaction().commit();
            return course;
        }
    }

}
