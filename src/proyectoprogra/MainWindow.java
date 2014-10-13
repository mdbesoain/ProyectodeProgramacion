
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import Filtro.Filtro;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComboBox;
import java.awt.Checkbox;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.CannotRedoException;
import myObjects.*;

/**
 *
 * @
 */
public class MainWindow extends JFrame implements ActionListener, MouseListener, ChangeListener, ItemListener
{

    private MyMenuBar menuBar;
    private MyJPanel statusBar;
    private MyToolBar barraHerramientas;
    private JPicturePanel panel;
    public PageFormat pf;
    public PrinterJob job;
    private MyTree tree;
    private VisorCarpetas visor;
    private Boolean activate;
    private Boolean hayTexto;
    private JLabel status;
    private JLabel path;
    private FiltrosOrdenamiento filtrosOrd;
    private Component ob;
    private MyJPanel objectOptions;
    private int estado;
    private int clicks;
    private JScrollPane scroll;
    private FiltrosImage fl;
    private MyPopupMenu popupMenu;
    private MyExplorerDirection explorerDirection;
    private CollagePanel collagePanel;
    private MyCollageToolbar collageToolbar;
    private boolean enEditor;
    private boolean enCollage;
    private RotarOptions rotate;
    private int anguloARotarFinal; //angulo a rotar
    private int anguloARotarInicial;//angulo a rotar
    private File fileImage;

