package ra.edu.utils.console.menu;


import ra.edu.utils.console.Color;
import ra.edu.utils.console.ConsoleController;

public class PrintAuthenticationMenu {

    public static void showInitMenu() {
        String[] items = {
                "Đăng nhập",
                "Tạo tài khoản mới"
        };
        ConsoleController.clearConsole();
        printMenuUtils.printMenu("CHÀO MỪNG BẠN!", items, "Thoát", false, Color.GREEN);
    }

    public static void showAuthorizationMenu() {
        String[] items = {
                "Xác thực với quyền quản trị viên",
                "Xác thực với quyền ứng viên"
        };
        ConsoleController.clearConsole();
        printMenuUtils.printMenu("XÁC THỰC QUYỀN HẠN", items, "Quay lại", false, Color.GREEN);
    }
}
