/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import com.sun.jna.Memory;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.swing.JPanel;

/**
 *
 * @author Guillaume
 */
public class BufferedImageBuilder {
    private BufferedImage image;
    private Memory imageBytes;
    private int sizeX = 0;
    private int sizeY = 0;
    
    /**
     *
     */
    public BufferedImageBuilder() {
        
    }
    
    /**
     *
     * @param jPanel
     */
    public BufferedImageBuilder(JPanel jPanel) {
        this.image = new BufferedImage(jPanel.getSize().width, jPanel.getSize().height, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics g2 = this.image.getGraphics();
        jPanel.printAll(g2);
        
        this.writeImageBits();
    }
    
    private void writeImageBits() {
        int ARGBSize = 4;
        this.sizeX = this.image.getWidth();
        this.sizeY = this.image.getHeight();              
        int pixelNumber = sizeX * sizeY;
        Raster raster = this.image.getData();        
        this.imageBytes = new Memory(pixelNumber * ARGBSize);  
        int[] pixels = new int[pixelNumber]; // we will have a pixelNumber array which will contain 32 bits integers
        int[] currentPixel = new int[ARGBSize]; // a current pixel need 4 components (ARGB)
        
        for (int y = 0; y < this.sizeY; y++) {
            for (int x = 0; x < this.sizeX; x++) {
                raster.getPixel(x, y, currentPixel);     
                /* A R G B */
                int red = currentPixel[0] << 16;
                int green = currentPixel[1] << 8;
                int blue = currentPixel[2];
                int alpha = currentPixel[3] << 24;     
                int argb = alpha | red | green | blue; // 32 bits integer
                pixels[y * this.sizeX + x] = argb;
            }
        }
            
        this.imageBytes.write(0, pixels, 0, pixels.length);
    }

    /**
     *
     * @return
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     *
     * @return
     */
    public Memory getImageBytes() {
        return this.imageBytes;
    }

    /**
     *
     * @return
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     *
     * @return
     */
    public int getSizeY() {
        return this.sizeY;
    }
}