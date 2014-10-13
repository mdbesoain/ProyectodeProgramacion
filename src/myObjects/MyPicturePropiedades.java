/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import com.drew.metadata.Tag;
import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import proyectoprogra.CommandNames;

/**
 *
 * @author Gloria
 */
public class MyPicturePropiedades extends JFrame{
    
    public JTabbedPane panel;
    public File file ;
    public MyPicturePropiedades(String path)
    {
        super();
        super.setSize ( 300,400);
        super.setLocationRelativeTo ( this );
        super.setResizable ( false);
        this.file = new File(path);
        this.panel=new JTabbedPane();
        super.setBackground ( null);
        super.setTitle ( CommandNames.CARACTERISTICAS );
        super.setIconImage ( new ImageIcon (CommandNames.ICON).getImage ());
        super.setDefaultCloseOperation ( DISPOSE_ON_CLOSE);
        caracteristicasGenerales(path);
        setCaracteristicas(path);
        
       super.add ( panel );
       
    }
    
    public void caracteristicasGenerales(String path)
    {
        Image icon = new ImageIcon(path).getImage ();
        String[] columsnames= {"tag","descripcion"};
        
        Object[][] data = new Object[6][2];
        
        data[0][0]="Nombre";
        data[0][1]=getName(path);
        data[1][0]="extension";
        data[1][1]=getExtension ( getName(path));        
        data[2][0]="Ancho";
        data[2][1]=icon.getWidth ( rootPane );
        data[3][0]="Alto";
        data[3][1]=icon.getHeight (rootPane );
        data[4][0]="Size";
        data[4][1]=getSizeFile();
        data[5][0]="Ubicacion:";
        data[5][1]= path;
        JTable table = new JTable(data,columsnames);
        table.setBackground ( null);
        table.setRowSelectionAllowed ( false);
        table.setAutoResizeMode ( MAXIMIZED_BOTH);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setFillsViewportHeight(true);
        this.panel.addTab ( CommandNames.TAB_GENERAL, scrollPane);
       
    }
    
    public String  getSizeFile()
    {  
      String string= "";
      long fileSize = file.length();
        System.out.println ( "fileSize:"+fileSize );
      if(fileSize<1024)
      {string = fileSize+" B";}
      if(fileSize<1048576 && fileSize>1024)
      {
         double size =(double)fileSize/(1024); 
          string = round(size) +" KB";
      }
      if(fileSize>1048576){
          double size =(double)fileSize/(1024*1024);
          string = round(size) +" MB";
      }
      return  string ; 
    }
    
    public  double round(double value)
    {   int places= 2;
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public void setCaracteristicas(String path)
    {   
        Iterator iterator =new MyExif(path).getIterator ();
        int i= 0;
        String[] columsnames= {"tag","descripcion"};
        Object[][] data = new Object[31][2];
        
        if(iterator!=null)  
        {   
            List list;
            while(iterator.hasNext ())
                {     
                     Tag tag = (Tag)iterator.next();                 
                       data[i][0]=tag.getTagName ();
                       data[i][1]=tag.getDescription ();
                       i=i+1;
                        
                }
         JTable table = new JTable(data,columsnames);
         table.setBackground ( null);
         table.setRowSelectionAllowed ( false);
         table.setAutoResizeMode ( MAXIMIZED_BOTH);
         JScrollPane scrollPane = new JScrollPane(table);
         table.setFillsViewportHeight(true);
         this.panel.addTab ( CommandNames.TAB_AVANZADO, scrollPane);
         
        }
        else
        {   super.setLayout(new BorderLayout ());
            super.add(BorderLayout.CENTER,new JLabel("No hay informacion disponible"));
        }
        
    }
    
    public void Visible(boolean visible)
    {
        super.setVisible ( visible );
    }
    public String getName ( String path )
    {
        int k = path.lastIndexOf ( "\\" );
        String name = null;
        if ( k != -1 )
        {
            name = path.substring ( k+1 , path.length () );
        }
       
        return name;
    }
    public String getExtension ( String name )
    {
        int k = name.lastIndexOf ( "." );
        String ext = null;
        if ( k != -1 )
        {
            ext = name.substring ( k , name.length () );
        }
        return ext;
    }
}
