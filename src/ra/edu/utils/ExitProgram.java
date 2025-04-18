package ra.edu.utils;

import ra.edu.utils.console.ColorTransfer;

import java.util.Scanner;

public class ExitProgram {
    public static void exit (Scanner sc){
        System.out.println(ColorTransfer.toBlue("Thoát ứng dụng, hẹn gặp lại..."));
        sc.close();
        System.exit(0);
    }
}
