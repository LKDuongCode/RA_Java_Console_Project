package ra.edu.business.dao.candidate;

import ra.edu.business.dao.BaseDAO;
import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.candidate.Candidate;

import java.util.Optional;

public interface CandidateDAO extends BaseDAO<Candidate> {
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> getRememberedUser();
    boolean saveRememberedUser(String email);
    void logout();

}
