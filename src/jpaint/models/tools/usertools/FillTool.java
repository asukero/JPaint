/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintShape;
import jpaint.models.tools.ShiftTool;

/**
 *
 * @author Guillaume & thomas
 */
public class FillTool extends ShiftTool {

    /**
     *
     */
    public static final String iconPath = "icons/bucket.png";

    /**
     *
     */
    public static final String NAME = "Fill";

    /**
     *
     */
    public FillTool() {
        super(NAME, iconPath);
    }
    
    private void fillSelectedShapes(DrawingData drawingData) {
        for (JPaintShape s : drawingData.getSelectedShapes()) {
            s.setFillColor(drawingData.getFillColor());
            s.setStrokeColor(drawingData.getStrokeColor());
        }
        
        drawingData.getSelectedShapes().clear();
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {
        this.fillSelectedShapes(drawingData);
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseDraggedAction(DrawingData drawingData) {
        
    }
    
    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseClickedAction(DrawingData drawingData) {
        this.fillSelectedShapes(drawingData);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mousePressedAction(DrawingData drawingData) {
        
    }
}