    MainWindow()
    {
        this.fileImage = new File("");

        this.path = new JLabel("Directorio");
        this.status = new JLabel("Posicion");
        this.popupMenu = new MyPopupMenu(this);
        estado = 50;

        this.setIconImage(new ImageIcon(CommandNames.ICON).getImage());
        this.setTitle(CommandNames.WINDOWS_TITLE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.enCollage = false;
        this.enEditor = false;
        this.rotate = new RotarOptions(this);
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent winEvt)
            {
                windowsClosing();
            }
        });

        //BARRAS
        menuBar = new MyMenuBar();
        this.barraHerramientas = new MyToolBar(this);
        this.barraHerramientas.addMouseListener(this);
        this.barraHerramientas.setRequestFocusEnabled(true);
        super.add(BorderLayout.PAGE_START, barraHerramientas);

        this.menuBar = new MyMenuBar(this);
        this.menuBar.addMouseListener(this);
        super.setJMenuBar(menuBar);

        this.panel = new JPicturePanel(this.barraHerramientas, menuBar);
        explorerDirection = new MyExplorerDirection();
        //centramos la ventana
        Dimension dim = this.getToolkit().getScreenSize();
        Rectangle abounds = this.getBounds();
        this.setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
        //
        this.tree = new MyTree(this);
        this.activate = true;
        this.hayTexto = false;
        ventanaUno();
        //ventanaDos();
        //this.add (panel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.clicks = 1;

        this.anguloARotarFinal = 0;
        this.anguloARotarInicial = 0;

    }

    /*private void imagePre ()
     {
     ImageIcon fot;
     fot = new ImageIcon ( CommandNames.EMPTYIMAGE );
     Image scaled = fot.getImage ().getScaledInstance ( panel.getWidth () , panel.getHeight () , Image.SCALE_SMOOTH );
     panel.setImage ( scaled );
     }*/
    private Image imagePre(Image image)
    {

        Image scaled = image.getScaledInstance(150, 100, Image.SCALE_REPLICATE);
        return scaled;
    }

    public void pageSetup()
    {
        job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        pf = job.pageDialog(aset);

    }

    public void windowsClosing()
    {
        if (enCollage && !collagePanel.canvasEmpty())
        {
            int choice = preventLeaving();
            if (choice == JOptionPane.YES_OPTION)
            {
                saveFiles();
                super.dispose();
            }
            if (choice == JOptionPane.NO_OPTION)
            {

                super.dispose();
            }
        } else
        {

            new JMp3(CommandNames.sound_sure);
            int choice = 0;
            choice = JOptionPane.showOptionDialog(this, CommandNames.SALIR_QUESTION, CommandNames.CAPTION_TITLE, choice, choice, null, CommandNames.SALIR_QUESTION_OPTIONS, this);
            if (choice == JOptionPane.YES_OPTION)
            {
                super.dispose();
            }

        }

        this.repaint();
    }

    public void print()
    {
        boolean printed = false;
        if (enEditor && this.panel.getImage() != null || enCollage && !this.collagePanel.canvas.isEmpty())
        {
            job = PrinterJob.getPrinterJob();
            JPrinter print = new JPrinter();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new sun.print.DialogOwner(this));
            pf = job.pageDialog(aset);
            int choice = 0;
            if (pf == null)
            {
                do
                {
                    new JMp3(CommandNames.sound_error);
                    choice = JOptionPane.showOptionDialog(this, CommandNames.PAGE_NOT_SET, CommandNames.CAPTION_TITLE, JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION, null, CommandNames.PAGE_NOT_SET_OPTIONS, this);
                    if (choice != JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION || choice == JOptionPane.CANCEL_OPTION)
                    {
                        System.out.println(choice);
                        break;
                    }
                    if (pf == null)
                    {
                        pf = job.pageDialog(aset);
                    }
                } while (pf == null && choice != JOptionPane.YES_OPTION);
            }
            if (pf != null)
            {
                Image aux = null;
                if (enEditor)
                {
                    aux = this.panel.getImage().getScaledInstance((int) pf.getImageableWidth(), (int) pf.getImageableHeight(), Image.SCALE_SMOOTH);
                }
                if (enCollage)
                {
                    BufferedImage bi = collagePanel.canvas.getAll();
                    aux = bi.getScaledInstance((int) pf.getImageableWidth(), (int) pf.getImageableHeight(), Image.SCALE_SMOOTH);

                }

                print.setValues(aux, aux.getWidth(null), aux.getHeight(null));
                job.validatePage(pf);
                job.setPrintable(print, pf);
                boolean ok = job.printDialog(aset);

                if (ok)
                {
                    try
                    {

                        job.print(aset);
                        printed = true;
                    } catch (PrinterException ex)
                    {
                        new JMp3(CommandNames.sound_error);
                        JOptionPane.showMessageDialog(this, CommandNames.PRINT_NOT_DONE, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                        printed = false;
                    }
                }

            }
        } else
        {
            new JMp3(CommandNames.sound_error);
            JOptionPane.showMessageDialog(this, CommandNames.NADA_QUE_IMPRIMIR, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
        }

        if (printed)
        {
            new JMp3(CommandNames.sound_print);
            JOptionPane.showMessageDialog(this, CommandNames.PRINT_DONE, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void saveFiles()
    {
        if (enEditor)
        {
            if (this.panel.getImage() != null && new JFileOpener().saveFile(this.panel))
            {
                new JMp3(CommandNames.sound_right);
                JOptionPane.showMessageDialog(this, CommandNames.CAPTION_GUARDADO, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (enCollage)
        {
            if (new JFileOpener().saveFileCollage(this.collagePanel))
            {
                new JMp3(CommandNames.sound_right);
                JOptionPane.showMessageDialog(this, CommandNames.CAPTION_GUARDADO, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void dosClickImagen(String path)
    {
        if (!path.contains(".gif"))
        {
            fileImage = new File(path);
            Image image = new ImageIcon(path).getImage();
            ventanaDos();

            //--------------Caracteristicas de la imagen------
            /*
             * Hay que solucionar lo de la extencion!!
             */
            this.panel.setImage(image);
            this.filtrosOrd.setCaracteristicas(path, "png");
            this.filtrosOrd.setIma(imagePre(image));
            enEditor = true;
            enCollage = false;
            barraHerramientas.switchBotones(2);
            //------------------------------------------------

        }
        barraHerramientas.switchBotones(2);
    }

    public void enableDirectionBotons()
    {
        barraHerramientas.adelante.setEnabled(explorerDirection.hasForward());
        barraHerramientas.atras.setEnabled(explorerDirection.hasBack());
        barraHerramientas.up.setEnabled(visor.hasUpperLevel(this.path.getText()));
    }

    public void dosClicksCarpeta(String path)
    {
        this.path.setText(path);
        this.visor.updateImagenesDos(new File(path));
        enableDirectionBotons();

    }

    public int preventLeaving()
    {
        new JMp3(CommandNames.sound_sure);
        int choice;

        choice = JOptionPane.showConfirmDialog(this, CommandNames.GUARDAR_COLLAGE_QUESTION, CommandNames.MENSAJE, JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {

            return JOptionPane.YES_OPTION;
        }
        if (choice == JOptionPane.NO_OPTION)
        {
            return JOptionPane.NO_OPTION;
        }
        return JOptionPane.CANCEL_OPTION;

    }

    public void openExternal(String path, boolean isPicture)
    {
        String[] commands =
        {
            "cmd.exe", "/c", "start", "\"DummyTitle\"", "\"" + path + "\""
        };
        Process p = null;
        try
        {
            p = Runtime.getRuntime().exec(commands);
        } catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            p.waitFor();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void goAhead()
    {
        if (explorerDirection.hasForward())
        {
            String forward = explorerDirection.getForward();
            this.path.setText(forward);
            this.visor.updateImagenesDos(new File(forward));
            enableDirectionBotons();
        } else
        {
            enableDirectionBotons();
        }
    }

    public void goBack()
    {
        if (explorerDirection.hasBack())
        {
            String back = explorerDirection.getBack();
            this.path.setText(back);
            this.visor.updateImagenesDos(new File(back));
            enableDirectionBotons();
        } else
        {
            enableDirectionBotons();
        }
    }

    public void goUp()
    {
        String upper = visor.getUpperLevel(this.path.getText());
        if (upper != null)
        {
            explorerDirection.goBack(upper);
            this.path.setText(upper);
            this.visor.updateImagenesDos(new File(upper));

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        popupMenu.setVisible(false);

        if (ae.getSource() instanceof MyIconExplorer)
        {

            MyIconExplorer icon = (MyIconExplorer) ae.getSource();
            if (icon.isPicture())
            {
                //metodoVentanaDos(icon.getPath());
                Image image = new ImageIcon(icon.getPath()).getImage();
                if (clicks == 1)
                {
                    this.filtrosOrd.setImage(imagePre(image));
                    this.filtrosOrd.setCaracteristicas(icon.getPath(), icon.extension);
                    this.repaint();
                }
                if (clicks == 2)
                {
                    dosClickImagen(icon.getPath());
                }
            } else
            {
                if (clicks == 1)
                {
                    this.filtrosOrd.setImage(new ImageIcon(CommandNames.CARPETAVISTA).getImage());
                    this.filtrosOrd.setCaracteristicas(icon.getPath(), icon.extension);
                    this.repaint();
                }
                if (clicks == 2)
                {
                    dosClicksCarpeta(icon.getPath());
                }

            }

        }

        String comando = ae.getActionCommand();
        switch (comando)
        {
            case CommandNames.ABRIR:
                try
                {
                    JFileOpener file = new JFileOpener();
                    Image image = file.getImage();
                    if (image != null && !file.getSelectedFile().getPath().contains(".gif"))
                    {

                        fileImage = file.getSelectedFile();
                        if (!enCollage || enEditor)
                        {
                            ventanaDos();
                            this.panel.setImage(image);
                            this.filtrosOrd.setCaracteristicas(file.getSelectedFile().getPath(), "png");
                            this.filtrosOrd.setImage(imagePre(image));
                            this.enCollage = false;
                            this.enEditor = true;
                        }
                        if (enCollage)
                        {
                            clickDerechoenCollage(fileImage.getPath());
                        }
                        validate();
                        this.repaint();
                    }
                    if (file.getSelectedFile().getPath().contains(".gif"))
                    {
                        new JMp3(CommandNames.sound_error);
                        JOptionPane.showMessageDialog(this, CommandNames.CAPTION_ERROR_OPEN, CommandNames.CAPTION_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex)
                {
                }
                barraHerramientas.switchBotones(2);
                this.repaint();
                break;
            case CommandNames.ACERCA_DE:
                new JAbout().setVisible(true);
                this.repaint();
                break;
            case CommandNames.COPIAR:
                if (enCollage)
                {
                    this.collagePanel.copiarAlPortapapeles();
                }
                if (enEditor && this.panel.getImage() != null)
                {
                    this.panel.copiarAlPortapapeles();
                }
                break;
            case CommandNames.PEGAR:
                if (enCollage)
                {
                    this.collagePanel.obtenerDatosPortapapeles();
                }
                if (enEditor && this.panel.getImage() != null)
                {
                    this.panel.obtenerDatosPortapapeles();
                }
                break;
            case CommandNames.DESHACER:
                if (this.panel.undoManager.canUndo())
                {
                    try
                    {
                        this.panel.undoManager.undo();
                    } catch (CannotRedoException cre)
                    {
                        //cre.printStackTrace();
                    }
                }
                this.panel.canvas.repaint();
                this.barraHerramientas.rehacer.setEnabled(this.panel.undoManager.canRedo());
                this.barraHerramientas.deshacer.setEnabled(this.panel.undoManager.canUndo());

                this.menuBar.rehacer.setEnabled(this.panel.undoManager.canRedo());
                this.menuBar.deshacer.setEnabled(this.panel.undoManager.canUndo());
                this.menuBar.validate();
                this.validate();
                panel.setElements();
                this.panel.updateUI();
                this.panel.validate();
                this.panel.repaint();
                this.panel.canvas.repaint();
                this.repaint();

                break;
            case CommandNames.REHACER:
                if (this.panel.undoManager.canRedo())
                {
                    try
                    {
                        this.panel.undoManager.redo();
                    } catch (CannotRedoException cre)
                    {
                        //cre.printStackTrace();
                    }
                }
                this.panel.canvas.repaint();
                this.barraHerramientas.rehacer.setEnabled(this.panel.undoManager.canRedo());
                this.barraHerramientas.deshacer.setEnabled(this.panel.undoManager.canUndo());

                this.menuBar.rehacer.setEnabled(this.panel.undoManager.canRedo());
                this.menuBar.deshacer.setEnabled(this.panel.undoManager.canUndo());
                this.menuBar.validate();
                this.validate();
                panel.setElements();
                this.panel.updateUI();
                this.panel.validate();
                this.panel.repaint();
                this.panel.canvas.repaint();
                this.repaint();

                break;
            case CommandNames.AYUDA:

                //new JHelp ().setVisible ( true );
                File paginaHTML;
                String urlDocumento;
                if (System.getProperty("os.name").contains(CommandNames.IOSWIN))
                {
                    paginaHTML = new File(System.getProperty("user.dir") + "\\TutorialFotoChop.htm");
                    urlDocumento = "file://localhost/" + paginaHTML.getAbsolutePath();

                } else
                {
                    paginaHTML = new File(System.getProperty("user.dir") + "/TutorialFotoChop.htm");
                    urlDocumento = "file://localhost/" + paginaHTML.getAbsolutePath();

                }
                //System.out.println("" + urlDocumento);
                Navegador minavegador = new Navegador(null, true, urlDocumento);
                minavegador.setSize(1024, 800);
                minavegador.setTitle(CommandNames.TUTORIAL_TITLE);
                minavegador.setLocationRelativeTo(null);
                minavegador.setVisible(true);
                minavegador.setSize(1024, 800);
                minavegador.setTitle(" Tutorial FotoChop");
                minavegador.setLocationRelativeTo(null);
                minavegador.setVisible(true);

                this.repaint();

                break;
            case CommandNames.GUARDAR:
                if (enEditor && this.panel.getImage() != null)
                {

                    String formato = fileImage.getName();
                    formato = formato.substring(formato.lastIndexOf('.') + 1, formato.length());
                    BufferedImage bi = new BufferedImage(panel.getImage().getWidth(null), panel.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D cG = bi.createGraphics();
                    panel.paint(cG);
                    cG.dispose();
                    try
                    {

                        ImageIO.write(bi, formato, fileImage);
                        JOptionPane.showMessageDialog(this, CommandNames.CAPTION_GUARDADO, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(this, CommandNames.CAPTION_ERROR, CommandNames.CAPTION_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (enCollage)
                {
                    saveFiles();
                }
                this.repaint();
                break;
            case CommandNames.GUARDAR_COMO:
                saveFiles();
                this.repaint();
                break;
            case CommandNames.PAGE_SETUP:
                pageSetup();
                this.repaint();
                break;
            case CommandNames.IMPRIMIR:
                print();
                this.repaint();
                break;
            case CommandNames.NUEVO:
                new MainWindow().setVisible(true);
                new JMp3(CommandNames.sound_right);
                JOptionPane.showMessageDialog(this, CommandNames.NUEVO_MENSAJE, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                this.repaint();
                break;
            case CommandNames.PREFERENCIAS:
                new JPreferencias().setVisible(true);
                this.repaint();
                break;
            case CommandNames.VISTAPREVIA:
                if (enEditor)
                {
                    this.filtrosOrd.mostrarPrevia();
                } else if (enCollage)
                {
                    this.collageToolbar.mostrarPrevia();
                } else
                {
                    this.filtrosOrd.mostrarPrevia();
                }
                this.repaint();
                break;
            case CommandNames.SALIR:
                windowsClosing();
                break;
            case CommandNames.MENU_1:
                dosClickImagen(this.popupMenu.getPath());
                
                this.popupMenu.mostrar(false, 0, 0, false);
                break;
            case CommandNames.MENU_2:
                clickDerechoenCollage(this.popupMenu.getPath());
                this.popupMenu.mostrar(false, 0, 0, false);
                break;

            case CommandNames.MENU_3:
                openExternal(this.popupMenu.getPath(), true);
                this.popupMenu.mostrar(false, 0, 0, false);
                break;
            case CommandNames.MENU_4:
                new MyPicturePropiedades(this.popupMenu.getPath()).Visible(true);
                this.popupMenu.mostrar(false, 0, 0, false);
                break;
            case CommandNames.MENU_5:
                dosClicksCarpeta(this.popupMenu.getPath());
                barraHerramientas.switchBotones(2);
                break;
            case CommandNames.MENU_6:
                openExternal(this.popupMenu.getPath(), false);
                break;

            case CommandNames.BACK_TOOLTIP:
                goBack();
                break;
            case CommandNames.FORWARD_TOOLTIP:
                goAhead();
                break;
            case CommandNames.UP_TOOLTIP:
                goUp();
                break;
            case CommandNames.GIRAR:
                if (!this.panel.recortar && this.panel.isWriting() && this.panel.getImage() != null)
                {
                    this.panel.girar();
                }
                this.repaint();
                break;
            case CommandNames.CAMBIARTAMANO:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting() && this.panel.getImage() != null)
                    {
                        if (!this.panel.cambiarTexto)
                        {
                            this.panel.cambiarTexto = true;
                            new CambiaTamano(this.panel);
                        }
                    }
                }    
                if(enCollage)
                {   if(collagePanel.canvas.imax!=null)
                {   collagePanel.isMoving = false;
                    collagePanel.isDeliting = false;
                    new CambiaTamano(this.collagePanel);
                }
                

                }

                this.repaint();
                break;
            case CommandNames.SELECCIONAR_TODO:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting() && this.panel.getImage() != null)
                    {
                        ArrayList<Integer> pointsX = new ArrayList<>();
                        ArrayList<Integer> pointsY = new ArrayList<>();
                        Image ima = this.panel.getImage();
                        pointsX.add(0);
                        pointsX.add(ima.getWidth(null));
                        pointsX.add(ima.getWidth(null));
                        pointsX.add(0);
                        pointsX.add(0);
                        //--------------------------------
                        pointsY.add(0);
                        pointsY.add(0);
                        pointsY.add(ima.getHeight(null));
                        pointsY.add(ima.getHeight(null));
                        pointsY.add(0);
                        this.panel.setCopyArea(pointsX);
                        this.panel.setCopyAreaY(pointsY);
                    }
                }
                if (enCollage)
                {
                    ArrayList<Integer> pointsX = new ArrayList<>();
                    ArrayList<Integer> pointsY = new ArrayList<>();
                    Dimension pSize = this.collagePanel.canvas.getPreferredSize();
                    Rectangle bounds = this.collagePanel.canvas.getBounds();
                    pointsX.add(3);
                    pointsX.add(4 + pSize.width);
                    pointsX.add(4 + pSize.width);
                    pointsX.add(3);
                    pointsX.add(3);
                    //--------------------------------
                    pointsY.add(7);
                    pointsY.add(7);
                    pointsY.add(8 + pSize.height);
                    pointsY.add(8 + pSize.height);
                    pointsY.add(7);
                    this.collagePanel.setCopyArea(pointsX);
                    this.collagePanel.setCopyAreaY(pointsY);
                }
                this.repaint();
                break;
            case CommandNames.RECORTAR:
                if (this.panel.isWriting() && this.panel.getImage() != null)
                {
                    this.panel.recortarImagen();
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new RecortarOptions(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                this.repaint();
                break;
            case CommandNames.DONE_RECORTAR:

                //metodo aplicar recortado!!
                this.panel.setElements();
                this.panel.recortar();
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }
                this.repaint();
                break;
            case CommandNames.CANCEL_RECORTAR:

                this.panel.setElements();
                this.panel.recortar = false;
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }
                this.repaint();
                break;

            case CommandNames.COMMAND_MOUSE_BUTTON:

                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(0);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }

                }

                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_SELECCION_BUTTON:

                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(10);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }

                    }
                }
                if (enCollage)
                {
                    collagePanel.isDeliting = false;
                    collagePanel.isMoving = false;
                    this.collagePanel.setDibujar(10);
                }

                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_PEN_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setBrush(false);
                        this.panel.setDibujar(1);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                        this.objectOptions = new Options(this);
                        this.objectOptions.getGrosor().addActionListener(this);
                        this.add(objectOptions, BorderLayout.PAGE_END);

                    }
                }
                if (enCollage)
                {
                    collagePanel.isDeliting = false;
                    collagePanel.isMoving = false;
                    this.collagePanel.setBrush(false);
                    this.collagePanel.setDibujar(1);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new Options(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }

                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_BRUSH_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(1);
                        this.panel.setBrush(true);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                    }

                }
                if (enCollage)
                {
                    collagePanel.isDeliting = false;
                    collagePanel.isMoving = false;

                    this.collagePanel.setDibujar(1);
                    this.collagePanel.setBrush(true);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                }

                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_ERASER_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(4);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }

                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(4);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                }
                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_CIRCLE_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(2);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                        this.objectOptions = new OptionsFill(this);
                        this.objectOptions.getGrosor().addActionListener(this);
                        this.add(objectOptions, BorderLayout.PAGE_END);

                    }
                }
                if (enCollage)
                {
                    collagePanel.isDeliting = false;
                    collagePanel.isMoving = false;
                    this.collagePanel.setDibujar(2);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);

                }
                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_RECTANGLE_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(3);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }

                        this.objectOptions = new OptionsFill(this);
                        this.objectOptions.getGrosor().addActionListener(this);
                        this.add(objectOptions, BorderLayout.PAGE_END);

                    }

                }
                if (enCollage)
                {
                    collagePanel.isDeliting = false;
                    collagePanel.isMoving = false;
                    this.collagePanel.setDibujar(3);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }

                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }

                this.validate();
                this.repaint();
                break;
            case CommandNames.COMMAND_SPRAY_BUTTON:
                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(14);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new SprayOptions(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_STAR_BUTTON:

                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(11);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_ROMBO_BUTTON:

                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(12);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(12);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.COMMAND_PENTAGONO_BUTTON:

                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(13);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new OptionsFill(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }

                this.validate();
                this.repaint();
                break;

            case CommandNames.COMMAND_TEXT_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar)
                    {
                        this.panel.setDibujar(5);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }

                        this.objectOptions = new TextOptions(this);
                        this.add(objectOptions, BorderLayout.PAGE_END);
                        if (!this.hayTexto)
                        {
                            this.hayTexto = true;
                            new CreaTexto(panel).setVisible(true);
                        }
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(5);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }

                    //this.objectOptions = new TextOptions ( this );
                    //this.add ( objectOptions , BorderLayout.PAGE_END );
                    if (!this.hayTexto)
                    {
                        this.hayTexto = true;
                        new CreaTexto(collagePanel).setVisible(true);
                    }

                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.DONE_TEXT:

                if (enEditor)
                {
                    if (this.panel.getText() != null)
                    {
                        if (this.panel.getText() != null)
                        {
                            this.panel.actualizarImagen(this.panel.addFiguras());
                        } else
                        {
                            JOptionPane.showMessageDialog(this, CommandNames.CAPTION_DONE_TEXT, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                        }
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                        this.panel.setDibujar(0);
                        this.panel.setText();
                        this.hayTexto = false;
                    }
                    if (enCollage)
                    {
                        if (this.collagePanel.getText() != null)
                        {
                            this.collagePanel.actualizarImagen(this.panel.addFiguras());
                        } else
                        {
                            JOptionPane.showMessageDialog(this, CommandNames.CAPTION_DONE_TEXT, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                        }
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                        this.collagePanel.setDibujar(0);
                        this.collagePanel.setText();
                        this.hayTexto = false;
                    }
                    this.validate();
                    this.repaint();
                    break;
                }
            case CommandNames.EDIT_TEXT:
                if (enEditor)
                {
                    if (this.panel.getText() != null)
                    {
                        new CreaTexto(panel, this.panel.getText().getLabel()).setVisible(true);
                    } else
                    {
                        JOptionPane.showMessageDialog(this, CommandNames.CAPTION_EDIT_TEXT, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                if (enCollage)
                {
                    if (this.collagePanel.getText() != null)
                    {
                        new CreaTexto(collagePanel, this.collagePanel.getText().getLabel()).setVisible(true);
                    } else
                    {
                        JOptionPane.showMessageDialog(this, CommandNames.CAPTION_EDIT_TEXT, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.CANCEL_TEXT:
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }
                if (enEditor)
                {
                    this.panel.setDibujar(0);
                    this.panel.setText();
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(0);
                    this.collagePanel.setText();
                }
                this.hayTexto = false;
                this.validate();
                this.repaint();
                break;
            case CommandNames.COMMAND_ICON_BRING_TO_FRONT:
                    if(enCollage)
                    {
                        if(collagePanel.canvas.imax!=null)
                        {
                            collagePanel.enviarAlFrente();
                        }
                    }
                    this.validate();
                    this.repaint();
                    break;
            case CommandNames.COMMAND_PICKER_BUTTON:
                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(6);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(6);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }

                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.COMMAND_LINE_BUTTON:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(7);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }
                        this.objectOptions = new LineOptions(this);
                        this.objectOptions.getGrosor().addActionListener(this);
                        this.objectOptions.getTipoLinea().addActionListener(this);
                        this.objectOptions.getTipoConector().addActionListener(this);
                        this.add(objectOptions, BorderLayout.PAGE_END);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setDibujar(7);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }
                    this.objectOptions = new LineOptions(this);
                    this.objectOptions.getGrosor().addActionListener(this);
                    this.objectOptions.getTipoLinea().addActionListener(this);
                    this.objectOptions.getTipoConector().addActionListener(this);
                    this.add(objectOptions, BorderLayout.PAGE_END);
                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.LINE_SQUARE:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.LINE_RHOMBUS:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.LINE_CIRCLE:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.LINE_ARROW:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.LINE_ARROW_FILL:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.LINE_NORMAL:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setOptionsLine(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setOptionsLine(comando);
                }
                break;
            case CommandNames.COMMAND_MARCO_BUTTON:
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting() && this.panel.getImage() != null)
                    {
                        new Marcos(panel);
                    }
                }
                if (enCollage)
                {
                    new Marcos(collagePanel);
                }
                break;
            case CommandNames.COMMAND_PATRON_BUTTON:
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }

                if (enCollage)
                {
                    new Patrones(collagePanel.canvas);
                }
                break;
            case CommandNames.COMMAND_FLOODFILL_BUTTON:
                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setDibujar(8);
                    try
                    {
                        this.remove(objectOptions);
                    } catch (Exception ex)
                    {
                    }

                }
                this.validate();
                this.repaint();
                break;
            case CommandNames.COLOR_BLACK:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_WHITE:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_GRAY:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_BROWN:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_RED:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_MAGENTA:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_ORANGE:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_YELLOW:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_GREEN:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_BLUE:
                if (enEditor)
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setColor(comando);
                    }
                }
                if (enCollage)
                {
                    this.collagePanel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_TRANSPARENTE:
                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setColor(comando);
                }
                break;
            case CommandNames.COLOR_MAS:
                if (!this.panel.recortar && this.panel.isWriting())
                {
                    this.panel.setColor(comando);
                }
                break;

            case CommandNames.EDITAR:
                try
                {
                    if (enCollage && !collagePanel.canvasEmpty())
                    {
                        int choice = preventLeaving();

                        if (choice == JOptionPane.YES_OPTION)
                        {
                            saveFiles();
                            this.enEditor = true;
                            this.enCollage = false;
                            showVentanaDos(true);
                            barraHerramientas.switchBotones(2);
                            if (this.panel.getImage() == null)
                            {
                                BufferedImage im = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
                                Graphics gr = im.getGraphics();
                                gr.fillRect(0, 0, im.getWidth(), im.getHeight());
                                gr.dispose();
                                this.panel.setImage(im);
                                this.filtrosOrd.setCaracteristicas(im);
                                this.filtrosOrd.setIma(im);
                            }
                        }
                        if (choice == JOptionPane.NO_OPTION)
                        {
                            this.enEditor = true;
                            this.enCollage = false;
                            showVentanaDos(true);
                            barraHerramientas.switchBotones(2);
                            if (this.panel.getImage() == null)
                            {
                                BufferedImage im = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
                                Graphics gr = im.getGraphics();
                                gr.fillRect(0, 0, im.getWidth(), im.getHeight());
                                gr.dispose();
                                this.panel.setImage(im);
                                this.filtrosOrd.setCaracteristicas(im);
                                this.filtrosOrd.setIma(im);
                            }
                        }
                        

                    } else
                    {
                        this.enEditor = true;
                        this.enCollage = false;
                        showVentanaDos(true);
                        barraHerramientas.switchBotones(2);
                        if (this.panel.getImage() == null)
                        {
                            BufferedImage im = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
                            Graphics gr = im.getGraphics();
                            gr.fillRect(0, 0, im.getWidth(), im.getHeight());
                            gr.dispose();
                            this.panel.setImage(im);
                            this.filtrosOrd.setCaracteristicas(im);
                            this.filtrosOrd.setIma(im);
                        }
                    }

                } catch (Exception ex)
                {
                }

                break;

            case CommandNames.EXPLORAR:
                try
                {
                    if (enCollage && !collagePanel.canvasEmpty())
                    {
                        int choice = preventLeaving();

                        if (choice == JOptionPane.YES_OPTION)
                        {
                            saveFiles();
                            showVentanaDos(false);
                            barraHerramientas.switchBotones(1);
                            this.enEditor = false;
                            this.enCollage = false;

                        }
                        if (choice == JOptionPane.NO_OPTION)
                        {
                            showVentanaDos(false);
                            barraHerramientas.switchBotones(1);
                            this.enEditor = false;
                            this.enCollage = false;

                        }

                    } else
                    {
                        showVentanaDos(false);
                        barraHerramientas.switchBotones(1);
                        this.enEditor = false;
                        this.enCollage = false;
                    }
                } catch (Exception exc)
                {
                }

                break;

            case CommandNames.COLLAGE:
                try
                {
                    remove(objectOptions);
                } catch (Exception ex)
                {
                }
                if(enEditor)
                {
                    if (this.panel.undoManager.canUndo())
                    {
                        new JMp3(CommandNames.sound_sure);
                        int choice = 0;
                        choice = JOptionPane.showOptionDialog(this, CommandNames.GUARDAR_QUESTION, CommandNames.CAPTION_TITLE, choice, choice, null, CommandNames.SALIR_QUESTION_OPTIONS, this);
                        if (choice == JOptionPane.YES_OPTION)
                        {
                            this.activate = true;
                            this.filtrosOrd.setVisible(true);
                            this.ob.setVisible(false);
                            this.enEditor = false;
                            this.enCollage = true;
                            if (!this.panel.recortar && this.panel.isWriting())
                            {
                                barraHerramientas.switchBotones(3);
                            }
                            ventanaTres();
                        } else
                        {
                            this.activate = false;
                        }
                        this.repaint();
                    } else
                    {
                            this.activate = true;
                            this.filtrosOrd.setVisible(true);
                            this.ob.setVisible(false);
                            this.enEditor = false;
                            this.enCollage = true;
                            if (!this.panel.recortar && this.panel.isWriting())
                            {
                                barraHerramientas.switchBotones(3);
                            }
                            ventanaTres();
                    }
                }
                else
                {
                    this.enEditor = false;
                    this.enCollage = true;
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        barraHerramientas.switchBotones(3);
                    }
                    ventanaTres();
                }

                break;

            case CommandNames.COMMAND_MOVE_IMAGE:
                try
                {
                    if (!this.panel.recortar && this.panel.isWriting())
                    {
                        this.panel.setDibujar(9);
                        try
                        {
                            this.remove(objectOptions);
                        } catch (Exception ex)
                        {
                        }

                    }
                } catch (Exception ex)
                {
                }
                try
                {
                    collagePanel.isMoving = true;
                    collagePanel.isDeliting = false;
                } catch (Exception ex)
                {
                }
                this.validate();
                this.repaint();
                break;

            case CommandNames.ADD_PICTURE:
                JFileOpener file = new JFileOpener();
                Image image = file.getImage();
                if (image != null && !file.getSelectedFile().getPath().contains(".gif"))
                {
                    collagePanel.drawImage(getScaledImage(image), file.getSelectedFile().getName());

                }
                break;
            case CommandNames.DELETE_PICTURE:
                collagePanel.isMoving = false;
                collagePanel.isDeliting = true;
                collagePanel.delete();
                break;

            case CommandNames.COMMAND_RESIZE_CANVAS:
                String dimensiones = (String) JOptionPane.showInputDialog(this,
                        CommandNames.DIMENSION,
                        CommandNames.DIMENSIONES_TITLE,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        CommandNames.DIMENSIONES,
                        CommandNames.DIMENSIONES[5]);
                if (dimensiones != null)
                {
                    collagePanel.canvasSetSize(CommandNames.getDimensions(dimensiones));
                }

                break;

            case CommandNames.COMMAND_ROTATE:
                rotate.resetSlider();
                anguloARotarFinal = 0;
                anguloARotarInicial = 0;
                collagePanel.isRotating = true;
                collagePanel.isMoving = false;
                collagePanel.isDeliting = false;
                try
                {
                    this.remove(objectOptions);
                } catch (Exception ex)
                {
                }

                this.objectOptions = rotate;
                this.add(objectOptions, BorderLayout.PAGE_END);
                this.repaint();
                validate();
                break;
        }
        if (comando.contains(CommandNames.COMBOCHANGED) && !this.panel.recortar && this.panel.isWriting())
        {
            try
            {
                JComboBox cb = (JComboBox) ae.getSource();
                String filtroName = (String) cb.getSelectedItem();

                if (filtroName.contains(CommandNames.GROSOR_1PX))
                {
                    if (enCollage)
                    {
                        this.collagePanel.setPen(CommandNames.GROSOR_1PX);
                    }
                    if (enEditor)
                    {
                        this.panel.setPen(CommandNames.GROSOR_1PX);
                    }
                } else if (filtroName.contains(CommandNames.GROSOR_3PX))
                {
                    if (enCollage)
                    {
                        this.collagePanel.setPen(CommandNames.GROSOR_3PX);
                    }
                    if (enEditor)
                    {
                        this.panel.setPen(CommandNames.GROSOR_3PX);
                    }

                } else if (filtroName.contains(CommandNames.GROSOR_5PX))
                {
                    if (enCollage)
                    {
                        this.collagePanel.setPen(CommandNames.GROSOR_5PX);
                    }
                    if (enEditor)
                    {
                        this.panel.setPen(CommandNames.GROSOR_5PX);
                    }
                } else if (filtroName.contains(CommandNames.GROSOR_7PX))
                {
                    if (enCollage)
                    {
                        this.collagePanel.setPen(CommandNames.GROSOR_7PX);
                    }
                    if (enEditor)
                    {
                        this.panel.setPen(CommandNames.GROSOR_7PX);
                    }
                } else if (filtroName.contains(CommandNames.NOMBRE))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {
                    }
                } else if (filtroName.contains(CommandNames.IMAGES))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {
                    }
                } else if (filtroName.contains(CommandNames.FILES))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {
                    }
                } else if (filtroName.contains(CommandNames.NOMBRE))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {

                    }
                } else if (filtroName.contains(CommandNames.IMAGES))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {

                    }
                } else if (filtroName.contains(CommandNames.FILES))
                {
                    try
                    {
                        visor.filtrarPor(filtroName);
                    } catch (Exception ex)
                    {

                    }
                } else
                {
                    try
                    {
                        
                        //Version linux!
                        URL[] urls2 =
                        {
                        new URL("file://" + new File("").getAbsolutePath() + "/src/Filtros/" + filtroName + ".jar")
                        };
                        
                        //Version Windows
                        URL[] urls =
                        {
                        new URL("file://localhost/" + System.getProperty("user.dir") + "/src/Filtros/" + filtroName + ".jar")
                        };

                        URLClassLoader urlClassLoader;
                        if(System.getProperty("os.name").contains(CommandNames.IOSWIN))
                        {
                            urlClassLoader= URLClassLoader.newInstance(urls);
                        }
                        else
                        {
                            urlClassLoader= URLClassLoader.newInstance(urls2);
                        }
                        Class clazz = urlClassLoader.loadClass("Filtro." + filtroName);

                        Filtro filter = (Filtro) clazz.newInstance();
                         //System.out.println(filter.getName());

                        BufferedImage ImaFiltro;
                        if (this.panel.imageList.isEmpty())
                        {
                            ImaFiltro = filter.aplicarFiltro(this.panel.getImage());
                        } else
                        {
                            ImaFiltro = filter.aplicarFiltro(this.panel.addFiguras());
                            this.panel.imageList = new ArrayList<>();
                        }
                        if (ImaFiltro != null)
                        {
                            this.panel.actualizarImagen(ImaFiltro);
                        }
                    } catch (Exception exc)
                    {
                        System.out.println("" + exc);
                    }

                }

            } catch (Exception ex)
            {
            }

            try
            {
                JComboBox cb = (JComboBox) ae.getSource();
                ImageIcon imagen = (ImageIcon) cb.getSelectedItem();
                String descrip = imagen.getDescription();
                String name = cb.getName();
                if (name.equals(CommandNames.TIPOLINEA))
                {
                    if (enEditor)
                    {
                        this.panel.setStyleLine(descrip);
                    }
                    if (enCollage)
                    {
                        this.collagePanel.setStyleLine(descrip);
                    }
                } else if (name.equals(CommandNames.TIPOConectores))
                {
                    if (enEditor)
                    {
                        this.panel.setConectorLine(descrip);
                    }
                    if (enCollage)
                    {
                        this.collagePanel.setConectorLine(descrip);
                    }
                }
            } catch (Exception ex)
            {
            }
        }
    }

    private void ventanaUno()
    {
        try
        {
            this.remove(ob);
            this.remove(filtrosOrd);
            this.remove(objectOptions);
            this.remove(statusBar);
        } catch (Exception ex)
        {
        }

        this.tree = new MyTree(this);
        this.tree.getTree().addMouseListener(this);
        this.visor = new VisorCarpetas(this, this);
        visor.setSel(tree.getSel());
        this.statusBar = new MyJPanel();
        this.statusBar.addMouseListener(this);
        this.statusBar.setBackground(null);
        this.statusBar.setLayout(new BorderLayout());
        this.filtrosOrd = new FiltrosOrdenamiento(true, this.panel.getWidth(), this.panel.getHeight(), false, this);
        this.filtrosOrd.getSort().addActionListener(this);
        statusBar.add(path, BorderLayout.WEST);
        statusBar.add(status, BorderLayout.EAST);
        JScrollPane jScrollPane = new JScrollPane(tree.getTree());
        jScrollPane.setMinimumSize(new Dimension(125, 100));
        JScrollPane jScrollPane2 = new JScrollPane(visor);
        this.filtrosOrd.getSort().addActionListener(this);
        this.ob = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, jScrollPane2);
        this.add(ob, BorderLayout.CENTER);
        this.add(filtrosOrd, BorderLayout.EAST);
        this.add(statusBar, BorderLayout.SOUTH);
        this.filtrosOrd.addMouseListener(this);
        this.ob.addMouseListener(this);
    }

    private void ventanaDos()
    {
        try
        {
            this.remove(ob);
            this.remove(filtrosOrd);
            this.remove(statusBar);
            this.remove(objectOptions);
        } catch (Exception ex)
        {
        }
        this.panel = new JPicturePanel(new Dimension(400, 400), this.barraHerramientas, this.menuBar);
        this.scroll = new JScrollPane(panel);
        this.filtrosOrd = new FiltrosOrdenamiento(false, this.panel.getWidth(), this.panel.getHeight(), true, this);
        this.panel.setFO(filtrosOrd);
        this.fl = new FiltrosImage(this);

        FiltrosImage fl = new FiltrosImage(this);

        fl.getSort().addActionListener(this);

        this.ob = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fl,
                scroll);
        ob.addMouseListener(this);
        this.add(ob, BorderLayout.CENTER);
        this.add(filtrosOrd, BorderLayout.EAST);
    }

    private void clickDerechoenCollage(String path)
    {
        ventanaTres();
        File file = new File(path);
        //System.out.println("name:+"+file.getName() );
        Image image = new ImageIcon(path).getImage();
        collagePanel.drawImage(getScaledImage(image), file.getName());
        this.enEditor = false;
        this.enCollage = true;

        validate();
        barraHerramientas.switchBotones(3);
        this.repaint();
    }

    private void ventanaTres()
    {

        this.collageToolbar = new MyCollageToolbar(this);

        try
        {
            this.remove(ob);
            this.remove(filtrosOrd);
            this.remove(statusBar);
        } catch (Exception ex)
        {
        }

        //this.collagePanel = new JPicturePanel ( CommandNames.getDimensions (
        //      dimensiones ) , this.barraHerramientas , this.menuBar );
        //this.collagePanel.setMaximumSize (  CommandNames.getDimensions (
        //       dimensiones ));
        this.collagePanel = new CollagePanel(CommandNames.getDimensions(CommandNames.DIMENSIONES[7]), barraHerramientas, menuBar, collageToolbar);

        this.scroll = new JScrollPane(collagePanel);
        ob.addMouseListener(this);

        this.ob = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, collageToolbar,
                scroll);
        ob.addMouseListener(this);
        this.add(ob, BorderLayout.CENTER);
    }

    private void showVentanaDos(boolean opcion)
    {
        if (opcion)
        {
            this.activate = false;
            this.filtrosOrd.setVisible(false);
            this.ob.setVisible(true);
            ventanaDos();
            validate();
            this.repaint();
        } else
        {
            if (this.panel.undoManager.canUndo())
            {
                new JMp3(CommandNames.sound_sure);
                int choice = 0;
                choice = JOptionPane.showOptionDialog(this, CommandNames.GUARDAR_QUESTION, CommandNames.CAPTION_TITLE, choice, choice, null, CommandNames.SALIR_QUESTION_OPTIONS, this);
                if (choice == JOptionPane.YES_OPTION)
                {
                    this.activate = true;
                    this.filtrosOrd.setVisible(true);
                    this.ob.setVisible(false);
                    ventanaUno();
                } else
                {
                    this.activate = false;
                }
                this.repaint();
            } else
            {
                this.activate = true;
                this.filtrosOrd.setVisible(true);
                this.ob.setVisible(false);
                ventanaUno();
            }
            validate();
            this.repaint();
        }
    }

    public void setBotonsWindows()
    {
    }

    public Image getScaledImage(Image image)
    {
        String dimensiones = (String) JOptionPane.showInputDialog(this,
                CommandNames.DIMENSION,
                CommandNames.DIMENSIONES_TITLE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                CommandNames.DIMENSIONES,
                CommandNames.DIMENSIONES[5]);

        if (dimensiones != null)
        {
            return image.getScaledInstance(CommandNames.getDimensions(dimensiones).width, CommandNames.getDimensions(dimensiones).height, Image.SCALE_SMOOTH);
        }

        return image.getScaledInstance(collagePanel.getWidth(), collagePanel.getHeight(), Image.SCALE_SMOOTH);
    }

    public void setPath()
    {
        try
        {
            this.path.setText(this.tree.getRutaSelec().getAbsolutePath());
        } catch (NullPointerException e)
        {
        }
    }

    public void visorDeCarpetasListener()
    {
    }

    public void botonUno(MouseEvent e)
    {
        if (e.getSource() instanceof MyIconExplorer)
        {
            this.clicks = e.getClickCount();
        }

        if (activate && e.getSource() instanceof JTree)
        {
            DefaultMutableTreeNode sel = this.tree.getSel();
            if (sel != this.tree.getRaiz())
            {
                explorerDirection.obtenerRuta(sel);
                this.visor.setSel(sel);
                enableDirectionBotons();
            }

            try
            {
                this.path.setText(this.tree.getRutaSelec().getAbsolutePath());
            } catch (Exception ex)
            {
            }
            this.status.setText("x: " + Integer.toString(e.getX()) + " y: " + Integer.toString(e.getY()));
        }

        validate();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        popupMenu.setVisible(false);
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            botonUno(e);
            popupMenu.setVisible(false);
        }
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            if (e.getSource() instanceof MyIconExplorer)
            {
                clicks = 1;
                MyIconExplorer explorer = (MyIconExplorer) e.getSource();
                if (explorer.isPicture())
                {
                    popupMenu.mostrar(true, e.getXOnScreen(), e.getYOnScreen(), false);
                    popupMenu.setPath(explorer.getPath());

                }
                if (!explorer.isPicture())
                {
                    popupMenu.mostrar(true, e.getXOnScreen(), e.getYOnScreen(), true);
                    popupMenu.setPath(explorer.getPath());
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        popupMenu.setVisible(false);
        if (activate)
        {
            setPath();
            this.clicks = e.getClickCount();
            this.status.setText("x: " + Integer.toString(e.getX()) + " y: " + Integer.toString(e.getY()));
        }
        this.repaint();

        if (e.getSource() instanceof CollagePanel)
        {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        popupMenu.setVisible(false);
        if (activate)
        {
            this.status.setText("x: " + Integer.toString(e.getX()) + " y: " + Integer.toString(e.getY()));
        }

        if (!this.panel.recortar && this.panel.isWriting())
        {
            try
            {
                JSlider js = (JSlider) e.getSource();
                this.panel.setFiltrandoManual(false);

            } catch (Exception ex)
            {

                try
                {
                    JSlider js = (JSlider) e.getSource();
                    this.panel.setFiltrandoManual(false);

                } catch (Exception ex2)
                {
                }

            }
        }
        this.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (activate)
        {
            this.status.setText("x: " + Integer.toString(e.getX()) + " y: " + Integer.toString(e.getY()));
        }
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {

        JSlider slider = (JSlider) e.getSource();
        String comando = slider.getName();

        if (!this.panel.recortar)
        {
            if (comando.equals("Dispersion"))
            {
                this.panel.setBIG_OFFSET(slider.getValue());
            }
            if (comando.equals("Concentrar"))
            {
                this.panel.setSMALL_OFFSET(slider.getValue());
            }
            if (comando.equals("Rotar"))
            {
                if (!slider.getValueIsAdjusting())
                {
                    collagePanel.canvas.angulo = slider.getValue();
                    collagePanel.girar(slider.getValue());

                }

                rotate.updateLabel(Integer.toString(slider.getValue()));

            }

            if (comando.equals(CommandNames.FILTRO_R))
            {
                if (this.panel.getImage() != null)
                {
                    int valorR = slider.getValue();
                    int valorG = this.fl.getDistanciaG();
                    int valorB = this.fl.getDistanciaB();
                    this.panel.setFiltroManual(valorR, valorG, valorB);
                }
            }
            if (comando.equals(CommandNames.FILTRO_G))
            {
                if (this.panel.getImage() != null)
                {
                    int valorG = slider.getValue();
                    int valorR = this.fl.getDistanciaR();
                    int valorB = this.fl.getDistanciaB();
                    this.panel.setFiltroManual(valorR, valorG, valorB);
                }
            }
            if (comando.equals(CommandNames.FILTRO_B))
            {
                if (this.panel.getImage() != null)
                {
                    int valorB = slider.getValue();
                    int valorR = this.fl.getDistanciaR();
                    int valorG = this.fl.getDistanciaG();
                    this.panel.setFiltroManual(valorR, valorG, valorB);
                }
            }
        }
        switch (comando)
        {

            case CommandNames.ZOOM:
                this.panel.setZoom(this.filtrosOrd.getZoom());
                break;

        }

        validate();
        this.panel.repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        int stateChange = e.getStateChange();
        Checkbox checkbox = (Checkbox) e.getSource();
        String name = checkbox.getName();
        if (name.equals("Girar Todos"))
        {   //System.out.println("girar todos");
            if (stateChange == 1)//selected
            {
                if (enCollage)
                {
                    this.collagePanel.setRotarTodo(true);
                }
            } else
            {
                if (enCollage)
                {
                    this.collagePanel.setRotarTodo(false);
                }
            }
        } else
        {
            if (stateChange == 1)//selected
            {
                if (enEditor)
                {
                    this.panel.setFill(true);
                }
                if (enCollage)
                {
                    this.collagePanel.setFill(true);
                }
            } else
            {
                if (enEditor)
                {
                    this.panel.setFill(false);
                }
                if (enCollage)
                {
                    this.collagePanel.setFill(false);
                }
            }
        }
    }
}
