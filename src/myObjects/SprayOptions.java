/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import proyectoprogra.MainWindow;

/**
 *
 * @author SergioElías
 */
public class SprayOptions extends MyJPanel 
{
    private ActionListener l; 
    private JSlider sliderDispersion;
    private JSlider sliderConcentrar;
    public SprayOptions(MainWindow aThis)
    {
        super();
        this.setLayout ( new FlowLayout (FlowLayout.LEFT));
        l= aThis;
        sliderDispersion = new JSlider(5, 50);
        sliderDispersion.setName("Dispersion");
        sliderDispersion.setToolTipText("Dispersion");
        sliderConcentrar = new JSlider(5, 30);
        sliderConcentrar.setName("Concentrar");
        
        sliderConcentrar.setToolTipText("Concentrar");
        sliderDispersion.setValue(17);
        sliderConcentrar.setValue(10);
        sliderDispersion.addChangeListener(aThis);
        sliderConcentrar.addChangeListener(aThis);
        
        add(new JLabel("Dispersion") );
        add(sliderDispersion);
        
        add(new JLabel("Concentrar"));
        add(sliderConcentrar);
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
