/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;


public class ImageCollage {
    
    public Image image;
    public Image original;
    public Image marco;
    public Dimension dimensiones;
    public Point location;
    public boolean isMoving;
    public String name;
    public Dimension size;
    public ImageCollage(Image image , String name)
    {   
        this.name=name;
        this.image=image;
        this.isMoving= false;
        this.location= new Point();
        this.marco=null;
        this.original=image;
        this.size=new Dimension(image.getWidth(null),image.getHeight(null));
    }

    public Point getLocation ()
    {
        return location;
    }
    
    public int getWidth()
    {
        return image.getWidth(null);
    }
    
    public int getHeight()
    {
        return image.getHeight(null);
    }
    public void setLocation( int X, int Y)
    {
        location.x=X;
        location.y=Y;
    }
    public boolean getIsMoving()
    {
        return isMoving;
    }
    
    public void setIsMoving(boolean isMoving)
    {
        this.isMoving=isMoving;
    }

    public Image getImage ()
    {
        return image;
    } 
    
    public Dimension getSize ()
    {
        return new Dimension(image.getWidth(null),image.getHeight(null));
    }   
        
    public void paint(Graphics g2)
    {   
        
        g2.drawImage ( image , location.x, location.y, null);
        
        if(marco!=null){

        g2.drawImage(marco, location.x, location.y,getWidth() , getHeight(),null);

        }
    }
}
