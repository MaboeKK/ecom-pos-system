package User.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlFileManipulation {

    private static final Logger logger = Logger.getLogger(HtmlFileManipulation.class.getName());

    public static String modifyHtmlFile(String htmlFilePath) {
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
            return htmlContent;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading HTML file: " + htmlFilePath, e);
            return null;
        }
    }
}