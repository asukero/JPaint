/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import java.util.Observable;

/**
 * Parent class Model
 * @author Thomas Fossati / Guillame Catto
 */
public class Model extends Observable{
    private boolean isExist;

    void setIsExist(boolean isExist) {
	this.isExist = isExist;
	setChanged();
	notifyObservers();
    } 

    boolean getIsExist() {
	return isExist;
    }  
    
}