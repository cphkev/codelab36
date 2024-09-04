package dk.cph.dao;

import dk.cph.dtos.CourseDTO;
import dk.cph.dtos.TeacherDTO;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherDaoImpl implements GenericDAO<Teacher, Integer> {

    private static TeacherDaoImpl instance;
    private static EntityManagerFactory emf;

    private TeacherDaoImpl() {
    }
    public static TeacherDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Teacher> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void persistEntity(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, id);
            if (teacher != null) {
                em.remove(teacher);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Teacher findEntity(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Teacher.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Teacher updateEntity(Teacher teacher, Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher existingTeacher = em.find(Teacher.class, id);
            if (existingTeacher != null) {
                existingTeacher.setName(teacher.getName());
                existingTeacher.setEmail(teacher.getEmail());
                existingTeacher.setZoom(teacher.getZoom());
                em.merge(existingTeacher);

            }
            em.getTransaction().commit();
            return existingTeacher;
        } finally {
            em.close();
        }
    }

    public TeacherDTO findTeacherWithCourses(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            Teacher teacher = em.find(Teacher.class, id);
            if (teacher != null) {
                TeacherDTO dto = new TeacherDTO();
                dto.setName(teacher.getName());
                dto.setZoom(teacher.getZoom());
                dto.setCourses(teacher.getCourses().stream()
                        .map(course -> {
                            CourseDTO courseDTO = new CourseDTO();
                            courseDTO.setName(course.getCourseName().name());
                            courseDTO.setStartDate(course.getStartDate());
                            courseDTO.setEndDate(course.getEndDate());
                            return courseDTO;
                        }).collect(Collectors.toSet()));
                return dto;
            }
            return null;
        } finally {
            em.close();
        }
    }
}
