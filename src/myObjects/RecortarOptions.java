/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
public class RecortarOptions extends MyJPanel 
{
    private ActionListener l;
    private JButton done;
    private JButton cancel;
    
    public RecortarOptions(MainWindow aThis)
    {
        super();
        this. l = aThis;
        this.setLayout ( new FlowLayout (FlowLayout.LEFT));
        this.done = JButton ( true, CommandNames.DONE_RECORTAR );
        this.cancel = JButton ( true, CommandNames.CANCEL_RECORTAR );
        this.add ( done);
        this.add ( cancel);
        
    }
    
    
    private JButton JButton( boolean estado, String actionCommand) {
        JButton button = new JButton(actionCommand);
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
