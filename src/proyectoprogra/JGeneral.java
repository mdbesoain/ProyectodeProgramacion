/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author 
 */
public class JGeneral extends JPanel {
    public  ActionListener ae;
    
    JGeneral ()
    {
        super.setToolTipText ( CommandNames.TAB1_TOOLTIP );
        super.setName ( CommandNames.TAB1 );
        super.setLayout ( null );
        addIdiomaSelecter ();
        addMeasureUnits ();
        super.setVisible ( true );
        
    }

    public void addIdiomaSelecter ()
    {
        JComboBox lenguajeSelecter = new JComboBox ( CommandNames.LANGUAJE_SELECTER );
        lenguajeSelecter.setSelectedIndex ( 0 );
        JLabel text = new JLabel( CommandNames.LANGUAJE_SELECTER_NAME); 
        text.setForeground ( null);
        lenguajeSelecter.addActionListener ( ae );
        super.add(lenguajeSelecter);
        super.add(text);
        Insets insets = super.getInsets();
        Dimension size = lenguajeSelecter.getPreferredSize();
        lenguajeSelecter.setBounds(54 + insets.left, 5 + insets.top,
             size.width, size.height);
        size= text.getPreferredSize ();
        text.setBounds(5 + insets.left, 8 + insets.top,
             size.width, size.height);
    }
    
    public void addMeasureUnits ()
    {
        JComboBox measureSelecter = new JComboBox ( CommandNames.MEASURE_SELECTER );
        measureSelecter.setSelectedIndex (3 );
        JLabel text = new JLabel( CommandNames.MEASURE_SELECTER_NAME); 
        text.setForeground ( null);
        measureSelecter.addActionListener ( ae );
        super.add(measureSelecter);
        super.add(text);
        Insets insets = super.getInsets();
        Dimension size = measureSelecter.getPreferredSize();
        measureSelecter.setBounds(115 + insets.left, 40 + insets.top,
             size.width, size.height);
        size= text.getPreferredSize ();
        text.setBounds(5 + insets.left, 43 + insets.top,
             size.width, size.height);
    
    }
}
