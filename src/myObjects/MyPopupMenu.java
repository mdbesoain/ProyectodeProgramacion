/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import proyectoprogra.CommandNames;

/**
 *
 * @author Gloria
 */
public class MyPopupMenu extends JPopupMenu{
    private Point point;
    private ActionListener l;
    private String path;
    public MyPopupMenu(ActionListener l)
    {   
        super();
        super.setCursor ( new Cursor(Cursor.HAND_CURSOR) );
        this.l=l;
        
    }
    
    public void setPath(String path)
    {
        this.path=path;
    }
    public String getPath()
    {
        return this.path;
    }
    private void addMenus1 ()
    {  super.removeAll ();
       
       super.add(createMenu(CommandNames.MENU_1,CommandNames.OPENICON));
       super.add(createMenu(CommandNames.MENU_2,CommandNames.PAGESETUPICON));
       super.add(createMenu(CommandNames.MENU_3,CommandNames.PREVIEW));
       super.addSeparator();
       super.add(createMenu(CommandNames.MENU_4,CommandNames.OPTIONS));
      
       
    }
    
    private void addMenus2 ()
    {  super.removeAll ();
       super.add(createMenu(CommandNames.MENU_5,CommandNames.CARPETAABIERTA));
       super.add(createMenu(CommandNames.MENU_6,CommandNames.WINDOWS_EXPLORER));
       super.add(createMenu(CommandNames.MENU_7,CommandNames.OPTIONS));
       
      
       
    }
    
    private JMenuItem createMenu(String name, String icon)
    {   
        JMenuItem item = new JMenuItem(name);
        item.setIcon ( new ImageIcon(icon));
        item.setActionCommand ( name);
        item.addActionListener ( l );
        
        return item;
        
    }    
    
    public void mostrar(boolean opcion, int x , int y, boolean isFolder)
    {
        if(!isFolder)
        {
            addMenus1();
        }
        if(isFolder)
        {
            addMenus2();
        }
        this.point = new Point(x , y );
        super.setLocation ( point );
        super.setVisible ( opcion );
    }
}
