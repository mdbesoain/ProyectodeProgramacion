/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class Circle extends Element
{

    private Point pointInicial;
    private Point pointFinal;
    private Color color;
    private String thin;
    private boolean fill;

    public Circle()
    {
        this.color = Color.BLACK;
        this.thin = CommandNames.GROSOR_1PX;
        this.fill = false;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    
    public void setPointFinal(Point pointFinal)
    {
        this.pointFinal = pointFinal;
    }

    public Point getPointInicial()
    {
        return pointInicial;
    }

    public void setPointInicial(Point pointInicial)
    {
        this.pointInicial = pointInicial;
    }

    public void setThin(String thin) {
        this.thin = thin;
    }
   
    private BasicStroke CreateGrosor() {
        switch(this.thin)
        {
            case CommandNames.GROSOR_1PX:
                return new BasicStroke(1.0f);
                
            case CommandNames.GROSOR_3PX:
                return new BasicStroke(3.0f);
                
            case CommandNames.GROSOR_5PX:
                return new BasicStroke( 5.0f );
                
            case CommandNames.GROSOR_7PX:
                return new BasicStroke(7.0f);
            default:
                return new BasicStroke(1.0f);
        }
    }
    
    @Override
    public void pintar(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(this.color);
        g2.setStroke( CreateGrosor());
        RenderingHints hi = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(hi);


        if ((int) pointFinal.x < (int) pointInicial.x)
        {
            if(fill){
             g2.fill(new Ellipse2D.Float(pointFinal.x, pointInicial.y, pointInicial.x - pointFinal.x, pointFinal.y - pointInicial.y));
            }
            else{
                g2.draw(new Ellipse2D.Float(pointFinal.x, pointInicial.y, pointInicial.x - pointFinal.x, pointFinal.y - pointInicial.y));
            }

        }
        if ((int) pointFinal.y < (int) pointInicial.y)
        {
            if(fill){
                g2.fill(new Ellipse2D.Float(pointInicial.x, pointFinal.y, pointFinal.x - pointInicial.x, pointInicial.y - pointFinal.y));
            }

            else{
                g2.draw(new Ellipse2D.Float(pointInicial.x, pointFinal.y, pointFinal.x - pointInicial.x, pointInicial.y - pointFinal.y));
            }

        }
        if ((int) pointFinal.x < (int) pointInicial.x && (int) pointFinal.y < (int) pointInicial.y)
        {
            if(fill){
                g2.fill(new Ellipse2D.Float(pointFinal.x, pointFinal.y, pointInicial.x - pointFinal.x, pointInicial.y - pointFinal.y));
            }
            else{
                g2.draw(new Ellipse2D.Float(pointFinal.x, pointFinal.y, pointInicial.x - pointFinal.x, pointInicial.y - pointFinal.y));
            }

        }
        else
        {
            if(fill){
                g2.fill(new Ellipse2D.Float(pointInicial.x, pointInicial.y, pointFinal.x - pointInicial.x, pointFinal.y - pointInicial.y));
            }
            else
            {
                g2.draw(new Ellipse2D.Float(pointInicial.x, pointInicial.y, pointFinal.x - pointInicial.x, pointFinal.y - pointInicial.y));
            }
        }

    }

}
