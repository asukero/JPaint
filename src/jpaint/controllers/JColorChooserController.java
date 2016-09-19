/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;
import jpaint.models.SelectedComponentsModel;
import jpaint.views.GroundPanel;

/**
 *
 * @author thomas
 */
public class JColorChooserController extends MouseAdapter {

    private SelectedComponentsModel selectedComponentsModel;

    /**
     * sets the current foreground or background color selected
     *
     * @param selectedComponentsModel
     */
    public JColorChooserController(SelectedComponentsModel selectedComponentsModel) {
        this.selectedComponentsModel = selectedComponentsModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        GroundPanel sourcePanel = (GroundPanel) e.getSource();
        Color color = JColorChooser.showDialog(null, null, sourcePanel.getBackground());
        if ("foreground".equals(sourcePanel.getTitle())) {
            selectedComponentsModel.setForegroundColor(color);
        } else {
            selectedComponentsModel.setBackgroundColor(color);
        }
    }

}
