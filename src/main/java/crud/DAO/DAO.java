package crud.DAO;

import java.util.List;

public interface DAO<T, ID> {
    T find(ID id);

    List<T> findAll();

    void save(T o);

    void update(T o);

    void delete(T o);
}
