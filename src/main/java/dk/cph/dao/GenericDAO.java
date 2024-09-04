package dk.cph.dao;

import java.util.List;

public interface GenericDAO<T, D> {

    List<T> findAll();
    void persistEntity(T entity);
    void removeEntity(D id);
    T findEntity(D id);
    T updateEntity(T entity, D id);

}