/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import java.awt.Color;

/**
 * Class that is send to notify the views of a change in the background or foreground color
 * @author thomas
 */
public class OperatedColor {
    private Color color;
    private String colorName;

    /**
     *
     * @param color
     * @param colorName
     */
    public OperatedColor(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public String getColorName() {
        return colorName;
    }
    
    
    
    
}
