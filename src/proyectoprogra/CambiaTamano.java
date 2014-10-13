/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import myObjects.CollagePanel;
import myObjects.MyJPanel;

/**
 *
 * @author SergioElías
 */
class CambiaTamano extends JFrame implements KeyListener, ActionListener
{

    private int ancho ;
    private int alto ;
    private JTextArea texto4;
    private JTextArea texto3;
    private JPicturePanel panel;
    private CollagePanel collagePanel;
    private boolean isFromCollage;
    CambiaTamano ( JPicturePanel panel )
    {
        super(CommandNames.CAMBIARTAMANO);
        this.panel = panel;
        Dimension size = panel.getDimension ();
        this.setIconImage ( new ImageIcon (CommandNames.ICON).getImage ());
        this.setSize ( 200, 200 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout(new BorderLayout ());
        this.setAlwaysOnTop ( true );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE);
        this.ancho = size.width;
        this.alto = size.height;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.isFromCollage=false;
        createEstructure();
    }
    //Constructor para cambiar tamaño desde collage
     CambiaTamano ( CollagePanel panel )
    {   
        super(CommandNames.CAMBIARTAMANO);
        this.collagePanel = panel;
        Dimension size = collagePanel.canvas.imax.getSize();
        this.setIconImage ( new ImageIcon (CommandNames.ICON).getImage ());
        this.setSize ( 200, 200 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout(new BorderLayout ());
        this.setAlwaysOnTop ( true );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE);
        this.ancho = size.width;
        this.alto = size.height;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        this.isFromCollage=true;
        createEstructure();
    }

    
    
    
    private void createEstructure ()
    {
        MyJPanel panelAux = new MyJPanel();
        panelAux.setLayout ( new GridLayout (2,1));
        
        MyJPanel panel = new MyJPanel();
        panel.setLayout(new GridLayout(3,2));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.TAMANOACTUAL));
        JLabel text = new JLabel (CommandNames.ANCHO);
        JTextArea texto = new JTextArea (  );
        texto.setText ( new Integer (ancho).toString ());
        texto.setBackground ( Color.white);
        texto.setForeground ( Color.red );
        texto.setEditable ( false);
        
        JLabel text2 = new JLabel (CommandNames.ALTO);
        JTextArea texto2 = new JTextArea (  );
        texto2.setText ( new Integer (alto).toString ());
        texto2.setBackground ( Color.white);
        texto2.setForeground ( Color.red );
        texto2.setEditable ( false);
        
        panel.add ( text );
        panel.add ( texto );
        panel.add (new JToolBar.Separator ());
        panel.add (new JToolBar.Separator ());
        panel.add ( text2 );
        panel.add ( texto2 );
        
        
        MyJPanel panel2 = new MyJPanel();
        panel2.setLayout(new GridLayout(3,2));
        panel2.setBackground(null);
        panel2.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.TAMANONUEVO));
        JLabel text3 = new JLabel (CommandNames.ANCHO);
        texto3 = new JTextArea (  );
        texto3.setText ( new Integer (ancho).toString ());
        texto3.setBackground ( Color.white);
        texto3.setForeground ( Color.BLACK );
        texto3.setName (CommandNames.ANCHO);
        texto3.addKeyListener ( this );
        
        JLabel text4 = new JLabel (CommandNames.ALTO);
        texto4 = new JTextArea (  );
        texto4.setText ( new Integer (alto).toString ());
        texto4.setBackground ( Color.white);
        texto4.setForeground ( Color.BLACK );
        texto4.addKeyListener ( this );
        texto4.setName (CommandNames.ALTO);
        
        panel2.add ( text3 );
        panel2.add ( texto3 );
        panel2.add (new JToolBar.Separator ());
        panel2.add (new JToolBar.Separator ());
        panel2.add ( text4 );
        panel2.add ( texto4 );
        
        panelAux.add (panel);
        panelAux.add (panel2);
        
        JButton cancel = new JButton (CommandNames.CANCEL);
        cancel.setActionCommand (CommandNames.CANCEL);
        cancel.addActionListener ( this);
        JButton ok = new JButton (CommandNames.OK);
        ok.setActionCommand (CommandNames.OK);
        ok.addActionListener ( this );
        MyJPanel panelbotons = new MyJPanel (new GridLayout (1,2));
        panelbotons.add (cancel);
        panelbotons.add (ok);
        
        this.add (panelAux, BorderLayout.CENTER);
        this.add (panelbotons,BorderLayout.SOUTH);
    
    }

    @Override
    public void keyTyped ( KeyEvent e )
    {
    }


    @Override
    public void keyReleased ( KeyEvent e )
    {
        String key = "" + e.getKeyChar ();
        try
        {
            if(e.getKeyCode () != KeyEvent.VK_DELETE &&
               e.getKeyCode () != KeyEvent.VK_BRACELEFT &&
               e.getKeyCode () != KeyEvent.VK_BRACERIGHT &&
               e.getKeyCode () != KeyEvent.VK_LEFT &&
               e.getKeyCode () != KeyEvent.VK_RIGHT &&
               e.getKeyCode () != KeyEvent.VK_DOWN &&
               e.getKeyCode () != KeyEvent.VK_UP &&
               e.getKeyCode () != KeyEvent.VK_ALT &&
               e.getKeyCode () != KeyEvent.VK_CLEAR &&
               e.getKeyCode () != KeyEvent.VK_CONTROL &&
               e.getKeyCode () != 16 &&
               e.getKeyCode () != 20 &&
               e.getKeyCode () != 8 &&
               e.getKeyCode () != 27 &&
               e.getKeyCode () != 524)
            {
                int n = new Integer (key );
                validateTamano ( (JTextArea) e.getSource () );
            }
            
            validateTamano ( (JTextArea) e.getSource () );
            
        }
        catch(Exception ex)
        {
            JTextArea tex = (JTextArea) e.getSource ();
            try
            {
                tex.setText ( tex.getText ( 0, tex.getText ().length () - 1));
                validateTamano( tex );
            }
            catch ( BadLocationException ex1 )
            {
                //Logger.getLogger ( CambiaTamano.class.getName() ).log ( Level.SEVERE , null , ex1 );
            }
        }
                
    }  

    @Override
    public void keyPressed ( KeyEvent e )
    {
        
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        String comando = e.getActionCommand ();
        switch(comando)
        {
            case CommandNames.CANCEL:
                if (!isFromCollage){this.panel.cambiarTexto =false;}
                dispose ();
                break;
            case CommandNames.OK:
                if (isOk ()!=true)
                {
                    JOptionPane.showMessageDialog ( this , CommandNames.ERROR_TAMANO , CommandNames.CAPTION_TITLE , JOptionPane.ERROR_MESSAGE );
                    if(!isFromCollage)
                    {this.panel.cambiarTexto =false;}
                }
                else
                {   
                    if(isFromCollage)
                    {
                        this.collagePanel.cambiaTamano(this.ancho,this.alto);
                    }
                    if(!isFromCollage)
                    {
                    this.panel.cambiaTamano(this.ancho,this.alto);
                    this.panel.cambiarTexto =false;
                    }
                    
                }
                dispose ();
                break;
        }
    }
    
    private boolean isOk()
    {
        //ancho valido
        try
        {
           int n = new Integer (texto3.getText () ); 
           this.ancho = n;
           int m = new Integer (texto4.getText ());
           this.alto = m;
           if(m ==0 || n ==0)
           {
               return false;
           }
           return true;
        }
        catch(Exception ex)
        {
            this.ancho = 0;
            this.alto = 0;
            return false;
        }
        
    }

    private void validateTamano ( JTextArea tex )
    {
        if(tex.getText ().length ()>4)
        {
            tex.setText ( tex.getText ().substring ( 0,4) );
        }
        try
           {
               switch(tex.getName ())
               {
                   case CommandNames.ANCHO:
                       //ancho text3
                       int n = new Integer (tex.getText () );
                       if(n !=0){
                       int m = (n * alto)/ancho;
                       texto4.setText ( ""+m );
                       texto3.setText ( ""+n );}
                       break;
               
                   case CommandNames.ALTO:
                       // alto text4
                       int m2 = new Integer (tex.getText () );
                       if(m2 !=0){
                       int n2 = (m2*ancho)/alto;
                       texto3.setText ( ""+n2);
                       texto4.setText ( ""+m2);}
                       break;
               }
              
           }
           catch(Exception ex)
           {
               //this.ancho = 0;
               //this.alto = 0;
           }
    }
    
}
