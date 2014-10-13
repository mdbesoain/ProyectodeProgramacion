/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import proyectoprogra.CommandNames;

/**
 *
 * @author Gloria
 */
public class MyToolBar extends JToolBar{
    
    public ActionListener l;
    public JButton deshacer;
    public JButton rehacer;
    public JButton atras;
    public JButton adelante;
    public JToggleButton explorador;
    public JToggleButton editor;
    public JToggleButton collage;
    public JButton copiar;
    public JButton pegar ;
    public JButton seleccionarTodo;
    public JButton cortar;
    public JButton up;
    
    public MyToolBar(ActionListener l)
    {
        super.setRollover ( true );
        super.setForeground ( CommandNames.getColor () );
        this.l=l;
        
        createToolBar();
    }
    
     private void createToolBar ()
    {
        ImageIcon newIcon = new ImageIcon ( CommandNames.NEWICON );
        ImageIcon openIcon = new ImageIcon ( CommandNames.OPENICON );
        ImageIcon undoIcon = new ImageIcon ( CommandNames.UNDOICON );
        ImageIcon redoIcon = new ImageIcon ( CommandNames.REDOICON );
        ImageIcon copyIcon = new ImageIcon ( CommandNames.COPYICON );
        ImageIcon pasteIcon = new ImageIcon ( CommandNames.PASTEICON );
        ImageIcon cutIcon = new ImageIcon ( CommandNames.CUTICON );
        ImageIcon selectAllIcon = new ImageIcon ( CommandNames.SELECTALLICON );
        ImageIcon backIcon = new ImageIcon ( CommandNames.ATRAS);
        ImageIcon forwardIcon = new ImageIcon ( CommandNames.ADELANTE);
        ImageIcon upIcon = new ImageIcon ( CommandNames.UP);
        ButtonGroup group1 = new ButtonGroup ();
        this.atras =createToolButton (   backIcon , false , CommandNames.BACK_TOOLTIP ,false , true);
        this.adelante=createToolButton ( forwardIcon , false , CommandNames.FORWARD_TOOLTIP , false, true) ;
        this.up=createToolButton ( upIcon , false , CommandNames.UP_TOOLTIP ,false , true) ;
        atras.setEnabled(false);
        adelante.setEnabled(false);
        up.setEnabled(false);
        group1.add ( this.atras);
        group1.add ( this.adelante);
        group1.add ( this.up);
        //group1.add ( createToolButton() );
        group1.add ( createToolButton (  newIcon , false , CommandNames.NUEVO ,false, true) );
        group1.add ( createToolButton (  openIcon , false , CommandNames.ABRIR ,false ,true) );
        
        this.deshacer = createToolButton ( undoIcon , false , CommandNames.DESHACER ,false , false);
        deshacer.setEnabled(false);
        this.rehacer = createToolButton (  redoIcon , false , CommandNames.REHACER ,false , false);
        rehacer.setEnabled(false);
        group1.add(deshacer);
        group1.add(rehacer);
        this.copiar=createToolButton ( copyIcon , false , CommandNames.COPIAR,false , false);
        group1.add (  copiar);
        this.pegar=createToolButton (  pasteIcon , false , CommandNames.PEGAR ,false , false);
        group1.add ( pegar );
        this.cortar = createToolButton (  cutIcon , false , CommandNames.RECORTAR ,false , false);
        group1.add (  cortar);
        this.seleccionarTodo=createToolButton (  selectAllIcon , false , CommandNames.SELECCIONAR_TODO,false , false);
        group1.add (seleccionarTodo  );
        group1.add ( createToolButton() );
        this.explorador = createToogleButton (  null , false , CommandNames.EXPLORAR ,true , true);
        explorador.setEnabled ( false);
        group1.add ( explorador );
        this.editor= createToogleButton (  null , false , CommandNames.EDITAR, true , true);
        group1.add ( editor );
        this.collage=createToogleButton (  null , false , CommandNames.COLLAGE, true, true );
        group1.add ( collage );
        group1.add ( createToolButton() );
    }
    

    private JButton createToolButton (  Icon icon , boolean state , String actionCommand ,boolean texto, boolean visible)
    {
        JButton button = new JButton ( icon  );
        button.setSelected ( state );
        button.setFocusable ( false );
        button.setActionCommand ( actionCommand );
        button.setToolTipText ( actionCommand );
        button.addActionListener ( l);

        if(texto)
        {
            button.setText ( actionCommand );
            //button.setOpaque ( true);
        }
        button.setVisible ( visible);
        super.add ( button );

        return button;
    }
    
    
    private JToggleButton createToogleButton (  Icon icon , boolean state , String actionCommand ,boolean texto, boolean visible)
    {
        JToggleButton button = new JToggleButton ( icon,false  );
        button.setSelected ( state );
        button.setFocusable ( false );
        button.setActionCommand ( actionCommand );
        button.setToolTipText ( actionCommand );
        button.addActionListener ( l);

        if(texto)
        {
            button.setText ( actionCommand );
            //button.setOpaque ( true);
        }
        button.setVisible ( visible);
        super.add ( button );

        return button;
    }
    
    private JToggleButton createToolButton (  )
    {
        JToggleButton button = new JToggleButton (  );
        button.setSelected ( false );
        button.setFocusable ( false );
        button.setFocusPainted ( false);
        button.setActionCommand ( null );
        button.setOpaque ( false);
        super.add ( new Separator( null ));
        return button;
    }
    
    
    public void switchBotones(int opcion)
    {   switch(opcion)
        {
        //estamos en el explorador
        case 1:
                explorador.setEnabled (false);
                editor.setEnabled (  true);
                collage.setEnabled ( true);
                
                up.setVisible ( true);
                atras.setVisible ( true);
                adelante.setVisible ( true);
                
                deshacer.setVisible ( false );
                rehacer.setVisible ( false );
                copiar.setVisible ( false );
                pegar.setVisible ( false);
                seleccionarTodo.setVisible ( false );
                cortar.setVisible ( false );
                break;
        //estamos editando
        case 2 :
                explorador.setEnabled (true);
                editor.setEnabled ( false);
                collage.setEnabled ( true );
                //.....
                up.setVisible ( false);
                atras.setVisible ( false);
                adelante.setVisible ( false);
                
                deshacer.setVisible ( true );
                rehacer.setVisible ( true );
                copiar.setVisible ( true );
                pegar.setVisible ( true );
                seleccionarTodo.setVisible ( true );
                cortar.setVisible ( true );
                //...
                break;
        //estamos haciendo un collage
        case 3 :
                explorador.setEnabled (true);
                editor.setEnabled ( true);
                collage.setEnabled ( false);
            
                up.setVisible ( false);
                atras.setVisible ( false);
                adelante.setVisible ( false);
                
                deshacer.setVisible ( false );
                rehacer.setVisible ( false );
                copiar.setVisible ( true );
                pegar.setVisible ( true );
                seleccionarTodo.setVisible ( true );
                cortar.setVisible ( true );
                break;
        }
    
    }
}
