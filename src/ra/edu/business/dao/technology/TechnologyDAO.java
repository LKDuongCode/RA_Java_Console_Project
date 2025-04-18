package ra.edu.business.dao.technology;

import ra.edu.business.dao.BaseDAO;
import ra.edu.business.model.technology.Technology;

import java.util.List;
import java.util.Optional;

public interface TechnologyDAO extends BaseDAO<Technology> {
    Optional<Technology> findByName (String name);
    List<Technology> getTechnologyPerPage (int page, int numOfElement);
    public int getTotalRow();
}
