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
public class Estrella extends Element {

    private Point pointInicio;
    private Point pointFinal;
    private String thin;
    private boolean fill;
    private Color color;

    public Estrella() {
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
        
        Point p1 = new Point(x+ (int)(ancho*0.63) , y + (int)(alto*0.4));
        
        Point p2= new Point( (x+ (int)(ancho*0.7)) , (y + (int)(alto*0.65)) );
       
        Point p3 = new Point( (x+ (int)(ancho*0.5)) , (y + (int)(alto*0.8)) );
        
        Point p4 = new Point((x+ (int)(ancho*0.3)) , (y + (int)(alto*0.65)));
        
        Point p5 = new Point((x+ (int)(ancho*0.4)) , (y + (int)(alto*0.4)) );
        //----------------
        int[] xPoints =
        {
            //x+ (int)(ancho*0.5) , x+ (int)(ancho*0.95),x+ (int)(ancho*0.2), x+ (int)(ancho*0.95), x+ (int)(ancho*0.2)
            x+ (int)(ancho*0.5), p1.x, x+ (int)(ancho*0.95), p2.x, x+ (int)(ancho*0.8), p3.x, x + (int)(ancho*0.2),
            p4.x, x+ (int)(ancho*0.05), p5.x
        };
        int[] yPoints =
        {
            //y , y + alto , y + (int)(alto*0.4) , y + (int)(alto*0.4), y+ alto
            y ,p1.y,y + (int)(alto*0.4), p2.y,y + alto ,p3.y,  y+ alto,
            p4.y, y + (int)(alto*0.4) ,p5.y
        };
        //g2.drawRect(x, y, ancho,alto);
        
        if(fill){
            g2.fillPolygon ( xPoints , yPoints , 10 );
        }
        else
        {
            g2.drawPolygon ( xPoints , yPoints , 10 );
        }
        //g2.fillPolygon ( xPoints , yPoints , 4 );
    }

    private Point intersepcion(int x1, int y1, int x2, int y2 , int x3, int y3, int x4, int y4 )
    {
        Point point = new Point(x1, y1);
        try{
        int p = (y2-y1)/(x2-x1);
        int n = (y1*x2 - y2*x1)/(x2-x1);
        int o = (y4-y3)/(x4-x3);
        int m = (y3*x4 - y4*x3)/(x4-x3);
        
        int x =(n-m)/(o-p);
        int y = n + p*x;
        
        point=new Point(x, y);
        }
        catch(Exception ex)
        {

        }
        return point;
        
    }

}
