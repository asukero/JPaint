/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author thomas
 */
public class JPaintClassLoader extends ClassLoader {

    public JPaintClassLoader() {
    }
    
    /**
     * load a class by reading bytes from the .class file located with the path
     * @param path path of a .class file
     * @return the Class ready to be instatiate
     * @throws NoClassDefFoundError
     * @throws IOException
     */
    public Class<?> addClass(Path path) {
            
        try {
            byte[] data = Files.readAllBytes(path);
            
            Class c = defineClass(null, data, 0, data.length);
            
            
            return c;
        }catch (NoClassDefFoundError e){
            System.err.println("Can't load class");
            return null;
            
        } catch (IOException ex) {
            System.err.println("Can't find class");
            return null;

        }

    }

}
