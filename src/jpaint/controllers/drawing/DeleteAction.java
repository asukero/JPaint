/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers.drawing;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import jpaint.models.drawing.DrawingData;

/**
 *
 * @author ar3s
 */
public class DeleteAction extends AbstractAction {

    private String command;
    private DrawingData drawingData;

    /**
     *
     * @param command
     * @param drawingData
     */
    public DeleteAction(String command, DrawingData drawingData) {
        this.command = command;
        this.drawingData = drawingData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.command.equalsIgnoreCase("delete")) {
            this.drawingData.deleteSelected();
        }
    }
}
