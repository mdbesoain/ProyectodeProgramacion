/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JComboBox;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class MyJPanel extends javax.swing.JPanel
{
    public Image image ;
    public MyJPanel (LayoutManager layout, boolean isDoubleBuffered)
    {
        super (layout, isDoubleBuffered);
    }

    public MyJPanel (LayoutManager layout)
    {
        super (layout);
    }

    public MyJPanel (boolean isDoubleBuffered)
    {
        super (isDoubleBuffered);
    }

    public MyJPanel ()
    {
        super();        
    }
    
    public MyJPanel (Image image)
    {
        
        this.image=image;
        repaint();
    }
    
    public void setColor(String color)
    {
        switch(color)
        {
            case CommandNames.ROJO:
                    CommandNames.setColor (Color.RED);
                    break;
            case CommandNames.AZUL:
                    CommandNames.setColor (Color.BLUE);
                    break;
            case CommandNames.VERDE:
                    CommandNames.setColor (Color.GREEN);
                    break;
            case CommandNames.NEGRO:
                    CommandNames.setColor (Color.BLACK);
                    break;
            case CommandNames.GRIS:
                    CommandNames.setColor (Color.GRAY);
                    break;
            default:
                    CommandNames.setColor (Color.GRAY);
                    break;
        }
        this.setBackground (CommandNames.getColor ());
    }

    void setColor ()
    {
        this.setBackground (CommandNames.getColor ());
    }

    public JComboBox getGrosor ()
    {
        return null;
    }

    public JComboBox getTipoLinea ()
    {
        return null;
    }
    
    public JComboBox getTipoConector()
    {
        return null;
    }
            
}
