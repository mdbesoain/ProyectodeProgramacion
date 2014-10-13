/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import Filtros.MarcoDeImagen;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import proyectoprogra.CommandNames;
import proyectoprogra.JPicturePanel;

/**
 *
 * @author SergioElías
 */
public class Marcos extends JFrame implements ActionListener, MouseListener
{
    private Image marco = null;
    private Image auxMarco=null;
    private JPicturePanel panel = null;
    private CollagePanel collage = null;
    private boolean fromCollage ;
    private CollageCanvas canvas = null;

    public Marcos (JPicturePanel panel)
    {
        super ( CommandNames.COMMAND_MARCO_BUTTON );
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 800 , 600 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setBackground ( null);
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.panel = panel;
        this.fromCollage= false;
        createEstructure ();
    }
     public Marcos (CollagePanel collage)
    {
        super ( CommandNames.COMMAND_MARCO_BUTTON );
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 800 , 600 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setBackground ( null);
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.fromCollage=true;
        this.collage=collage;
        createEstructure ();
    }
     public Marcos (CollageCanvas collage)
    {
        super ( CommandNames.COMMAND_MARCO_BUTTON );
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 800 , 600 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setBackground ( null);
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.fromCollage=true;
        this.canvas=collage;
        createEstructure ();
    }
    private void createEstructure ()
    {
        JPanel pM = new JPanel ();
        //pM.setSize ( 700, 550);
        //pM.setMaximumSize ( new Dimension (700, 550));
        pM.setPreferredSize (new Dimension (700, 550));
        pM.setBackground ( null );
        File file;
        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
        {
         file= new File (new File ("").getAbsolutePath () + "\\Imagenes\\Masks\\");
        }
        else{
            file= new File (new File ("").getAbsolutePath () + "/Imagenes/Masks/");
        }
        pM.setLayout ( new GridLayout( 5,(file.list ().length %5)) );
        for(String string : file.list ())
        {
            String ext = string.substring(string.lastIndexOf('.'));
            if(!ext.contains ( "db"))
            {
                Image image = new ImageIcon("Imagenes/Masks/" + string).getImage ().getScaledInstance ( 75, 75, Image.SCALE_FAST);
                ImageIcon icon = new ImageIcon(image);
                
                pM.add ( JButton ( icon, false, ("Imagenes/Masks/" + string)));
                //pM.add ( JButton ( new ImageIcon ("Imagenes/Marks/" + string), false, ("Imagenes/Marks/" + string)));
            }
        }
        
        
        JPanel pan = new JPanel ();
        pan.setBackground ( null );
        pan.setLayout ( new FlowLayout (FlowLayout.RIGHT));
        pan.add (JButton ( this, false, CommandNames.OK));
        pan.add (JButton ( this, false, CommandNames.CANCEL));
        //pan.add (JButton ( this, false, CommandNames.DELETE_PICTURE));
        
        add(new JScrollPane (pM ),BorderLayout.CENTER);
        add(pan ,BorderLayout.PAGE_END);
        
    }
    
    private JButton JButton(ImageIcon icon, boolean estado, String actionCommand) {
        JButton button = new JButton(icon);
        button.setSize(75, 75);
        button.setMinimumSize(new Dimension(75, 75));
        button.setMaximumSize (new Dimension(75, 75));
        button.setPreferredSize (new Dimension(75, 75));
        button.setSelected(estado);
        //button.setFocusPainted ( false);
        button.setActionCommand(actionCommand);
        //button.setBackground(null);
        //button.setToolTipText(actionCommand);
        button.setBounds(0, 0, 75, 75);
        button.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed ( ActionEvent e )
            {
                marco = new ImageIcon (e.getActionCommand ()).getImage ();
            }
        });
        return button;
    }
    
    private JButton JButton ( Marcos aThis , boolean b , String actionCommand )
    {
        JButton button = new JButton();
        button.setSize(100, 35);
        button.setMinimumSize(new Dimension(100, 35));
        button.setMaximumSize (new Dimension(100, 35));
        button.setPreferredSize (new Dimension(100, 35));
        button.setSelected(b);
        button.setActionCommand(actionCommand);
        button.setBackground(null);
        button.setToolTipText(actionCommand);
        button.setText ( actionCommand );
        button.setBorderPainted(true);
        button.setBounds(0, 0, 100, 35);
        button.addActionListener(aThis);
        return button;
    }
    

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        String comando = e.getActionCommand ();
        switch ( comando )
        {
            case CommandNames.CANCEL:
                dispose ();
                break;
            case CommandNames.OK:
                if(marco!=null)
                {   if(!fromCollage)
                    {marco = new MarcoDeImagen ().aplicarFiltro ( marco );
                    panel.setMarco ( marco );
                    }
                    if(fromCollage)
                    {
                        
                         marco= new MarcoDeImagen ().aplicarFiltro ( marco );
                         if(canvas!=null)
                         {
                             canvas.addPatron(marco);
                         }
                         else{
                             collage.canvas.addMarco(marco);
                         }
                    }
                    dispose ();
                }
                break;
        }
    }

    @Override
    public void mouseClicked ( MouseEvent e )
    {
        throw new UnsupportedOperationException ( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed ( MouseEvent e )
    {
        throw new UnsupportedOperationException ( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased ( MouseEvent e )
    {
        throw new UnsupportedOperationException ( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered ( MouseEvent e )
    {
        throw new UnsupportedOperationException ( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited ( MouseEvent e )
    {
        throw new UnsupportedOperationException ( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    
}
