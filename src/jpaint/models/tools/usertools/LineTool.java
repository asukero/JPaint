/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import jpaint.models.drawing.JPaintShape;
import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintLine;
import jpaint.models.tools.Tool;

/**
 *
 * @author Guillaume & thomas
 */
public class LineTool extends Tool {

    /**
     *
     */
    public static final String iconPath = "icons/line.png";

    /**
     *
     */
    public static final String NAME = "Line";
    
    /**
     *
     */
    public LineTool() {
        super(NAME, iconPath,true);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {        
        JPaintShape newJPaintLine = new JPaintLine(drawingData.getMousePressedPoint(), drawingData.getMouseReleasedPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getShapes().add(newJPaintLine);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseDraggedAction(DrawingData drawingData) {        
        JPaintShape newJPaintLine = new JPaintLine(drawingData.getMousePressedPoint(), drawingData.getMouseCurrentPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getTemporaryShapes().add(newJPaintLine);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseClickedAction(DrawingData drawingData) {
        
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mousePressedAction(DrawingData drawingData) {
        
    }
}
