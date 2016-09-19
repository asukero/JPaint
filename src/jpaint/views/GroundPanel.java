/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import jpaint.models.OperatedColor;

/**
 *
 * @author thomas
 */
public class GroundPanel extends JPanel implements Observer {

    private final String title;

    /**
     *
     * @param title
     */
    public GroundPanel(String title) {
        this.title = title;
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof OperatedColor) {
            OperatedColor operatedColor = (OperatedColor)arg;
            if(operatedColor.getColorName() == null ? title == null : operatedColor.getColorName().equals(title)){
            this.setBackground(operatedColor.getColor());
            }
        }
    }

}
