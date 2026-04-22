package util;

import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isPositiveInt(String value) {
        try {
            return Integer.parseInt(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveDouble(String value) {
        try {
            return Double.parseDouble(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPhone(String phone) {
        return Pattern.matches("^\\d{10}$", phone);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    public static boolean isValidId(String id, String prefix) {
        return Pattern.matches("^" + Pattern.quote(prefix) + "\\d+$", id);
    }
}
