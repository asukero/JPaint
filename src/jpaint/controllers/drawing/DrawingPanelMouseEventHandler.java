/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers.drawing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import jpaint.models.drawing.DrawingData;
import jpaint.views.drawing.DrawingPanel;

/**
 *
 * @author ar3s
 */
public class DrawingPanelMouseEventHandler extends MouseAdapter {
    private DrawingData drawingData;
    
    /**
     *
     * @param drawingData
     * @param drawingPanel
     */
    public DrawingPanelMouseEventHandler(DrawingData drawingData, DrawingPanel drawingPanel) {
        this.drawingData = drawingData;
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.drawingData.setMouseClickedPoint(e.getPoint());
        this.drawingData.mouseClickedAction();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.drawingData.setMousePressedPoint(e.getPoint());
        this.drawingData.mousePressedAction();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.drawingData.setMouseReleasedPoint(e.getPoint());
        this.drawingData.mouseReleasedAction();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        this.drawingData.setMouseCurrentPoint(e.getPoint());
        this.drawingData.mouseDraggedAction();
    }
}