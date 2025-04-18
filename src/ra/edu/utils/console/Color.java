package ra.edu.utils.console;

public enum Color {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    ORANGE("\u001B[38;5;208m"),
    ROYAL_PURPLE("\u001B[38;5;54m"),
    BOLD("\u001B[1m"),
    DEEP_RED("\u001B[38;5;124m"),
    UNDERLINE("\u001B[4m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String wrap(String text) {
        return code + text + RESET.code;
    }

    public void println(String text) {
        System.out.println(wrap(text));
    }

    public void print(String text) {
        System.out.print(wrap(text));
    }
}
