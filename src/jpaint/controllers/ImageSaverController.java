/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers;

import com.sun.jna.Memory;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import jpaint.models.BufferedImageBuilder;
import jpaint.models.JNAMapper;

/**
 *
 * @author Guillaume & thomas
 */
public class ImageSaverController extends MouseAdapter {

    private BufferedImageBuilder bufferedImageBuilder = null;
    public static final String DEFAULT_EXPORT_PATH = "./default.png"; // default path for the saved image
    private JPanel mainPanel;

    public ImageSaverController() {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton && this.mainPanel != null) {
            
            this.bufferedImageBuilder = new BufferedImageBuilder(mainPanel);            
            JButton button = (JButton) e.getSource();
            JFileChooser fileChooser = new JFileChooser(new File(""));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            
            int action = fileChooser.showOpenDialog(null);
            String exportPath;

            if (action == JFileChooser.APPROVE_OPTION) {
                try {
                    exportPath = fileChooser.getSelectedFile().getPath();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.out.println("using default path : ./default.png");
                    exportPath = ImageSaverController.DEFAULT_EXPORT_PATH;
                }
                
                Memory imageBytes = this.bufferedImageBuilder.getImageBytes();
                int width = this.bufferedImageBuilder.getSizeX();
                int height = this.bufferedImageBuilder.getSizeY();

                switch (button.getToolTipText()) {
                    case "Export Blue Channel":
                        JNAMapper.INSTANCE.cutARGBRed(imageBytes, width, height);
                        JNAMapper.INSTANCE.cutARGBGreen(imageBytes, width, height);
                        break;
                    case "Export Red Channel":
                        JNAMapper.INSTANCE.cutARGBBlue(imageBytes, width, height);
                        JNAMapper.INSTANCE.cutARGBGreen(imageBytes, width, height);
                        break;
                    case "Export Green Channel":
                        JNAMapper.INSTANCE.cutARGBRed(imageBytes, width, height);
                        JNAMapper.INSTANCE.cutARGBBlue(imageBytes, width, height);
                        break;
                    case "Export Mirror Channel":
                        JNAMapper.INSTANCE.mirror(imageBytes, width, height);
                        break;
                    default:
                        break;
                }

                this.exportBuffer(exportPath);
            }
        }
    }

    /* we assume the image exported will always be .png here */
    /* we could make it more generic */
    private String checkExportPath(String exportPath) {
        if (!exportPath.toLowerCase().endsWith(".png")) {
            exportPath += ".png";
        }

        return exportPath;
    }

    /* this function will convert the buffer into a png image written on the disk */
    public void exportBuffer(String exportPath) {
        if (this.bufferedImageBuilder != null) {
            
            Memory imageBytes = this.bufferedImageBuilder.getImageBytes();
            int width = this.bufferedImageBuilder.getSizeX();
            int height = this.bufferedImageBuilder.getSizeY();
            int[] bitPerComponent = {8, 8, 8, 8}; // 8 bit per component (ARGB)
            int scanlineStride = width * 4; // each pixel row takes up width * 4 (ARGB)
            int pixelStride = 4; // each pixel takes up 4 bytes
            int[] bandsOffset = {2, 1, 0, 3}; /* B G R A (ARGB) */
            
            boolean hasAlpha = true;
            boolean isAlphaPremultiplied = false;
            
            byte[] pixels = imageBytes.getByteArray(0, (int) imageBytes.size());               
            DataBufferByte pixelsBuffer = new DataBufferByte(pixels, pixels.length);                        
            WritableRaster writableRaster = Raster.createInterleavedRaster(pixelsBuffer, width, height, scanlineStride, pixelStride, bandsOffset, null);            
            ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            ColorModel colorModel = new ComponentColorModel(colorSpace, bitPerComponent, hasAlpha, isAlphaPremultiplied, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
            BufferedImage imageToSave = new BufferedImage(colorModel, writableRaster, false, null);
            
            try {
                ImageIO.write(imageToSave, "PNG", new File(this.checkExportPath(exportPath)));
            } catch (IOException ex) {
                Logger.getLogger(ImageSaverController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setBufferedImageBuilder(BufferedImageBuilder bufferedImageBuilder) {
        this.bufferedImageBuilder = bufferedImageBuilder;
    }
    
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
