/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JToolBar;

/**
 *
 * @author thomas
 */
public class ToolBarOrientationListener implements PropertyChangeListener {

    private final JComponent comp;

    /**
     *
     * @param comp
     */
    public ToolBarOrientationListener(JComponent comp) {
        this.comp = comp;
    }

    /**
     * Va permettre d'afficher correctement les composants de la toolBar à la verticale lorsque que celle-ci l'est aussi
     * Will allow to display correctly the components of the toolBar when it is displayed vertically  
     * @param e 
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String name = e.getPropertyName();
        if (name.equals("orientation")) {
            int o = ((Integer) e.getNewValue()).intValue();

            if (o == JToolBar.VERTICAL) {
                comp.setLayout(new BoxLayout(comp, BoxLayout.PAGE_AXIS)); //ou GridLayout(0,1)
            } else {
                comp.setLayout(new BoxLayout(comp, BoxLayout.LINE_AXIS)); //ou GridLayout(1,0)
            }
        }
    }
}