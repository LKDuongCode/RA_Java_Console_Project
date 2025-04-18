package ra.edu.presentation.admin;

import ra.edu.business.service.admin.AdminService;
import ra.edu.business.service.admin.AdminServiceImpl;
import ra.edu.utils.ExitProgram;
import ra.edu.utils.console.menu.PrintAdminMenu;
import ra.edu.validate.InputValidator;

import java.util.Scanner;

public class AdminUI {
    private AdminService adminService = new AdminServiceImpl();
    private TechnologyUI technologyUI = new TechnologyUI();

    public boolean handle (Scanner sc){
        boolean back = false;
        while (!back){
            PrintAdminMenu.showDashboardMenu();

            int choice = InputValidator.validateInt(sc);

            switch (choice){
                case 1:
                    technologyUI.handle(sc);
                    break;
                case 2:
                    PrintAdminMenu.showCandidateManagementMenu();
                    break;
                case 3:
                    PrintAdminMenu.showPositionManagementMenu();
                    break;
                case 4:
                    PrintAdminMenu.showApplicationManagementMenu();
                    break;
                case 5:
                    ExitProgram.exit(sc);
                    break;
                case 0:
                    adminService.logout(sc);
                    return true;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                    break;
            }
        }

        return back;
    }
}
