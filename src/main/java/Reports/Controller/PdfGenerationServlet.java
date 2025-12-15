package Reports.Controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

@WebServlet("/generatePdf")
public class PdfGenerationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PdfGenerationServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> reportTypes = getReportTypesFromRequest(request);

        try (PDDocument document = new PDDocument()) {
            for (String reportType : reportTypes) {
                String chartDataUrl = request.getParameter(reportType);
                if (chartDataUrl != null) {
                    byte[] chartImageData = decodeDataUrl(chartDataUrl);
                    if (chartImageData != null) {
                        PDPage page = new PDPage(PDRectangle.A4);
                        document.addPage(page);

                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, chartImageData, "image/png");
                        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true)) {
                            float pageWidth = page.getMediaBox().getWidth();
                            float pageHeight = page.getMediaBox().getHeight();
                            float imageWidth = pdImage.getWidth();
                            float imageHeight = pdImage.getHeight();
                            contentStream.drawImage(pdImage, 0, 0, pageWidth, pageHeight);
                        }
                    }
                }
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"reports.pdf\"");
            document.save(response.getOutputStream());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error generating PDF", e);
            throw new ServletException("Error generating PDF", e);
        }
    }

    private List<String> getReportTypesFromRequest(HttpServletRequest request) {
        List<String> reportTypes = new ArrayList<>();
        String[] types = request.getParameterValues("reportTypes");
        if (types != null) {
            for (String type : types) {
                reportTypes.add(type);
            }
        }
        return reportTypes;
    }

    private byte[] decodeDataUrl(String dataUrl) {
        String[] parts = dataUrl.split(",");
        if (parts.length > 1) {
            String base64Data = parts[1];
            return java.util.Base64.getDecoder().decode(base64Data);
        }
        return null;
    }
}
