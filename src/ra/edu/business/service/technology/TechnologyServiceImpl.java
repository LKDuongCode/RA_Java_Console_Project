package ra.edu.business.service.technology;

import ra.edu.business.dao.technology.TechnologyDAO;
import ra.edu.business.dao.technology.TechnologyDaoImpl;
import ra.edu.business.model.technology.Technology;
import ra.edu.business.model.technology.TechnologyStatus;

import java.util.List;
import java.util.Optional;

public class TechnologyServiceImpl implements TechnologyService{
    private final TechnologyDAO technologyDAO = new TechnologyDaoImpl();

    public boolean add(Technology tech) {
        String normalizedName = tech.getName().trim().toLowerCase();
        tech.setName(normalizedName);

        return technologyDAO.insert(tech);
    }


    @Override
    public Optional<Technology> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Technology> getAll() {
        return List.of();
    }

    @Override
    public boolean update(Technology tech) {
        tech.setName(tech.getName().trim().toLowerCase());
        return technologyDAO.update(tech);
    }

    @Override
    public boolean delete(Technology technology) {
        return false;
    }

    @Override
    public Optional<Technology> findById(int id) {
        return technologyDAO.findById(id);
    }

    @Override
    public boolean isNameDeleted(String name) {
        Optional<Technology> found = findByName(name);
        if(found.isPresent() && found.get().getStatus() == TechnologyStatus.DELETED){
            return true;
        }
        return false;
    }

    @Override
    public List<Technology> getTechnologyPerPage(int page, int numOfElement) {
        return technologyDAO.getTechnologyPerPage(page, numOfElement);
    }
    @Override
    public int getTotalRow (){
        return technologyDAO.getTotalRow();
    }

}
