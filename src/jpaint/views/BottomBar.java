/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import jpaint.models.tools.Tool;

/**
 *
 * @author thomas
 */
public class BottomBar extends JPanel implements Observer {

    private JLabel selectToolLabel = new JLabel("[none]");

    /**
     *
     * @param layout
     */
    public BottomBar(LayoutManager layout) {
        super(layout);
        this.add(selectToolLabel);
        this.add(new JLabel("tool selected"));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Tool) {
            Tool tool = (Tool) arg;
            selectToolLabel.setText("[" + tool.getName() + "]");
        } else if (arg == null) {
            selectToolLabel.setText("[none]");

        }

    }

}
