/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views;

import jpaint.views.drawing.DrawingPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jpaint.models.drawing.DrawingData;

/**
 *
 * @author thomas
 */
public class MainFrame extends JFrame {

    static final String TITLE = "JPaint";
    private BottomBar bottomBar = new BottomBar(new FlowLayout(FlowLayout.LEFT));
    private ToolBar toolBar = new ToolBar();
    private JPanel drawingPanel;
    private List<DrawingData> drawingDatas;

    /**
     *
     */
    public MainFrame() {
        super(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);

        this.setLayout(new BorderLayout());
        this.add(toolBar, BorderLayout.NORTH);
        this.add(bottomBar, BorderLayout.SOUTH);
        this.drawingDatas = new ArrayList<>();

        try {
            DrawingData drawingData = new DrawingData();
            this.drawingDatas.add(drawingData);
            this.drawingPanel = new DrawingPanel(drawingData);
            this.add(drawingPanel, BorderLayout.CENTER);
            drawingData.setDrawingPanel((DrawingPanel) this.drawingPanel);

            
            
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        this.setVisible(true);

    }

    /**
     *
     * @return
     */
    public BottomBar getBottomBar() {
        return bottomBar;
    }

    /**
     *
     * @return
     */
    public ToolBar getToolBar() {
        return toolBar;
    }

    /**
     *
     * @return
     */
    public List<DrawingData> getDrawingDatas() {
        return this.drawingDatas;
    }

    /**
     *
     * @return
     */
    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

}
