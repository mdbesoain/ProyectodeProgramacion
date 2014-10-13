/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import proyectoprogra.CommandNames;
import proyectoprogra.MainWindow;

/**
 *
 * @author SergioElías
 */
public class RotarOptions extends MyJPanel 
{
    private ActionListener l; 
    private JSlider sliderRotacion;
    private JLabel angulo;
    private JCheckBox girarTodos;
    public RotarOptions(MainWindow aThis)
    {
        super();
        this.setLayout ( new FlowLayout (FlowLayout.LEFT));
        sliderRotacion = new JSlider(0, 90);
        
        sliderRotacion.setValue(0);
        sliderRotacion.setName("Rotar");
       
        
        sliderRotacion.setToolTipText(CommandNames.COMMAND_ROTATE);
        sliderRotacion.addChangeListener(aThis);
        add(new JLabel("Rotar") );
        add(new JLabel("Angulo"));
        add(sliderRotacion);
        girarTodos= new JCheckBox("Girar Todos");
        girarTodos.addItemListener(aThis);
        angulo=new JLabel("0");
        add(angulo);
        this.add(girarTodos);
        
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
    
    public void updateLabel(String value)
    {
        this.angulo.setText(value);
        this.repaint();
    }

    public void resetSlider()
    {
        this.sliderRotacion.setValue(0);
    }
}
