/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import jpaint.models.OperatedColor;
import jpaint.models.SelectedComponentsModel;
import jpaint.models.tools.Tool;
import jpaint.models.tools.usertools.SelectorTool;
import jpaint.views.drawing.DrawingPanel;

/**
 *
 * @author Guillaume
 * DrawingData is an object which contains all the data needed by the tools
 */
public class DrawingData extends Observable implements Observer {
    private Point mouseClickedPoint;
    private Point mouseReleasedPoint;
    private Point mouseCurrentPoint;
    private Point mousePressedPoint;
    private List<JPaintShape> selectedShapes;
    private List<JPaintShape> shapes;
    private List<JPaintShape> temporaryShapes;

    /**
     *
     */
    public static final Color DEFAULT_COLOR = Color.WHITE;
    private Color fillColor = Color.blue;
    private Color strokeColor = Color.red;
    private Graphics2D graphics;
    private DrawingPanel drawingPanel;
    private Tool selectedTool = null;
    
    /**
     *
     */
    public DrawingData() {
        Point defaultPoint = new Point(0, 0);      
        this.mouseClickedPoint = defaultPoint;
        this.mouseReleasedPoint = defaultPoint;
        this.mouseCurrentPoint = defaultPoint;
        this.selectedShapes = new LinkedList<>();
        this.temporaryShapes = new LinkedList<>();
        this.shapes = new LinkedList<>();
        
        
    }
    
    /**
     *
     * @param drawingPanel
     */
    public DrawingData(DrawingPanel drawingPanel) {
        this();
        
        this.drawingPanel = drawingPanel;
    }
    
    private void beforeAction() {        
        this.temporaryShapes.clear();
        if (this.selectedTool == null) {
            this.selectedTool = new SelectorTool();
        }
    }
    
    private void updateDrawing() {
        this.setChanged();
        this.notifyObservers();        
    }
    
    /**
     *
     */
    public void deleteSelected() {
        this.beforeAction();
        int size = this.shapes.size();
        
        for (int i = 0; i < size; i++) {
            if (this.selectedShapes.contains(this.shapes.get(i))) {
                this.shapes.remove(i);
                size--;
                i--;
            }
        }
        
        this.selectedShapes.clear();
        this.updateDrawing();
    }
    
    /**
     *
     */
    public void mouseClickedAction() {
        this.beforeAction();
        this.selectedTool.mouseClickedAction(this);
        this.updateDrawing();
    }
    
    /**
     *
     */
    public void mouseDraggedAction() {
        this.beforeAction();
        this.selectedTool.mouseDraggedAction(this);
        this.updateDrawing();
    }
    
    /**
     *
     */
    public void mouseReleasedAction() {
        this.beforeAction();
        this.selectedTool.mouseReleasedAction(this);
        this.updateDrawing();
    }
    
    /**
     *
     */
    public void mousePressedAction() {
        this.beforeAction();
        this.selectedTool.mousePressedAction(this);
        this.updateDrawing();
    }
    
    private void resetAllPoints() {
        this.mouseClickedPoint = new Point(0, 0);
        this.mouseReleasedPoint = this.mouseClickedPoint;
        this.mouseCurrentPoint = this.mouseClickedPoint;
    }

    /**
     *
     * @return the mouse clicked point
     */
    public Point getMouseClickedPoint() {
        return this.mouseClickedPoint;
    }
    
    /**
     *
     * @param mouseClickedPoint
     */
    public void setMouseClickedPoint(Point mouseClickedPoint) {
        this.mouseClickedPoint = mouseClickedPoint;
    }

    /**
     *
     * @return the mouse released point
     */
    public Point getMouseReleasedPoint() {
        return this.mouseReleasedPoint;
    }
    
    /**
     *
     * @param mouseReleasedPoint
     */
    public void setMouseReleasedPoint(Point mouseReleasedPoint) {
        this.mouseReleasedPoint = mouseReleasedPoint;
    }

    /**
     *
     * @return the current mouse point
     */
    public Point getMouseCurrentPoint() {
        return this.mouseCurrentPoint;
    }
    
    /**
     *
     * @param mouseCurrentPoint
     */
    public void setMouseCurrentPoint(Point mouseCurrentPoint) {
        this.mouseCurrentPoint = mouseCurrentPoint;
    }

    /**
     *
     * @return the mouse pressed point
     */
    public Point getMousePressedPoint() {
        return this.mousePressedPoint;
    }

    /**
     *
     * @param mousePressedPoint
     */
    public void setMousePressedPoint(Point mousePressedPoint) {
        this.mousePressedPoint = mousePressedPoint;
    }

    /**
     *
     * @return
     */
    public List<JPaintShape> getSelectedShapes() {
        return this.selectedShapes;
    }
    
    /**
     *
     * @return
     */
    public List<JPaintShape> getShapes() {
        return this.shapes;
    }    

    /**
     *
     * @return
     */
    public List<JPaintShape> getTemporaryShapes() {
        return this.temporaryShapes;
    }
    
    /**
     *
     * @return
     */
    public Color getStrokeColor() {
        return this.strokeColor;
    }

    /**
     *
     * @param strokeColor
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    } 

    /**
     *
     * @return
     */
    public Color getFillColor() {
        return this.fillColor;
    }

    /**
     *
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    /**
     *
     * @param tool
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
    }
    
    /**
     *
     * @return
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    } 
    
    /**
     *
     * @param drawingPanel
     */
    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }
    
    /**
     *
     * @return
     */
    public DrawingPanel getDrawingPanel() {
        return this.drawingPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof OperatedColor) {
            OperatedColor c = (OperatedColor) arg;
            
            if (c.getColorName().equals("foreground")) { // in order to update colors
                this.strokeColor = c.getColor();
            } else if (c.getColorName().equals("background")) {                
                this.fillColor = c.getColor();
            }
        } else if (o instanceof SelectedComponentsModel) {
            SelectedComponentsModel selectedComponentsModel = (SelectedComponentsModel) o;
            this.setSelectedTool(selectedComponentsModel.getSelectedTool());
        }
    }
}
