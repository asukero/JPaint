/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author thomas
 */
public class ColorGroundPanel extends JPanel {

    private GroundPanel foreGroundPanel = new GroundPanel("foreground");
    private GroundPanel backGroundPanel = new GroundPanel("background");

    /**
     *
     * @param layout
     */
    public ColorGroundPanel(LayoutManager layout) {
        super(layout);

        foreGroundPanel.setPreferredSize(new Dimension(40, 20));
        foreGroundPanel.setMaximumSize(new Dimension(40, 20));
        foreGroundPanel.setBackground(Color.red);

        backGroundPanel.setBackground(Color.blue);
        backGroundPanel.setPreferredSize(new Dimension(40, 20));
        backGroundPanel.setMaximumSize(new Dimension(40, 20));

        this.add(foreGroundPanel);
        this.add(backGroundPanel);

    }

    /**
     *
     * @return
     */
    public GroundPanel getForeGroundPanel() {
        return foreGroundPanel;
    }

    /**
     *
     * @return
     */
    public GroundPanel getBackGroundPanel() {
        return backGroundPanel;
    }

}
