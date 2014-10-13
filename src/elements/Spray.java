/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class Spray extends Element
{

    private int x;
    private int y;
    private Color color;
    private int SMALL_OFFSET;
    private int BIG_OFFSET;
    private ArrayList<Point> puntos;

    public Spray()
    {
        this.color = Color.BLACK;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSMALL_OFFSET() {
        return SMALL_OFFSET;
    }

    public void setSMALL_OFFSET(int SMALL_OFFSET) {
        this.SMALL_OFFSET = SMALL_OFFSET;
    }

    public int getBIG_OFFSET() {
        return BIG_OFFSET;
    }

    public void setBIG_OFFSET(int BIG_OFFSET) {
        this.BIG_OFFSET = BIG_OFFSET;
    }
    
    public void puntos()
    {
        int tempx;
        int tempy;
        puntos= new ArrayList<>();
        
        for (int i=0; i<100; i++)
        {

            // use static final ints now
            tempx = x + (int) Math.round(2*SMALL_OFFSET*(Math.random() -0.5));
            tempy = y + (int) ( ((Math.random()-0.5)*2) * Math.sqrt(
                      (SMALL_OFFSET * SMALL_OFFSET) - ((x - tempx) * (x - tempx))));
                puntos.add(new Point(tempx, tempy));
        }

        for (int i=0; i<70; i++)
        {    
            tempx = (x + (int) Math.round(2*BIG_OFFSET*(Math.random() -0.5)));
            tempy = (y + (int) ( ((Math.random()-0.5)*2) * Math.sqrt(
                    (BIG_OFFSET * BIG_OFFSET) - ((x - tempx) * (x - tempx)))));
                puntos.add(new Point(tempx, tempy));
        }
    }
    
    @Override
    public void pintar(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        RenderingHints hi = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(hi);

        for (Point point : puntos)
        {
            g2.drawLine(point.x, point.y, point.x, point.y);
        }

    }

}
