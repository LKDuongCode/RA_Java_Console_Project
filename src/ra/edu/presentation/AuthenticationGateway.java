package ra.edu.presentation;

import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.candidate.Candidate;
import ra.edu.business.model.candidate.Gender;
import ra.edu.business.model.person.PersonStatus;
import ra.edu.business.service.admin.AdminService;
import ra.edu.business.service.admin.AdminServiceImpl;
import ra.edu.business.service.candidate.CandidateService;
import ra.edu.business.service.candidate.CandidateServiceImpl;
import ra.edu.utils.ExitProgram;
import ra.edu.utils.console.ColorTransfer;
import ra.edu.utils.console.menu.PrintAuthenticationMenu;
import ra.edu.utils.user.CurrentLoginUtils;
import ra.edu.utils.user.Role;
import ra.edu.validate.AuthenticationValidator;
import ra.edu.validate.InputValidator;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class AuthenticationGateway {
    private final AdminService adminService = new AdminServiceImpl();
    private final CandidateService candidateService = new CandidateServiceImpl();

    public boolean startAuthenticationFlow (Scanner sc){
        do {
            PrintAuthenticationMenu.showInitMenu();
            int choice = InputValidator.validateInt(sc);

            switch (choice){
                case 1:
                    return startLoginFlow(sc);
                case 2:
                    startRegisterFlow(sc);
                    break;
                case 0:
                    ExitProgram.exit(sc);
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại");
                    break;
            }
        }while (true);
    }

    private boolean startLoginFlow (Scanner sc){
         while (true){
             PrintAuthenticationMenu.showAuthorizationMenu();
             int choice = InputValidator.validateInt(sc);

             switch (choice){
                 case 1:
                     // check xem có nhớ không
                     boolean isRemembered = adminService.checkLoggedIn();
                     if(isRemembered){
                         Admin admin = adminService.getRememberedAdmin();
                         //lưu vào user đang dùng.
                         CurrentLoginUtils.setLogin(admin.getId(), Role.ADMIN);

                         //chuyển đến menu chính bên admin.
                         System.out.println(ColorTransfer.toGreen("Đăng nhập thành công!"));
                        return true;
                     }

                     // nếu không -> đăng nhập
                     System.out.print("Nhập email: ");
                     String e = AuthenticationValidator.validateEmail(sc);
                     System.out.print("Nhập mật khẩu: ");
                     String p = AuthenticationValidator.validatePassword(sc);

                     Optional<Admin> adminLoginBox = adminService.login(e,p);

                    if(adminLoginBox.isEmpty()){
                        break;
                    }
                     Admin admin =adminLoginBox.get();
                     //lưu vào user đang dùng.
                     CurrentLoginUtils.setLogin(admin.getId(),Role.ADMIN);
                     System.out.println(ColorTransfer.toGreen("Đăng nhập thành công!"));

                     // hỏi muốn ghi nhớ cho lần sau đăng nhập không?
                     System.out.println(ColorTransfer.toYellow("Bạn có muốn ghi nhớ cho lần đăng nhập tiếp theo? (y/n)"));
                     while (true) {
                         String input = sc.nextLine().trim().toLowerCase();
                         switch (input) {
                             case "y":
                                 adminService.rememberMe(admin.getEmail());
                                 break;
                             case "n":
                                 System.out.println(ColorTransfer.toYellow("Bạn đã chọn không nhớ phiên đăng nhập."));
                                 break;
                             default:
                                 System.err.println("Vui lòng nhập 'y' hoặc 'n'");
                                 continue;
                         }
                         break;
                     }

                     //chuyển đến menu chính bên admin.
                     return true;
                 case 2:
                     // check xem có nhớ không
                     boolean isRememberedCandidate = candidateService.checkLoggedIn();
                     if (isRememberedCandidate) {
                         Candidate candidate = candidateService.getRememberedCandidate();
                         CurrentLoginUtils.setLogin(candidate.getId(), Role.CANDIDATE);

                         System.out.println(ColorTransfer.toGreen("Đăng nhập thành công!"));
                         return true;
                     }

                     // nếu không -> đăng nhập
                     System.out.print("Nhập email: ");
                     String email = AuthenticationValidator.validateEmail(sc);
                     System.out.print("Nhập mật khẩu: ");
                     String password = AuthenticationValidator.validatePassword(sc);

                     Optional<Candidate> candidateLoginBox = candidateService.login(email, password);
                     if (candidateLoginBox.isEmpty()) {
                         break;
                     }

                     Candidate candidate = candidateLoginBox.get();
                     CurrentLoginUtils.setLogin(candidate.getId(), Role.CANDIDATE);
                     System.out.println(ColorTransfer.toGreen("Đăng nhập thành công!"));

                     // hỏi muốn ghi nhớ cho lần sau đăng nhập không?
                     System.out.println(ColorTransfer.toYellow("Bạn có muốn ghi nhớ cho lần đăng nhập tiếp theo? (y/n)"));
                     while (true) {
                         String input = sc.nextLine().trim().toLowerCase();
                         switch (input) {
                             case "y":
                                 candidateService.rememberMe(candidate.getEmail());
                                 break;
                             case "n":
                                 System.out.println(ColorTransfer.toYellow("Bạn đã chọn không nhớ phiên đăng nhập."));
                                 break;
                             default:
                                 System.err.println("Vui lòng nhập 'y' hoặc 'n'");
                                 continue;
                         }
                         break;
                     }

                     return true;
                 case 0:
                     return false;
                 default:
                     System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại");
                     break;
             }
         }

    }

    private void startRegisterFlow(Scanner sc) {
        System.out.println("ĐĂNG KÝ TÀI KHOẢN ỨNG VIÊN");

        System.out.print("Nhập họ tên: ");
        String name = InputValidator.validateString(sc,"họ và tên",1,255);

        System.out.print("Nhập email: ");
        String email = AuthenticationValidator.validateEmail(sc);

        System.out.print("Nhập mật khẩu: ");
        String password = AuthenticationValidator.validatePassword(sc);

        System.out.print("Nhập số điện thoại: ");
        String phone = AuthenticationValidator.validatePhoneNumber(sc);

        System.out.print("Nhập số năm kinh nghiệm (0 nếu chưa có): ");
        int experience = InputValidator.validateInt(sc);

        System.out.println("Chọn giới tính:");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("3. Khác");
        int genderChoice;
        String gender;
        while (true) {
            genderChoice = InputValidator.validateInt(sc);
            switch (genderChoice) {
                case 1: gender = "MALE"; break;
                case 2: gender = "FEMALE"; break;
                case 3: gender = "OTHER"; break;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại:");
                    continue;
            }
            break;
        }

        System.out.print("Nhập mô tả ngắn về bản thân: ");
        String description = InputValidator.validateString(sc,"mô tả bản thân",1,255);

        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        LocalDate dob = InputValidator.validateLocalDate(sc);

        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmail(email);
        candidate.setPassword(password);
        candidate.setPhone(phone);
        candidate.setExperience(experience);
        candidate.setGender(Gender.valueOf(gender));
        candidate.setDescription(description);
        candidate.setDob(dob);
        candidate.setStatus(PersonStatus.ACTIVE);

        boolean isSuccess = candidateService.add(candidate);
        if (isSuccess) {
            System.out.println(ColorTransfer.toGreen("Đăng ký tài khoản thành công!"));
            // chuyển đến flow đăng nhập
        } else {
            System.err.println("Đăng ký thất bại, vui lòng thử lại sau.");
        }
    }

}
