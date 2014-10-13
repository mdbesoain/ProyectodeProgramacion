/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Gloria
 */
public class JInterfaz extends JPanel {
    
   JFrame a ;
    JInterfaz( JFrame a)
    {
        this.setToolTipText ( CommandNames.TAB2_TOOLTIP);
        this.setName ( CommandNames.TAB2 );
        super.setLayout ( null );
        this.a =a;
        createEstructure();
        this.setVisible ( true );
        
    }

    private void createEstructure() 
    {
        this.setBorder(BorderFactory.createTitledBorder(
                                    CommandNames.AGREGAEXTRAS));
        JButton agrega = JButton( true, CommandNames.AGREGARFiltro);
        Insets insets = super.getInsets();
        Dimension size = agrega.getPreferredSize();
        agrega.setBounds(insets.left, 5 + insets.top,
             size.width + 20, size.height+20);
        agrega.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFileChooser file = new JFileChooser ();
                file.setFileFilter ( new FileNameExtensionFilter ( "Jar" , "jar" ));
                file.setAcceptAllFileFilterUsed ( false );
                if (file.showOpenDialog ( file )== JFileChooser.APPROVE_OPTION)
                {
                    File f = file.getSelectedFile ();
                    int choice = 0;
                    choice = JOptionPane.showOptionDialog ( a ,  CommandNames.CAPTION_ADDFiltrer , CommandNames.CAPTION_TITLE , choice , choice , null , CommandNames.SALIR_QUESTION_OPTIONS , this );
                    if ( choice == JOptionPane.YES_OPTION )
                    {
                        File newFile;
                        if(System.getProperty("os.name").contains(CommandNames.IOSLINUX))
                        {
                            newFile = new File( System.getProperty ( "user.dir") + "/src/Filtros/", f.getName() );
                        }
                        else
                        {
                            newFile= new File (System.getProperty ( "user.dir") + "/src/Filtros/", f.getName() );
                        }
                        
                        System.out.println("File ruta: " + newFile.getAbsolutePath());
                        try
                        {
                            newFile.createNewFile();
                            
                        } catch (IOException ex)
                        {
                            Logger.getLogger(JInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        a.dispose ();
                        System.exit ( 0 );
                    }
                    a.repaint ();
                    
                }
            }
        });
        
        JButton marco = JButton( true, CommandNames.AGREGARMarco);
        marco.setBounds(insets.left + size.width + 30, 5 + insets.top,
             size.width + 20, size.height+20);
        marco.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed ( ActionEvent e )
            {
                JFileChooser file = new JFileChooser ();
                file.setFileFilter ( new FileNameExtensionFilter ( "Archivos de Imagen" , "png" , "jpg" , "jpeg" , "bmp" ));
                file.setAcceptAllFileFilterUsed ( false );
                if (file.showOpenDialog ( file )== JFileChooser.APPROVE_OPTION)
                {
                    File f = file.getSelectedFile ();
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read ( f );
                    }
                    catch ( IOException ex ) {
                        //Logger.getLogger ( JInterfaz.class.getName() ).log ( Level.SEVERE , null , ex );
                    }
                    String name = f.getName ();
                    String formatName = name.substring ( name.lastIndexOf ( '.') + 1  );

                    File ruta;
                    if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                    {
                        ruta= new File (new File ("").getAbsolutePath () + "\\Imagenes\\Masks\\" + name );
                    }
                    else
                    {
                        ruta= new File (new File ("").getAbsolutePath () + "/Imagenes/Masks/" + name );
                    }
                    try            
                    {
                        ImageIO.write( image , formatName, ruta);
                        
                    }
                    catch ( IOException ex )
                    {
                        //Logger.getLogger ( JInterfaz.class.getName() ).log ( Level.SEVERE , null , ex );
                    }
                    
                }
            }
            
        });
        JButton patron = JButton( true, CommandNames.AGREGARPatron);
        patron.setBounds(insets.left, size.height+25 + insets.top,
             size.width + 20, size.height+20);
        patron.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed ( ActionEvent e )
            {
                JFileChooser file = new JFileChooser ();
                file.setFileFilter ( new FileNameExtensionFilter ( "Archivos de Imagen" , "png" , "jpg" , "jpeg" , "bmp" ));
                file.setAcceptAllFileFilterUsed ( false );
                if (file.showOpenDialog ( file )== JFileChooser.APPROVE_OPTION)
                {
                    File f = file.getSelectedFile ();
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read ( f );
                    }
                    catch ( IOException ex ) {
                        //Logger.getLogger ( JInterfaz.class.getName() ).log ( Level.SEVERE , null , ex );
                    }
                    String name = f.getName ();
                    String formatName = name.substring ( name.lastIndexOf ( '.') + 1  );
                    File ruta;
                    if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                    {
                        ruta= new File (new File ("").getAbsolutePath () + "\\Imagenes\\Patrones\\" + name );
                    }
                    else
                    {
                        ruta= new File (new File ("").getAbsolutePath () + "/Imagenes/Patrones/" + name );
                    }
                    try            
                    {
                        ImageIO.write( image , formatName, ruta);
                        
                    }
                    catch ( IOException ex )
                    {
                        //Logger.getLogger ( JInterfaz.class.getName() ).log ( Level.SEVERE , null , ex );
                    }
                    
                }
            }
            
        });
        
        
        super.add(agrega);
        super.add(marco);
        super.add(patron);
        
    }
    
    private JButton JButton( boolean estado, String actionCommand) {
        JButton button = new JButton(actionCommand);
        button.setSize(22, 22);
        button.setMinimumSize(new Dimension(22, 44));
        button.setActionCommand(actionCommand);
        button.setBackground(null);
        button.setToolTipText(actionCommand);
        button.setBorderPainted(true);
        button.setBounds(0, 0, 22, 22);
        //button.addActionListener(l);
        return button;
    }
    
}
