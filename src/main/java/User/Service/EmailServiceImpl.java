package User.Service;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmailServiceImpl implements EmailService {
    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());

    // Configuration loaded from environment variables for security
    // Set: SMTP_USERNAME, SMTP_PASSWORD, SMTP_HOST, SMTP_PORT, COMPANY_NAME
    private static final String COMPANY_NAME = getEnvOrDefault("COMPANY_NAME", "Carol's Boutique!");
    private static final String SMTP_HOST = getEnvOrDefault("SMTP_HOST", "smtp.gmail.com");
    private static final int SMTP_PORT = Integer.parseInt(getEnvOrDefault("SMTP_PORT", "587"));
    private static final String SMTP_USERNAME = getEnvOrDefault("SMTP_USERNAME", "");
    private static final String SMTP_PASSWORD = getEnvOrDefault("SMTP_PASSWORD", "");

    /**
     * Gets a configuration value from system property, environment variable, or default.
     * Priority: System Property > Environment Variable > Default Value
     */
    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return defaultValue;
    }

    @Override
    public void sendEmailToEmp(String email, String firstname, String role, String storeName) {
        if (email != null && !email.isEmpty()) {
            String htmlFilePath = "/htmlEmail.html";
            String modifiedHtmlContent = modifyHtmlFile(htmlFilePath, firstname, role, storeName);
            if (modifiedHtmlContent != null) {
                Email newEmpEmail = EmailBuilder.startingBlank()
                        .from(COMPANY_NAME, SMTP_USERNAME)
                        .to(email)
                        .withSubject("Welcome To " + COMPANY_NAME)
                        .withHTMLText(modifiedHtmlContent)
                        //.withAttachment(filename, createFileDataSource(filename))
                        .buildEmail();

                Mailer mailer = MailerBuilder
                        .withSMTPServer(SMTP_HOST, SMTP_PORT, SMTP_USERNAME, SMTP_PASSWORD)
                        .buildMailer();

                try {
                    mailer.sendMail(newEmpEmail);
                    logger.log(Level.INFO, "Email sent successfully to {0}", email);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error sending email to {0}: {1}", new Object[]{email, e.getMessage()});
                }
            } else {
                logger.severe("Failed to modify HTML content.");
            }
        } else {
            logger.severe("Failed to send email. Email address not found.");
        }
    }

    @Override
    public void sendEmailToPaidCustomer(String email, String firstname) {

    }

    @Override
    public void sendEmailToManager(String email, String firstname, String role) {

    }

    @Override
    public void sendEmailToRegMan(String email, String firstname, String store_id) {

    }

private static String modifyHtmlFile(String htmlFilePath, String firstname, String role, String storeName) {
        try {
            // Load the file from the resources directory
            InputStream inputStream = EmailServiceImpl.class.getResourceAsStream(htmlFilePath);
            if (inputStream == null) {
                throw new NoSuchFileException("htmlEmail.html not found");
            }

            // Read the content of the file using BufferedReader
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String htmlContent = reader.lines().collect(Collectors.joining("\n"));

                // Modify the email content by replacing placeholders
                htmlContent = htmlContent.replace("{{firstname}}", firstname);
                htmlContent = htmlContent.replace("{{role}}", role);
                htmlContent = htmlContent.replace("{{storeName}}", storeName);

                return htmlContent;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to modify HTML content.", e);
            return null;
        }
    }
}
