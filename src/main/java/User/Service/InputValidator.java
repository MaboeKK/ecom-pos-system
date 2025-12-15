package User.Service;

import java.util.regex.Pattern;

/**
 * Utility class for validating user input.
 * Provides common validation methods for security and data integrity.
 *
 * @author User
 */
public class InputValidator {

    // Email regex pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    // Phone number pattern (South African format)
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(\\+27|0)[0-9]{9}$"
    );

    // Alphanumeric pattern
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile(
            "^[A-Za-z0-9]+$"
    );

    // Name pattern (letters, spaces, hyphens, apostrophes)
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[A-Za-z][A-Za-z\\s'-]{1,49}$"
    );

    /**
     * Checks if a string is null or empty.
     * @param value The string to check
     * @return true if null or empty
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Checks if a string is not null and not empty.
     * @param value The string to check
     * @return true if not null and not empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validates an email address format.
     * @param email The email to validate
     * @return true if valid email format
     */
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validates a phone number (South African format).
     * @param phone The phone number to validate
     * @return true if valid phone format
     */
    public static boolean isValidPhone(String phone) {
        if (isNullOrEmpty(phone)) {
            return false;
        }
        String cleaned = phone.replaceAll("[\\s-]", "");
        return PHONE_PATTERN.matcher(cleaned).matches();
    }

    /**
     * Validates a name (letters, spaces, hyphens, apostrophes).
     * @param name The name to validate
     * @return true if valid name format
     */
    public static boolean isValidName(String name) {
        if (isNullOrEmpty(name)) {
            return false;
        }
        return NAME_PATTERN.matcher(name.trim()).matches();
    }

    /**
     * Validates if a string is alphanumeric.
     * @param value The string to validate
     * @return true if alphanumeric
     */
    public static boolean isAlphanumeric(String value) {
        if (isNullOrEmpty(value)) {
            return false;
        }
        return ALPHANUMERIC_PATTERN.matcher(value).matches();
    }

    /**
     * Validates if a string is a valid positive integer.
     * @param value The string to validate
     * @return true if valid positive integer
     */
    public static boolean isValidPositiveInteger(String value) {
        if (isNullOrEmpty(value)) {
            return false;
        }
        try {
            int num = Integer.parseInt(value.trim());
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string is a valid non-negative integer.
     * @param value The string to validate
     * @return true if valid non-negative integer
     */
    public static boolean isValidNonNegativeInteger(String value) {
        if (isNullOrEmpty(value)) {
            return false;
        }
        try {
            int num = Integer.parseInt(value.trim());
            return num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string is a valid positive double.
     * @param value The string to validate
     * @return true if valid positive double
     */
    public static boolean isValidPositiveDouble(String value) {
        if (isNullOrEmpty(value)) {
            return false;
        }
        try {
            double num = Double.parseDouble(value.trim());
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates password strength.
     * Requirements: minimum 8 characters, at least one letter and one number.
     * @param password The password to validate
     * @return true if password meets requirements
     */
    public static boolean isValidPassword(String password) {
        if (isNullOrEmpty(password) || password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        return hasLetter && hasDigit;
    }

    /**
     * Validates string length is within bounds.
     * @param value The string to validate
     * @param minLength Minimum length (inclusive)
     * @param maxLength Maximum length (inclusive)
     * @return true if length is within bounds
     */
    public static boolean isValidLength(String value, int minLength, int maxLength) {
        if (value == null) {
            return minLength == 0;
        }
        int length = value.length();
        return length >= minLength && length <= maxLength;
    }

    /**
     * Sanitizes input by trimming whitespace and removing potentially dangerous characters.
     * @param input The input to sanitize
     * @return Sanitized string or null if input was null
     */
    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        return input.trim()
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;");
    }

    /**
     * Safely parses an integer from a string.
     * @param value The string to parse
     * @param defaultValue The default value if parsing fails
     * @return Parsed integer or default value
     */
    public static int parseIntSafe(String value, int defaultValue) {
        if (isNullOrEmpty(value)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Safely parses a double from a string.
     * @param value The string to parse
     * @param defaultValue The default value if parsing fails
     * @return Parsed double or default value
     */
    public static double parseDoubleSafe(String value, double defaultValue) {
        if (isNullOrEmpty(value)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
