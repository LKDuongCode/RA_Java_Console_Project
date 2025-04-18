package ra.edu.business.service.candidate;

import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.candidate.Candidate;
import ra.edu.business.service.BaseService;

import java.util.Optional;
import java.util.Scanner;

public interface CandidateService extends BaseService<Candidate> {
    Optional<Candidate> login(String email, String password);
    boolean logout(Scanner sc);
    void rememberMe(String email);
    boolean checkLoggedIn ();
    Candidate getRememberedCandidate ();
}
