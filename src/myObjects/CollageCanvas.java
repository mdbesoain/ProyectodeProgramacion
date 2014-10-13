/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import elements.Element;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CollageCanvas extends JPanel{
    
    private ArrayList<Integer> copyAreaX;
    private ArrayList<Integer> copyAreaY;
    public ArrayList<ImageCollage> imageList;
    public int cursor;
    public ImageCollage imax;
    public  ArrayList<Element> elements;
    //private MyText text = null;
    public ArrayList<MyText> textos;
    public MyText textAux;
    private Image patron;
    public boolean rotanto;
    public int angulo;
    public boolean rotateAll=false;
    
    public CollageCanvas(Dimension dim)
    {   super();
        super.setSize ( dim );
        super.setPreferredSize(dim);
        super.setMaximumSize ( dim );
        super.setMinimumSize(dim );
        super.setBackground ( Color.WHITE );
        super.setAutoscrolls(true);
        this.imageList=new ArrayList<> ( 20 );
        this.cursor= -1;
        this.imax=null;
        this.textAux=null;
        this.elements = new ArrayList<> ();
        this.textos= new ArrayList<> ( 20 );
    }
    public void addText(MyText texto)
    {
        textos.add(texto);
        repaint();
        
    }
    public ImageCollage getImax()
    {
        return imax;
    }
    public MyText getTextAux()
    {
        return textAux;
    }
    public void addImage(ImageCollage image)
    {
        imageList.add ( image );
        this.repaint();
    }

    public ArrayList<Element> getElements()
    {
        return elements;
    }
    
    
    public void setElements(ArrayList<Element> elements)
    {
        this.elements = elements;
    }

    
    public void addMarco(Image marco)
    {
        imax.marco=marco;
        this.repaint();
    }
    public void addElement(Element elem)
    {
      elements.add ( elem );
      this.repaint();
    }   
    
    public boolean insidePicture(Point p)
    {
        if(p.x>imax.location.x && imax.location.x+imax.getWidth()>p.x )
        {
            if(p.y>imax.location.y && imax.location.y+ imax.getHeight()>p.y)
            {
                return true;
            }
        }
        return false;
        
    }
    public boolean insideText(Point p)
    {
        if(p.x>textAux.getPosX() && p.x<textAux.getPosX() + textAux.getLabel().getWidth())
        {
            if(p.y<textAux.getPosY() && p.y>textAux.getPosY() - textAux.getLabel().getHeight())
            {
                return true;
            }
        }
        return false;
    }
    public void isOnBorder(Point p)
    {   
        if ( !imageList.isEmpty () )
        {   
            for ( ImageCollage imageCollage : imageList )
            {
                if ( (p.x>=imageCollage.location.x && p.x <= imageCollage.location.x + imageCollage.image.getWidth ( this ) ) )
                {        
                    if(p.y==imageCollage.location.y)   
                   {
                       this.setCursor ( new Cursor ( Cursor.N_RESIZE_CURSOR));
                       this.cursor=1;
                       this.imax= imageCollage; 
                       break;
                   }
                   if(p.y == imageCollage.location.y+ imageCollage.image.getHeight ( this ))
                   {
                       this.setCursor ( new Cursor ( Cursor.S_RESIZE_CURSOR));
                       this.cursor=2;
                       this.imax= imageCollage; 
                       break;
                   }
                   
                  
                }
                if(p.y >= imageCollage.location.y && p.y <= imageCollage.location.y + imageCollage.image.getHeight ( this ))    
                {
                    if(p.x==imageCollage.location.x)
                    {
                       this.setCursor ( new Cursor ( Cursor.W_RESIZE_CURSOR));
                       this.cursor=3;
                       this.imax= imageCollage; 
                       break;
                    }
                    
                    if(p.x== imageCollage.location.x +imageCollage.image.getWidth ( this ))
                    {
                       this.setCursor ( new Cursor ( Cursor.E_RESIZE_CURSOR));
                       this.cursor=4;
                       this.imax= imageCollage; 
                       break;
                    }
                   
                }
                
                else{this.setCursor ( new Cursor ( Cursor.DEFAULT_CURSOR));}
            }
        }
        
       
        
    }
     
   public Point realPosition(Point p, Image dim )
    {
        p.x=p.x - (dim.getWidth ( this )/2);
        p.y=p.y -(dim.getHeight ( this )/4);
        
        return p;
    }
    
     public void moveImage(Point p)
     {      
          
          //imax.setLocation (realPosition(p,imax.image).x,realPosition(p,imax.image).y );
          imax.setLocation (p.x,p.y );
          repaint();
     }
     
     public void moveText(Point p)
     {
         textAux.setPosX(p.x);
         textAux.setPosY(p.y);
         repaint();
     }
    
    
    
    public void deleteText()
    {
       if(!textos.isEmpty() && textAux != null)
       {
           textos.remove(textAux);
       }
        this.repaint();
    }
    
    public void deletePicture()
    {   
         if ( !imageList.isEmpty () && imax!=null )
        {   
            imageList.remove(imax);
            
        }
       this.repaint();
    }
    
   
  
    public  void getSelectImage(String nameBuscado)
    {   
        if ( !imageList.isEmpty () )
        {   
            for ( ImageCollage imageCollage : imageList )
            {
                if(imageCollage.name.equals ( nameBuscado))
                {
                    imax= imageCollage;
                    break;
                }
            }

            
       }

    }
     public void getSelectedText(String nameBuscado)
     {
         if ( !textos.isEmpty () )
        {   
            for ( MyText texto : textos )
            {
                if(texto.getLabel().getText().equals ( nameBuscado))
                {
                    textAux= texto;
                    break;
                }
            }
            
       }
     }     
    
    
    
     
    public void addPatron(Image marco)
    {
        this.patron = marco;
        this.repaint();
    }
     
     public boolean isEmpty()
    {   
        if(!imageList.isEmpty() || !textos.isEmpty())
        {
            return false;
        }
        return true;
    }
    
    public BufferedImage getAll()
    {
        BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D cG = bi.createGraphics();
        this.paint(cG);
        cG.dispose();
        return bi;
    }
            
     
     @Override
    public void paint ( Graphics g )
    {   
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_SPEED);
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        if ( !imageList.isEmpty ())
        {
            for ( ImageCollage imageCollage : imageList )
            {   
                imageCollage.paint(g2);
            }   

        }
         
        if(!textos.isEmpty())
        {
             for ( MyText  text : textos )
            {
              text.paint(g2);
            }
        }
        for ( int i = 0 ; i < this.elements.size () ; i ++ )
        {
            elements.get ( i ).pintar ( g2 );
        }
        
        if(patron!=null)
        {
            g2.drawImage(patron, 0 , 0 , this.getWidth(), this.getHeight() , this);
        }
        g2.dispose();
        /*if (copyAreaX!=null && !copyAreaX.isEmpty())
        {
            float[] dash
                    =
                    {
                        15
                    };

            if (copyAreaX.size() == 1)
            {
                Point point = new Point(copyAreaX.get(0), copyAreaY.get(0));
                g2.drawLine(point.x, point.y, point.x, point.y);
            } else
            {
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));

                for (int i = 0;
                        i < copyAreaX.size() - 1;
                        i++)
                {
                    Point point = new Point(copyAreaX.get(i), copyAreaY.get(i));
                    Point point1 = new Point(copyAreaX.get(i + 1), copyAreaY.get(i + 1));
                    g2.drawLine(point.x, point.y, point1.x, point1.y);
                }
            }

        }*/
    }

    void resetPoints()
    {
        this.copyAreaX = new ArrayList<>();
        this.copyAreaY = new ArrayList<>();
    }

    void addPointX(int x)
    {
        this.copyAreaX.add(x);
    }

    void addPointY(int y)
    {
        this.copyAreaY.add(y);
    }
      
       
}
    

