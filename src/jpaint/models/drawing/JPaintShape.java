/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

/**
 *
 * @author Guillaume
 */
public abstract class JPaintShape implements JPaintShapeActions {
    private Shape shape;
    private Color fillColor;
    private Color strokeColor;
    private Stroke stroke;
    
    /**
     *
     * @param s
     */
    public JPaintShape(Shape s) {
        this.shape = s;
        this.fillColor = Color.white;
        this.strokeColor = Color.black;
        this.stroke = new BasicStroke(1);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     */
    public JPaintShape(Shape s, Color fillColor) {
        this.shape = s;
        this.strokeColor = Color.black;
        this.fillColor = fillColor;
        this.stroke = new BasicStroke(1);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     */
    public JPaintShape(Shape s, Color fillColor, Color strokeColor) {
        this.shape = s;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.stroke = new BasicStroke(1);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     * @param stroke
     */
    public JPaintShape(Shape s, Color fillColor, Color strokeColor, Stroke stroke) {
        this.shape = s;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     *
     * @param shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
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
    public Stroke getStroke() {
        return this.stroke;
    }

    /**
     *
     * @param stroke
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    /**
     *
     * @param newReference
     * @param oldReference
     */
    @Override
    public void move(Point newReference, Point oldReference) {
        Rectangle bounds = this.getShape().getBounds();
        double offsetX = newReference.getX() - oldReference.getX(); // we want the click location to be conserved compared to the shape
        double offsetY = newReference.getY() - oldReference.getY();
        Point newPosition = new Point((int) bounds.getX() + (int) offsetX, (int) bounds.getY() + (int) offsetY);
        this.setShape(this.createShape(bounds.width, bounds.height, newPosition));     
    }

    /**
     *
     * @param xToMove
     * @param yToMove
     */
    @Override
    public void move(int xToMove, int yToMove) {
        Rectangle bounds = this.getShape().getBounds();
        Point position = new Point((int) bounds.getX() + xToMove, (int) bounds.getY() + yToMove);
        this.setShape(this.createShape(bounds.width, bounds.height, position));
    }
}
