/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import proyectoprogra.CommandNames;

/**
 *
 * @author sergio
 */
public class MyTree extends JTree
{
    private DefaultMutableTreeNode raiz ;
    private JTree tree;
    private DefaultMutableTreeNode selecionado;
    private MouseListener l;
    public MyTree (MouseListener l)
    {
        super();
        super.setBackground (Color.GRAY);
        this.raiz = new DefaultMutableTreeNode(CommandNames.EXPLORER);
        this.tree = new JTree (raiz);
        this.selecionado = raiz;
        this.tree.setCellRenderer(new RendererArbol());
        this.setTreeSelectionListener();
        this.l=l;
        super.addMouseListener ( l);
    }

    public DefaultMutableTreeNode getSel ()
    {
        return selecionado;
    }

    public void setSel (DefaultMutableTreeNode sel)
    {
        this.selecionado = sel;
        
        
    }

    public DefaultMutableTreeNode getRaiz ()
    {
        return raiz;
    }

    public JTree getTree ()
    {
        return tree;
    }
    
    public void setColor(String color)
    {
        switch(color)
        {
            case CommandNames.ROJO:
                    CommandNames.setColor (Color.RED);
                    break;
            case CommandNames.AZUL:
                    CommandNames.setColor (Color.BLUE);
                    break;
            case CommandNames.VERDE:
                    CommandNames.setColor (Color.GREEN);
                    break;
            case CommandNames.NEGRO:
                    CommandNames.setColor (Color.BLACK);
                    break;
            case CommandNames.GRIS:
                    CommandNames.setColor (Color.GRAY);
                    break;
            default:
                    CommandNames.setColor (Color.GRAY);
                    break;
        }
        this.tree.setBackground (CommandNames.getColor ());
    }
    
    public void setSelectionPath(String path)
    {   
         DefaultMutableTreeNode node = new DefaultMutableTreeNode (path);
         tree.getSelectionModel ().setSelectionPath ( new TreePath ( node));
    
    }
    private void setTreeSelectionListener ()
    {   
        this.tree.addTreeSelectionListener (new TreeSelectionListener ()
        {   
            @Override
            public void valueChanged (TreeSelectionEvent e)
            {   
                
                try
                {
                    DefaultMutableTreeNode sel = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent ();
                    setSel ( sel );
                    agregarHijos (sel);
                    for (int i = 0; i < sel.getChildCount (); i ++)
                    {
                        DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) sel.getChildAt (i);
                        agregarHijos (hijo);
                    }

                }
                catch (NullPointerException npe)
                {
                }
            }
            
           
        });
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode (System.getProperty ("user.home"));
        
        setSel(hijo);
        raiz.add (hijo);
        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
        {
            DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode ("C:\\");
            DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode ("D:\\");
            raiz.add(hijo2);
            raiz.add(hijo3);
        }
    }
    
    public File getRutaSelec ()
    {
        DefaultMutableTreeNode sel = (DefaultMutableTreeNode) this.tree.getLastSelectedPathComponent ();
        return obtenerRuta (sel);
    }

    public void agregarHijos (DefaultMutableTreeNode padre) throws NullPointerException
    {
        if (padre != this.raiz)
        {
            File fpadre = obtenerRuta (padre);
            if (fpadre.isDirectory())
            {
                for (int i = 0; i < fpadre.list ().length; i ++)
                {
                    if (fpadre.list ()[i].charAt (0) != '.')
                    {
                        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode (fpadre.list ()[i]);
                       
                        File file = new File (fpadre.list ()[i]);
                        if (getExtension (file.getAbsolutePath ()).equals (""))
                        {
                            if(padre.getChildCount()>0)
                            {
                                TreeNode firstChild = padre.getFirstChild();
                                if(firstChild.toString().contentEquals(hijo.toString())){
                                    break;
                                }
                                else{
                                    padre.add(hijo);
                                }
                            }
                            else
                            {
                                padre.add(hijo);
                            }
                        }
                    }

                }
            }
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
