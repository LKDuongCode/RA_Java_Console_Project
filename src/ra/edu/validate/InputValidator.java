package ra.edu.validate;

import ra.edu.exception.StringFormatException;
import ra.edu.exception.EnumNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {

    public static int validateInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên hợp lệ.");
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static double validateDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số thực hợp lệ.");
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static String validateString(Scanner sc, String attribute, int min, int max) {
        while (true) {
            try {
                String input = sc.nextLine().trim();

                if (input.isEmpty())
                    throw new StringFormatException("Trường '" + attribute + "' không được để trống.");

                if (input.length() < min || input.length() > max)
                    throw new StringFormatException("Trường '" + attribute + "' phải có từ " + min + " đến " + max + " ký tự.");

                return input;
            } catch (StringFormatException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static LocalDate validateLocalDate(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Vui lòng nhập đúng định dạng ngày dd/MM/yyyy.");
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static LocalDateTime validateLocalDateTime(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        while (true) {
            try {
                System.out.print("Nhập ngày (dd/MM/yyyy): ");
                String date = sc.nextLine().trim();

                System.out.print("Nhập giờ (HH:mm:ss): ");
                String time = sc.nextLine().trim();

                return LocalDateTime.parse(date + " " + time, formatter);
            } catch (DateTimeParseException e) {
                System.err.println(" Vui lòng nhập đúng định dạng 'dd/MM/yyyy HH:mm:ss'.");
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static boolean validateBoolean(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine().trim().toLowerCase();
                if (input.equals("true") || input.equals("false")) {
                    return Boolean.parseBoolean(input);
                }
                System.err.println("Vui lòng nhập 'true' hoặc 'false'.");
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static <T extends Enum<T>> T validateEnum(Scanner sc, Class<T> enumClass) {
        while (true) {
            try {
                String input = sc.nextLine().trim().toUpperCase();
                for (T constant : enumClass.getEnumConstants()) {
                    if (constant.name().equals(input)) {
                        return constant;
                    }
                }
                throw new EnumNotFoundException("Giá trị không hợp lệ. Vui lòng chọn một trong: " +
                        String.join(" | ", getEnumNames(enumClass)));
            } catch (EnumNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(" Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    private static <T extends Enum<T>> String[] getEnumNames(Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        String[] names = new String[constants.length];

        for (int i = 0; i < constants.length; i++) {
            names[i] = constants[i].name();
        }

        return names;
    }
}
