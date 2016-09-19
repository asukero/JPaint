/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import java.util.ArrayList;
import java.util.Collection;
import jpaint.models.tools.Tool;

/**
 * this class wraps an ArrayList to be able to inherit of the Model class (which implements the Observable Interface) like  a Decorator design pattern
 * @author thomas
 */
public class ToolList extends Model {

    private ArrayList<Tool> toolList = new ArrayList<>(5);

    /**
     *
     */
    public ToolList() {

    }

    /**
     *
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        return toolList.indexOf(o); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param index
     * @return
     */
    public Tool remove(int index) {
        Tool tool = toolList.remove(index);
        this.setChanged();
        this.notifyObservers(new OperatedTool(tool.getToolButton(), false));
        return tool; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param index
     * @param element
     */
    public void add(int index, Tool element) {
        toolList.add(index, element); //To change body of generated methods, choose Tools | Templates.
        this.setChanged();
        this.notifyObservers(new OperatedTool(element.getToolButton(), true));

    }

    /**
     *
     * @param index
     * @param element
     * @return
     */
    public Tool set(int index, Tool element) {

        Tool tool = toolList.set(index, element);
        this.setChanged();
        this.notifyObservers(this);
        return tool; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param index
     * @return
     */
    public Tool get(int index) {
        return toolList.get(index);//To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public void clear() {

        toolList.clear(); //To change body of generated methods, choose Tools | Templates.
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     *
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index, Collection<? extends Tool> c) {
        boolean success = toolList.addAll(c);
        this.setChanged();
        this.notifyObservers(this);
        return success; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends Tool> c) {
        boolean success = toolList.addAll(c);
        this.setChanged();
        this.notifyObservers(this);
        return success; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        boolean success = toolList.remove(o);
        this.setChanged();
        this.notifyObservers(new OperatedTool(((Tool)o).getToolButton(), false));
        return success; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean add(Tool e) {
        boolean success = toolList.add(e);
        
        this.setChanged();
        this.notifyObservers(new OperatedTool(e.getToolButton(), true));
        return success; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public int size() {
        return toolList.size(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean contains(Object o) {
        return toolList.contains(o); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public ArrayList<Tool> getToolList() {
        return toolList;
    }
    


}
