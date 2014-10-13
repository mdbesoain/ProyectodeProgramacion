/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Filtros;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class MarcoDeImagen 
{

    public BufferedImage aplicarFiltro ( Image image )
    {
        BufferedImage bi = null;

        BufferedImage imgsel = this.convertImageToBufferredImage ( image );
        if ( imgsel != null )
        {
            bi = new BufferedImage ( imgsel.getWidth () , imgsel.getHeight () , imgsel.getType () );
            for ( int x = 0 ; x < imgsel.getWidth () ; x ++ )
            {
                for ( int y = 0 ; y < imgsel.getHeight () ; y ++ )
                {
                    Color c = new Color ( imgsel.getRGB ( x , y ) );
                    int r =c.getRed ();
                    int g =c.getGreen ();
                    int b =c.getBlue ();
                    if (r<125 || g<125 || b<125)
                    {
                        bi.setRGB ( x , y , new Color ( 255 , 255 , 255, 0 ).getRGB () );
                    }
                    else
                    {
                        bi.setRGB ( x , y , c.getRGB ());
                    }
                    
                }
            }
        }
        return bi;
    }
    
    private BufferedImage convertImageToBufferredImage(Image ig)
    {
        BufferedImage imgsel = new BufferedImage(ig.getWidth(null), ig.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = imgsel.createGraphics();
        bGr.drawImage(ig,0 ,0 ,ig.getWidth(null), ig.getHeight(null), null);
        bGr.dispose();
        return imgsel;
    }
    
    
}
