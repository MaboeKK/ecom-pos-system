/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarcodeGenerate;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Tshiamo
 */
public class Barcode {

    private static final Logger logger = Logger.getLogger(Barcode.class.getName());

    // Barcode output directory - configure via environment variable or use system temp
    // Set BARCODE_OUTPUT_DIR environment variable to customize
    private static final String BARCODE_DIR = getBarcodePath();

    private static String getBarcodePath() {
        String envPath = System.getenv("BARCODE_OUTPUT_DIR");
        if (envPath != null && !envPath.isEmpty()) {
            return envPath;
        }
        // Default to system temp directory + barcodes subfolder
        return System.getProperty("java.io.tmpdir") + File.separator + "barcodes";
    }

    public void barCode(String barcodeText) throws IOException{

        // Ensure barcode directory exists
        File barcodeDir = new File(BARCODE_DIR);
        if (!barcodeDir.exists()) {
            barcodeDir.mkdirs();
        }

        int count = 0;
        String filePath = BARCODE_DIR + File.separator + "barcode" + count + ".png";
        File myFile = new File(filePath);
        while(myFile.exists())
        {
            count++;
            filePath = BARCODE_DIR + File.separator + "barcode" + count + ".png";
            myFile = new File(filePath);
        }
        myFile.createNewFile();

        int width = 400;
        int height = 200;
        int textHeight = 20;
        String fileType = "png";


        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, width, height);
            BufferedImage image = new BufferedImage(width, height + textHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height + textHeight);
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (bitMatrix.get(i, j)) {
                        g2d.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(barcodeText);
            int x = (width - textWidth) / 2;
            int y = height + fm.getAscent() + 5; // Place text below the barcode

            g2d.drawString(barcodeText, x, y);

            g2d.dispose();
            File file = new File(filePath);
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    
    }
}
