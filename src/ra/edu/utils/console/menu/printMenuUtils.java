package ra.edu.utils.console.menu;

import ra.edu.utils.console.Color;

public class printMenuUtils {
    private static final int WIDTH = 50;

    public static void printMenu(String title, String[] items, String zeroOptionLabel, boolean hasExitAppOption, Color mainColor) {
        String border = "+" + "=".repeat(WIDTH) + "+";

        // In viền trên
        System.out.println(mainColor.wrap(border));

        // In tiêu đề (in đậm + màu chủ đạo)
        String centered = centerText(title);
        String titleLine = "| " + String.format("%-" + (WIDTH - 2) + "s", centered) + "|";
        System.out.println(mainColor.wrap(Color.BOLD.wrap(titleLine)));

        // In viền dưới tiêu đề
        System.out.println(mainColor.wrap(border));

        // In các dòng menu
        for (int i = 0; i < items.length; i++) {
            String content = (i + 1) + ". " + items[i];
            String line = "| " + String.format("%-" + (WIDTH - 2) + "s", content) + "|";
            System.out.println(mainColor.wrap(line));
        }

        // In dòng "Thoát ứng dụng" (nếu có)
        if (hasExitAppOption) {
            int exitNumber = items.length + 1;
            String exitContent = exitNumber + ". Thoát ứng dụng";
            String exitLine = "| " + String.format("%-" + (WIDTH - 2) + "s", exitContent) + "|";
            Color.WHITE.println(exitLine);
        }

        // In dòng 0
        String zeroLine = "| 0. " + String.format("%-" + (WIDTH - 5) + "s", zeroOptionLabel) + "|";
        if ("đăng xuất".equalsIgnoreCase(zeroOptionLabel)) {
            Color.RED.println(zeroLine);
        } else {
            Color.WHITE.println(zeroLine);
        }

        // In viền dưới
        System.out.println(mainColor.wrap(border));
        System.out.print("Lựa chọn của bạn: ");
    }

    private static String centerText(String text) {
        int padding = (WIDTH - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}
