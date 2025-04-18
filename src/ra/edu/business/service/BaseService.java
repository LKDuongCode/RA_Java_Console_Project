package ra.edu.business.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}
