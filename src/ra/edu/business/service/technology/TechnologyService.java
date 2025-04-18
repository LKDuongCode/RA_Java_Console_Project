package ra.edu.business.service.technology;

import ra.edu.business.model.technology.Technology;
import ra.edu.business.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface TechnologyService extends BaseService<Technology> {
    Optional<Technology> findByName(String name);
    boolean isNameDeleted(String name);
    Optional<Technology> findById(int id);
    List<Technology> getTechnologyPerPage(int page, int numOfElement);
    public int getTotalRow ();
}
