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
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import proyectoprogra.CommandNames;
import proyectoprogra.MainWindow;


public class MyCollageToolbar extends MyJPanel {
    private JComboBox sort ;
    private ActionListener l;
    private JComboBox grosor;
    private Object[][] data;
    private Object[] columnas;
    private JTable table;
   
    private JList list;
    private DefaultListModel listModel;
    private MyJPanel panelL;
    public JButton add;
    public JButton del;
    private final MyJPanel panel3;
    private KPanel visor;
    private boolean previa;
     public MyCollageToolbar(MainWindow aThis)
    {
        super();
        this.previa = true;
        l=aThis;
        this.setMinimumSize(new Dimension(150, 100));
        this.setPreferredSize (new Dimension(150, 100) );
        this.setLayout(new GridLayout(4, 1));
        
        //----------------------------------------------------
        panelL = new MyJPanel(new BorderLayout());
        panelL.setLayout ( null);
        panelL.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.PICTURES));
        panelL.setMinimumSize(new Dimension(150, 120));
        panelL.setPreferredSize (new Dimension(150,120) );
        add = new JButton(CommandNames.ADD_PICTURE);
        del = new JButton (CommandNames.DELETE_PICTURE);
        add.addActionListener ( l );
        del.addActionListener ( l );
        del.setSelected ( false);
        add.setSelected ( false);
        panelL.add(del);
        panelL.add(add);
        add.setHorizontalAlignment(SwingConstants.LEADING);
        del.setHorizontalAlignment(SwingConstants.LEFT);
        Insets insets = panelL.getInsets ();
        Dimension size = del.getPreferredSize();
         add.setPreferredSize(size);
        add.setBounds( insets.left-10,2 + insets.top-10,
             size.width +3, size.height);
        size = add.getPreferredSize();
        
        del.setBounds(65+ insets.left, 2 + insets.top-10,
             size.width, size.height);
       
        
       //----------------------------
         listModel = new DefaultListModel();
         listModel.addElement("none");
         list= new JList(listModel);
         list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         list.setLayoutOrientation(JList.VERTICAL);
         
         list.setVisibleRowCount(5);
         list.setCursor ( new Cursor(Cursor.HAND_CURSOR) );
         JScrollPane listScrollPane = new JScrollPane(list);
         
         panelL.add (listScrollPane);
         //panelL.add (list);
         insets = panelL.getInsets ();
         size = listScrollPane.getPreferredSize();
         listScrollPane.setBounds ( 0, 23 + insets.top, size.width+110,size.height + 30);
         
         this.add(panelL);
        //----------------------------------------------------
        MyJPanel pane2L = new MyJPanel();
        pane2L.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.TOOLS));
        pane2L.setAutoscrolls(true);
        pane2L.setAutoscrolls(true);
        pane2L.setLayout(new GridLayout(3, 3));
        pane2L.setBackground(null);

        JButton move = JButton(new ImageIcon(CommandNames.ICON_MOVE_IMAGE), false, CommandNames.COMMAND_MOVE_IMAGE);
        JButton resize = JButton(new ImageIcon(CommandNames.ICON_RESIZE_IMAGE), false, CommandNames.CAMBIARTAMANO);
        JButton pen = JButton(new ImageIcon(CommandNames.ICON_PEN), false, CommandNames.COMMAND_PEN_BUTTON);
        JButton brush = JButton( new ImageIcon(CommandNames.ICON_BRUSH), false,  CommandNames.COMMAND_BRUSH_BUTTON);
        JButton text = JButton( new ImageIcon(CommandNames.ICON_TEXT), false,  CommandNames.COMMAND_TEXT_BUTTON);
        JButton picker = JButton( new ImageIcon(CommandNames.ICON_PICKER), false,  CommandNames.COMMAND_PICKER_BUTTON);
        JButton rectangle = JButton( new ImageIcon(CommandNames.ICON_RECTANGLE), false,  CommandNames.COMMAND_RECTANGLE_BUTTON);
        JButton circle = JButton( new ImageIcon(CommandNames.ICON_CIRCLE), false,  CommandNames.COMMAND_CIRCLE_BUTTON);
        JButton line = JButton( new ImageIcon(CommandNames.ICON_LINE), false,  CommandNames.COMMAND_LINE_BUTTON);
        JButton canvasSize= JButton( new ImageIcon(CommandNames.ICON_RESIZE_CANVAS), false,  CommandNames.COMMAND_RESIZE_CANVAS);
        JButton marco = JButton( new ImageIcon(CommandNames.ICON_Marco), false,  CommandNames.COMMAND_MARCO_BUTTON);
        JButton rotar = JButton( new ImageIcon(CommandNames.ICON_ROTATE), false,  CommandNames.COMMAND_ROTATE);
        JButton seleccion = JButton( new ImageIcon(CommandNames.ICON_Seleccion), false,  CommandNames.COMMAND_SELECCION_BUTTON);
        JButton collage = JButton( new ImageIcon(CommandNames.ICON_COLLAGE), false,  CommandNames.COMMAND_PATRON_BUTTON);
        JButton top = JButton( new ImageIcon(CommandNames.ICON_BRING_TO_FRONT), false,  CommandNames.COMMAND_ICON_BRING_TO_FRONT);
        
        pane2L.add(move);
        pane2L.add(seleccion);
        pane2L.add(resize);
        pane2L.add(brush);
        pane2L.add(text);
        pane2L.add(picker);
        pane2L.add(rectangle);
        pane2L.add(circle);
        pane2L.add(line);
        pane2L.add(canvasSize);
        pane2L.add(marco);
        pane2L.add(rotar);
        pane2L.add(collage);
        pane2L.add(top);
        this.add(pane2L);
        //---------------------------------------
        MyJPanel panel5 = new MyJPanel();
        panel5.setAutoscrolls(true);
        panel5.setLayout(new GridLayout(3, 3));
        panel5.setBackground(null);
        
        panel5.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.COLORES));
        JButton colorBlack = JButton( new ImageIcon(CommandNames.ICON_BLACK), false,  CommandNames.COLOR_BLACK);
        JButton colorWhite = JButton( new ImageIcon(CommandNames.ICON_WHITE), false,  CommandNames.COLOR_WHITE);
        JButton colorGray = JButton( new ImageIcon(CommandNames.ICON_GRAY), false,  CommandNames.COLOR_GRAY);
        JButton colorBrown = JButton( new ImageIcon(CommandNames.ICON_BROWN), false,  CommandNames.COLOR_BROWN);
        JButton colorRed = JButton( new ImageIcon(CommandNames.ICON_RED), false,  CommandNames.COLOR_RED);
        JButton colorMagenta = JButton( new ImageIcon(CommandNames.ICON_MAGENTA), false,  CommandNames.COLOR_MAGENTA);
        JButton colorOrange = JButton( new ImageIcon(CommandNames.ICON_ORANGE), false,  CommandNames.COLOR_ORANGE);
        JButton colorYellow = JButton( new ImageIcon(CommandNames.ICON_YELLOW), false,  CommandNames.COLOR_YELLOW);
        JButton colorGreen = JButton( new ImageIcon(CommandNames.ICON_GREEN), false,  CommandNames.COLOR_GREEN);
        JButton colorBlue = JButton( new ImageIcon(CommandNames.ICON_BLUE), false,  CommandNames.COLOR_BLUE);
        panel5.add(colorBlack);
        panel5.add(colorWhite);
        panel5.add(colorGray);
        panel5.add(colorBrown);
        panel5.add(colorRed);
        panel5.add(colorMagenta);
        panel5.add(colorOrange);
        panel5.add(colorYellow);
        panel5.add(colorGreen);
        panel5.add(colorBlue);
       
        this.add(panel5);
        panel3 = new MyJPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(null);
        panel3.setBorder(BorderFactory.createTitledBorder(CommandNames.VISTAPREVIA));
        this.visor = new KPanel();
        panel3.add( visor, BorderLayout.CENTER );
        this.add(panel3);
        //------------------------------------------------
         


         
    }
     
     private JButton JButton(String text,boolean estado, String actionCommand)
     {
        JButton button = new JButton(text);
        button.setSize(22, 22);
        button.setMinimumSize(new Dimension(22, 44));
        button.setSelected(estado);
        button.setActionCommand(actionCommand);
        button.setBackground(null);
        button.setToolTipText(actionCommand);
        button.setBorderPainted(false);
        button.setBounds(0, 0, 22, 22);
        button.addActionListener(l);
        return button; 
     }

        public KPanel getVisor()
        {
            return visor;
        }
     
      private JButton JButton(ImageIcon imageIcon, boolean estado, String actionCommand) {
        JButton button = new JButton(imageIcon);
        button.setSize(22, 22);
        button.setMinimumSize(new Dimension(22, 44));
        button.setSelected(estado);
        button.setActionCommand(actionCommand);
        button.setBackground(null);
        button.setToolTipText(actionCommand);
        button.setBorderPainted(false);
        button.setBounds(0, 0, 22, 22);
        button.addActionListener(l);
        return button;
    }
    public DefaultListModel getListMode()
    {
        return listModel;
    }
    public JList getlist()
    {
        return this.list;
    }
    public void delLabel(int listSelectedItem)
    {  if(listSelectedItem!=-1)
            {   
                listModel.remove ( listSelectedItem);
                super.validate ();
                super.updateUI ();
                super.repaint ();
            }
    }
    
    public  void addLabel(String name)
    {
     this.listModel.addElement ( name );
     this.updateUI ();
     this.validate ();
     this.repaint ();
      
    }
    protected String getExtension(String name)
    {
        String[] str = name.split("\\.");
        if (str.length > 1)
        {
            return "."+str[str.length -1];
        }

        return ""; //-- no extension
    }
   public boolean isPicture(int index)
   {    
       
       if(CommandNames.isPicture(getExtension(listModel.elementAt(index).toString())))
       {    
           return true;
       }
       
       return false;
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

    void setImagen(BufferedImage all)
    {
        this.visor.setImagen(all);
        repaint();
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
      
}
