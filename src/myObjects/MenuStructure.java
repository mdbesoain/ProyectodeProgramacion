/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.plaf.IconUIResource;
import proyectoprogra.CommandNames;
/**
 *
 * @author sergio
 */
public class MenuStructure
{

    private static HashMap<MenuObject , List<MenuObject>> structure;
    private static List<MenuObject> menus;

    static
    {
        MenuObject separator = new MenuObject ();
        separator.setSeparator ( true );

        MenuStructure.structure = new HashMap<> ();
        //----------------------- ARCHIVO-----------------------------------------
        MenuObject archivo = new MenuObject ( CommandNames.ARCHIVO , KeyEvent.VK_A );
        List<MenuObject> itemsArchivo = new ArrayList<> ();
        itemsArchivo.add ( new MenuObject ( CommandNames.NUEVO , KeyEvent.VK_N , KeyStroke.getKeyStroke ( KeyEvent.VK_N , ActionEvent.CTRL_MASK ), new ImageIcon(CommandNames.NEWICON) ) ) ;
        itemsArchivo.add ( new MenuObject ( CommandNames.ABRIR , KeyEvent.VK_B, KeyStroke.getKeyStroke ( KeyEvent.VK_A , ActionEvent.CTRL_MASK ),new ImageIcon(CommandNames.OPENICON) ) );
        itemsArchivo.add ( separator );
        itemsArchivo.add ( new MenuObject ( CommandNames.GUARDAR , KeyEvent.VK_G , KeyStroke.getKeyStroke ( KeyEvent.VK_G , ActionEvent.CTRL_MASK ),new ImageIcon(CommandNames.SAVEICON) ) );
        itemsArchivo.add ( new MenuObject ( CommandNames.GUARDAR_COMO , KeyEvent.VK_S, KeyStroke.getKeyStroke ( KeyEvent.VK_S, ActionEvent.CTRL_MASK ),new ImageIcon(CommandNames.SAVEASICON) ) );
        itemsArchivo.add ( separator );        
        itemsArchivo.add ( new MenuObject ( CommandNames.PAGE_SETUP , KeyEvent.VK_C ,null, new ImageIcon ( CommandNames.PAGESETUPICON ) ));
        itemsArchivo.add ( new MenuObject ( CommandNames.IMPRIMIR , KeyEvent.VK_C , KeyStroke.getKeyStroke ( KeyEvent.VK_P, ActionEvent.ALT_MASK ), new ImageIcon ( CommandNames.PRINTICON ) ));
        itemsArchivo.add ( separator ); 
        itemsArchivo.add ( new MenuObject ( CommandNames.SALIR , KeyEvent.VK_C , KeyStroke.getKeyStroke ( KeyEvent.VK_F4 , ActionEvent.ALT_MASK ), new ImageIcon ( CommandNames.EXITICON ) ));
        MenuStructure.structure.put ( archivo , itemsArchivo );
         //----------------------- EDICION-----------------------------------------
        MenuObject edicion = new MenuObject ( CommandNames.EDICION , KeyEvent.VK_E );
        List<MenuObject> itemsEdicion = new ArrayList<> ();
        itemsEdicion.add (  new MenuObject ( CommandNames.DESHACER , KeyEvent.VK_D , KeyStroke.getKeyStroke ( KeyEvent.VK_D , ActionEvent.CTRL_MASK ), new ImageIcon ( CommandNames.UNDOICON ), false) );
        itemsEdicion.add ( new MenuObject ( CommandNames.REHACER , KeyEvent.VK_R , KeyStroke.getKeyStroke ( KeyEvent.VK_R , ActionEvent.CTRL_MASK ), new ImageIcon ( CommandNames.REDOICON ), false ) );
        itemsEdicion.add ( separator );
        itemsEdicion.add ( new MenuObject ( CommandNames.COPIAR , KeyEvent.VK_C , KeyStroke.getKeyStroke ( KeyEvent.VK_C , ActionEvent.CTRL_MASK ), new ImageIcon ( CommandNames.COPYICON )  ) );
        itemsEdicion.add ( new MenuObject ( CommandNames.RECORTAR , KeyEvent.VK_X , KeyStroke.getKeyStroke ( KeyEvent.VK_X , ActionEvent.CTRL_MASK ) , new ImageIcon ( CommandNames.CUTICON ) ) );
        itemsEdicion.add ( new MenuObject ( CommandNames.PEGAR , KeyEvent.VK_V, KeyStroke.getKeyStroke ( KeyEvent.VK_V , ActionEvent.CTRL_MASK ) , new ImageIcon ( CommandNames.PASTEICON ) ) );
        itemsEdicion.add ( separator );
        itemsEdicion.add ( new MenuObject ( CommandNames.SELECCIONAR_TODO , KeyEvent.VK_C , KeyStroke.getKeyStroke ( KeyEvent.VK_E , ActionEvent.CTRL_MASK ) , new ImageIcon ( CommandNames.SELECTALLICON ) ) );
        
        MenuStructure.structure.put ( edicion , itemsEdicion );
       
        
        //------------------------VER-------------------------------------------
        MenuObject ver = new MenuObject ( CommandNames.VER , KeyEvent.VK_V );
        List<MenuObject> itemsVer = new ArrayList<> ();
        itemsVer.add ( new MenuObject ( CommandNames.VISTAPREVIA , KeyEvent.VK_I, new ImageIcon ( CommandNames.PREVIEW ) ) );
        MenuStructure.structure.put ( ver , itemsVer );

       //------------------------HERRAMIENTAS-------------------------------------------
        MenuObject herramientas= new MenuObject ( CommandNames.HERRAMIENTAS , KeyEvent.VK_H );
        List<MenuObject> itemsHerramientas= new ArrayList<> ();
        itemsHerramientas.add ( new MenuObject ( CommandNames.PREFERENCIAS , KeyEvent.VK_P , KeyStroke.getKeyStroke ( KeyEvent.VK_P , ActionEvent.CTRL_MASK ), new ImageIcon ( CommandNames.PREFERENCESICON ) ) );
        MenuStructure.structure.put ( herramientas , itemsHerramientas );
        //----------------------------AYUDA-------------------------------------
        MenuObject ayuda = new MenuObject ( CommandNames.AYUDA , KeyEvent.VK_U );
        List<MenuObject> itemsAyuda = new ArrayList<> ();
        itemsAyuda.add ( new MenuObject ( CommandNames.AYUDA_ITEM , KeyEvent.VK_Y, KeyStroke.getKeyStroke ( KeyEvent.VK_F10 , ActionEvent.CTRL_MASK ), new ImageIcon ( CommandNames.HELPSICON ) ) );
        itemsAyuda.add ( new MenuObject ( CommandNames.ACERCA_DE , KeyEvent.VK_R, KeyStroke.getKeyStroke ( KeyEvent.VK_B , ActionEvent.CTRL_MASK ) , new ImageIcon ( CommandNames.ABOUTICON )) );
        
        MenuStructure.structure.put ( ayuda , itemsAyuda );

        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        MenuStructure.menus = new ArrayList<> ();
        MenuStructure.menus.add ( archivo );
        MenuStructure.menus.add ( edicion );
        MenuStructure.menus.add ( ver );
        MenuStructure.menus.add ( herramientas);
        MenuStructure.menus.add ( ayuda );

    }

    static public List<MenuObject> getMenus ()
    {
        return MenuStructure.menus;
    }

    static public List<MenuObject> getMenuItems ( MenuObject menu )
    {
        return MenuStructure.structure.get ( menu );
    }
}