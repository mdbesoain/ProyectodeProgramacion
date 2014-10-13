/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import proyectoprogra.CommandNames;
import proyectoprogra.JPicturePanel;

/**
 *
 * @author Sergio Flores Labra
 */
public class FiltrosOrdenamiento extends MyJPanel implements ChangeListener, ActionListener{
    private int estado = 100;
    private JComboBox sort ;
    private JSlider range ;
    private JPicturePanel visor;
    private KPanel visor2;
    private int anchoPanel, altoPanel;
    private int diferencia; 
    private boolean flag;
    private boolean previa;
    private boolean enEditar;
    public MyJPanel panel3;
    public MyJPanel panel4;
    public JTable table;
    
    public FiltrosOrdenamiento( boolean a , int anchoPanel, int altoPanel, boolean enEditar, ChangeListener l  ) 
    {
        super();
        
        Dimension dim = new Dimension(150, 100);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setSize(dim);
        this.setPreferredSize(dim);
        
        this.anchoPanel = anchoPanel;
        this.altoPanel = altoPanel;
        this.diferencia=0;
        this.previa = true;
        this.enEditar=enEditar;
        this.setLayout(new GridLayout(4, 1));
        
        MyJPanel panel = new MyJPanel();
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createTitledBorder(CommandNames.FILTROS));
        this.sort = new JComboBox(createFiltros());
        panel.add(sort);    
        //-----------------------------------------
        
        MyJPanel panel2 = new MyJPanel();
        panel2.setLayout(new GridLayout(5, 1));
        panel2.setBackground(null);
        panel2.setBorder(BorderFactory.createTitledBorder(CommandNames.ZOOM));
        this.range = new JSlider(0, 200,100);
        this.range.setName(CommandNames.ZOOM);
        this.range.addChangeListener(this);
  
        /*this.range.setMajorTickSpacing(20);
        this.range.setMinorTickSpacing(20);
        this.range.setPaintTicks(true);
        this.range.setPaintLabels(true);
        this.range.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        Font font = new Font("Serif", Font.ITALIC, 10);
        this.range.setFont(font);
        */
        this.range.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel2.add(this.range);
        
        //-----------------------------------------
        
        panel3 = new MyJPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(null);
        panel3.setBorder(BorderFactory.createTitledBorder(CommandNames.VISTAPREVIA));
        if(enEditar)
        {
            this.visor2 = new KPanel();
            panel3.add(visor2, BorderLayout.CENTER);
        }
        else{
            this.visor = new JPicturePanel();
            this.visor.setImageTamano(true);
            panel3.add( visor, BorderLayout.CENTER );
        }
        this.range.addChangeListener(l);
        //-----------------------------------------
        
        panel4 = new MyJPanel();
        panel4.setBackground(null);
        //panel4.setLayout ( new FlowLayout ());
        panel4.setBorder(BorderFactory.createTitledBorder(CommandNames.CARACTERISTICAS));
        //-----------------------------------------
        
         
        if( a )
        {
            this.add(panel);
        }
        this.add(panel4);
        if(!a)
        {
            this.add(panel2);
        }
        if( a )
        {
            
            this.add(panel3);
        }
        else{
            panel3.setBorder(BorderFactory.createTitledBorder(CommandNames.IMAGENORIGINAL));
            this.add(panel3);
        }
        
    }

    private Object[] createFiltros() 
    {
        String[] lista = {CommandNames.NOMBRE,CommandNames.IMAGES,CommandNames.FILES};
        return lista;
    }
    
    
    public void setImage( Image image )
    {
        this.visor.setImage(image);
    }
    public void setIma ( Image image )
    {
        this.visor2.setImagen(image);
        repaint();
    }
    public Image imageZoomOUT()
    {
        return new ZoomImagen().disminuir(this.diferencia,this.visor.getImage(),this.anchoPanel,this.altoPanel);
    }
    
    public Image imageZoomIN()
    {
         return new ZoomImagen().aumentar(this.diferencia,this.visor.getImage(),this.anchoPanel,this.altoPanel);
    }
    
    public void setCaracteristicas(String path,String ext)
    {   this.panel4.removeAll ();
        MyExif myExif =new MyExif(path);
        if(myExif.getIterator() !=null)
        {   
            String[] columsnames= {"tag","descripcion"};
            if(myExif.getYear () !=null)
            {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()},{"Mes", myExif.getMonth ()} ,{"Año",myExif.getYear ()}};
            table = new JTable(data,columsnames);
            }
            else
            {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()}};
            table = new JTable(data,columsnames);
            }
            
        }
        else
        {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()}};
            String[] columsnames= {"tag","descripcion"};
            table = new JTable(data,columsnames);
        }
            table.setBackground ( null);
            table.setSize (70 ,70 );
            
            //JScrollPane scroll = new JScrollPane(table);
            //table.setFillsViewportHeight(true);
            panel4.add(table);
    }

    public JComboBox getSort()
    {
        return sort;
    }
       
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
       JSlider source =(JSlider) e.getSource();
        if(source.getValueIsAdjusting())
        {
            this.diferencia = source.getValue();
        }
    }

    public double getZoom() 
    {
        
        return (this.diferencia /100.0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
               
    }

    public void mostrarPrevia ()
    {
        if (previa)
        {
            previa = false;
        }
        else
        {
            previa = true;
        }
        this.panel3.setVisible ( previa);
        repaint ();
    }
    
     private class KPanel extends JPanel
    {
        private Image image;
        
        public KPanel()
        {
            super();
            image = null;
        }
        
        public void setImagen(Image ima)
        {
            this.image = ima;
        }
        
        @Override
        public void paint (Graphics g)
        {
            if(image!=null){g.drawImage(image, 0, 0,getWidth(),getHeight(),this);}
            else{g.setColor(Color.white); g.fillRect(0, 0,getWidth(),getHeight());}
        }
    }
    public void setCaracteristicas(BufferedImage im)
    {
        /*this.panel4.removeAll ();
        MyExif myExif =new MyExif(path);
        if(myExif.getIterator() !=null)
        {   
            String[] columsnames= {"tag","descripcion"};
            if(myExif.getYear () !=null)
            {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()},{"Mes", myExif.getMonth ()} ,{"Año",myExif.getYear ()}};
            table = new JTable(data,columsnames);
            }
            else
            {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()}};
            table = new JTable(data,columsnames);
            }
            
        }
        else
        {
            Object[][] data= {{"Tamaño",new ImageIcon(path).getIconWidth ()+" x "+new ImageIcon(path).getIconHeight ()}};
            String[] columsnames= {"tag","descripcion"};
            table = new JTable(data,columsnames);
        }
            table.setBackground ( null);
            table.setSize (70 ,70 );
            
            //JScrollPane scroll = new JScrollPane(table);
            //table.setFillsViewportHeight(true);
            panel4.add(table);*/
    }
}
