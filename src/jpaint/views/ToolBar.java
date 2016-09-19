/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import java.awt.FlowLayout;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import jpaint.models.OperatedTool;

/**
 *
 * @author thomas
 */
public class ToolBar extends JToolBar implements Observer {

    private ButtonGroup buttonGroup = new ButtonGroup();

    private ColorGroundPanel colorGroundPanel = new ColorGroundPanel(new FlowLayout());
    private JButton addToolButton = new JButton("+");
    private JButton removeToolButton = new JButton("-");
    private JPanel buttonPanel = new JPanel();
    private JPanel toolPanel = new JPanel();

    /**
     *
     */
    public ToolBar() {

        this.setOrientation(HORIZONTAL);
        this.add(this.toolPanel);
        this.addPropertyChangeListener(new ToolBarOrientationListener(this.toolPanel));
        this.addPropertyChangeListener(new ToolBarOrientationListener(this.buttonPanel));
        this.addPropertyChangeListener(new ToolBarOrientationListener(this.colorGroundPanel));

    }

    /**
     *
     * @return
     */
    public ColorGroundPanel getColorGroundPanel() {
        return colorGroundPanel;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     *
     * @return
     */
    public JButton getAddToolButton() {
        return addToolButton;
    }

    /**
     *
     * @return
     */
    public JButton getRemoveToolButton() {
        return removeToolButton;
    }

    /**
     *
     * @return
     */
    public JPanel getToolPanel() {
        return toolPanel;
    }

    /**
     * this method is called in the initView of the JPaint class. The next component are added on the toolbar after that the tools are loaded so they can't be instantiate in the constructor
     */
    public void addNextComponents() {
        this.addSeparator();
        this.add(colorGroundPanel);
        buttonPanel.add(addToolButton);
        buttonPanel.add(removeToolButton);
        this.add(buttonPanel);

    }
    /**
     * updates the toolBar from the modification that happened on the toolList
     * Checks if a tool was either removed or added and adjust the toolbar
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof OperatedTool) {
            OperatedTool op = (OperatedTool) arg;
            if (op.isAdded()) {
                buttonGroup.add(op.getToolButton());
                toolPanel.add(op.getToolButton());
                toolPanel.revalidate();

            } else {
                Enumeration<AbstractButton> buttonEnum = buttonGroup.getElements();
                while (buttonEnum.hasMoreElements()) {
                    AbstractButton abstractButton = buttonEnum.nextElement();
                    if (abstractButton.equals(op.getToolButton())) {
                        buttonGroup.remove(abstractButton);
                        toolPanel.remove(abstractButton);
                    }

                }
            }
        }
    }

}
