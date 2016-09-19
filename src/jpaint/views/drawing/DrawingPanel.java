/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.views.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import jpaint.controllers.drawing.DrawingPanelKeyEventHandler;
import jpaint.controllers.drawing.DrawingPanelMouseEventHandler;
import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintShape;

/**
 *
 * @author Guillaume
 */
public class DrawingPanel extends JPanel implements Observer {

    /**
     * DrawingPanel is the main panel where the user will be allowed to draw on
     */
    public static final Color DEFAULT_COLOR = Color.WHITE;
    private List<JPaintShape> jPaintShapes = null; // main shapes
    private List<JPaintShape> selectedJPaintShapes = null; // shapes which are selected by a tool
    private List<JPaintShape> temporaryJPaintShapes = null; // temporary shapes (graphical effects, etc.)
    
    /**
     *
     * @param drawingData
     * @throws ClassNotFoundException
     */
    public DrawingPanel(DrawingData drawingData) throws ClassNotFoundException {
        this.setBackground(DrawingPanel.DEFAULT_COLOR);
        drawingData.addObserver(this);
        DrawingPanelMouseEventHandler drawingPanelMouseEventHandler = new DrawingPanelMouseEventHandler(drawingData, this);
        DrawingPanelKeyEventHandler drawingPanelKeyEventHandler = new DrawingPanelKeyEventHandler(drawingData, this);
        this.setFocusable(true);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            this.drawAllShapes(g2d);
        }
    }
    
    private void drawShape(Graphics2D g2d, JPaintShape s) {
        g2d.setPaint(s.getFillColor());
        g2d.fill(s.getShape());
        g2d.setPaint(s.getStrokeColor());
        g2d.setStroke(s.getStroke());
        g2d.draw(s.getShape());
    }
    
    private void drawTemporaryShape(Graphics2D g2d, JPaintShape s) {
        Color tmp = s.getFillColor();
        /* in order to have a different color than the final shape */
        Float r = ((float) tmp.getRed()/255);
        Float g = ((float) tmp.getBlue()/255);
        Float b = ((float) tmp.getGreen()/255);
        Color c = new Color(r, g, b, .5f);
        
        g2d.setPaint(c);
        g2d.fill(s.getShape());
        g2d.setPaint(s.getStrokeColor());
        g2d.setStroke(s.getStroke());
        g2d.draw(s.getShape());
    }
    
    private void drawSelectedShape(Graphics2D g2d, JPaintShape s) {
        Rectangle hitBoxSelection = s.getShape().getBounds();
        g2d.setPaint(new Color(0.2f, 1f, 0.7f, 0.8f));
        float dashAttributes[] = { 7.0f };
        g2d.setStroke(new BasicStroke(5.0f, BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_MITER, 10.0f, dashAttributes, 0.0f));
        g2d.draw(hitBoxSelection);
    }
    
    private void drawAllShapes(Graphics2D g2d) {
        if (this.jPaintShapes != null) {
            for (JPaintShape s : this.jPaintShapes) {
                this.drawShape(g2d, s);
            }
        }
        
        if (this.temporaryJPaintShapes != null) {
            for (JPaintShape s : this.temporaryJPaintShapes) {
                this.drawTemporaryShape(g2d, s);
            }
        }
        
        if (this.selectedJPaintShapes != null) {
            for (JPaintShape s : this.selectedJPaintShapes) {
                this.drawSelectedShape(g2d, s);
            }
        }
    }    

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DrawingData) {
            DrawingData drawingData = (DrawingData) o;
            this.jPaintShapes = drawingData.getShapes();
            this.temporaryJPaintShapes = drawingData.getTemporaryShapes();
            this.selectedJPaintShapes = drawingData.getSelectedShapes();
            this.repaint();
        }
    }
}