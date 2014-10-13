/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import proyectoprogra.CommandNames;
import proyectoprogra.MainWindow;

/**
 *
 * @author Sergio Flores Labra
 */
public class FiltrosImage extends MyJPanel implements ChangeListener
{
    private int distanciaR;
    private int estadoR;
    private int distanciaG;
    private int distanciaB;
    private JComboBox sort ;
    private ActionListener l;
    private JComboBox grosor;
    
    public FiltrosImage(MainWindow aThis)
    {
        super();
        this.l = aThis;
        this.setMinimumSize(new Dimension(150, 100));
        this.setPreferredSize (new Dimension(150, 100) );
        this.distanciaR = 3;
        this.distanciaG = 3;
        this.distanciaB = 3;
        this.setLayout(new GridLayout(4, 1));
        
        MyJPanel panel = new MyJPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.FILTROS));
        this.sort = new JComboBox(createFiltros());
        sort.setToolTipText(CommandNames.FILTROS);
        panel.add(sort);
        //--------------------------------------------
        
        MyJPanel panelR = new MyJPanel(new BorderLayout());
        JSlider r = new JSlider(-100, 100);
        r.addMouseListener ( aThis );
        r.setValue(3);
        r.setName("R");        
        r.addChangeListener(this);
        r.addChangeListener(aThis);
        r.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelR.add(new JLabel("R"), BorderLayout.WEST );
        panelR.add(r, BorderLayout.CENTER);
        
        panel.add(panelR);
        
        MyJPanel panelG =  new MyJPanel(new BorderLayout());
        JSlider g = new JSlider(-100, 100);
        g.addMouseListener ( aThis );
        g.setValue(3);
        g.setName("G");
        g.addChangeListener(this);
        g.addChangeListener(aThis);
        g.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelG.add(new JLabel("G"), BorderLayout.WEST);
        panelG.add(g, BorderLayout.CENTER);
        panel.add(panelG);
        
        MyJPanel panelB = new MyJPanel(new BorderLayout());
        JSlider b = new JSlider(-100, 100);
        b.addMouseListener ( aThis );
        b.setValue(3);
        b.setName("B");
        b.addChangeListener(this);
        b.addChangeListener(aThis);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelB.add(new JLabel("B"), BorderLayout.WEST);
        panelB.add(b, BorderLayout.CENTER);
        panel.add(panelB);
        
        //--------------------------------------------
        
        MyJPanel panel3 = new MyJPanel();
        panel3.setLayout(new GridLayout(5, 1));
        panel3.setBackground(null);
        Color color = this.getBackground();
        panel3.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.EDICION));
        
        Button recortar = new MyButton(CommandNames.RECORTAR, color);
        recortar.addActionListener(l);
        panel3.add(recortar);
        
        Button cambiaTamano = new MyButton (CommandNames.CAMBIARTAMANO, color);
        cambiaTamano.addActionListener ( l );
        panel3.add(cambiaTamano);
        
        Button girar= new MyButton(CommandNames.GIRAR, color);
        girar.addActionListener(l);
        panel3.add(girar);
        
        Button selectAll = new MyButton(CommandNames.SELECCIONAR_TODO, color);
        selectAll.addActionListener(l);
        panel3.add(selectAll);
        
        //--------------------------------------------
        MyJPanel panel4 = new MyJPanel();
        panel4.setAutoscrolls(true);
        panel4.setLayout(new GridLayout(4, 2));
        panel4.setBackground(null);
        
        panel4.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.FIGURAS));
        JButton mouse = JButton(new ImageIcon(CommandNames.ICON_MOUSE), false, CommandNames.COMMAND_MOUSE_BUTTON);
        JButton move = JButton(new ImageIcon(CommandNames.ICON_MOVE_IMAGE), false, CommandNames.COMMAND_MOVE_IMAGE);
        JButton pen = JButton(new ImageIcon(CommandNames.ICON_PEN), false, CommandNames.COMMAND_PEN_BUTTON);
        JButton brush = JButton( new ImageIcon(CommandNames.ICON_BRUSH), false,  CommandNames.COMMAND_BRUSH_BUTTON);
        JButton eraser = JButton( new ImageIcon(CommandNames.ICON_ERASER), false,  CommandNames.COMMAND_ERASER_BUTTON);
        JButton text = JButton( new ImageIcon(CommandNames.ICON_TEXT), false,  CommandNames.COMMAND_TEXT_BUTTON);
        JButton picker = JButton( new ImageIcon(CommandNames.ICON_PICKER), false,  CommandNames.COMMAND_PICKER_BUTTON);
        JButton bote = JButton( new ImageIcon(CommandNames.ICON_FLOODFILL), false,  CommandNames.COMMAND_FLOODFILL_BUTTON);
        JButton spray = JButton( new ImageIcon(CommandNames.ICON_SPRAY), false,  CommandNames.COMMAND_SPRAY_BUTTON);
        JButton rectangle = JButton( new ImageIcon(CommandNames.ICON_RECTANGLE), false,  CommandNames.COMMAND_RECTANGLE_BUTTON);
        JButton star = JButton( new ImageIcon(CommandNames.ICON_STAR), false,  CommandNames.COMMAND_STAR_BUTTON);
        JButton rombo = JButton( new ImageIcon(CommandNames.ICON_ROMBO), false,  CommandNames.COMMAND_ROMBO_BUTTON);
        JButton pentagono = JButton( new ImageIcon(CommandNames.ICON_PENTAGONO), false,  CommandNames.COMMAND_PENTAGONO_BUTTON);
        JButton circle = JButton( new ImageIcon(CommandNames.ICON_CIRCLE), false,  CommandNames.COMMAND_CIRCLE_BUTTON);
        JButton line = JButton( new ImageIcon(CommandNames.ICON_LINE), false,  CommandNames.COMMAND_LINE_BUTTON);
        JButton marco = JButton( new ImageIcon(CommandNames.ICON_Marco), false,  CommandNames.COMMAND_MARCO_BUTTON);
        JButton seleccion = JButton( new ImageIcon(CommandNames.ICON_Seleccion), false,  CommandNames.COMMAND_SELECCION_BUTTON);
                
        panel4.add(mouse);
        panel4.add(seleccion);
        panel4.add(move);
        panel4.add(pen);
        panel4.add(brush);
        panel4.add(eraser);
        panel4.add(text);
        panel4.add(picker);
        panel4.add(bote);
        panel4.add(spray);
        panel4.add(marco);
        panel4.add(rectangle);
        panel4.add(star);
        panel4.add(rombo);
        panel4.add(pentagono);
        panel4.add(circle);
        panel4.add(line);
        
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
        JButton colorTransparente = JButton( new ImageIcon(CommandNames.ICON_Transparente), false,  CommandNames.COLOR_TRANSPARENTE );
        JButton colores = JButton( new ImageIcon(CommandNames.ICON_Colores), false,  CommandNames.COLOR_MAS );
        
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
        //panel5.add(colorTransparente);
        panel5.add(colores);
      
        
        this.add(panel);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }
        
    private Object[] createFiltros() 
    {
        /*String[] lista = {CommandNames.RGBaRBG,CommandNames.RGBaGBR,CommandNames.CUARTEADO,CommandNames.NEGATIVO, CommandNames.BLANCOYNEGRO, CommandNames.SEPIA
                        ,CommandNames.PRIORIZAROJO,CommandNames.PRIORIZAAZUL,CommandNames.PRIORIZAVERDE,CommandNames.BORDEFRONTERA,CommandNames.ESPEJOX
                        ,CommandNames.ESPEJOY,CommandNames.BRILLO,CommandNames.ESCALAGRISES, CommandNames.POLARIZADO};*/
        ArrayList<String> filtros = new ArrayList<> () ;
        try
        {
            File file ;
            if (System.getProperty("os.name").contains(CommandNames.IOSWIN))
            {
                file= new File (new File ("").getAbsolutePath () + "\\src\\Filtros\\");
            }
            else
            {
                file= new File (new File ("").getAbsolutePath () + "/src/Filtros/");
            }
            for(String string : file.list ())
            {
                string = string.substring(0, string.lastIndexOf('.'));
                if(!string.equals ( "MarcoDeImagen") && !string.equals ( "FloodFill") && !string.equals ( "Filtro"))
                {
                    filtros.add ( string );
                }
            }
            
        }
        catch (Exception ex)
        {
        }
        
        return filtros.toArray ();
    }
   
    public JComboBox getSort()
    {
        return sort;
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
        button.setFocusPainted ( false);
        button.setBounds(0, 0, 22, 22);
        button.addActionListener(l);
        return button;
    }

    public int getDistanciaR() {
        return this.distanciaR;
    }

    public void setDistanciaR(int distanciaR) {
        this.distanciaR = distanciaR;
    }

    public int getEstadoR() {
        return estadoR;
    }

    public void setEstadoR(int estadoR) {
        this.estadoR = estadoR;
    }

    public int getDistanciaB() {
        return distanciaB;
    }

    public void setDistanciaB(int distanciaB) {
        this.distanciaB = distanciaB;
    }

    public int getDistanciaG()
    {
        return this.distanciaG;
    }
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        JSlider source =(JSlider) e.getSource();
        String comando = source.getName();

        if(comando.equals(CommandNames.FILTRO_R))
        {
            if(source.getValueIsAdjusting())
            {
                
                this.distanciaR = source.getValue();
            }
        }
        if(comando.equals(CommandNames.FILTRO_G))
        {
            if(source.getValueIsAdjusting())
            {
                
                this.distanciaG = source.getValue();
            }
        }
        if(comando.equals(CommandNames.FILTRO_B))
        {
            if(source.getValueIsAdjusting())
            {
                
                this.distanciaB = source.getValue();
            }
        }
      }
}

