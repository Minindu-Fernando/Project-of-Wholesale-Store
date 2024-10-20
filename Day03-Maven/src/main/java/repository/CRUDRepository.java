package repository;

import java.util.List;

public interface CRUDRepository<T, ID> extends SuperDao {
    boolean save(T entity);

    boolean update(T entity, ID id);

    boolean delete(ID id);

    List<T> findAll();
}
