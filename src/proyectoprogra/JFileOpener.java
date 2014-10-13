/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import myObjects.CollagePanel;

/**
 *
 * @author Gloria
 */

public class JFileOpener extends JFileChooser {

    public File file;
    public File[] files;
    public ImageIcon imageIcon;
    public String nameWindows;

    JFileOpener ()
    {
        super.setAcceptAllFileFilterUsed ( false );   
        this.nameWindows = CommandNames.ABRIR;
        //super.addChoosableFileFilter ( new FileNameExtensionFilter ( "Archivos de Imagen" , "png" , "jpg" , "jpeg" , "bmp" ) );
        super.setFileFilter(new FileNameExtensionFilter ( "Archivos de Imagen" , "png" , "jpg" , "jpeg" , "bmp" ));
        //super.addChoosableFileFilter(new FileNameExtensionFilter ( "GIF" , "gif" ));
        super.addChoosableFileFilter(new FileNameExtensionFilter ( "JPG" , "jpg" ,"jpeg"));
        super.addChoosableFileFilter(new FileNameExtensionFilter ( "BMP" , "bmp"));
        super.addChoosableFileFilter(new FileNameExtensionFilter ( "png" , "png"));
        super.setFileHidingEnabled ( true );
        super.setMultiSelectionEnabled ( false);
        super.setRequestFocusEnabled ( true );
        
    }


    public Image getImage ()
    {
        setNameWindows ( CommandNames.ABRIR );
        try
        {

            setFileSelectionMode ( JFileChooser.FILES_ONLY );

            if ( showOpenDialog ( super.getTopLevelAncestor () ) == JFileChooser.APPROVE_OPTION )
            {
                file = getSelectedFile ();
                imageIcon = new ImageIcon ( file.getAbsolutePath () );
                return imageIcon.getImage ();
            }

        } catch ( Exception e )
        {
        }

        return null;
    }


    public boolean saveFile ( JPicturePanel panel )
    {
        setNameWindows ( CommandNames.GUARDAR_COMO );
        if ( showSaveDialog ( super.getTopLevelAncestor () ) == JFileChooser.APPROVE_OPTION )
        {
           File selectedFile= checkExtension(getSelectedFile());
            panel.saveTo ( selectedFile );
            return true;
        }
        return false;

    }
    public boolean saveFileCollage ( CollagePanel panel )
    {
        setNameWindows ( CommandNames.GUARDAR_COMO );
        if ( showSaveDialog ( super.getTopLevelAncestor () ) == JFileChooser.APPROVE_OPTION )
        {
           File selectedFile= checkExtension(getSelectedFile());
            panel.saveTo ( selectedFile );
            return true;
        }
        return false;

    }

    public void setNameWindows ( String nameWindows )

    {
        this.nameWindows = nameWindows;
    }

    @Override
    protected JDialog createDialog ( Component parent ) throws HeadlessException
    {
        JDialog dialog = super.createDialog ( parent );

        dialog.setIconImage ( new ImageIcon ( CommandNames.ICON ).getImage () );
        dialog.setTitle ( this.nameWindows );
        return dialog;

    }

    private File checkExtension(File selectedFile) {
         String ext = getExtension(selectedFile.getPath());
         if(ext.equals(""))
         {
             if(this.getFileFilter().getDescription().contains("Archivos de Imagen"))
             {
             return new File(selectedFile.getPath()+ ".png");
             }
             else
             {
                 return new File(selectedFile.getPath()+ "." + this.getFileFilter().getDescription());
             }
         }
         else
         {
             for(int i=0; i< ImageIO.getWriterFormatNames().length;i++)
             {
                 if(ImageIO.getWriterFormatNames()[i].contains(ext))
                 {
                     return selectedFile;
                 }
             }
             return new File(selectedFile.getPath()+ ".png");
         }
    }

    protected String getExtension (String name)
    {
        String[] str = name.split ("\\.");
        if (str.length > 1)
        {
            return str[str.length - 1];
        }

        return ""; //-- no extension
    }
}
