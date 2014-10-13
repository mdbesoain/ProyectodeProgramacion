/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author SergioElías
 */
class PaintCanvas extends JPanel{

    private ArrayList<Image> panels;
    
    PaintCanvas(ArrayList<Image> panels) {
       super();
       this.panels = panels;
       setOpaque(true);
       setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
      for(int i=0; i<panels.size();i++)
      {
       Image image = panels.get(i);
       g2.drawImage(image, 0, 0,  this);
      }
    }

    
    Image getImage() {
        if(panels.size()>0)
        {
            return panels.get(panels.size()-1);
        }
        return null;
    }
}
