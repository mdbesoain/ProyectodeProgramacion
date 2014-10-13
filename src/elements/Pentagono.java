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
public class Pentagono extends Element {

    private Point pointInicio;
    private Point pointFinal;
    private String thin;
    private boolean fill;
    private Color color;

    public Pentagono() {
        this.color = Color.BLACK;
        this.thin = CommandNames.GROSOR_1PX;
        this.fill= false;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setPointFinal(Point pointFinal) {
        this.pointFinal = pointFinal;
    }

    public void setPointInicio(Point pointInicio) {
        this.pointInicio = pointInicio;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThin(String thin) {
        this.thin = thin;
    }

    private Stroke CreateGrosor() {
        switch (this.thin) {
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
    public void pintar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.setStroke(CreateGrosor());
        RenderingHints hi = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(hi);
        int x ;
        int y ;
        int ancho;
        int alto ;
        if ((int) pointFinal.x < (int) pointInicio.x && (int) pointFinal.y > (int) pointInicio.y ) 
        {
            x=(int) pointFinal.x;
            y = (int) pointInicio.y;
            ancho=(int) pointInicio.x - (int) pointFinal.x;
            alto=(int) pointFinal.y - (int) pointInicio.y;
        }
        else if ((int) pointFinal.y < (int) pointInicio.y && (int) pointFinal.x > (int) pointInicio.x ) 
        {
            x= (int) pointInicio.x;
            y= (int) pointFinal.y;
            ancho= (int) pointFinal.x - (int) pointInicio.x;
            alto=(int) pointInicio.y - (int) pointFinal.y;
            
        }
        else if ((int) pointFinal.x < (int) pointInicio.x && (int) pointFinal.y < (int) pointInicio.y) 
        {
            x= (int) pointFinal.x;
            y= (int) pointFinal.y;
            ancho=(int) pointInicio.x - (int) pointFinal.x;
            alto=(int) pointInicio.y - (int) pointFinal.y;
            
        } 
        else 
        {
            x= (int) pointInicio.x;
            y= (int) pointInicio.y;
            ancho= (int) pointFinal.x - (int) pointInicio.x;
            alto = (int) pointFinal.y - (int) pointInicio.y;
        }
        
        
        //----------------
        int[] xPoints =
        {
            x+ (int)(ancho*0.5), x+ ancho, x+ (int)(ancho*0.8),x+ (int)(ancho*0.2), x
        };
        int[] yPoints =
        {
            y ,y + (int)(alto*0.4), y+alto, y+alto  ,y + (int)(alto*0.4) 
        };
        
        if(fill){
            g2.fillPolygon ( xPoints , yPoints , 5 );
        }
        else
        {
            g2.drawPolygon ( xPoints , yPoints , 5 );
        }
    }

    

}
