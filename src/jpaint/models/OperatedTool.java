/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import javax.swing.AbstractButton;

/**
 * Class that is send to notify the views of a change in the toolList
 * Either removing or adding a tool
 * It only passes the abstract button that will be displayed on the toolBar
 * @author thomas
 */
public class OperatedTool {

    private AbstractButton toolButton;
    private boolean added;

    /**
     *
     * @param toolButton
     * @param added
     */
    public OperatedTool(AbstractButton toolButton, boolean added) {
        this.toolButton = toolButton;
        this.added = added;
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
    public void setToolButton(AbstractButton toolButton) {
        this.toolButton = toolButton;
    }

    /**
     *
     * @return
     */
    public boolean isAdded() {
        return added;
    }

    /**
     *
     * @param added
     */
    public void setIsAdded(boolean added) {
        this.added = added;
    }
    

}
