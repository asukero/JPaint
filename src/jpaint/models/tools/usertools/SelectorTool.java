/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools.usertools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jpaint.models.drawing.DrawingData;
import jpaint.models.drawing.JPaintRectangle;
import jpaint.models.drawing.JPaintShape;

/**
 *
 * @author Guillaume & thomas
 */
public class SelectorTool extends RectangleTool {
    public static final String iconPath = "icons/selection.png";
    private final Color selectionHitBoxFillColor;
    private final Color selectionHitBoxStrokeColor;
    private final Stroke selectionHitBoxStroke;
    private List<JPaintShape> selectedShapes;
    private boolean isMoving = false;

    /**
     *
     */
    public static final String NAME = "Selector";
    private Point previousPoint = null;
    private List<Integer> offsetsX;
    private List<Integer> offsetsY;

    /**
     *
     */
    public SelectorTool() {
        super(NAME, iconPath);
        this.selectionHitBoxFillColor = new Color(0, 0, 0.7f, .2f);
        this.selectionHitBoxStrokeColor = new Color(0, 0, 200);
        float dashAttributes[] = {10.0f};
        this.selectionHitBoxStroke = new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_MITER, 10.0f, dashAttributes, 0.0f);
        this.selectedShapes = new ArrayList<>();
    }

    private boolean isShapeContained(DrawingData drawingData, Shape shape) {
        JPaintShape selection = new JPaintRectangle(drawingData.getMousePressedPoint(), drawingData.getMouseCurrentPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());

        return selection.getShape().contains(shape.getBounds());
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseReleasedAction(DrawingData drawingData) {
        if (!this.isMoving) { // case where a rectangle is used to select shapes
            this.clear(drawingData); // we clear previously selected shapes
            // in order to know the indexes of the shapes to bring to front
            List<Integer> shapeToBringToFrontIndexes = new ArrayList<>();
            List<JPaintShape> shapes = drawingData.getShapes();
            int size = shapes.size();

            for (int i = 0; i < size; i++) {
                JPaintShape jPaintShape = shapes.get(i);
                
                if (this.isShapeContained(drawingData, jPaintShape.getShape())) {
                    this.add(drawingData, jPaintShape); // we add the selected shape
                    shapeToBringToFrontIndexes.add(i);
                }
            }
            
            // we bring to front all the shapes selected
            this.bringShapesToFront(drawingData, shapeToBringToFrontIndexes);
        } else { // case where the user currently moves (a) shape(s)
            int size = drawingData.getSelectedShapes().size();
            
            for (int i = 0; i < size; i++) {
                // we move all the shapes selected here
                JPaintShape jPaintShape = drawingData.getSelectedShapes().get(i);
                this.getPreviousPoint(jPaintShape, i);
                jPaintShape.move(drawingData.getMouseReleasedPoint(), this.previousPoint);
            }           
            
            this.isMoving = false;
            this.previousPoint = null;
            this.offsetsX = null;
            this.offsetsY = null;
        }
    }
    
    /* the previous point is needed to move a shape */
    private void getPreviousPoint(JPaintShape jPaintShape, int index) {
        this.previousPoint = new Point((int) jPaintShape.getShape().getBounds().getX() + this.offsetsX.get(index),
                (int) jPaintShape.getShape().getBounds().getY() + this.offsetsY.get(index));
    }
    
    /**
     * when the user starts moving (a) shape(s)
     * @param drawingData
     */
    @Override
    public void mousePressedAction(DrawingData drawingData) {
        this.offsetsX = new LinkedList<>();
        this.offsetsY = new LinkedList<>();
        
        for (JPaintShape jPaintShape : this.selectedShapes) {
            Point mousePressedPoint = drawingData.getMousePressedPoint();
            
            if (jPaintShape.getShape().contains(mousePressedPoint)) {
                this.isMoving = true;                
            }
            
            Rectangle bounds = jPaintShape.getShape().getBounds();            
            this.offsetsX.add(this.computeOffset(bounds, mousePressedPoint, true));
            this.offsetsY.add(this.computeOffset(bounds, mousePressedPoint, false));
        }
    }
    
    private int computeOffset(Rectangle bounds, Point clickLocation, boolean isX) {
        int offset;
        
        if (isX) {
            offset = (int) (clickLocation.getX() - bounds.getX());
        } else {
            offset = (int) (clickLocation.getY() - bounds.getY());
        }
        
        return offset;
    }
    
    private void add(DrawingData drawingData, JPaintShape jPaintShape) {
        drawingData.getSelectedShapes().add(jPaintShape);
        this.selectedShapes.add(jPaintShape);
    }
    
    private void remove(DrawingData drawingData, JPaintShape jPaintShape) {
        drawingData.getSelectedShapes().remove(jPaintShape);
        this.selectedShapes.remove(jPaintShape);
    }
    
    private void clear(DrawingData drawingData) {
        drawingData.getSelectedShapes().clear();
        this.selectedShapes.clear();
    }
    
    private void drawSelectionHitBox(DrawingData drawingData) {
        JPaintShape jPaintShape = new JPaintRectangle(drawingData.getMousePressedPoint(), drawingData.getMouseCurrentPoint(), drawingData.getFillColor(), drawingData.getStrokeColor());
        jPaintShape.setFillColor(this.selectionHitBoxFillColor);
        jPaintShape.setStrokeColor(this.selectionHitBoxStrokeColor);
        jPaintShape.setStroke(this.selectionHitBoxStroke);
        drawingData.getTemporaryShapes().add(jPaintShape);
    }
    
    private void bringShapesToFront(DrawingData drawingData, List<Integer> indexes) {
        for (Integer i : indexes) {
            this.bringShapeToFront(drawingData, (int) i);
        }
    }
    
    private void bringShapeToFront(DrawingData drawingData, int index) {
        List<JPaintShape> shapes = drawingData.getShapes();
        JPaintShape shapeToMove = shapes.get(index);
        shapes.remove(index);
        shapes.add(shapeToMove);
    }
    
    private void bringShapeToFront(DrawingData drawingData, JPaintShape jPaintShape) {
        List<JPaintShape> shapes = drawingData.getShapes();
        shapes.remove(jPaintShape);
        shapes.add(jPaintShape);
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseDraggedAction(DrawingData drawingData) {
        if (!this.isMoving) {
            this.drawSelectionHitBox(drawingData);
        } else {
            int size = drawingData.getSelectedShapes().size();

            for (int i = 0; i < size; i++) {
                JPaintShape jPaintShape = drawingData.getSelectedShapes().get(i);
                this.getPreviousPoint(jPaintShape, i);
                jPaintShape.move(drawingData.getMouseCurrentPoint(), this.previousPoint);
            }
        }
    }

    /**
     *
     * @param drawingData
     */
    @Override
    public void mouseClickedAction(DrawingData drawingData) {
        this.clear(drawingData); // clearing previous selected shapes
        JPaintShape topShape = null;
        
        for (JPaintShape jPaintShape : drawingData.getShapes()) {
            // we only select the top based shape
            if (jPaintShape.getShape().contains(drawingData.getMouseClickedPoint())) {
                topShape = jPaintShape;
            }
        }
        
        if (topShape != null) {
            this.add(drawingData, topShape);
            this.bringShapeToFront(drawingData, topShape); // we have to bring it to front
        }
    }
}
