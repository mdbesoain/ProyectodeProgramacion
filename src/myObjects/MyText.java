/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.swing.JLabel;

/**
 *
 * @author SergioElías
 */
public class MyText
{

    private String texto;
    private Font font;
    private Color color;
    private int PosX = 20;
    private int PosY = 100;
    public int max ;
    private JLabel label;
    private Dimension size;
    public MyText ( JLabel label, int wight )
    {
        texto = label.getText ();
        font = label.getFont ();
        color = label.getForeground ();
        this.label=label;
        this.max = wight;
        size=label.getSize();
    }

    public void move ( int x , int y )
    { 
        PosX  = x;
        PosY  = y;
    }
    public Dimension getSize()
    {
        return size;
    }
    public JLabel getLabel ()
    {
        return label;
    }

    public int getPosX ()
    {
        return PosX;
    }
    public void setPosX(int posX)
    {
        this.PosX= posX;
    }
    public int getPosY ()
    {
        return PosY;
    }
    public void setPosY(int posY)
    {
        this.PosY= posY;
    }
    public void setLabel ( JLabel label )
    {
        this.label = label;
        texto = label.getText ();
        font = label.getFont ();
        color = label.getForeground ();
    }

    public void paint ( Graphics2D g2 )
    {
        RenderingHints hi = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(hi);
        
        int ancho =label.getWidth ();
        int alto =label.getHeight ();
        if(PosX+ancho >= max)
        {
            PosX = max-ancho;
        }
        if(PosY-(alto/2 )<0)
        {
            PosY = (alto/2 )+ 1 ;
        }
        g2.setColor ( color );
        g2.setFont ( font );
        g2.drawString (texto , PosX , PosY );
    }

}
