/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.drawing;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;

/**
 *
 * @author Guillaume
 */
public abstract class JPaintPlaneFigure extends JPaintShape {

    /**
     *
     * @param s
     */
    public JPaintPlaneFigure(Shape s) {
        super(s);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     */
    public JPaintPlaneFigure(Shape s, Color fillColor) {
        super(s, fillColor);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     */
    public JPaintPlaneFigure(Shape s, Color fillColor, Color strokeColor) {
        super(s, fillColor, strokeColor);
    }
    
    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @param fillColor
     * @param strokeColor
     */
    public JPaintPlaneFigure(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor) {
        super(null, fillColor, strokeColor);        
        this.setShape(this.createShape(firstPoint, lastPoint));
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     * @param stroke
     */
    public JPaintPlaneFigure(Shape s, Color fillColor, Color strokeColor, Stroke stroke) {
        super(s, fillColor, strokeColor, stroke);
    }
    
    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @param fillColor
     * @param strokeColor
     * @param stroke
     */
    public JPaintPlaneFigure(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor, Stroke stroke) {
        super(null, fillColor, strokeColor, stroke);        
        this.setShape(this.createShape(firstPoint, lastPoint));
    }
    
    /**
     *
     * @param startPoint
     * @param endPoint
     * @return
     */
    @Override
    public int computeWidth(Point startPoint, Point endPoint) {
        return (int) (endPoint.getX()-startPoint.getX());
    }
    
    /**
     *
     * @param startPoint
     * @param endPoint
     * @return
     */
    @Override
    public int computeHeight(Point startPoint, Point endPoint) {
        return (int) (endPoint.getY()-startPoint.getY());
    }
    
    /**
     *
     * @param length
     * @return
     */
    @Override
    public boolean isLengthOk(double length) {
        return length > 0;
    }
    
    /**
     *
     * @param width
     * @param height
     * @param position
     * @return
     */
    @Override
    public abstract Shape createShape(int width, int height, Point position);
    
    /**
     *
     * @param width
     * @param height
     * @param firstPoint
     * @param lastPoint
     * @return
     */
    @Override
    public Shape createShape(int width, int height, Point firstPoint, Point lastPoint) {
        /* 4 cases */
        if (this.isLengthOk(width) && this.isLengthOk(height)) { // "normal" case
            return this.createShape(width, height, firstPoint);
        } else if (!this.isLengthOk(width) && this.isLengthOk(height)) {
            return this.createShape(-width, height, new Point((int) lastPoint.getX(), (int) lastPoint.getY()-height));
        } else if (this.isLengthOk(width) && !this.isLengthOk(height)) {
            return this.createShape(width, -height, new Point((int) lastPoint.getX()-width, (int) lastPoint.getY()));
        } else {
            return this.createShape(-width, -height, lastPoint);
        }
    }

    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @return
     */
    @Override
    public Shape createShape(Point firstPoint, Point lastPoint) {        
        int width = this.computeWidth(firstPoint, lastPoint);
        int height = this.computeHeight(firstPoint, lastPoint);
        
        return this.createShape(width, height, firstPoint, lastPoint);
    }
}
