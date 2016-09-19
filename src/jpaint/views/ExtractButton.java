/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author thomas
 */
public class ExtractButton extends JButton{

    /**
     *
     */
    public ExtractButton() {
    }

    /**
     * JButton for the Extract Tools when it's pressend a file chooser popup appears
     * @param text use to check which button is pressed for the listener
     * @param icon
     */
    public ExtractButton(String text, Icon icon) {
        super(text, icon);
    }
    
    
    
}
