package ra.edu.business.dao.admin;

import ra.edu.business.dao.BaseDAO;
import ra.edu.business.model.admin.Admin;


import java.util.Optional;

public interface AdminDAO extends BaseDAO<Admin> {
    Optional<Admin> findByEmail(String email);
     Optional<Admin> getRememberedUser();
     boolean saveRememberedUser(String email);
    void logout();
}
