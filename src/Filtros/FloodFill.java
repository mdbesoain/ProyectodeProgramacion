/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

/**
 *
 * @author SergioElías
 */
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.LinkedList;

public class FloodFill
{

    public void AplicarFiltro ( BufferedImage image , Point point , Color antiguo , Color nuevo )
    {
        int ancho = image.getWidth ();
        int alto = image.getHeight ();
        int viejo = antiguo.getRGB ();
        int nuevito = nuevo.getRGB ();
        if ( viejo != nuevito )
        {
            Deque<Point> queue = new LinkedList<Point> ();
            do
            {
                int x = point.x;
                int y = point.y;
                while ( x > 0 && isIgualColor ( image.getRGB ( x - 1 , y ) , viejo) )
                {
                    x --;
                }
                boolean arriba = false;
                boolean abajo = false;
                while ( x < ancho && isIgualColor ( image.getRGB ( x , y ) , viejo ) )
                {
                    image.setRGB ( x , y , nuevito );
                    if (  ! arriba && y > 0 && isIgualColor ( image.getRGB ( x , y - 1 ) , viejo ) )
                    {
                        queue.add ( new Point ( x , y - 1 ) );
                        arriba = true;
                    }
                    else if ( arriba && y > 0 &&  ! isIgualColor ( image.getRGB ( x , y - 1 ) , viejo ) )
                    {
                        arriba = false;
                    }
                    if (  ! abajo && y < alto - 1 && isIgualColor ( image.getRGB ( x , y + 1 ) , viejo ) )
                    {
                        queue.add ( new Point ( x , y + 1 ) );
                        abajo = true;
                    }
                    else if ( abajo && y < alto - 1 &&  ! isIgualColor ( image.getRGB ( x , y + 1 ) , viejo ) )
                    {
                        abajo = false;
                    }
                    x ++;
                }
            }
            while ( (point = queue.pollFirst ()) != null );
            queue.clear ();
        }
    }

    private boolean isIgualColor ( int rgb , int target )
    {
        Color c = new Color ( rgb );
        Color c2 = new Color ( target );
        int r = c.getRed ();
        int g = c.getGreen ();
        int b = c.getBlue ();
        int r2 = c2.getRed ();
        int g2 = c2.getGreen ();
        int b2 = c2.getBlue ();
        
        //para que el color tenga un poco mas de variabilidad
        if ( r2 > r - 20 && r2 < r + 20 && g2 > g - 20 && g2 < g + 20 && b2 > b - 20 && b2 < b + 20 )
        {
            return true;
        }
        return false;
    }
}
