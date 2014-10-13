/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import proyectoprogra.CommandNames;
import proyectoprogra.MainWindow;

/**
 *
 * @author SergioElías
 */
public class OptionsFill extends MyJPanel implements ActionListener
{
    private ActionListener l; 
    private ItemListener l2; 
    private JComboBox grosor; 
    private Checkbox box;
    
    public OptionsFill(MainWindow aThis)
    {
        super();
        this.setLayout ( new FlowLayout (FlowLayout.LEFT));
        l= aThis;
        l2= aThis;
        // Selector de Grosor
        this.grosor = new JComboBox( createGrosores() );
        grosor.addActionListener(l);
        grosor.setToolTipText(CommandNames.GROSOR);
        grosor.setName(CommandNames.GROSOR);
        
        this.box = new Checkbox("Relleno");
        box.addItemListener(l2);
        this.add(grosor);
        this.add(box);
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        
    }
    
    private Object[] createGrosores() 
    {
        String[] lista = {CommandNames.GROSOR_1PX,CommandNames.GROSOR_3PX,CommandNames.GROSOR_5PX,CommandNames.GROSOR_7PX};
        return lista;
    }
    
    @Override
    public JComboBox getGrosor() 
    {
        return grosor;
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
