/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models.tools;

/**
 *
 * @author thomas
 */
public abstract class ShiftTool extends Tool {

    /**
     *
     * @param name
     * @param iconPath
     */
    public ShiftTool(String name, String iconPath) {
        super(name, iconPath,true);
    }
}
