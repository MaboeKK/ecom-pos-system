package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class.
 * Provides centralized database connection management.
 *
 * Configuration is loaded from environment variables for security.
 * Set the following environment variables before running:
 * - DB_URL: Database connection URL (default: jdbc:mysql://localhost:3306/ecom)
 * - DB_USER: Database username
 * - DB_PASSWORD: Database password
 */
public class DbConn {

    private static final String URL = getEnvOrDefault("DB_URL", "jdbc:mysql://localhost:3306/ecom?useSSL=false");
    private static final String USER = getEnvOrDefault("DB_USER", "root");
    private static final String PASSWORD = getEnvOrDefault("DB_PASSWORD", "");

    /**
     * Gets a configuration value from system property, environment variable, or default.
     * Priority: System Property > Environment Variable > Default Value
     * @param key The property/environment variable name
     * @param defaultValue The default value if not set
     * @return The configuration value
     */
    private static String getEnvOrDefault(String key, String defaultValue) {
        // First try system property (set via -D flag)
        String value = System.getProperty(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Then try environment variable
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return defaultValue;
    }

    /**
     * Establishes and returns a database connection.
     * @return A Connection object to the database
     * @throws RuntimeException if connection fails
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
