/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintEllipse;
import jpaint.models.drawing.JPaintShape;
import jpaint.models.tools.Tool;

/**
 *
 * @author Guillaume & thomas
 */
public class EllipseTool extends Tool {

    /**
     *
     */
    public static final String iconPath = "icons/oval.png";

    /**
     *
     */
    public static final String NAME = "Ellipse";

    /**
     *
     */
    public EllipseTool() {
        super(NAME, iconPath,true);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {        
        JPaintShape jPaintEllipse = new JPaintEllipse(drawingData.getMousePressedPoint(), drawingData.getMouseReleasedPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getShapes().add(jPaintEllipse);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseDraggedAction(DrawingData drawingData) {        
        JPaintShape jPaintEllipse = new JPaintEllipse(drawingData.getMousePressedPoint(), drawingData.getMouseCurrentPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getTemporaryShapes().add(jPaintEllipse);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mousePressedAction(DrawingData drawingData) {
        
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseClickedAction(DrawingData drawingData) {
        
    }
}
