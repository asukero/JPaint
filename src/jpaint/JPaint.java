/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint;

import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import jpaint.controllers.ImageSaverController;
import jpaint.controllers.JColorChooserController;
import jpaint.controllers.ManageToolController;
import jpaint.controllers.ToolSelectedController;
import jpaint.models.SelectedComponentsModel;
import jpaint.models.drawing.DrawingData;
import jpaint.models.tools.Tool;
import jpaint.views.MainFrame;
import jpaint.views.ToolBar;

/**
 *
 * @author thomas
 */
public class JPaint {

    private SelectedComponentsModel selectedComponentsModel;

    private JColorChooserController colorChooserController;
    private ToolSelectedController toolSelectedController;
    private ManageToolController manageToolController;
    private ImageSaverController imageSaverController;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JPaint main = new JPaint();
        main.initModels();
        main.initControllers();
        main.initViews();

    }

    /**
     * 
     */
    public void initModels() {

        selectedComponentsModel = new SelectedComponentsModel();

    }

    /**
     * init the GUI & adds GUI observers on model
     */
    public void initViews() {
        MainFrame frame = new MainFrame();
        imageSaverController.setMainPanel(frame.getDrawingPanel());
        ToolBar toolBar = frame.getToolBar();
        selectedComponentsModel.addObserver(toolBar.getColorGroundPanel().getBackGroundPanel());
        selectedComponentsModel.addObserver(toolBar.getColorGroundPanel().getForeGroundPanel());
        selectedComponentsModel.addObserver(frame.getBottomBar());
        selectedComponentsModel.getToolsList().addObserver(toolBar);

        List<DrawingData> drawingDatas = frame.getDrawingDatas();

        for (DrawingData drawingData : drawingDatas) {
            selectedComponentsModel.addObserver(drawingData);
        }

        toolBar.getColorGroundPanel().getForeGroundPanel().addMouseListener(colorChooserController);
        toolBar.getColorGroundPanel().getBackGroundPanel().addMouseListener(colorChooserController);

        for (Tool tool : selectedComponentsModel.getToolsList().getToolList()) {

            toolBar.getButtonGroup().add(tool.getToolButton());
            toolBar.getToolPanel().add(tool.getToolButton());

        }

        toolBar.addNextComponents();
        toolBar.getAddToolButton().addMouseListener(manageToolController);
        toolBar.getRemoveToolButton().addMouseListener(manageToolController);

        Enumeration<AbstractButton> abEnumeration = toolBar.getButtonGroup().getElements();
        while (abEnumeration.hasMoreElements()) {
            AbstractButton button = abEnumeration.nextElement();
            if (button instanceof JToggleButton) {
               
                button.addMouseListener(toolSelectedController);

            } else {
                
                button.addMouseListener(imageSaverController);
            }
        }
        this.selectedComponentsModel.setSelectedTool(this.selectedComponentsModel.getToolsList().get(6));
        this.selectedComponentsModel.getToolsList().get(6).getToolButton().setSelected(true);
        frame.revalidate();
    }

    /**
     *
     */
    public void initControllers() {
        colorChooserController = new JColorChooserController(selectedComponentsModel);
        toolSelectedController = new ToolSelectedController(selectedComponentsModel);
        imageSaverController = new ImageSaverController();
        manageToolController = new ManageToolController(selectedComponentsModel, toolSelectedController, imageSaverController);
        
    }

}
