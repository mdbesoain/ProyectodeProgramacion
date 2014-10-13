
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Gloria
 */
public final class JAbout extends JFrame{
   private Image image;
   
   public JAbout ()
    {
        super ( CommandNames.ACERCA_DE );
        super.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        super.setVisible ( true );
        super.setResizable(false);
        super.setSize ( 300 , 250 );
        super.setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        super.setLocation(250, 200);
        super.setLayout(null);
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        agregaTexto();
        image = new ImageIcon(CommandNames.ICON).getImage();
    }
    
     public void agregaTexto()
    {   
        JLabel textArea = new JLabel((CommandNames.TEXT_ABOUT));
        JLabel textArea1 = new JLabel((CommandNames.TEXT_ABOUT_1));
        JLabel textArea2 = new JLabel((CommandNames.TEXT_ABOUT_2));
        JLabel textArea3 = new JLabel((CommandNames.TEXT_ABOUT_3));
        JLabel textArea4 = new JLabel((CommandNames.TEXT_ABOUT_4));
        JLabel textArea5 = new JLabel((CommandNames.TEXT_ABOUT_5));
        super.add ( textArea);
        super.add ( textArea1);
        super.add ( textArea2);
        super.add ( textArea3);
        super.add ( textArea4); 
        super.add ( textArea5); 
        
        Insets insets = super.getInsets();
        Dimension size = textArea.getPreferredSize();
        textArea.setBounds(130 + insets.left, 50 + insets.top,
             size.width, size.height);
        
        size = textArea1.getPreferredSize();
        textArea1.setBounds(120 + insets.left, 80 + insets.top,
             size.width, size.height);
        
        size = textArea2.getPreferredSize();
        textArea2.setBounds(120 + insets.left, 110 + insets.top,
             size.width, size.height);
        
        size = textArea3.getPreferredSize();
        textArea3.setBounds(120 + insets.left, 130 + insets.top,
             size.width, size.height);
        
        size = textArea4.getPreferredSize();
        textArea4.setBounds(120 + insets.left, 150 + insets.top,
             size.width, size.height);
        
        size = textArea5.getPreferredSize();
        textArea5.setBounds(60 + insets.left, 190 + insets.top,
             size.width, size.height);
    repaint();
    }
     
       @Override
    public void paint ( Graphics g )
    {
        
        super.paintComponents(g);
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING ,
                              RenderingHints.VALUE_ANTIALIAS_ON );

        g2.drawImage ( image , 0 , 40 , null );        
        g2.setColor ( Color.WHITE );
        g2.setFont ( new Font ( Font.SERIF , 3 , 40 ) );
        g2.drawString ( CommandNames.WINDOWS_TITLE , 115 ,60 );
        
    }
}

