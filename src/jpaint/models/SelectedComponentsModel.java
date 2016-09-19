/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import jpaint.models.tools.Tool;
import java.awt.Color;
import java.io.File;
import jpaint.JPaintClassLoader;

/**
 * Main Model, knows the current selected tool, the colors selected and the list of tools
 * @author thomas
 */
public class SelectedComponentsModel extends Model {

    private Tool selectedTool;
    private Color backgroundColor = new Color(255, 0, 0);
    private Color foregroundColor = new Color(0, 0, 255);
    private ToolList toolsList = new ToolList();

    /**
     *
     */
    public SelectedComponentsModel() {

        loadTools();
       

    }

    /**
     *
     * @return
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setChanged();
        this.notifyObservers(new OperatedColor(backgroundColor, "background"));
    }

    /**
     *
     * @return
     */
    public Color getForegroundColor() {
        return foregroundColor;
    }

    /**
     *
     * @param foregroundColor
     */
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        this.setChanged();
        this.notifyObservers(new OperatedColor(foregroundColor, "foreground"));
    }

    /**
     *
     * @return
     */
    public Tool getSelectedTool() {
        return selectedTool;
    }

    /**
     *
     * @param selectedTool
     */
    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;
        this.setChanged();
        this.notifyObservers(this.selectedTool);
    }

    /**
     *
     * @return
     */
    public ToolList getToolsList() {
        return toolsList;
    }
    /**
     * loads every class located in the tools folder
     */
    private void loadTools() {

        File folder = new File("tools");
        File[] FilesList = folder.listFiles();

        for (File file : FilesList) {
            if (file.isFile()) {
                loadTool(file);
            }
        }

    }

    /**
     * load a tool from file with reflection
     * Checks if the object which is instantiated from the loaded class is a Tool and adds it in the toolsList 
     * @param file
     */
    public void loadTool(File file) {
        JPaintClassLoader cl = new JPaintClassLoader();
                
       Class c = cl.addClass(file.toPath());

        
        

        try {
            
            Object o = c.newInstance();
            if (o instanceof Tool) {
                Tool tool = (Tool) o;
                toolsList.add(tool);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println(e.getMessage());
        }
        

    }
}
