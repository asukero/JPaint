/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import jpaint.models.SelectedComponentsModel;
import jpaint.models.tools.Tool;

/**
 *
 * @author thomas
 */
public class ToolSelectedController extends MouseAdapter{

    private SelectedComponentsModel selectedComponentsModel;

    /**
     *
     * @param selectedComponentsModel
     */
    public ToolSelectedController(SelectedComponentsModel selectedComponentsModel) {
        this.selectedComponentsModel = selectedComponentsModel;
        Tool defaultTool = this.selectedComponentsModel.getToolsList().get(0);        
        this.selectedComponentsModel.setSelectedTool(defaultTool);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            for (Tool tool : this.selectedComponentsModel.getToolsList().getToolList()) {
                    if (tool.getName().equals(button.getToolTipText())) {
                    tool.setIsSelected(true);
                    this.selectedComponentsModel.setSelectedTool(tool);
                    
                }
            }

        }

    }

}
