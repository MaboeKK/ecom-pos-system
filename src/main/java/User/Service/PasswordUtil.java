package User.Service;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing and verification using BCrypt.
 * BCrypt automatically handles salting and is resistant to rainbow table attacks.
 */
public class PasswordUtil {

    // Work factor for BCrypt (higher = more secure but slower, 10-12 is recommended)
    private static final int WORK_FACTOR = 12;

    /**
     * Hashes a plain text password using BCrypt.
     * @param plainPassword The plain text password to hash
     * @return The hashed password
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(WORK_FACTOR));
    }

    /**
     * Verifies a plain text password against a hashed password.
     * @param plainPassword The plain text password to verify
     * @param hashedPassword The hashed password to check against
     * @return true if the password matches, false otherwise
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            // Handle case where hashedPassword is not a valid BCrypt hash
            // This allows backward compatibility with existing plain-text passwords
            return plainPassword.equals(hashedPassword);
        }
    }

    /**
     * Checks if a password is already hashed with BCrypt.
     * @param password The password to check
     * @return true if the password appears to be a BCrypt hash
     */
    public static boolean isHashed(String password) {
        if (password == null || password.length() < 60) {
            return false;
        }
        // BCrypt hashes start with $2a$, $2b$, or $2y$
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }
}
