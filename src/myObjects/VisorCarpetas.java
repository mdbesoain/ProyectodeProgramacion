/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class VisorCarpetas extends MyJPanel implements MouseListener , ActionListener {

    DefaultMutableTreeNode sel;
    File selec;
    ArrayList<ArchivosDeImagenes> imagenes;
    FlowLayout grid;
    MyIconExplorer selected;
    ActionListener l;
    MouseListener m;
    String filtro;
    boolean aZNombre;
    boolean aZCarpeta;
    boolean aZImagenes;
    
    public VisorCarpetas (ActionListener l, MouseListener m)
    {
        super ();
        this.aZNombre = true;
        this.aZCarpeta = true;
        this.aZImagenes = true;
        this.filtro = CommandNames.NOMBRE;
        super.addMouseListener ( m );
        setGrid();
        super.setLayout ( new GridLayout(10,2));
        setMinimumSize ( new Dimension ( 480 , 300 ) );
        this.setAutoscrolls(true);
        this.sel = null;
        this.imagenes = new ArrayList<> ();
        super.setVisible ( true );
        this.l=l;
        this.m=m;
    }

    public void setGrid()
    {   
        grid = new FlowLayout (FlowLayout.LEFT );
        grid.setHgap ( 5);
        grid.setVgap ( 10);
        
        
    }
    public DefaultMutableTreeNode getSel ()
    {
        return sel;
    }

    public void setSel ( DefaultMutableTreeNode sel )
    {
        this.sel = sel;
        updateImagenes ();
    }
    public String getUpperLevel(String path)
    {
        int index;
        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
        {
            index= path.lastIndexOf ( "\\");
        }
        else
        {
            index= path.lastIndexOf ( "/");
        }
        if(index!= -1)
        {return path.substring ( 0, index);}
        
        return null;
    }
    
    public boolean hasUpperLevel(String path)
    {
        boolean opcion= false;
        if(path.lastIndexOf ( "\\") !=-1 || path.lastIndexOf ( "/") !=-1)
        {
            opcion=true;
        }
        return opcion;
    }
    public void filtrarPor (String filtro)
    {
        switch(filtro)
        {
            case CommandNames.NOMBRE:
                this.filtro = filtro;
                if (aZNombre)
                {
                    aZNombre  =false;
                }
                else{
                    aZNombre = true;
                }
                break;
            case CommandNames.IMAGES:
                this.filtro = filtro;
                if (aZImagenes)
                {
                    aZImagenes  =false;
                }
                else{
                   aZImagenes = true;
                }
                break;
            case CommandNames.FILES:
                this.filtro = filtro;
                if (aZCarpeta)
                {
                    aZCarpeta  =false;
                }
                else{
                    aZCarpeta = true;
                }
                break;
        }
        updateImagenesDos(this.selec);
    }
    public void updateImagenesDos(File file)
    {   
        super.removeAll ();
        this.selec = file;
        this.imagenes = new ArrayList<> ();
        
        if ( file.list() != null )
        {
            String [] files = file.list();
            files = Sort(files);
            if(files.length<=1)
            {   
                if(files.length==1 && files[0].equals(".metadata"))
                {  
                    Label label = new Label("No hay datos para mostrar");
                    label.setFont(new Font("Arial",2, 30));
                    label.setForeground(Color.WHITE);
                    super.add (label);
                }
                else if(files.length == 0 )
                {  
                    Label label = new Label("No hay datos para mostrar");
                    label.setFont(new Font("Arial",2, 30));
                    label.setForeground(Color.WHITE);
                    super.add (label);
                }
            }
            for ( int i = 0 ; i < files.length ; i++ )
            {
                File fileAux = new File ( files[i] );
                if ( getExtencion ( fileAux.getName () ) == null )
                {
                    MyIconExplorer explorer ;
                    if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                     {
                         explorer= new MyIconExplorer ( true , fileAux.getName () , null , file.getPath () + "\\"+ fileAux.getName (),l ,m);
                     }
                    else
                    {
                       explorer= new MyIconExplorer ( true , fileAux.getName () , null , file.getPath () + "/"+ fileAux.getName (),l ,m);
                     }
                    explorer.addActionListener (l );
                    
                    
                    super.add ( explorer );
                }
                if ( getExtencion ( fileAux.getName () ) != null )
                {
                    if ( isPicture ( getExtencion ( fileAux.getName () ) ) )
                    {
                        Image imagen = new ImageIcon(fileAux.getAbsolutePath ()).getImage ();
                        MyIconExplorer explorer ;
                        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                        {
                            explorer= new MyIconExplorer ( false , fileAux.getName () , null , file.getPath () + "\\"+ fileAux.getName () ,l,m);
                        }
                       else
                       {
                          explorer= new MyIconExplorer ( false , fileAux.getName () , null , file.getPath () + "/"+ fileAux.getName () ,l,m);
                        }  
                        explorer.addActionListener (this );
                        
                        super.add ( explorer );
                    }
                   
                }

               updateUI ();
               repaint ();
            }

        }
        else
        {
            Label label = new Label("No hay datos para mostrar");
            label.setFont(new Font("Arial",2, 30));
            label.setForeground(Color.WHITE);
            super.add (label);
        }
        
    }
    
    public void updateImagenes ()
    {
        super.removeAll ();
        File file = obtenerRuta ( this.sel );
        this.selec = file;
        this.imagenes = new ArrayList<> ();
        if ( file.list() != null )
        {
            String [] files = file.list();
            files = Sort(files);
            if(files.length<=1)
            {   
                if(files.length==1 && files[0].equals(".metadata"))
                {  
                    Label label = new Label("No hay datos para mostrar");
                    label.setFont(new Font("Arial",2, 30));
                    label.setForeground(Color.WHITE);
                    super.add (label);
                }
                else if(files.length == 0 )
                {  
                    Label label = new Label("No hay datos para mostrar");
                    label.setFont(new Font("Arial",2, 30));
                    label.setForeground(Color.WHITE);
                    super.add (label);
                }
            }
            for ( int i = 0 ; i < files.length ; i++ )
            {
                File fileAux = new File ( files[i] );
                if ( getExtencion ( fileAux.getName () ) == null )
                {
                    MyIconExplorer explorer = new MyIconExplorer ( true , fileAux.getName () , null , file.getPath () + "\\"+ fileAux.getName (),l ,m);
                    explorer.addActionListener (l );
                    
                    
                    super.add ( explorer );
                }
                if ( getExtencion ( fileAux.getName () ) != null )
                {
                    if ( isPicture ( getExtencion ( fileAux.getName () ) ) )
                    {
                        Image imagen = new ImageIcon(fileAux.getAbsolutePath ()).getImage ();
                        MyIconExplorer explorer = new MyIconExplorer ( false , fileAux.getName () , null , file.getPath () + "\\"+ fileAux.getName () ,l,m);  
                        explorer.addActionListener (this );
                        
                        super.add ( explorer );
                    }
                   
                }

               updateUI ();
               repaint ();
            }

        }
        else
        {
            Label label = new Label("No hay datos para mostrar");
            label.setFont(new Font("Arial",2, 30));
            label.setForeground(Color.WHITE);
            super.add (label);
        }
    }

    public String getExtencion ( String name )
    {
        int k = name.lastIndexOf ( "." );
        String ext = null;
        if ( k != -1 )
        {
            ext = name.substring ( k , name.length () );
        }
        return ext;
    }

    public boolean isPicture ( String ext )
    {
        boolean flag = false;
        for ( String extension : CommandNames.PICTURE_EXTENCIONS )
        {
            if ( extension.equals ( ext ) )
            {
                flag = true;
            }
        }

        return flag;

    }
     public MyIconExplorer getSelected()
     {
         return this.selected;
     }

    public File obtenerRuta ( DefaultMutableTreeNode p )
    {
        String ruta = "";
        for ( int i = 0 ; i < p.getPath ().length - 1 ; i++ )
        {
            ruta += p.getPath ()[i + 1];
            if ( ruta.charAt ( ruta.length () - 1 ) != '/' )
            {
                ruta += "/";
            }
        }
        File f = new File ( ruta );
        return f;
    }

    @Override
    public void setColor ( String color )
    {
        switch ( color )
        {
            case CommandNames.ROJO:
                CommandNames.setColor ( Color.RED );
                break;
            case CommandNames.AZUL:
                CommandNames.setColor ( Color.BLUE );
                break;
            case CommandNames.VERDE:
                CommandNames.setColor ( Color.GREEN );
                break;
            case CommandNames.NEGRO:
                CommandNames.setColor ( Color.BLACK );
                break;
            case CommandNames.GRIS:
                CommandNames.setColor ( Color.GRAY );
                break;
            default:
                CommandNames.setColor ( Color.GRAY );
                break;
        }
        this.setBackground ( CommandNames.getColor () );
    }

    @Override
    public void mouseClicked ( MouseEvent e )
    {}

    @Override
    public void mousePressed ( MouseEvent e )
    {}

    @Override
    public void mouseReleased ( MouseEvent e )
    {}

    @Override
    public void mouseEntered ( MouseEvent e )
    {}

    @Override
    public void mouseExited ( MouseEvent e )
    {}

    @Override
    public void actionPerformed ( ActionEvent e )
    {}

    private String[] Sort(String[] files)
    {
        if (filtro.equals(CommandNames.NOMBRE))
        {
            sort(files);
            if (!aZNombre)
            {
                List < String > list = Arrays.asList(files);
                Collections.reverse(list);
                return (String[]) list.toArray();
            }
            return files;
        }
        else if (filtro.equals(CommandNames.FILES))
        {
            ArrayList<String> sorted = new ArrayList<>();
            for(String string : files)
            {
                File fileAux = new File ( string );
                if ( getExtencion ( fileAux.getName () ) == null )
                {
                    sorted.add(string);
                }
            }
            files = toArray(sorted);
            sort(files);
            if (!aZCarpeta)
            {
                List < String > list = Arrays.asList(files);
                Collections.reverse(list);
                return (String[]) list.toArray();
            }
            return files;
        }
        else if (filtro.equals(CommandNames.IMAGES))
        {
            ArrayList<String> sorted = new ArrayList<>();
            for(String string : files)
            {
                File fileAux = new File ( string );
                if ( getExtencion ( fileAux.getName () ) != null && isPicture(getExtencion ( fileAux.getName () )) )
                {
                    sorted.add(string);
                }
            }
            files = toArray(sorted);
            sort(files);
            if (!aZImagenes)
            {
                List < String > list = Arrays.asList(files);
                Collections.reverse(list);
                return (String[]) list.toArray();
            }
            return files;
        }
        return files;
    }

    private String[] toArray(ArrayList<String> sorted)
    {
        String[] array =  new String [sorted.size()];
        for (int i = 0; i<sorted.size() ; i++)
        {
            array[i]= sorted.get(i);
        }
        return array;
    }


    
    
}
