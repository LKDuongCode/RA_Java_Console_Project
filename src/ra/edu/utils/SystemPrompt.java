package ra.edu.utils;

import ra.edu.utils.console.ColorTransfer;

import java.util.Scanner;

public class SystemPrompt {
    public static boolean confirmAction(String message, Scanner sc) {
        while (true) {
            System.out.println(ColorTransfer.toRed(message + " (y/n)"));
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Vui lòng nhập 'y' hoặc 'n'!");
            }
        }
    }
}
