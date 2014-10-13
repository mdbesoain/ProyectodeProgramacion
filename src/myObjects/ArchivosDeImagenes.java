/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
class ArchivosDeImagenes extends JPanel
{
   private Image image;
   private String nombre;
   private String descripcion;
   private int ubicacionX;
   private int ubicacionY;

    public ArchivosDeImagenes ()
    {
        image = null;
        nombre = null;
        descripcion = null;
        ubicacionX = 0;
        ubicacionY = 0;
    }

    public ArchivosDeImagenes (File file)
    {
        ubicacionX = 0;
        ubicacionY = 0;
        ImageIcon imagen;
        if (file.isDirectory ())
        {
            imagen = new ImageIcon (CommandNames.CARPETAABIERTA);
            descripcion = null;
        }
        else{
            imagen = new ImageIcon (file.getAbsolutePath ());
            //imagen = new ImageIcon (CommandNames.CARPETAABIERTA);
            descripcion = imagen.getDescription ();
        }
       image = imagen.getImage ().getScaledInstance ( 30 , 30 , Image.SCALE_SMOOTH );
       nombre = file.getName ();
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image.getScaledInstance ( 30 , 30  , Image.SCALE_AREA_AVERAGING );
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion ()
    {
        return descripcion;
    }

    public void setDescripcion (String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getUbicacionX ()
    {
        return ubicacionX;
    }

    public void setUbicacionX (int ubicacionX)
    {
        this.ubicacionX = ubicacionX;
    }

    public int getUbicacionY ()
    {
        return ubicacionY;
    }

    public void setUbicacionY (int ubicacionY)
    {
        this.ubicacionY = ubicacionY;
    }
    
    public void setUbicacion(int x, int y)
    {
        this.ubicacionY = y;
        this.ubicacionX = x;
        //this.setLocation (x, y);
    }
    
   /*@Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        g.setColor (Color.WHITE);
        g.fillRect(ubicacionX, ubicacionY , ubicacionX + 70 , ubicacionY + 50);
        g.drawImage (image, ubicacionX, ubicacionY, this);
        g.setColor (Color.BLACK); 
        g.setFont ( new Font ( Font.SERIF, 3, 13) );
        //FontMetrics fm=g.getFontMetrics();
        //int ancho=fm.stringWidth(nombre);
        //g.drawString (nombre, ((ubicacionX + 71)-ancho)/2 ,ubicacionY + 65 );
    }
    */
}
