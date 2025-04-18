package ra.edu.business.service.admin;

import ra.edu.business.model.admin.Admin;
import ra.edu.business.service.BaseService;

import java.util.Optional;
import java.util.Scanner;

public interface AdminService extends BaseService<Admin> {
    Optional<Admin> login(String email, String password);
    boolean logout(Scanner sc);
    void rememberMe(String email);
    boolean checkLoggedIn ();
    Admin getRememberedAdmin ();
}
