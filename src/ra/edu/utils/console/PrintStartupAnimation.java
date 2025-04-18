package ra.edu.utils.console;


public class PrintStartupAnimation {

    public static void showIntroAnimation() {
        final int WIDTH = 60;
        String border = "+" + "=".repeat(WIDTH) + "+";
        String title = "HỆ THỐNG QUẢN LÍ TUYỂN DỤNG IT";
        String slogan = "Tin cậy - Thực tiễn - Tinh gọn - Tận tâm";


        System.out.println(Color.DEEP_RED.wrap(border));
        System.out.println(Color.DEEP_RED.wrap(Color.BOLD.wrap("| " + String.format("%-" + (WIDTH - 2) + "s", centerText(title, WIDTH - 2)) + " |")));
        System.out.println(Color.DEEP_RED.wrap("| " + String.format("%-" + (WIDTH - 2) + "s", centerText(slogan, WIDTH - 2)) + " |"));
        System.out.println(Color.DEEP_RED.wrap(border));

        sleep(2000);
        ConsoleController.clearConsole();
        System.out.println(Color.DEEP_RED.wrap("[Loading...]"));

        sleep(1000);
        ConsoleController.clearConsole();
    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }


    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    private static String centerText(String text) {
        int width = 46;
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

}
