package ra.edu.utils.console;

public class ColorTransfer {

    public static String toRed(String mes) {
        return "\u001B[31m" + mes + "\u001B[0m";
    }

    public static String toGreen(String mes) {
        return "\u001B[32m" + mes + "\u001B[0m";
    }

    public static String toBlue(String mes) {
        return "\u001B[34m" + mes + "\u001B[0m";
    }

    public static String toYellow(String mes) {
        return "\u001B[33m" + mes + "\u001B[0m";
    }

    public static String toPurple(String mes) {
        return "\u001B[35m" + mes + "\u001B[0m";
    }

    public static String toCyan(String mes) {
        return "\u001B[36m" + mes + "\u001B[0m";
    }

    public static String toWhite(String mes) {
        return "\u001B[37m" + mes + "\u001B[0m";
    }

    public static String toBlack(String mes) {
        return "\u001B[30m" + mes + "\u001B[0m";
    }

    public static String toBold(String mes) {
        return "\u001B[1m" + mes + "\u001B[0m";
    }

    public static String toUnderline(String mes) {
        return "\u001B[4m" + mes + "\u001B[0m";
    }
}
