/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import jpaint.models.drawing.DrawingData;
import jpaint.models.tools.ExtractTool;

/**
 *
 * @author thomas
 */
public class ExportBlueChannelTool extends ExtractTool{

    /**
     *
     */
    public static final String iconPath = "icons/blue.png";

    /**
     *
     */
    public static final String NAME = "Export Blue Channel";

    /**
     *
     */
    public ExportBlueChannelTool() {
        super(NAME,iconPath);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {        
        
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
