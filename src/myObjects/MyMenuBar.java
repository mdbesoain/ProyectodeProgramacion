/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import proyectoprogra.CommandNames;


/**
 *
 * @author Gloria
 */
public class MyMenuBar  extends JMenuBar{
    
    public ActionListener l;
    public JMenuItem rehacer;
    public JMenuItem deshacer;
    
    public MyMenuBar(ActionListener l)
    {
        super();
        this.l = l;
        createMenu();
    }
    public MyMenuBar( )
    {
        super();
    }

    public JMenuItem getRehacer() {
        return rehacer;
    }

    public JMenuItem getDeshacer() {
        return deshacer;
    }
    
     private void createMenu ()
    {
        
        List<MenuObject> menusObject = MenuStructure.getMenus ();
        for ( MenuObject menuObject : menusObject )
        {
            JMenu menu = this.addMenu ( menuObject );
            List<MenuObject> itemsObject = MenuStructure.getMenuItems ( menuObject );
            for ( MenuObject itemObject : itemsObject )
            {
                this.addMenuItem ( menu , itemObject );
            }
            super.add ( menu );
        }
        
    }

    private JMenu addMenu ( MenuObject menuObject )
    {
        JMenu menu = new JMenu ( menuObject.getName () );
        menu.setMnemonic ( menuObject.getMnemonic () );
        menu.setVisible(menuObject.isVisible());
        menu.setActionCommand ( TOOL_TIP_TEXT_KEY );
        if ( menuObject.hasAccelerator () )
        {
            menu.setAccelerator ( menuObject.getAccelerator () );
        }
        return menu;
    }

    private void addMenuItem ( JMenu parent , MenuObject itemObject )
    {
        if ( itemObject.isSeparator () )
        {
            parent.addSeparator ();
            return;
        }
        JMenuItem item = new JMenuItem ( itemObject.getName () );
        if ( itemObject.hasMnemonic () )
        {
            item.setMnemonic ( itemObject.getMnemonic () );
        }
        if ( itemObject.hasAccelerator () )
        {
            item.setAccelerator ( itemObject.getAccelerator () );
        }
        if ( itemObject.hasIcon () )
        {
            item.setIcon ( itemObject.getIcon () );
        }
        if(!itemObject.isVisible())
        {
            item.setEnabled(false);
        }
        item.setActionCommand ( itemObject.getName () );
        item.addActionListener ( l );
        if(item.getActionCommand().equals(CommandNames.REHACER))
        {
            this.rehacer = item;
        }
        if(item.getActionCommand().equals(CommandNames.DESHACER))
        {
            this.deshacer = item;
        }
        parent.add ( item );
    }

}
