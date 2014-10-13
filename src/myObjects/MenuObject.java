/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sergio
 */
public class MenuObject
{
    private Icon icon;
    private String name;
    private int mnemonic;
    private KeyStroke accelerator;
    private boolean separator;
    private boolean visible;

    public MenuObject ()
    {
        this.name = null;
        this.mnemonic = -1;
        this.accelerator = null;
        this.separator = false;
        this.icon = null;
        this.visible =true;
    }

    public MenuObject ( String name )
    {
        this.name = name;
        this.mnemonic = -1;
        this.accelerator = null;
        this.separator = false;
        this.icon = null;
        this.visible =true;
    }

    public MenuObject ( String name , int mnemonic )
    {
        this.name = name;
        this.mnemonic = mnemonic;
        this.accelerator = null;
        this.separator = false;
        this.icon = null;
        this.visible =true;
    }

    public MenuObject ( String name , KeyStroke accelerator )
    {
        this.name = name;
        this.accelerator = accelerator;
        this.icon = null;
        this.visible =true;
    }

    public MenuObject ( String name , int mnemonic , KeyStroke accelerator )
    {
        this ( name , mnemonic );
        this.accelerator = accelerator;
    }
    
    public MenuObject ( String name , int mnemonic , ImageIcon icono )
    {
        this ( name , mnemonic );
        this.icon = icono;
    }
    
    public MenuObject ( String name , int mnemonic , KeyStroke accelerator, ImageIcon icono )
    {
        this ( name , mnemonic );
        this.accelerator = accelerator;
        this.icon = icono;
    }

    
    public MenuObject ( String name , int mnemonic , KeyStroke accelerator, ImageIcon icono, boolean visible )
    {
        this ( name , mnemonic );
        this.accelerator = accelerator;
        this.icon = icono;
        this.visible = visible;
    }

    public boolean hasIcon()
    {
        return (this.icon!= null);
    }
    
    public boolean hasMnemonic ()
    {
        return ( this.mnemonic != -1 );
    }

    public void setSeparator ( boolean separator )
    {
        this.separator = separator;
    }

    public boolean isSeparator ()
    {
        return separator;
    }

    public KeyStroke getAccelerator ()
    {
        return accelerator;
    }

    public void setAccelerator ( KeyStroke accelerator )
    {
        this.accelerator = accelerator;
    }

    public int getMnemonic ()
    {
        return mnemonic;
    }

    public void setMnemonic ( int mnemonic )
    {
        this.mnemonic = mnemonic;
    }

    public String getName ()
    {
        return name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public boolean hasAccelerator ()
    {
        return ( this.accelerator != null );
    }

    Icon getIcon ()
    {
        return this.icon;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}