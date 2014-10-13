/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import proyectoprogra.CommandNames;

/**
 *
 * @author Gloria
 */
public class MyIconExplorer extends JButton
{

    private String name;
    private boolean isFolder;
    private ImageIcon icon;
    private Image image;
    private int width;
    private String path;
    public String extension;

    public MyIconExplorer(boolean isFolder, String name, Image image, String path, ActionListener l, MouseListener m)
    {
        super();
        super.setSize(50, 50);
        super.setPreferredSize(new Dimension(50, 50));
        super.setMaximumSize(new Dimension(50, 50));
        super.addActionListener(l);
        super.addMouseListener(m);

        super.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        super.setHorizontalAlignment ( SwingConstants.LEFT);
        super.setVerticalTextPosition ( SwingConstants.BOTTOM);

        //super.setCursor(new Cursor(Cursor.HAND_CURSOR));

        super.setHorizontalAlignment(SwingConstants.LEFT);
        super.setVerticalTextPosition(SwingConstants.BOTTOM);


        //super.setIcon ( null );
        this.path = path;
        this.extension = null;
        if (!isFolder)
        {
            this.extension = getExtension(name);
        }
        this.setToolTipText(name);
        if (name.length() > 20)
        {
            this.name = name.substring(0, 20) + "...";
        } else
        {
            this.name = name;
        }
        this.width = 30;
        this.isFolder = isFolder;
        if (isFolder)
        {
            File file = new File(path);
            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            try
            {
                if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                {
                    this.icon = (ImageIcon) fileSystemView.getSystemIcon(file);
                }
                else
                {
                    this.icon = new ImageIcon(CommandNames.CARPETACERRADA );
                }
            } catch (Exception ex)
            {
                //System.out.println("Excep:" + ex);
            }
            //this.icon = new ImageIcon();
        } else
        {
            File file = new File(path);
            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
            {
                this.icon = (ImageIcon) fileSystemView.getSystemIcon(file);
            }
            else
            {
                this.icon = new ImageIcon(CommandNames.Photo );
            }

        }

        //this.image=icon.getImage ().getScaledInstance ( this.getWidth (), this.getHeight (), Image.SCALE_SMOOTH);
        super.setText(this.name);
        super.setOpaque(true);
        super.setIcon(icon);

    }

    public boolean isPicture()
    {
        if (this.isFolder)
        {
            return false;
        }
        return true;
    }

    public String getPath()
    {
        return this.path;
    }

    private void setWidth(int width)
    {
        this.width = width;
    }

    private int getPositionX()
    {
        int xPanel = this.getWidth();
        int xImage = this.image.getWidth(this);

        return (xPanel - xImage) / 2;
    }

    private int getPositionY()
    {
        int yPanel = this.getHeight();
        int yImage = this.image.getHeight(this);

        return (yPanel - yImage) / 2;
    }

    public String getExtension(String name)
    {
        int k = name.lastIndexOf(".");
        String ext = null;
        if (k != -1)
        {
            ext = name.substring(k, name.length());
        }
        return ext;
    }

}
