/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint.models;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 *
 * @author thomas
 */
public interface JNAMapper extends Library {

    /**
     *
     */
    JNAMapper INSTANCE = (JNAMapper) Native.loadLibrary("filters/libImageFilters.so", JNAMapper.class);
    
    /**
     *
     * @param imageBytes
     * @param pixelWidth
     * @param pixelHeight
     */
    void cutARGBRed(Pointer imageBytes, int pixelWidth, int pixelHeight);

    /**
     *
     * @param imageBytes
     * @param pixelWidth
     * @param pixelHeight
     */
    void cutARGBGreen(Pointer imageBytes, int pixelWidth, int pixelHeight);

    /**
     *
     * @param imageBytes
     * @param pixelWidth
     * @param pixelHeight
     */
    void cutARGBBlue(Pointer imageBytes, int pixelWidth, int pixelHeight);

    /**
     *
     * @param imageBytes
     * @param pixelWidth
     * @param pixelHeight
     */
    void mirror(Pointer imageBytes, int pixelWidth, int pixelHeight);
}
