package ra.edu.validate;

import ra.edu.exception.EmailFormatException;
import ra.edu.exception.PasswordFormatException;
import ra.edu.exception.PhoneFormatException;

import java.util.regex.Pattern;

import java.util.Scanner;


public class AuthenticationValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z][A-Za-z0-9]*@gmail\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d).+$";

    public static String validateEmail(Scanner sc) {
        while (true) {
            try {
                String email = InputValidator.validateString(sc, "email", 5, 255);

                if (!Pattern.matches(EMAIL_REGEX, email)) {
                    throw new EmailFormatException("Email phải đúng định dạng (ví dụ: ten123@gmail.com), vui lòng nhập lại.");
                }

                return email;
            } catch (EmailFormatException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static String validatePassword(Scanner sc) {
        while (true) {
            try {
                String password = InputValidator.validateString(sc, "mật khẩu", 5, 255);

                if (!Pattern.matches(PASSWORD_REGEX, password)) {
                    throw new PasswordFormatException("Mật khẩu phải có ít nhất 1 chữ cái và 1 chữ số. Vui lòng nhập lại.");
                }

                return password;
            } catch (PasswordFormatException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static final String PHONE_REGEX = "^0[35789][0-9]{8}$";

    public static String validatePhoneNumber(Scanner sc) {
        while (true) {
            try {
                String phone = InputValidator.validateString(sc, "số điện thoại", 10, 12);

                if (!Pattern.matches(PHONE_REGEX, phone)) {
                    throw new PhoneFormatException("Số điện thoại phải có 10 chữ số, bắt đầu bằng 03, 05, 07, 08 hoặc 09. Vui lòng nhập lại.");
                }

                return phone;
            } catch (PhoneFormatException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }


}
