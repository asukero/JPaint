/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import jpaint.models.SelectedComponentsModel;
import jpaint.models.ToolList;
import jpaint.models.tools.ExtractTool;
import jpaint.models.tools.Tool;

/**
 *
 * @author thomas
 */
public class ManageToolController extends MouseAdapter {

    private SelectedComponentsModel selectedComponentsModel;
    private ToolSelectedController toolSelectedController;
    private ImageSaverController imageSaverController;

    /**
     *
     * @param selectedComponentsModel
     * @param toolSelectedController
     * @param imageSaverController
     */
    public ManageToolController(SelectedComponentsModel selectedComponentsModel, ToolSelectedController toolSelectedController, ImageSaverController imageSaverController) {
        this.selectedComponentsModel = selectedComponentsModel;
        this.toolSelectedController = toolSelectedController;
        this.imageSaverController = imageSaverController;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("+")) {
                addNewTool();
            } else {
                removeCurrentTool();
            }
        }
    }

    /**
     * add new a tool from ./tool and adds listener on the loaded tool's button
     */
    public void addNewTool() {
        JFileChooser fileChooser = new JFileChooser(new File("./tools"));
        int action = fileChooser.showOpenDialog(null);
        if (action == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedComponentsModel.loadTool(file);

            ToolList toolList = selectedComponentsModel.getToolsList();
            Tool loadedTool = toolList.get(toolList.size() - 1);
            if (loadedTool instanceof ExtractTool) {
                loadedTool.getToolButton().addMouseListener(imageSaverController);
            } else {
                loadedTool.getToolButton().addMouseListener(toolSelectedController);
            }
            

        }
    }

    /**
     * removes selected tool
     */
    public void removeCurrentTool() {
        Tool toRemoveTool = null;
        for (Tool tool : selectedComponentsModel.getToolsList().getToolList()) {
            if (tool.equals(selectedComponentsModel.getSelectedTool())) {
                toRemoveTool = tool;

            }
        }
        if (toRemoveTool != null) {
            selectedComponentsModel.getToolsList().remove(toRemoveTool);
            selectedComponentsModel.setSelectedTool(null);
        }

    }

}
