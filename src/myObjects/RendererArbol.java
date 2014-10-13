

package myObjects;

import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import proyectoprogra.CommandNames;

/**
 *
 * 
 */
public class RendererArbol extends DefaultTreeCellRenderer{
    
    /** Creates a new instance of RendererArbol */
    ImageIcon carpeta;
    public RendererArbol() {
        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
        {
            File file = new File(System.getProperty("user.dir")); 
            FileSystemView fileSystemView = FileSystemView.getFileSystemView(); 

            carpeta = (ImageIcon) fileSystemView.getSystemIcon(file);
        }
        else
        {
            carpeta = new ImageIcon(CommandNames.CARPETACERRADA );
        }
        
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {                        
        super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
        String val = value.toString();
        ImageIcon i;
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)value;        
        try{
            File file= obtenerRuta(nodo);
            FileSystemView fileSystemView = FileSystemView.getFileSystemView(); 
            Icon miIcono = fileSystemView.getSystemIcon(file);
            if(val.contentEquals(CommandNames.EXPLORER))
            {  
                setIcon(carpeta);
            }
            
            else
            {
                if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                {
                    setIcon(miIcono);
                }
                else
                {
                    if (file.isDirectory())
                    {
                        setIcon(carpeta);
                    }
                    else
                    {
                        setIcon(new ImageIcon(CommandNames.Photo ));
                    }
                }
            }
            
        }
        catch(Exception ex)
        {
            setIcon(carpeta);
        }
        return this;
    }   
    
    public File obtenerRuta (DefaultMutableTreeNode p)
    {
        String ruta = "";
        for (int i = 0; i < p.getPath ().length - 1; i ++)
        {
            ruta += p.getPath ()[i + 1];
            if (ruta.charAt (ruta.length () - 1) != '/')
            {
                ruta += "/";
            }
        }
        
        File f = new File (ruta);
        return f;
    }
}
