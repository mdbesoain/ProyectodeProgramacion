/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Gloria
 */
public class Hillo  implements Runnable {

    public Component  frame;    
    Hillo(Component[] components )
    {
        this.frame = components[0];
    }

    @Override
    public void run ()
    {
         System.out.println ( "gola" );
        if ( SwingUtilities.isEventDispatchThread () )
        {   
            
            frame.repaint ();
        } else
        {
            
        }
    
    }
    
}
