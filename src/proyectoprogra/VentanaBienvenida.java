/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author sergio
 */
class VentanaBienvenida extends JFrame
{
//Constructor

    public JProgressBar current;
    private Image image;
    private ImageIcon icon;
    
    public VentanaBienvenida ()
    {
        super.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        
        dibujarVentana ();
    }

//Código de inicialización
    private void dibujarVentana ()
    {
        this.setSize ( 600 , 300 );
        this.setTitle ( CommandNames.WELCOME );
        this.setUndecorated ( true );
        this.setResizable ( false );
        this.setFocusable ( false );
        this.setAlwaysOnTop ( true );
        image = new ImageIcon(CommandNames.BACKGROUND).getImage();
        icon = new ImageIcon(CommandNames.ICON);
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.current = new JProgressBar ( 0 , 500 ); // Crear un JProgressBar con valores 0-2000
        this.current.setStringPainted ( true ); // Mostrar valor numérico del progreso de la barra
        this.current.setForeground ( Color.WHITE );//color de la barra de carga
        add ( current , BorderLayout.PAGE_END );
        new JMp3 ( CommandNames.sound_opening );
        
    }
    
    public void setCurrentValue ( int num )
    {
        this.current.setValue ( num );
    }
    
    public void iterate ()
    {
        
        int num = 0;
        
        while ( num < 1000 )
        {
            this.current.setValue ( num ); // Asignar un valor a la barra de progreso.
            try
            {
                Thread.sleep ( 57 );
                
            }
            catch ( InterruptedException e )
            {
            }
            num += 10;
        }
        
    }
    
    @Override
    public void paint ( Graphics g )
    {
        //super.paint ( g );
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING ,
                              RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2.drawImage ( image , 0 , 0 ,this.getWidth(), this.getHeight(), null );
        
        g2.drawImage ( icon.getImage() , 50 , 70 , null );
        
        g2.setColor ( Color.WHITE );
        g2.setFont ( new Font ( Font.SERIF , 3 , 80 ) );
        g2.drawString ( CommandNames.WINDOWS_TITLE , 190 , 150 );
    }
}
