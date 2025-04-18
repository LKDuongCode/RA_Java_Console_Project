package ra.edu.presentation.candidate;

import ra.edu.business.service.candidate.CandidateService;
import ra.edu.business.service.candidate.CandidateServiceImpl;
import ra.edu.utils.ExitProgram;
import ra.edu.utils.console.menu.PrintCandidateMenu;
import ra.edu.validate.InputValidator;

import java.util.Scanner;

public class CandidateUI {
    private final CandidateService candidateService = new CandidateServiceImpl();

    public boolean handle(Scanner sc) {
        boolean back = false;
        while (!back) {
            PrintCandidateMenu.showHomeMenu();
            int choice = InputValidator.validateInt(sc);

            switch (choice) {
                case 1:
                    handleProfileMenu(sc);
                    break;
                case 2:
                    PrintCandidateMenu.showApplicationManagementMenu();
                    break;
                case 3:
                    ExitProgram.exit(sc);
                    break;
                case 0:
                    if (candidateService.logout(sc)) {
                        return true;
                    }
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
        return false;
    }

    private void handleProfileMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            PrintCandidateMenu.showProfile();
            int choice = InputValidator.validateInt(sc);

            switch (choice) {
                case 1:
                    System.out.println("Thay đổi thông tin cá nhân.");
                    break;
                case 2:
                    System.out.println("Thay đổi mật khẩu.");
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
    }
}
