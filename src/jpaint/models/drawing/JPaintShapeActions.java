/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.drawing;

import java.awt.Point;
import java.awt.Shape;

/**
 *
 * @author Guillaume
 */
public interface JPaintShapeActions {    

    /**
     *
     * @param startPoint
     * @param endPoint
     * @return
     */
    int computeWidth(Point startPoint, Point endPoint);
    
    /**
     *
     * @param startPoint
     * @param endPoint
     * @return
     */
    int computeHeight(Point startPoint, Point endPoint);
    
    /**
     *
     * @param length
     * @return
     */
    boolean isLengthOk(double length);
    
    /**
     *
     * @param width
     * @param height
     * @param position
     * @return
     */
    Shape createShape(int width, int height, Point position);
    
    /**
     *
     * @param width
     * @param height
     * @param firstPoint
     * @param lastPoint
     * @return
     */
    Shape createShape(int width, int height, Point firstPoint, Point lastPoint);
    
    /**
     *
     * @param firstPoint
     * @param lastPoint
     * @return
     */
    Shape createShape(Point firstPoint, Point lastPoint);
    
    /**
     *
     * @return
     */
    Shape getBounds();
    
    /**
     *
     * @param oldReference
     * @param newReference
     */
    void move(Point oldReference, Point newReference);
    
    /**
     *
     * @param xToMove
     * @param yToMove
     */
    void move(int xToMove, int yToMove);
}       