/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import myObjects.CollagePanel;
import myObjects.MyJPanel;

/**
 *
 * @author SergioElías
 */
class CreaTexto extends JFrame implements ActionListener
{
    
    private JComboBox tamanosCombo;
    private JComboBox fuentes;
    private String[] tamanos;
    private JLabel textoPrevio;
    private JTextArea texto;
    private JPanel colors;
    private MyJPanel panelTexto;
    private boolean fromCollage;
    private JPicturePanel panel;
    private CollagePanel panelCollage;
    private JComboBox styles;
    
    CreaTexto ( JPicturePanel panel )
    {
        super ( CommandNames.AGREGATEXTO );
        Dimension size = panel.getDimension ();
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 300 , 300 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        this.panel = panel;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        createEstructure ();
        fromCollage=false;
    }
    CreaTexto ( JPicturePanel panel, JLabel label )
    {
        super ( CommandNames.AGREGATEXTO );
        Dimension size = panel.getDimension ();
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 300 , 300 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        this.panel = panel;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        createEstructure ();
        textoPrevio.setText ( label.getText ());
        colors.setBackground ( label.getForeground () );
        texto.setText ( label.getText () );
        fuentes.setSelectedItem ( label.getFont ().getFontName () );
        styles.setSelectedIndex(label.getFont ().getStyle());
        tamanosCombo.setSelectedIndex ( getTamano("" + label.getFont ().getSize ()));
        textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) ,styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
        textoPrevio.setForeground ( colors.getBackground () );
        textoPrevio.setAlignmentY ( JLabel.TOP);
        fromCollage=false;
    }
     CreaTexto ( CollagePanel panel, JLabel label )
    {
        super ( CommandNames.AGREGATEXTO );
        Dimension size = panel.size ();
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 300 , 300 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        this.panelCollage = panel;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        createEstructure ();
        textoPrevio.setText ( label.getText ());
        colors.setBackground ( label.getForeground () );
        texto.setText ( label.getText () );
        fuentes.setSelectedItem ( label.getFont ().getFontName () );
        styles.setSelectedIndex(label.getFont ().getStyle());
        tamanosCombo.setSelectedIndex ( getTamano("" + label.getFont ().getSize ()));
        textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) ,styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
        textoPrevio.setForeground ( colors.getBackground () );
        textoPrevio.setAlignmentY ( JLabel.TOP);
        fromCollage=true;
    }
    
     CreaTexto ( CollagePanel panel )
    {
        super ( CommandNames.AGREGATEXTO );
        Dimension size = panel.getSize();
        this.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        this.setSize ( 300 , 300 );
        this.setResizable ( false );
        this.setVisible ( true );
        this.setLayout ( new BorderLayout () );
        this.setFocusable ( true );
        this.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        this.panelCollage = panel;
        Dimension dim = this.getToolkit ().getScreenSize ();
        Rectangle abounds = this.getBounds ();
        this.setLocation ( (dim.width - abounds.width) / 2 , (dim.height - abounds.height) / 2 );
        fromCollage=true;
        createEstructure ();
    }
    
    private void createEstructure ()
    {
        MyJPanel panelAux = new MyJPanel ();
        panelAux.setLayout ( new GridLayout ( 2 , 1 ) );
        
        panelTexto = new MyJPanel ();
        panelTexto.setBackground ( null );
        panelTexto.setBorder ( BorderFactory.createTitledBorder (
                CommandNames.VISTAPREVIA ) );
        textoPrevio = new JLabel ( "Default Text" );
        textoPrevio.setAlignmentY ( JLabel.TOP);
        panelTexto.add ( textoPrevio );
        
        MyJPanel panelPropieties = new MyJPanel ();
        panelPropieties.setLayout ( new GridLayout ( 5 , 2 ) );
        panelPropieties.setBackground ( null );
        panelPropieties.setBorder ( BorderFactory.createTitledBorder (
                CommandNames.PROPIEDADES ) );
        JLabel text = new JLabel ( CommandNames.TEXT );
        texto = new JTextArea ("Default Text");
        texto.setForeground ( Color.red );
        texto.addKeyListener ( new java.awt.event.KeyAdapter ()
        {
            @Override
            public void keyTyped ( java.awt.event.KeyEvent evt )
            {
                String t = texto.getText ();
                t = t + evt.getKeyChar ();
                if ( t.length () > 0 )
                {
                    textoPrevio.setText ( t );
                }
            }
        } );
        JLabel fuente = new JLabel ( CommandNames.FUENTE );
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fuentes = new JComboBox(gEnv.getAvailableFontFamilyNames());
        
        fuentes.addItemListener ( new ItemListener ()
        {
            @Override
            public void itemStateChanged ( ItemEvent e )
            {
                textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) , styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
                textoPrevio.setForeground ( colors.getBackground () );
            }
            
        } );
        JLabel tam = new JLabel ( CommandNames.TAMANO );
        tamanos = new String[ 26 ];
        for ( int i = 4 ; i < 30 ; i ++ )
        {
            tamanos[i - 4] = "" + (i + 1);
        }
        tamanosCombo = new JComboBox ( tamanos );
        tamanosCombo.setSelectedIndex ( 7 );
        tamanosCombo.addItemListener ( new ItemListener ()
        {
            
            @Override
            public void itemStateChanged ( ItemEvent e )
            {
                textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) , styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
                textoPrevio.setForeground ( colors.getBackground () );
            }
            
        } );
        JLabel style = new JLabel ( CommandNames.Styles );
        String[] styleNames = {"Plain", "Bold", "Italic", "Bold Italic"};
        styles = new JComboBox(styleNames);
        styles.setSelectedIndex(0);
        styles.addItemListener ( new ItemListener () {

            @Override
            public void itemStateChanged(ItemEvent e)
            {
                textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) , styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
                textoPrevio.setForeground ( colors.getBackground () );
            }
        });
        
        JLabel color = new JLabel ( CommandNames.COLORES );
        colors = new JPanel ();
        colors.setBackground ( Color.WHITE );
        colors.setBorder ( javax.swing.BorderFactory.createLineBorder ( Color.BLACK ) );
        colors.setPreferredSize ( new java.awt.Dimension ( 100 , 20 ) );
        colors.addMouseListener ( new java.awt.event.MouseAdapter ()
        {
            @Override
            public void mouseClicked ( java.awt.event.MouseEvent evt )
            {
                Color color = JColorChooser.showDialog ( colors , "Seleccione un Color" , Color.WHITE );
                colors.setBackground ( color );
                textoPrevio.setFont ( new Font ( "" + fuentes.getItemAt ( fuentes.getSelectedIndex () ) , styles.getSelectedIndex () , Integer.parseInt ( tamanos[tamanosCombo.getSelectedIndex ()] ) ) );
                textoPrevio.setForeground ( colors.getBackground () );
                repaint ();
            }
        } );
        
        panelPropieties.add ( text );
        panelPropieties.add ( texto );
        panelPropieties.add ( fuente );
        panelPropieties.add ( fuentes );
        panelPropieties.add ( tam );
        panelPropieties.add ( tamanosCombo );
        panelPropieties.add ( style );
        panelPropieties.add ( styles );
        panelPropieties.add ( color );
        panelPropieties.add ( colors );
        
        panelAux.add ( panelTexto );
        panelAux.add ( panelPropieties );

        //-------ok and cancel button
        JButton cancel = new JButton ( CommandNames.CANCEL );
        cancel.setActionCommand ( CommandNames.CANCEL );
        cancel.addActionListener ( this );
        JButton ok = new JButton ( CommandNames.OK );
        ok.setActionCommand ( CommandNames.OK );
        ok.addActionListener ( this );
        MyJPanel panelbotons = new MyJPanel ( new GridLayout ( 1 , 2 ) );
        panelbotons.add ( cancel );
        panelbotons.add ( ok );
        
        this.add ( panelAux , BorderLayout.CENTER );
        this.add ( panelbotons , BorderLayout.SOUTH );
        
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
                if(!fromCollage)
                {panel.setText ( textoPrevio );}
                if(fromCollage)
                {
                    panelCollage.setText(textoPrevio);
                }
                dispose ();
                break;
        }
    }

    private int getTamano ( String num )
    {
        for (int i = 0; i<tamanos.length;i++)
        {
            if (tamanos[i].contains (num ))
            {
                return i;
            }
        }
        return 7;
    }
    
}
