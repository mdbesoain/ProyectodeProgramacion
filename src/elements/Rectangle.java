/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.*;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class Rectangle extends Element
{
    
    private Point pointInicio;
    private Point pointFinal;
    private String thin;
    private Color color;
    private boolean fill;
    private boolean filled;
    
    public Rectangle ()
    {
        this.color = Color.BLACK;
        this.thin = CommandNames.GROSOR_1PX;
        this.fill = false;
        this.filled = false;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    public void setPointFinal ( Point pointFinal )
    {
        this.pointFinal = pointFinal;
    }
    
    public void setPointInicio ( Point pointInicio )
    {
        this.pointInicio = pointInicio;
    }
    
    public void setColor ( Color color )
    {
        this.color = color;
    }
    
    public void setThin (String thin )
    {
        this.thin = thin;
    }
    
    public void setFill(boolean b) {
        this.fill = b;
    }

    public boolean isFill() {
        return fill;
    }
    
    
    private Stroke CreateGrosor() {
        switch(this.thin)
        {
            case CommandNames.GROSOR_1PX:
                return new BasicStroke(1.0f);
                
            case CommandNames.GROSOR_3PX:
                return new BasicStroke(3.0f);
                
            case CommandNames.GROSOR_5PX:
                return new BasicStroke(5.0f);
                
            case CommandNames.GROSOR_7PX:
                return new BasicStroke(7.0f);
            default:
                return new BasicStroke(1.0f);
        }
    }
    
    @Override
    public void pintar ( Graphics g )
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.setStroke(CreateGrosor());
        RenderingHints hi = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(hi);
        
        if ( fill )
        {
            g2.setColor ( Color.WHITE );
            g2.fillRect ( ( int ) pointInicio.x - 15 , ( int ) pointInicio.y - 15 , 30 , 30 );
        }
        else
        {
            if ( ( int ) pointFinal.x < ( int ) pointInicio.x )
            {
                g2.setColor ( this.color );
                if(filled)
                {
                    g2.fillRect ( ( int ) pointFinal.x , ( int ) pointInicio.y , ( int ) pointInicio.x - ( int ) pointFinal.x , ( int ) pointFinal.y - ( int ) pointInicio.y );
                }
                else{
                    g2.drawRect ( ( int ) pointFinal.x , ( int ) pointInicio.y , ( int ) pointInicio.x - ( int ) pointFinal.x , ( int ) pointFinal.y - ( int ) pointInicio.y );
                }
                
            }
            if ( ( int ) pointFinal.y < ( int ) pointInicio.y )
            {
                g2.setColor ( this.color );
                if(filled)
                {
                    g2.fillRect ( ( int ) pointInicio.x , ( int ) pointFinal.y , ( int ) pointFinal.x - ( int ) pointInicio.x , ( int ) pointInicio.y - ( int ) pointFinal.y );
                }
                else
                {
                    g2.drawRect ( ( int ) pointInicio.x , ( int ) pointFinal.y , ( int ) pointFinal.x - ( int ) pointInicio.x , ( int ) pointInicio.y - ( int ) pointFinal.y );
                }
                
            }
            if ( ( int ) pointFinal.x < ( int ) pointInicio.x && ( int ) pointFinal.y < ( int ) pointInicio.y )
            {
                g2.setColor ( this.color );
                if(filled)
                {
                    g2.fillRect ( ( int ) pointFinal.x , ( int ) pointFinal.y , ( int ) pointInicio.x - ( int ) pointFinal.x , ( int ) pointInicio.y - ( int ) pointFinal.y );
                }
                else
                {
                    g2.drawRect ( ( int ) pointFinal.x , ( int ) pointFinal.y , ( int ) pointInicio.x - ( int ) pointFinal.x , ( int ) pointInicio.y - ( int ) pointFinal.y );
                }
                
            }
            else
            {
                g2.setColor ( this.color );
            }
            if(filled)
            {
                g2.fillRect ( ( int ) pointInicio.x , ( int ) pointInicio.y , ( int ) pointFinal.x - ( int ) pointInicio.x , ( int ) pointFinal.y - ( int ) pointInicio.y );
            }
            else
            {
                g2.drawRect ( ( int ) pointInicio.x , ( int ) pointInicio.y , ( int ) pointFinal.x - ( int ) pointInicio.x , ( int ) pointFinal.y - ( int ) pointInicio.y );
            }
            
        }
        
    }
    
}
