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
import java.awt.geom.Line2D;

/**
 *
 * @author ar3s
 */
public class JPaintLine extends JPaintShape {
    private Point positionA = null;
    private Point positionB = null;
    
    /**
     *
     * @param s
     */
    public JPaintLine(Shape s) {
        super(s);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     */
    public JPaintLine(Shape s, Color fillColor) {
        super(s, fillColor);
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     */
    public JPaintLine(Shape s, Color fillColor, Color strokeColor) {
        super(s, fillColor, strokeColor);
    }
    
    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @param fillColor
     * @param strokeColor
     */
    public JPaintLine(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor) {
        super(null, fillColor, strokeColor);
        this.positionA = firstPoint;
        this.positionB = lastPoint;
        this.setShape(this.createShape(firstPoint, lastPoint));
    }
    
    /**
     *
     * @param s
     * @param fillColor
     * @param strokeColor
     * @param stroke
     */
    public JPaintLine(Shape s, Color fillColor, Color strokeColor, Stroke stroke) {
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
    public JPaintLine(Point firstPoint, Point lastPoint, Color fillColor, Color strokeColor, Stroke stroke) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param startPoint
     * @param endPoint
     * @return
     */
    @Override
    public int computeHeight(Point startPoint, Point endPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param length
     * @return
     */
    @Override
    public boolean isLengthOk(double length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return new Line2D.Double(position.getX(), position.getY(), position.getX()+width, position.getY()+height);
    }

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
        this.positionA = firstPoint;
        this.positionB = new Point((int) firstPoint.getX() + width, (int) firstPoint.getY() + height);
        
        return this.createShape(width, height, firstPoint);
    }

    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @return
     */
    @Override
    public Shape createShape(Point firstPoint, Point lastPoint) {        
        return new Line2D.Double(firstPoint.getX(), firstPoint.getY(), lastPoint.getX(), lastPoint.getY());
    }

    /**
     *
     * @param newReference
     * @param oldReference
     */
    @Override
    public void move(Point newReference, Point oldReference) {
        double offsetX = newReference.getX() - oldReference.getX(); // we want the click location to be conserved compared to the shape
        double offsetY = newReference.getY() - oldReference.getY();
        this.positionA = new Point((int) (this.positionA.getX()+offsetX), (int) (this.positionA.getY()+offsetY));
        this.positionB = new Point((int) (this.positionB.getX()+offsetX), (int) (this.positionB.getY()+offsetY));
        this.setShape(this.createShape(this.positionA, this.positionB));
    }

    /**
     *
     * @param xToMove
     * @param yToMove
     */
    @Override
    public void move(int xToMove, int yToMove) {
        this.positionA = new Point(((int) this.positionA.getX())+xToMove, ((int) this.positionA.getY())+yToMove);
        this.positionB = new Point(((int) this.positionB.getX())+xToMove, ((int) this.positionB.getY())+yToMove);
        this.setShape(this.createShape(this.positionA, this.positionB));
    }
   
    /**
     *
     * @return
     */
    @Override
    public Shape getBounds() {
        return this.getShape().getBounds();
    }

    /**
     *
     * @return
     */
    public Point getPositionA() {
        return this.positionA;
    }

    /**
     *
     * @return
     */
    public Point getPositionB() {
        return this.positionB;
    }
}
