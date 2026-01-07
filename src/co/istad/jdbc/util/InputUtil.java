package co.istad.jdbc.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String getText(String label) {
        ViewUtil.print(label + "-> ", false);
        return scanner.nextLine();
    }

    public static BigDecimal getMoney(String label) {
        ViewUtil.print(label + "-> ", false);
        return scanner.nextBigDecimal();
    }

    public static Integer getInteger(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

    public static Double getDouble(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }
}

