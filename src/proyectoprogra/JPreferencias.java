/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Gloria
 */
public class JPreferencias extends JFrame {
    JPreferencias()
    {
        super.setTitle ( CommandNames.PREFERENCIAS);
        super.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        super.setResizable ( false);
        super.setSize(425,400);
        super.setLocation ( 350, 50);
        super.setDefaultCloseOperation ( DISPOSE_ON_CLOSE);
        addTabs();
    }
    
    public void addTabs()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = new ImageIcon("images/middle.gif");

        /*tabbedPane.addTab(CommandNames.TAB1, icon, new JGeneral(), CommandNames.TAB1_TOOLTIP);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        
        tabbedPane.addTab(CommandNames.TAB2, icon, new JInterfaz( this ), CommandNames.TAB2_TOOLTIP);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JPanel panel3 = new JPanel();
        tabbedPane.addTab(CommandNames.TAB3, icon, panel3,CommandNames.TAB3_TOOLTIP);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JPanel panel4 = new JPanel();
        tabbedPane.addTab(CommandNames.TAB4, icon, panel4,CommandNames.TAB4_TOOLTIP);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);*/
        tabbedPane.addTab(CommandNames.TAB1, icon, new JInterfaz( this ), CommandNames.TAB1_TOOLTIP);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        super.add(tabbedPane);
    }

}
