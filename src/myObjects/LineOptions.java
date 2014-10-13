/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import proyectoprogra.CommandNames;
import proyectoprogra.MainWindow;

/**
 *
 * @author SergioElías
 */
public class LineOptions extends MyJPanel 
{
    private ActionListener l;
    private JButton flechaLlena;
    private JButton flechahueca;
    private JButton circulo;
    private JButton rombo;
    private JButton cuadrado;  
    private JButton normal; 
    private JComboBox grosor; 
    private JComboBox tipoLinea; 
    private JComboBox tipoConector;
    private Checkbox box;
    private Checkbox box2;
    
    public LineOptions(MainWindow aThis)
    {
        super();
        this. l = aThis;
        this.setLayout ( new FlowLayout (FlowLayout.LEFT));
        this.normal = JButton ( new ImageIcon (CommandNames.ICON_LINE_NORMAL), true,CommandNames.LINE_NORMAL );
        this.flechaLlena = JButton ( new ImageIcon (CommandNames.ICON_LINE_ARROW_FILL) ,false , CommandNames.LINE_ARROW_FILL );
        this.flechahueca = JButton ( new ImageIcon (CommandNames.ICON_LINE_ARROW) , false , CommandNames.LINE_ARROW );
        this.circulo = JButton ( new ImageIcon (CommandNames.ICON_LINE_CIRCLE) , false , CommandNames.LINE_CIRCLE );
        this.rombo = JButton ( new ImageIcon (CommandNames.ICON_LINE_RHOMBUS) , false , CommandNames.LINE_RHOMBUS );
        this.cuadrado = JButton ( new ImageIcon (CommandNames.ICON_LINE_SQUARE) , false ,CommandNames.LINE_SQUARE );
        
        this.add ( normal);
        this.add ( flechaLlena);
        this.add ( flechahueca);
        this.add ( circulo);
        this.add ( rombo);
        this.add ( cuadrado);
        // Selector de Grosor
        this.grosor = new JComboBox( createGrosores() );
        grosor.addActionListener(l);
        grosor.setToolTipText(CommandNames.GROSOR);
        grosor.setName(CommandNames.GROSOR);
        this.add(grosor);
        
        this.tipoLinea = new JComboBox( createLineas () );
        tipoLinea.addActionListener(l);
        tipoLinea.setToolTipText(CommandNames.TIPOLINEA);
        tipoLinea.setName(CommandNames.TIPOLINEA);
        this.add(tipoLinea);
        
        this.tipoConector = new JComboBox( createConectores() );
        tipoConector.addActionListener(l);
        tipoConector.setToolTipText(CommandNames.TIPOConectores);
        tipoConector.setName(CommandNames.TIPOConectores);
        this.add(tipoConector);
        
        this.box = new Checkbox("Derecha");
        box.addItemListener(aThis);
        //this.add(box);
        
        this.box2 = new Checkbox("Ambos");
        box2.addItemListener(aThis);
        //this.add(box2);
    }
    
    private Object[] createGrosores() 
    {
        String[] lista = {CommandNames.GROSOR_1PX,CommandNames.GROSOR_3PX,CommandNames.GROSOR_5PX,CommandNames.GROSOR_7PX};
        return lista;
    }
    
    private Object[] createLineas() 
    {
        ImageIcon lineaNormal = new ImageIcon (CommandNames.ICON_Linea);
        ImageIcon lineaPunteada = new ImageIcon (CommandNames.ICON_LineaPunteada );
        ImageIcon lineaSegmentada = new ImageIcon (CommandNames.ICON_LineaSegmentada );
        ImageIcon lineaCompuesta = new ImageIcon (CommandNames.ICON_LineaCompuesta );
        
        lineaNormal.setDescription ( CommandNames.ICON_Linea );
        lineaPunteada.setDescription ( CommandNames.ICON_LineaPunteada );
        lineaSegmentada.setDescription ( CommandNames.ICON_LineaSegmentada );
        lineaCompuesta.setDescription ( CommandNames.ICON_LineaCompuesta );
        Object[] lista = {lineaNormal, lineaPunteada, lineaSegmentada,lineaCompuesta};
        return lista;
    }
    private Object[] createConectores() 
    {
        ImageIcon lineaNormal = new ImageIcon (CommandNames.ICON_LineaNormal);
        ImageIcon lineaCurva = new ImageIcon (CommandNames.ICON_LineaCurva );
        ImageIcon lineaAngular = new ImageIcon (CommandNames.ICON_LineaAngular );
        
        lineaNormal.setDescription ( CommandNames.ICON_LineaNormal );
        lineaCurva.setDescription ( CommandNames.ICON_LineaCurva );
        lineaAngular.setDescription ( CommandNames.ICON_LineaAngular );
        Object[] lista = {lineaNormal, lineaCurva,lineaAngular};
        return lista;
    }
    
    @Override
    public JComboBox getGrosor() 
    {
        return grosor;
    }

    @Override
    public JComboBox getTipoLinea ()
    {
        return tipoLinea;
    }

    public JComboBox getTipoConector()
    {
        return tipoConector;
    }
    
    private JButton JButton(ImageIcon imageIcon, boolean estado, String actionCommand) {
        JButton button = new JButton(imageIcon);
        button.setSize(22, 22);
        button.setMinimumSize(new Dimension(22, 44));
        button.setActionCommand(actionCommand);
        button.setBackground(null);
        button.setToolTipText(actionCommand);
        button.setBorderPainted(true);
        button.setBounds(0, 0, 22, 22);
        button.addActionListener(l);
        return button;
    }
}
