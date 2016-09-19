/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers.drawing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import jpaint.models.drawing.DrawingData;
import jpaint.views.drawing.DrawingPanel;

/**
 *
 * @author ar3s
 */
public class DrawingPanelKeyEventHandler extends KeyAdapter {
    private DrawingData drawingData;
    
    /**
     *
     * @param drawingData
     * @param drawingPanel
     */
    public DrawingPanelKeyEventHandler(DrawingData drawingData, DrawingPanel drawingPanel) {
        this.drawingData = drawingData;
        
        InputMap im = drawingPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = drawingPanel.getActionMap();
        
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        im.put(keyStroke, "delete");
        am.put("delete", new DeleteAction("delete", this.drawingData));
    }
}
