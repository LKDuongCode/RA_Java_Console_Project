package ra.edu.business.service.admin;


import ra.edu.business.dao.admin.AdminDAO;
import ra.edu.business.dao.admin.AdminDaoImpl;
import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.person.PersonStatus;
import ra.edu.utils.PasswordUtils;
import ra.edu.utils.SystemPrompt;
import ra.edu.utils.console.ColorTransfer;
import ra.edu.validate.AuthenticationValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO = new AdminDaoImpl();


    @Override
    public Optional<Admin> login(String email, String password) {
        Optional<Admin> adminOpt = adminDAO.findByEmail(email);

        if (adminOpt.isEmpty()) {
            System.err.println("Tài khoản hoặc mật khẩu không đúng!");
            return Optional.empty();
        }

        Admin admin = adminOpt.get();
        System.out.println(password);
        System.out.println(admin.getPassword());
        if (!PasswordUtils.verifyPassword(password, admin.getPassword())) {
            System.err.println("Tài khoản hoặc mật khẩu không đúng!");
            return Optional.empty();
        }

        if (admin.getStatus() == PersonStatus.INACTIVE) {
            System.err.println("Tài khoản đã bị khóa");
            return Optional.empty();
        }

        return Optional.of(admin);
    }
    @Override
    public boolean logout(Scanner sc) {
        boolean confirmed = SystemPrompt.confirmAction("Bạn có chắc muốn đăng xuất không?",sc);
        if (!confirmed){
            System.out.println(ColorTransfer.toGreen("Hủy đăng xuất!"));
            return false;
        }

        adminDAO.logout();
        return true;
    }

    @Override
    public void rememberMe(String email) {
        if(adminDAO.saveRememberedUser(email)){
            System.out.println(ColorTransfer.toYellow("Đã lưu phiên đăng nhập!"));
        }else {
            System.err.println("Lưu phiên đăng nhập thất bại.");
        }
    }

    @Override
    public boolean checkLoggedIn() {
        if(adminDAO.getRememberedUser().isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Admin getRememberedAdmin() {
        return adminDAO.getRememberedUser().get();
    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }

    @Override
    public boolean add(Admin admin) {
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        return false;
    }


    @Override
    public boolean delete(Admin admin) {
        return false;
    }


}
