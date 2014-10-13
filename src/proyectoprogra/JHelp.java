/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Gonzalo
 */
public class JHelp extends JFrame{
    private JEditorPane panelEditor;
    
    JHelp()
      {
        super ( CommandNames.AYUDA);
        super.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        super.setVisible ( true );
        super.setResizable(false);
        super.setSize ( 300 , 250 );
        super.setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        super.setLocation(800, 200);
        super.setLayout(new BorderLayout());
        agregaTexto();
    }
    
     public void agregaTexto()
    {   
        File paginaHTML = new File("TutorialFotoChop.html");
        panelEditor = new JEditorPane();
        panelEditor.setEditable(false);
        try {
            panelEditor.setPage(new URL(System.getProperty("user.dir")+"TutorialFotoChop.html"));
        } catch (IOException ex) 
        {
           
        }
        super.add((panelEditor));   
    }
}
