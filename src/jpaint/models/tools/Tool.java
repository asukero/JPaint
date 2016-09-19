/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import jpaint.models.drawing.DrawingData;
import jpaint.models.Model;

/**
 *
 * @author thomas
 */
public abstract class Tool extends Model {

    /**
     *
     */
    protected String name;

    /**
     *
     */
    protected boolean isSelected;

    /**
     *
     */
    protected AbstractButton toolButton;

    /**
     *
     */
    protected Image icon;

    /**
     *
     * @param name
     */
    public Tool(String name) {
        this.name = name;

    }

    /**
     *
     * @param name
     * @param iconPath
     * @param isToggleTool
     */
    public Tool(String name, String iconPath, boolean isToggleTool) {
        this.name = name;
        if (isToggleTool) {
            this.toolButton = new JToggleButton();
        } else {
            this.toolButton = new JButton();
        }
        this.toolButton.setToolTipText(name);

        try {

            this.icon = ImageIO.read(getClass().getResource(iconPath));
            if (this.icon != null) {
                this.toolButton.setIcon(new ImageIcon(this.icon));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            try {
                this.icon = ImageIO.read(getClass().getResource("icons/default.png"));
                this.toolButton.setIcon(new ImageIcon(this.icon));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     * @param drawingData
     */
    public abstract void mouseReleasedAction(DrawingData drawingData);

    /**
     *
     * @param drawingData
     */
    public abstract void mouseDraggedAction(DrawingData drawingData);

    /**
     *
     * @param drawingData
     */
    public abstract void mouseClickedAction(DrawingData drawingData);

    /**
     *
     * @param drawingData
     */
    public abstract void mousePressedAction(DrawingData drawingData);

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public boolean isIsSelected() {
        return isSelected;
    }

    /**
     *
     * @param isSelected
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     *
     * @return
     */
    public AbstractButton getToolButton() {
        return toolButton;
    }

    /**
     *
     * @param toolButton
     */
    public void setToolButton(JToggleButton toolButton) {
        this.toolButton = toolButton;
    }

    /**
     *
     * @return
     */
    public Image getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(Image icon) {
        this.icon = icon;
    }

}
