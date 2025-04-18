package ra.edu.utils.console.menu;

import ra.edu.utils.console.Color;
import ra.edu.utils.console.menu.printMenuUtils;

public class PrintAdminMenu {

    public static void showDashboardMenu() {
        String[] items = {
                "Quản lí công nghệ",
                "Quản lí ứng viên",
                "Quản lí vị trí tuyển dụng",
                "Quản lí đơn ứng tuyển"
        };
        printMenuUtils.printMenu("DASHBOARD", items, "Đăng xuất", true, Color.CYAN);
    }

    public static void showTechnologyManagementMenu() {
        String[] items = {
                "Hiển thị các công nghệ đang tuyển dụng",
                "Thêm một công nghệ mới",
                "Xóa công nghệ theo ID",
                "Sửa thông tin công nghệ theo ID"
        };
        printMenuUtils.printMenu("QUẢN LÍ CÔNG NGHỆ", items, "Quay lại", false, Color.CYAN);
    }

    public static void showCandidateManagementMenu() {
        String[] items = {
                "Hiển thị tất cả các ứng viên",
                "Khóa tài khoản ứng viên theo ID",
                "Mở khóa tài khoản ứng viên theo ID",
                "Tìm kiếm ứng viên theo tên",
                "Reset mật khẩu ứng viên theo ID",
                "Lọc ứng viên"
        };
        printMenuUtils.printMenu("QUẢN LÍ ỨNG VIÊN", items, "Quay lại", false, Color.CYAN);
    }

    public static void showCandidateFilterMenu() {
        String[] items = {
                "Theo kinh nghiệm",
                "Theo độ tuổi",
                "Theo giới tính",
                "Theo công nghệ"
        };
        printMenuUtils.printMenu("LỌC ỨNG VIÊN", items, "Quay lại", false, Color.CYAN);
    }

    public static void showPositionManagementMenu() {
        String[] items = {
                "Thêm một vị trí tuyển dụng mới",
                "Sửa một vị trí tuyển dụng theo ID",
                "Xóa một vị trí tuyển dụng theo ID",
                "Tìm kiếm vị trí tuyển dụng"
        };
        printMenuUtils.printMenu("QUẢN LÍ VỊ TRÍ TUYỂN DỤNG", items, "Quay lại", false, Color.CYAN);
    }

    public static void showApplicationManagementMenu() {
        String[] items = {
                "Hiển thị danh sách đơn ứng tuyển",
                "Xem đơn ứng tuyển",
                "Hủy đơn ứng tuyển",
                "Lọc đơn ứng tuyển",
                "Cập nhật kết quả đơn ứng tuyển",
                "Cập nhật đơn sang quá trình phỏng vấn"
        };
        printMenuUtils.printMenu("QUẢN LÍ ĐƠN ỨNG TUYỂN", items, "Quay lại", false, Color.CYAN);
    }


}
