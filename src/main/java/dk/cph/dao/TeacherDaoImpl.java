package dk.cph.dao;

import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDaoImpl {

    private static TeacherDaoImpl instance;
    private static EntityManagerFactory emf;

    public static TeacherDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherDaoImpl();
        }
        return instance;
    }
}
