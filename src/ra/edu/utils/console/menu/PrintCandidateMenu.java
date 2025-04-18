package ra.edu.utils.console.menu;

import ra.edu.utils.console.Color;
import ra.edu.utils.console.menu.printMenuUtils;

public class PrintCandidateMenu {

    public static void showHomeMenu() {
        String[] items = {
                "Thông tin cá nhân",
                "Vị trí ứng tuyển"
        };
        printMenuUtils.printMenu("HOME", items, "Đăng xuất",true, Color.ROYAL_PURPLE);
    }

    public static void showProfile() {
        String[] items = {
                "Thay đổi thông tin cá nhân.",
                "Thay đổi mật khẩu."
        };
        printMenuUtils.printMenu("PROFILE", items, "Quay lại",false, Color.ROYAL_PURPLE);
    }

    public static void showApplicationManagementMenu() {
        String[] items = {
                "Xem các đơn ứng tuyển đã đăng kí.",
                "Xem các đơn ứng tuyển đang mở."
        };
        printMenuUtils.printMenu("HOME", items, "Đăng xuất",true, Color.ROYAL_PURPLE);
    }

}
