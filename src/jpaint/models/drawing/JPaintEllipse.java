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
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Guillaume
 */
public class JPaintEllipse extends JPaintPlaneFigure {

    /**
     *
     * @param s
     */
    public JPaintEllipse(Shape s) {
        super(s);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     */
    public JPaintEllipse(Shape s, Color fillColor) {
        super(s, fillColor);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     */
    public JPaintEllipse(Shape s, Color fillColor, Color strokeColor) {
        super(s, fillColor, strokeColor);
    }
    
    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @param fillColor
     * @param strokeColor
     */
    public JPaintEllipse(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor) {
        super(firstPoint, lastPoint, fillColor, strokeColor);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     * @param stroke
     */
    public JPaintEllipse(Shape s, Color fillColor, Color strokeColor, Stroke stroke) {
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
    public JPaintEllipse(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor, Stroke stroke) {
        super(firstPoint, lastPoint, fillColor, strokeColor, stroke);
    }
    
    /**
     *
     * @param width
     * @param height
     * @param position
     * @return
     */
    @Override
    public Shape createShape(int width, int height, Point position) {
        return new Ellipse2D.Double((int) position.getX(), (int) position.getY(), width, height);
    }
        
    /**
     *
     * @return
     */
    @Override
    public Shape getBounds() {
        return this.getShape();
    }
}
