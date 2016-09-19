/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintRectangle;
import jpaint.models.drawing.JPaintShape;
import jpaint.models.tools.Tool;

/**
 *
 * @author Guillaume & thomas
 */
public class RectangleTool extends Tool {

    /**
     *
     */
    public static String iconPath = "icons/rectangle.png";

    /**
     *
     */
    public static String NAME = "Rectangle";

    /**
     *
     */
    public RectangleTool() {
        super(NAME, iconPath, true);

    }

    /**
     *
     * @param name
     * @param iconPath
     */
    public RectangleTool(String name, String iconPath) {
        super(name, iconPath, true);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {
        JPaintShape jPaintRectangle = new JPaintRectangle(drawingData.getMousePressedPoint(), drawingData.getMouseReleasedPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getShapes().add(jPaintRectangle);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseDraggedAction(DrawingData drawingData) {
        JPaintShape jPaintRectangle = new JPaintRectangle(drawingData.getMousePressedPoint(), drawingData.getMouseCurrentPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        drawingData.getTemporaryShapes().add(jPaintRectangle);
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
