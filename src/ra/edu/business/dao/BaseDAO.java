package ra.edu.business.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    List<T> getAll();
    boolean insert(T t);
    boolean update(T t);
    Optional<T> findById (int id);
}
