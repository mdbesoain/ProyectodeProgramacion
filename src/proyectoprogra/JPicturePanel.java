/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Filtros.FloodFill;
import elements.Circle;
import elements.Element;
import elements.Estrella;
import elements.Line;
import elements.Pentagono;
import elements.Rectangle;
import elements.Rombo;
import elements.Spray;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import myObjects.FiltrosOrdenamiento;
import myObjects.ImageCollage;
import myObjects.ImageSelection;
import myObjects.ImageTransform;
import myObjects.MyMenuBar;
import myObjects.MyText;
import myObjects.MyToolBar;

/**
 *
 *
 */
public class JPicturePanel extends JPanel implements MouseListener, MouseMotionListener
{

    private Image imageOriginal;
    //---------- RECORTAR ------------

    private int grosor_Borde;
    private float x1;
    private float y1;
    private float ancho;
    private float alto;
    public Boolean recortar;

    private Boolean bordeDerecho;
    private Boolean bordeIzquierdo;
    private Boolean bordeArriba;
    private Boolean bordeAbajo;
    private Boolean bordeSurOeste;
    private Boolean bordeSurEste;
    private Boolean bordeNorteOeste;
    private Boolean bordeNorteEste;

    //variables para el movimiento de recortar
    private int Pos_Marca_new_X;
    private int Pos_Marca_new_Y;
    private int Pos_Marca_X;
    private int Pos_Marca_Y;
    private int Dist_X;
    private int Dist_Y;
    private BufferedImage Imagen_en_memoria;
    private Graphics2D g2D;
    private BufferedImage tmp_Recorte;

    //------------------------------
    private Image image;
    private int x_original;
    private int y_original;
    private int BIG_OFFSET;
    private int SMALL_OFFSET;

    private Boolean a;
    private Boolean b = true;
    private Boolean filtrandoManual = false;
    private double zoom = 1;
    public UndoManager undoManager;
    public ArrayList<Image> imagenes = new ArrayList<>();
    protected PaintCanvas canvas = new PaintCanvas(imagenes);
    private MyToolBar tool;

    private MyMenuBar menu;
    private double grados;
    private ArrayList<Element> elements;
    private ArrayList<Integer> copyAreaX;
    private ArrayList<Integer> copyAreaY;
    private Line auxLine;
    private Circle circulo;
    private Rectangle rectangulo;
    //private ArrayList<Rectangle> eraser;
    private int dibujar;
    private Color colorActual;
    private String thin;
    private boolean fill;
    private boolean brush;
    private boolean move;
    private boolean moveText;
    private String optionsLine;
    private String styleLine;
    private String conectorLine;
    private Image imageAUX;
    private MyText text;
    boolean cambiarTexto;
    private Image marco;
    private Clipboard portapapeles;
    public ArrayList<ImageCollage> imageList;
    public boolean selec;
    private Estrella estrella;
    private Rombo rombo;
    private Pentagono pentagono;
    private Spray spray;
    private FiltrosOrdenamiento filOrd;

    public JPicturePanel(MyToolBar toolBar, MyMenuBar menu)
    {
        this.conectorLine = CommandNames.ICON_LineaNormal;
        this.BIG_OFFSET = 17;
        this.SMALL_OFFSET = 10;
        this.fill = false;
        this.moveText = false;

        this.x1 = 1;
        this.y1 = 1;
        this.ancho = 160;
        this.alto = 160;
        this.bordeDerecho = false;
        this.bordeIzquierdo = false;
        this.bordeArriba = false;
        this.bordeAbajo = false;
        this.bordeSurOeste = false;
        this.bordeSurEste = false;
        this.bordeNorteOeste = false;
        this.bordeNorteEste = false;
        this.Pos_Marca_new_X = 0;
        this.Pos_Marca_new_Y = 0;
        this.Pos_Marca_X = 0;
        this.Pos_Marca_Y = 0;
        this.Dist_X = 0;
        this.Dist_Y = 0;
        this.selec = false;
        this.copyAreaY = new ArrayList<>();
        this.copyAreaX = new ArrayList<>();
        this.imageList = new ArrayList<>();
        this.tool = toolBar;
        this.menu = menu;
        this.a = false;
        this.recortar = false;
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.zoom = 1;
        this.x_original = 0;
        this.y_original = 0;
        this.undoManager = new UndoManager();
        this.grados = 0;
        this.move = true;
        this.cambiarTexto = false;
        this.elements = new ArrayList<>();
        this.auxLine = new Line();
        this.circulo = new Circle();
        this.rectangulo = new Rectangle();
        this.dibujar = 0;
        this.thin = CommandNames.GROSOR_1PX;
        this.colorActual = Color.BLACK;
        this.text = null;
        this.marco = null;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public JPicturePanel()
    {
        this.conectorLine = CommandNames.ICON_LineaNormal;
        this.BIG_OFFSET = 17;
        this.SMALL_OFFSET = 10;
        this.fill = false;
        this.moveText = false;

        this.x1 = 1;
        this.y1 = 1;
        this.ancho = 160;
        this.alto = 160;
        this.bordeDerecho = false;
        this.bordeIzquierdo = false;
        this.bordeArriba = false;
        this.bordeAbajo = false;
        this.bordeSurOeste = false;
        this.bordeSurEste = false;
        this.bordeNorteOeste = false;
        this.bordeNorteEste = false;
        this.Pos_Marca_new_X = 0;
        this.Pos_Marca_new_Y = 0;
        this.Pos_Marca_X = 0;
        this.Pos_Marca_Y = 0;
        this.Dist_X = 0;
        this.Dist_Y = 0;
        this.selec = false;
        this.copyAreaY = new ArrayList<>();
        this.copyAreaX = new ArrayList<>();
        this.imageList = new ArrayList<>();
        this.tool = null;
        this.menu = null;
        this.marco = null;
        this.grados = 0;
        this.a = false;
        this.recortar = false;
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.zoom = 1;
        this.move = true;
        this.cambiarTexto = false;
        this.x_original = 0;
        this.y_original = 0;
        this.undoManager = new UndoManager();
        this.elements = new ArrayList<>();
        this.auxLine = new Line();
        this.circulo = new Circle();
        this.rectangulo = new Rectangle();
        this.dibujar = 0;
        this.thin = CommandNames.GROSOR_1PX;
        this.colorActual = Color.BLACK;
        this.text = null;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public JPicturePanel(Dimension dim, MyToolBar toolBar, MyMenuBar menu)
    {
        this.conectorLine = CommandNames.ICON_LineaNormal;
        this.BIG_OFFSET = 17;
        this.SMALL_OFFSET = 10;
        this.fill = false;
        this.moveText = false;
        this.x1 = 1;
        this.y1 = 1;
        this.ancho = 160;
        this.alto = 160;
        this.bordeDerecho = false;
        this.bordeIzquierdo = false;
        this.bordeArriba = false;
        this.bordeAbajo = false;
        this.bordeSurOeste = false;
        this.bordeSurEste = false;
        this.bordeNorteOeste = false;
        this.bordeNorteEste = false;
        this.Pos_Marca_new_X = 0;
        this.Pos_Marca_new_Y = 0;
        this.Pos_Marca_X = 0;
        this.Pos_Marca_Y = 0;
        this.Dist_X = 0;
        this.Dist_Y = 0;
        this.selec = false;
        this.copyAreaY = new ArrayList<>();
        this.copyAreaX = new ArrayList<>();
        this.imageList = new ArrayList<>();
        this.a = false;
        this.recortar = false;
        this.marco = null;
        this.grados = 0;
        this.undoManager = new UndoManager();
        this.tool = toolBar;
        this.menu = menu;
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.zoom = 1;
        this.move = true;
        this.cambiarTexto = false;
        this.x_original = 0;
        this.y_original = 0;
        this.elements = new ArrayList<>();
        this.auxLine = new Line();
        this.circulo = new Circle();
        this.rectangulo = new Rectangle();
        this.dibujar = 0;
        this.thin = CommandNames.GROSOR_1PX;
        this.colorActual = Color.BLACK;
        this.text = null;
        this.setMinimumSize(dim);
        this.setSize(new Dimension(100, 100));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public int getBIG_OFFSET()
    {
        return BIG_OFFSET;
    }

    public void setBIG_OFFSET(int BIG_OFFSET)
    {
        this.BIG_OFFSET = BIG_OFFSET;
    }

    public int getSMALL_OFFSET()
    {
        return SMALL_OFFSET;
    }

    public void setSMALL_OFFSET(int SMALL_OFFSET)
    {
        this.SMALL_OFFSET = SMALL_OFFSET;
    }

    public boolean isFill()
    {
        return fill;
    }

    public void setFill(boolean fill)
    {
        this.fill = fill;
    }

    public ArrayList<Integer> getCopyArea()
    {
        return copyAreaX;
    }

    public void setCopyArea(ArrayList<Integer> copyArea)
    {
        this.copyAreaX = copyArea;
    }

    public ArrayList<Integer> getCopyAreaY()
    {
        return copyAreaY;
    }

    public void setCopyAreaY(ArrayList<Integer> copyAreaY)
    {
        this.copyAreaY = copyAreaY;
    }

    public MyText getText()
    {
        return text;
    }

    public void setText(JLabel label)
    {
        if (this.text != null)
        {
            this.text.setLabel(label);
        } else
        {
            this.text = new MyText(label, this.image.getWidth(null));
        }
    }

    public void setText()
    {
        this.text = null;
    }

    public Boolean isFiltrandoManual()
    {
        return filtrandoManual;
    }

    public void setFiltrandoManual(Boolean filtrandoManual)
    {
        this.filtrandoManual = filtrandoManual;
        this.actualizarImagen(imageAUX);
    }

    public int getX_original()
    {
        return x_original;
    }

    public int getY_original()
    {
        return y_original;
    }

    public String getOptionsLine()
    {
        return optionsLine;
    }

    public void setOptionsLine(String optionsLine)
    {
        this.optionsLine = optionsLine;
    }

    public String getStyleLine()
    {
        return styleLine;
    }

    public void setStyleLine(String styleLine)
    {
        this.styleLine = styleLine;
    }

    public String getConectorLine()
    {
        return conectorLine;
    }

    public void setConectorLine(String conectorLine)
    {
        this.conectorLine = conectorLine;
    }

    public void setColor(String color)
    {
        switch (color)
        {
            case CommandNames.COLOR_BLACK:
                this.colorActual = Color.BLACK;
                break;
            case CommandNames.COLOR_WHITE:
                this.colorActual = Color.WHITE;
                break;
            case CommandNames.COLOR_GRAY:
                this.colorActual = Color.GRAY;
                break;
            case CommandNames.COLOR_BROWN:
                this.colorActual = new Color(139, 69, 19);
                break;
            case CommandNames.COLOR_RED:
                this.colorActual = Color.RED;
                break;
            case CommandNames.COLOR_MAGENTA:
                this.colorActual = Color.MAGENTA;
                break;
            case CommandNames.COLOR_ORANGE:
                this.colorActual = new Color(255, 140, 0);
                break;
            case CommandNames.COLOR_YELLOW:
                this.colorActual = Color.YELLOW;
                break;
            case CommandNames.COLOR_GREEN:
                this.colorActual = Color.GREEN;
                break;
            case CommandNames.COLOR_BLUE:
                this.colorActual = Color.BLUE;
                break;
            case CommandNames.COLOR_TRANSPARENTE:
                this.colorActual = new Color(255, 255, 255, 0);
                break;
            case CommandNames.COLOR_MAS:
                this.colorActual = JColorChooser.showDialog(this, "Seleccione un Color", this.colorActual);
                break;
            default:
                this.colorActual = Color.BLACK;
                break;
        }
    }

    public void setAuxLine()
    {
        this.auxLine = new Line();
    }

    public void setElements()
    {
        this.elements = new ArrayList<>();
        this.imageList = new ArrayList<>();
        this.marco = null;
    }

    public void girar()
    {
        grados += 90;
        double zoomAux = zoom;
        zoom = 1;
        BufferedImage aux = convertImageToBufferredImage(image);
        BufferedImage rotacionImagen = ImageTransform.rotacionImagen(aux, grados);
        zoom = zoomAux;
        setElements();
        actualizarImagen(rotacionImagen);

        grados = 0;
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    //------------------------Recortar ---------------
    public void recortarImagen()
    {
        this.dibujar = 8;
        double zoomAux = zoom;
        zoom = 1;
        BufferedImage imgsel = convertImageToBufferredImage(image);
        zoom = zoomAux;
        Pos_Marca_X = this.image.getWidth(null) / 2;
        Pos_Marca_Y = this.image.getHeight(null) / 2;
        ancho = this.image.getWidth(null) / 2;
        alto = this.image.getHeight(null) / 2;
        recortar = true;
    }

    //SE OCUPARA PARA GUARDAR UNA IMAGEN RECORTADA
    public void recortar()
    {
        double zoomAux = zoom;
        zoom = 1;
        BufferedImage bi = convertImageToBufferredImage(image);
        tmp_Recorte = bi.getSubimage((int) x1, (int) y1, (int) ancho, (int) alto);
        zoom = zoomAux;
        actualizarImagen(tmp_Recorte);
        this.recortar = false;
    }

    public void cambiaTamano(int ancho, int alto)
    {
        Image imagen = image.getScaledInstance(ancho, alto, Image.SCALE_REPLICATE);
        actualizarImagen(imagen);
    }

    public Dimension getDimension()
    {
        Dimension dim = new Dimension(image.getWidth(null), image.getHeight(null));
        return dim;
    }

    public double getZoom()
    {
        return this.zoom;
    }

    public void setZoom(double zoom)
    {
        this.zoom = zoom;
        if (image != null)
        {
            int width = (int) (image.getWidth(null) * zoom);
            int height = (int) (image.getHeight(null) * zoom);
            super.setPreferredSize(new Dimension(width, height));
            super.updateUI();
        }
        super.repaint();
        canvas.repaint();

    }

    public Image getImage()
    {
        return this.image;
    }

    public void setImage(Image image)
    {
        this.imageOriginal = image;
        actualizarImagen(image);
        int width = image.getWidth(this);
        int height = image.getHeight(this);

        this.x_original = width;
        this.y_original = height;
        super.setPreferredSize(new Dimension(width, height));
        super.repaint();
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    public Point getPointRefactor(Point point)
    {
        if (this.image != null)
        {
            int valorX = (int) ((this.image.getWidth(null) * point.getX()) / (this.image.getWidth(null) * zoom));
            int valorY = (int) ((this.image.getHeight(null) * point.getY()) / (this.image.getHeight(null) * zoom));
            return new Point(valorX, valorY);
        }
        return null;
    }

    public void setImageTamano(Boolean a)
    {
        this.a = a;
        super.repaint();
    }

    public void setPen(String pen)
    {
        this.thin = pen;
    }

    public void setBrush(boolean b)
    {
        this.brush = b;
    }

    public void setDibujar(int dibujar)
    {
        this.dibujar = dibujar;
        this.fill = false;
        this.thin = CommandNames.GROSOR_1PX;
        this.selectionCursor(dibujar);
        this.copyAreaX = new ArrayList<>();
        this.copyAreaY = new ArrayList<>();
    }

    private void selectionCursor(int dibujar)
    {
        switch (dibujar)
        {
            case 1:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 2:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case 3:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case 4:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            case 5:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 6:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case 7:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            case 8:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case 9:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 10:
                selec = true;
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 11:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 12:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 13:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case 14:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;

            default:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point pointRefactor = getPointRefactor(e.getPoint());
        if (this.dibujar == 6)
        {
            Robot r = null;
            try
            {
                r = new Robot();
            } catch (AWTException ex)
            {

            }

            this.colorActual = r.getPixelColor(e.getXOnScreen(), e.getYOnScreen());
        }
        if (this.dibujar == 8 && !recortar)
        {
            Robot r = null;
            try
            {
                r = new Robot();
            } catch (AWTException ex)
            {

            }
            if (isEnImagen(pointRefactor, this.image))
            {
                Color colorPixel = r.getPixelColor(e.getXOnScreen(), e.getYOnScreen());
                double zoomAux = this.zoom;
                this.zoom = 1;
                try
                {
                    BufferedImage bi = convertImageToBufferredImage(image);
                    FloodFill floodFill = new FloodFill();
                    floodFill.AplicarFiltro(bi, pointRefactor, colorPixel, colorActual);
                    actualizarImagen(bi);
                } catch (Exception ex)
                {

                }
                this.zoom = zoomAux;
                repaint();
            }
        }

        if (this.dibujar == 14 && !recortar)
        {
            this.spray = new Spray();
            this.spray.setColor(colorActual);
            this.spray.setBIG_OFFSET(BIG_OFFSET);
            this.spray.setSMALL_OFFSET(SMALL_OFFSET);
            this.spray.setX((int) pointRefactor.getX());
            this.spray.setY((int) pointRefactor.getY());
            this.spray.puntos();
            this.elements.add(spray);
            repaint();
        }
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point pointRefactor = getPointRefactor(e.getPoint());
        if (recortar && this.image != null)
        {
            Pos_Marca_X = (int) pointRefactor.getX();
            Pos_Marca_Y = (int) pointRefactor.getY();

            //REVISO SI ESTOY EN EL BORDE
            esBorde();
        }
        if (this.dibujar == 1 && this.image != null)
        {
            this.auxLine = new Line();
            this.auxLine.add(pointRefactor);
            this.auxLine.setColor(this.colorActual);
            this.auxLine.setThin(this.thin);
            if (brush)
            {
                this.auxLine.setThin(CommandNames.GROSOR_1PX);
            }
            this.auxLine.setBrush(this.brush);
            this.elements.add(this.auxLine);
            repaint();
        }
        if (this.dibujar == 2 && this.image != null)
        {
            this.circulo = new Circle();
            this.circulo.setPointInicial(pointRefactor);
            this.circulo.setPointFinal(pointRefactor);
            this.circulo.setColor(this.colorActual);
            this.circulo.setThin(this.thin);
            this.circulo.setFill(fill);
            this.elements.add(this.circulo);
            repaint();
        }
        if (this.dibujar == 3 && this.image != null)
        {
            this.rectangulo = new Rectangle();
            this.rectangulo.setFill(false);
            this.rectangulo.setFilled(fill);
            this.rectangulo.setPointInicio(pointRefactor);
            this.rectangulo.setPointFinal(pointRefactor);
            this.rectangulo.setColor(this.colorActual);
            this.rectangulo.setThin(this.thin);
            this.elements.add(this.rectangulo);
            repaint();
        }
        if (this.dibujar == 4 && this.image != null)
        {
            this.rectangulo = new Rectangle();
            this.rectangulo.setFill(true);
            this.rectangulo.setThin(this.thin);
            this.rectangulo.setPointInicio(pointRefactor);
            this.elements.add(this.rectangulo);
            repaint();
        }
        if (this.dibujar == 5 && this.image != null)
        {
            //algo
            try
            {
                JLabel elLabel = text.getLabel();
                int pX = text.getPosX();
                int pY = text.getPosY();
                int wi = elLabel.getWidth();
                int he = elLabel.getHeight();
                Pos_Marca_X = (int) pointRefactor.getX();
                Pos_Marca_Y = (int) pointRefactor.getY();
                he /= 2;
                if (Pos_Marca_X >= pX && Pos_Marca_X <= pX + wi && Pos_Marca_Y <= pY && Pos_Marca_Y >= pY - he)
                {
                    this.moveText = true;
                } else
                {
                    this.moveText = false;
                }

            } catch (Exception exc)
            {
                this.moveText = false;
            }
            repaint();
        }
        if (this.dibujar == 7 && this.image != null)
        {
            this.auxLine = new Line(true, optionsLine, styleLine, conectorLine);
            this.auxLine.setPointInicio(pointRefactor);
            this.auxLine.setPointFinal(pointRefactor);
            this.auxLine.setColor(this.colorActual);
            this.auxLine.setThin(this.thin);
            this.elements.add(this.auxLine);
            repaint();
        }
        if (this.dibujar == 9 && this.image != null)
        {
            this.Pos_Marca_X = (int) pointRefactor.getX();
            this.Pos_Marca_Y = (int) pointRefactor.getY();
        }
        if (this.dibujar == 10 && this.image != null)
        {

            if (copyAreaY.size() > 1)
            {
                selec = true;
                copyAreaX = new ArrayList<>();
                copyAreaY = new ArrayList<>();
                copyAreaX.add((int) pointRefactor.getX());
                copyAreaY.add((int) pointRefactor.getY());
            }

            repaint();
        }
        if (this.dibujar == 11 && this.image != null)
        {
            this.estrella = new Estrella();
            this.estrella.setThin(this.thin);
            this.estrella.setFill(fill);
            this.estrella.setPointInicio(pointRefactor);
            this.estrella.setPointFinal(pointRefactor);
            this.estrella.setColor(this.colorActual);
            this.elements.add(this.estrella);
            repaint();
        }
        if (this.dibujar == 12 && this.image != null)
        {
            this.rombo = new Rombo();
            this.rombo.setThin(this.thin);
            this.rombo.setFill(fill);
            this.rombo.setPointInicio(pointRefactor);
            this.rombo.setPointFinal(pointRefactor);
            this.rombo.setColor(this.colorActual);
            this.elements.add(this.rombo);
            repaint();
        }
        if (this.dibujar == 13 && this.image != null)
        {
            this.pentagono = new Pentagono();
            this.pentagono.setThin(this.thin);
            this.pentagono.setFill(fill);
            this.pentagono.setPointInicio(pointRefactor);
            this.pentagono.setPointFinal(pointRefactor);
            this.pentagono.setColor(this.colorActual);
            this.elements.add(this.pentagono);
            repaint();
        }
        if (this.dibujar == 14 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.spray = new Spray();
                this.spray.setColor(colorActual);
                this.spray.setBIG_OFFSET(BIG_OFFSET);
                this.spray.setSMALL_OFFSET(SMALL_OFFSET);
                this.spray.setX((int) pointRefactor.getX());
                this.spray.setY((int) pointRefactor.getY());
                this.spray.puntos();
                this.elements.add(spray);
            }
            repaint();
        }
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (dibujar != 0 && dibujar != 9 && dibujar != 10 && dibujar != 5 && recortar != true && image != null)
        {
            actualizarImagen(addFiguras());
            setElements();
        }
        bordeDerecho = false;
        bordeIzquierdo = false;
        bordeArriba = false;
        bordeAbajo = false;
        bordeSurOeste = false;
        bordeSurEste = false;
        bordeNorteOeste = false;
        bordeNorteEste = false;
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public boolean EstaDentroRecortar()
    {

        if ((Pos_Marca_X > x1 + 1 && Pos_Marca_X < x1 + ancho) && (Pos_Marca_Y > y1 + 1 && Pos_Marca_Y < y1 + alto))
        {
            return true;
        }
        return false;

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        Point pointRefactor = getPointRefactor(e.getPoint());
        if (this.dibujar == 1 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.auxLine.add(pointRefactor);
                this.auxLine.setColor(this.colorActual);
            }
            repaint();
        }
        if (this.dibujar == 2 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.circulo.setPointFinal(pointRefactor);
                this.circulo.setColor(this.colorActual);
                this.circulo.setThin(this.thin);
                this.circulo.setFill(fill);
            }
            repaint();
        }
        if (this.dibujar == 3 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.rectangulo.setPointFinal(pointRefactor);
                this.rectangulo.setFill(false);
                this.rectangulo.setFilled(fill);
                this.rectangulo.setColor(this.colorActual);
                this.rectangulo.setThin(this.thin);
            }
            repaint();
        }
        if (this.dibujar == 4 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.rectangulo = new Rectangle();
                this.rectangulo.setFill(true);
                this.rectangulo.setThin(this.thin);
                this.rectangulo.setPointInicio(pointRefactor);
                this.elements.add(this.rectangulo);
            }
            repaint();
        }
        if (this.dibujar == 5 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image) && moveText)
            {
                Pos_Marca_new_X = (int) pointRefactor.getX();
                Pos_Marca_new_Y = (int) pointRefactor.getY();
                x1 = text.getPosX();
                y1 = text.getPosY();
                ancho = text.getLabel().getWidth();
                alto = text.getLabel().getHeight();
                presionarDentroImagen(Pos_Marca_new_X, Pos_Marca_new_Y);
                this.text.move((int) x1, (int) y1);

            }
            this.repaint();
        }
        if (this.dibujar == 7 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.auxLine.setPointFinal(pointRefactor);
                this.auxLine.setColor(colorActual);
                this.auxLine.setThin(thin);
            }
            this.repaint();
        }
        if (this.dibujar == 9 && this.image != null)
        {
            if (!imageList.isEmpty() && valida(pointRefactor, imageList.get(0)))
            {
                Pos_Marca_new_X = (int) pointRefactor.getX();
                Pos_Marca_new_Y = (int) pointRefactor.getY();
                x1 = imageList.get(0).getLocation().x;
                y1 = imageList.get(0).getLocation().y;
                ancho = (float) (imageList.get(0).getImage().getWidth(null));
                alto = (float) (imageList.get(0).getImage().getHeight(null));
                presionarDentroImagen(Pos_Marca_new_X, Pos_Marca_new_Y);
                //this.moveImage(new Point((int) x1, (int) y1), imageList.get(0));
                imageList.get(0).setLocation((int) x1, (int) y1);

            }
            this.repaint();
        }
        if (this.dibujar == 10 && this.image != null)
        {
            if (copyAreaX.size() > 2)
            {
                if (pointRefactor.getX() > copyAreaX.get(0) - 2 && pointRefactor.getX() < copyAreaX.get(0) + 2
                        && pointRefactor.getY() > copyAreaY.get(0) - 2 && pointRefactor.getY() < copyAreaY.get(0) + 2)
                {
                    selec = false;
                    copyAreaX.add(copyAreaX.get(0));
                    copyAreaY.add(copyAreaY.get(0));
                }
            }
            if (selec)
            {
                copyAreaX.add((int) pointRefactor.getX());
                copyAreaY.add((int) pointRefactor.getY());
            }

            repaint();
        }
        if (this.dibujar == 11 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.estrella.setColor(this.colorActual);
                this.estrella.setThin(this.thin);
                this.estrella.setFill(fill);
                this.estrella.setPointFinal(pointRefactor);
            }
            repaint();
        }
        if (this.dibujar == 12 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.rombo.setColor(this.colorActual);
                this.rombo.setThin(this.thin);
                this.rombo.setFill(fill);
                this.rombo.setPointFinal(pointRefactor);
            }
            repaint();
        }
        if (this.dibujar == 13 && this.image != null)
        {
            if (isEnImagen(pointRefactor, this.image))
            {
                this.pentagono.setColor(this.colorActual);
                this.pentagono.setThin(this.thin);
                this.pentagono.setFill(fill);
                this.pentagono.setPointFinal(pointRefactor);
            }
            repaint();
        }

        if (this.dibujar == 14 && this.image != null)
        {

            if (isEnImagen(pointRefactor, this.image))
            {
                this.spray = new Spray();
                this.spray.setColor(colorActual);
                this.spray.setBIG_OFFSET(BIG_OFFSET);
                this.spray.setSMALL_OFFSET(SMALL_OFFSET);
                this.spray.setX((int) pointRefactor.getX());
                this.spray.setY((int) pointRefactor.getY());
                this.spray.puntos();
                this.elements.add(spray);
            }
            repaint();
        }

        if (recortar && this.image != null)
        {
            Pos_Marca_new_X = (int) pointRefactor.getX();
            Pos_Marca_new_Y = (int) pointRefactor.getY();

            //SI ESTA DENTRO
            if ((EstaDentroRecortar() && !bordeDerecho) && (EstaDentroRecortar() && !bordeIzquierdo)
                    && (EstaDentroRecortar() && !bordeArriba) && (EstaDentroRecortar() && !bordeAbajo)
                    && (EstaDentroRecortar() && !bordeSurOeste) && (EstaDentroRecortar() && !bordeSurEste)
                    && (EstaDentroRecortar() && !bordeNorteOeste) && (EstaDentroRecortar() && !bordeNorteEste))
            {

                presionarDentroImagen(Pos_Marca_new_X, Pos_Marca_new_Y);
            } //ESTAMOS EN BORDE DERECHO
            else if (bordeDerecho)
            {
                derecho();
            } //ESTAMOS EN BORDE IZQUIERDO
            else if (bordeIzquierdo)
            {
                izquierdo();

            } //OPERACIONES BORDE ARRIBA
            else if (bordeArriba)
            {
                arriba();
            } //BORDE ABAJO
            else if (bordeAbajo)
            {
                abajo();
            } //BORDE SUR-OESTE
            else if (bordeSurOeste)
            {
                abajo();
                izquierdo();
            } //BORDE SUR-ESTE
            else if (bordeSurEste)
            {
                abajo();
                derecho();
            } //BORDE NORTE-OESTE
            else if (bordeNorteOeste)
            {
                arriba();
                izquierdo();
            } //BORDE NORTE-ESTE
            else if (bordeNorteEste)
            {
                arriba();
                derecho();
            }

        }
        this.repaint();
        if(this.filOrd !=null)
        {
            this.filOrd.setIma(image);
        }
    }

    public void presionarDentroImagen(int posicionXMouse, int posicionYMouse)

    {

        Dist_X = posicionXMouse - Pos_Marca_X;
        Dist_Y = posicionYMouse - Pos_Marca_Y;
        Pos_Marca_X = Pos_Marca_X + Dist_X;
        Pos_Marca_Y = Pos_Marca_Y + Dist_Y;
        //se coloca la nueva posicion
        x1 = x1 + Dist_X;
        y1 = y1 + Dist_Y;

        if (zoom >= 1)
        {
            if (x1 <= 0)
            {
                x1 = 1;
            }
            if (y1 <= 0)
            {
                y1 = 1;
            }
            if ((x1 + ancho) > this.image.getWidth(null))
            {
                x1 = (float) (this.image.getWidth(null) - ancho);
            }
            if (moveText)
            {
                if (y1 > this.image.getHeight(null))
                {
                    y1 = (float) (this.image.getHeight(null));
                }
            } else if ((y1 + alto) > this.image.getHeight(null))
            {
                y1 = (float) (this.image.getHeight(null) - alto);
            }
        } else
        {
            if (x1 <= 0)
            {
                x1 = 1;
            }
            if (y1 <= 0)
            {
                y1 = 1;
            }
            if ((x1 + ancho) > this.image.getWidth(null))
            {
                x1 = (float) (this.image.getWidth(null) - ancho);
            }
            if (moveText)
            {
                if (y1 > this.image.getHeight(null) + this.image.getHeight(null) * zoom)
                {
                    y1 = (float) (this.image.getHeight(null) + this.image.getHeight(null) * zoom);
                }
            } else if ((y1 + alto) > this.image.getHeight(null))
            {
                y1 = (float) (this.image.getHeight(null) - alto);
            }
        }
    }

    public boolean esBorde()
    {
        //BORDE DERECHO
        if (((int) (x1 + ancho) == Pos_Marca_X)
                && (Pos_Marca_Y >= y1 && Pos_Marca_Y <= (int) (y1 + alto)))
        {
            bordeDerecho = true;

            bordeIzquierdo = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeSurEste = false;
            bordeNorteOeste = false;
            bordeNorteEste = false;

            return true;
        }

        //BORDE IZQUIERDO
        if (((int) (x1) == Pos_Marca_X)
                && (Pos_Marca_Y >= y1 && Pos_Marca_Y <= (int) (y1 + alto)))
        {
            bordeIzquierdo = true;

            bordeDerecho = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeSurEste = false;
            bordeNorteOeste = false;
            bordeNorteEste = false;

            return true;
        }
        //BORDE ARRIBA
        if (((int) (y1) == Pos_Marca_Y)
                && (Pos_Marca_X >= x1 && Pos_Marca_X <= (int) (x1 + ancho)))
        {
            bordeArriba = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeSurEste = false;
            bordeNorteOeste = false;
            bordeNorteEste = false;

            return true;
        }
        //BORDE ABAJO
        if (((int) (y1 + alto) == Pos_Marca_Y) && (Pos_Marca_X >= x1 && Pos_Marca_X <= (int) (x1 + ancho)))
        {
            bordeAbajo = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeArriba = false;
            bordeNorteEste = false;
            bordeNorteOeste = false;
            bordeSurEste = false;
            bordeSurOeste = false;

            return true;

        }

        //BORDE SUR-OESTE
        if ((Pos_Marca_X > (x1 - 1) && Pos_Marca_X < (x1 + 2)) && ((Pos_Marca_Y < (y1 + alto) + 1) && Pos_Marca_Y > (y1 + alto) - 2))
        {

            bordeSurOeste = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurEste = false;
            bordeNorteOeste = false;
            bordeNorteEste = false;

            return true;
        }

        //BORDE SUR-ESTE
        if ((Pos_Marca_X < ((x1 + ancho) + 2) && Pos_Marca_X > (x1 + ancho - 2))
                && ((Pos_Marca_Y < (y1 + alto) + 2) && Pos_Marca_Y > (y1 + alto) - 2))
        {
            bordeSurEste = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeNorteOeste = false;
            bordeNorteEste = false;

            return true;

        }
        //BORDE NORTE-OESTE
        if ((Pos_Marca_X > (x1 - 1) && Pos_Marca_X < (x1 + 2)) && (Pos_Marca_Y > (y1 - 1) && Pos_Marca_Y < (y1 + 2)))
        {
            bordeNorteOeste = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeSurEste = false;
            bordeNorteEste = false;

            return true;
        }
        //BORDE NORTE-ESTE
        if ((Pos_Marca_X < ((x1 + ancho) + 2) && Pos_Marca_X > (x1 + ancho - 2)) && (Pos_Marca_Y > (y1 - 1) && Pos_Marca_Y < (y1 + 2)))
        {
            bordeNorteEste = true;

            bordeDerecho = false;
            bordeIzquierdo = false;
            bordeArriba = false;
            bordeAbajo = false;
            bordeSurOeste = false;
            bordeSurEste = false;
            bordeNorteOeste = false;

            return true;
        }
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        Point pointRefactor = getPointRefactor(e.getPoint());
        if (recortar)
        {
            Pos_Marca_X = (int) pointRefactor.getX();
            Pos_Marca_Y = (int) pointRefactor.getY();

            //CURSOR BORDES ARRIBA Y ABAJO
            if ((esBorde() && bordeArriba) || (esBorde() && bordeAbajo))
            {
                this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            } //CURSOR BORDES SUR-OESTE Y NOR-ESTE 
            else if (esBorde() && bordeSurOeste || (esBorde() && bordeNorteEste))
            {
                this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
            } //CURSOR BORDES SUR-ESTE Y NOR-OESTE
            else if (esBorde() && bordeSurEste || (esBorde() && bordeNorteOeste))
            {
                this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            } //CURSOR BORDES DERECHO E IZQUIERDO
            else if ((esBorde() && bordeDerecho) || (esBorde() && bordeIzquierdo))
            {
                this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            } //CURSOR SI ESTOY DENTRO DEL CUADRADO DE RECTANGULO
            else if (EstaDentroRecortar())
            {
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            } //CURSOR POR DEFECTO
            else
            {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }
        if (this.dibujar == 9 && !imageList.isEmpty() && valida(pointRefactor, imageList.get(0)))
        {
            super.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        if(filOrd !=null)
        {
           filOrd.setIma(image);
        }
    }

    private boolean isEnImagen(Point p, Image image)
    {
        int x = (int) p.getX();
        int y = (int) p.getY();
        if (x <= image.getWidth(null) && y <= image.getHeight(null) && x > 0 && y > 0)
        {
            return true;
        }
        return false;
    }

    public void setMarco(Image marco)
    {
        if (this.image != null)
        {
            this.marco = marco;
        }
    }

    public BufferedImage addFiguras()
    {

        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        b = false;
        this.paint(g);
        b = true;
        return bi;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (recortar && this.image != null)
        {
            /* Imagen_en_memoria = new BufferedImage ( this.image.getWidth ( null ) , this.image.getHeight ( null ) , BufferedImage.TYPE_INT_RGB );
             g2D = Imagen_en_memoria.createGraphics ();
             g2D.setRenderingHint ( RenderingHints.KEY_ANTIALIASING ,
             RenderingHints.VALUE_ANTIALIAS_ON );
             g2D.setRenderingHint ( RenderingHints.KEY_RENDERING ,
             RenderingHints.VALUE_RENDER_QUALITY );
             //se añade la foto grande original
             g2D.setComposite ( AlphaComposite.SrcAtop );
             g2D.drawImage ( image , 1 , 1 , image.getWidth ( this ) + 1 , image.getHeight ( this ) + 1 , this );
             //se crea un recuadro que sirve de referencia para el recorte*/

            Rectangle2D r2 = new Rectangle2D.Float(x1, y1, ancho, alto);

            g2.scale(zoom, zoom);
            //se dibuja todo
            //----Oscurece imagen------------
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f);
            g2.setComposite(ac);
            g2.drawImage(image, 1, 1, this);

            //-----recorte de la imagen-------
            ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f);

            g2.setComposite(ac);
            float[] dash
                    =
                    {
                        15
                    };
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));
            g2.setColor(Color.WHITE);

            g2.draw(new Rectangle2D.Float(x1 - 1, y1 - 1, ancho + 1, alto + 1));
            g2.clip(r2);

            g2.drawImage(image, 1, 1, this);

        } else
        {
            if (filtrandoManual && this.image != null)
            {
                g2.scale(zoom, zoom);
                g2.drawImage(imageAUX, 0, 0,image.getWidth(null), image.getHeight(null), this);
                
            } else
            {

                if (image == null)
                {
                    JLabel label;
                    if (a)
                    {
                        label = new JLabel(CommandNames.VISTAPREVIA);
                        FontMetrics fm = g.getFontMetrics();
                        int ancho = fm.stringWidth(label.getText());
                        g2.setColor(Color.white);
                        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
                        g2.setColor(Color.black);
                        g2.drawString(label.getText(), (this.getWidth() - ancho) / 2, this.getHeight() / 2);
                    } 
                    
                } else if (a == false)
                {
                    this.image = canvas.getImage();

                    if (b == true)
                    {
                        g2.scale(zoom, zoom);

                    }

                    /*g2.setPaint(new GradientPaint(image.getWidth(null), 0, Color.BLACK, image.getWidth(null) - 10, 0, Color.white, true));

                    g2.fillRect(3, 2, image.getWidth(null) + 3, image.getHeight(null) + 2);
                    g2.setPaint(new GradientPaint(0, image.getWidth(null), Color.BLACK, 0, image.getWidth(null) - 10, Color.white, true));
                    g2.fillRect(3, image.getHeight(null), image.getWidth(null) - 1, 4);*/
                    //-------------//
                    g2.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), this);

                } else
                {
                    //imagen de vista previa
                    g2.drawImage(image, getPositionX(), getPositionY(), this);

                }
                if (!imageList.isEmpty())
                {
                    for (ImageCollage imageCollage
                            : imageList)
                    {

                        Image image = imageCollage.image;
                        g2.scale(1, 1);
                        g2.drawImage(image, imageCollage.location.x, imageCollage.location.y, this);

                    }

                }
                if (image != null)
                {

                    if (text != null)
                    {
                        text.paint(g2);
                    }
                    for (int i = 0;
                            i < this.elements.size();
                            i++)
                    {
                        g2.scale(1, 1);
                        elements.get(i).pintar(g2);
                    }
                }
                if (marco != null)
                {
                    //g2.scale(zoom, zoom);
                    g2.drawImage(marco, 0, 0, image.getWidth(null), image.getHeight(null), this);
                }
                if (!copyAreaX.isEmpty() && b)
                {
                    float[] dash
                            =
                            {
                                15
                            };

                    if (copyAreaX.size() == 1)
                    {
                        Point point = new Point(copyAreaX.get(0), copyAreaY.get(0));
                        g2.setColor(Color.WHITE);
                        g2.drawLine(point.x, point.y, point.x, point.y);
                    } else
                    {
                        g2.setColor(Color.WHITE);
                        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));

                        for (int i = 0;
                                i < copyAreaX.size() - 1;
                                i++)
                        {
                            Point point = new Point(copyAreaX.get(i), copyAreaY.get(i));
                            Point point1 = new Point(copyAreaX.get(i + 1), copyAreaY.get(i + 1));
                            g2.drawLine(point.x, point.y, point1.x, point1.y);
                        }
                    }

                }
            }
            g2.dispose();
        }
    }

    private void ArrayCopy(Object[] a, int[] b)
    {
        for (int i = 0;
                i < a.length;
                i++)
        {
            b[i] = (int) a[i];
        }
    }

    private int getPositionX()
    {
        int xPanel = this.getWidth();
        //int xImage = (int) (this.image.getWidth(this) * zoom);
        int xImage = this.image.getWidth(null);

        return (xPanel - xImage) / 2;
    }

    private int getPositionY()
    {
        int yPanel = this.getHeight();
        //int yImage = (int) (this.image.getHeight(this) * zoom);
        int yImage = this.image.getHeight(null);

        return (yPanel - yImage) / 2;
    }

    public BufferedImage convertImageToBufferredImage(Image ig)
    {
        BufferedImage imgsel = new BufferedImage(ig.getWidth(null), ig.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = imgsel.createGraphics();
        paint(bGr);
        bGr.dispose();
        return imgsel;
    }

    public void setFiltroManual(int valorR, int valorG, int valorB)
    {

        BufferedImage bi = null;
        double zoomAux = zoom;
        zoom=1;
        BufferedImage imgsel = this.convertImageToBufferredImage(image);
        zoom=zoomAux;
        if (imgsel != null)
        {
            bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
            for (int x = 0;
                    x < imgsel.getWidth();
                    x++)
            {
                for (int y = 0;
                        y < imgsel.getHeight();
                        y++)
                {
                    Color c = new Color(imgsel.getRGB(x, y));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    r = (int) r + ((r * valorR) / 100);
                    g = (int) g + ((g * valorG) / 100);
                    b = (int) b + ((b * valorB) / 100);
                    if (r > 255)
                    {
                        r = 255;
                    }
                    if (g > 255)
                    {
                        g = 255;
                    }
                    if (b > 255)
                    {
                        b = 255;
                    }
                    if (r < 0)
                    {
                        r = 0;
                    }
                    if (g < 0)
                    {
                        g = 0;
                    }
                    if (b < 0)
                    {
                        b = 0;
                    }

                    bi.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
        }
        this.imageAUX = bi;
        this.filtrandoManual = true;
    }

    public void saveTo(File selectedFile)
    {
        try
        {
            BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            b = false;
            this.paint(g);
            b = true;
            ImageIO.write(bi, getExtension(selectedFile.getPath()).toUpperCase(), selectedFile);
            //JOptionPane.showMessageDialog(this, CommandNames.CAPTION_GUARDADO, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex)
        {
            //JOptionPane.showMessageDialog(this, CommandNames.CAPTION_ERROR, CommandNames.CAPTION_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void obtenerDatosPortapapeles()
    {
        portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable datos = portapapeles.getContents(null);
        if (datos.isDataFlavorSupported(DataFlavor.imageFlavor))
        {
            try
            {
                //actualizarImagen ( (Image) datos.getTransferData(DataFlavor.imageFlavor));
                if (!imageList.isEmpty())
                {
                    actualizarImagen(addFiguras());
                    setElements();
                    /*BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                     Graphics g = bi.getGraphics();
                     this.paint(g);
                     Point p = imageList.get(0).location;
                     g.drawImage(imageList.get(0).getImage(), p.x, p.y, this.image.getWidth(null), this.image.getHeight(null), this);
                     if (marco != null)
                     {
                     g.drawImage(marco, 0, 0, this);
                     }
                     image = bi;*/
                    repaint();
                }
                this.imageList = new ArrayList<>();
                this.imageList.add(new ImageCollage((Image) datos.getTransferData(DataFlavor.imageFlavor), datos.getTransferData(DataFlavor.imageFlavor).toString()));
                repaint();

            } catch (Exception ex)
            {

            }
        }
        repaint();
    }

    public void copiarAlPortapapeles()
    {
        if (!copyAreaX.isEmpty())
        {

            int[] x = new int[copyAreaX.size()];
            int[] y = new int[copyAreaX.size()];
            ArrayCopy(copyAreaX.toArray(), x);
            ArrayCopy(copyAreaY.toArray(), y);
            int wid = FindDistancia(x);
            int hei = FindDistancia(y);
            int min = FindMin(x);
            int minY = FindMin(y);
            Double zoomAux = zoom;
            zoom = 1;
            BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            g.clip(new Polygon(x, y, x.length));
            copyAreaX = new ArrayList<>();
            copyAreaY = new ArrayList<>();
            this.paint(g);

            try
            {
                bi = recortar(bi, min, minY, wid, hei);
                portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
                ImageSelection ima = new ImageSelection(bi);
                portapapeles.setContents(ima, null);
            } catch (Exception ex)
            {

            }
            zoom = zoomAux;
        }
        copyAreaX = new ArrayList<>();
        copyAreaY = new ArrayList<>();
        validate();
        repaint();
    }

    public BufferedImage recortar(BufferedImage bi, int x1, int y1, int ancho, int alto)
    {
        BufferedImage tmp = bi.getSubimage((int) x1, (int) y1, (int) ancho, (int) alto);
        return tmp;
    }

    protected String getExtension(String name)
    {
        String[] str = name.split("\\.");
        if (str.length > 1)
        {
            return str[str.length - 1];
        }

        return ""; //-- no extension
    }

    public Point realPosition(Point p, Image dim)
    {
        if ((p.y + dim.getHeight(null)) <= image.getHeight(null) * zoom && (p.x + dim.getWidth(null)) <= image.getWidth(null) * zoom && p.y >= 0 && p.x >= 0)
        {
            return p;
        } else
        {
            if (p.x < 0)
            {
                p.x = 0;
            }
            if (p.y < 0)
            {
                p.y = 0;
            }
            if ((p.x + dim.getWidth(null)) > image.getWidth(null) * zoom)
            {
                p.x = (int) (image.getWidth(null) * zoom - dim.getWidth(null));
            }
            if ((p.y + dim.getHeight(null)) > image.getHeight(null) * zoom)
            {
                p.y = (int) (image.getHeight(null) * zoom - dim.getHeight(null));
            }
            return p;
        }
    }

    public void moveImage(Point p, ImageCollage imax)
    {
        imax.setLocation(realPosition(p, imax.image).x, realPosition(p, imax.image).y);
        repaint();
    }

    public void actualizarImagen(Image image)
    {
        if (a == false)
        {
            this.image = image;

            this.imagenes.add(this.image);

            undoManager.undoableEditHappened(new UndoableEditEvent(canvas, new UndoablePaintSquare(this.image, imagenes)));
            setupUndoHotkeys();
            this.tool.deshacer.setEnabled(undoManager.canUndo());
            this.tool.rehacer.setEnabled(undoManager.canRedo());
            this.menu.deshacer.setEnabled(undoManager.canUndo());
            this.menu.rehacer.setEnabled(undoManager.canRedo());

            canvas.repaint();
        } else
        {
            this.image = image;
        }
        int width = (int) (this.image.getWidth(null) * zoom);
        int height = (int) (this.image.getHeight(null) * zoom);
        super.setPreferredSize(new Dimension(width, height));
        super.validate();
        super.updateUI();
        super.repaint();
        canvas.repaint();
        if(filOrd !=null)
        {
           filOrd.setIma(image);
        }
    }

    private void setupUndoHotkeys()
    {
        String UNDO = CommandNames.DESHACER;
        String REDO = CommandNames.REHACER;
        Action undoAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (undoManager.canUndo())
                {
                    undoManager.undo();
                }
                tool.deshacer.setEnabled(undoManager.canUndo());
                tool.rehacer.setEnabled(undoManager.canRedo());
                menu.deshacer.setEnabled(undoManager.canUndo());
                menu.rehacer.setEnabled(undoManager.canRedo());
                canvas.repaint();
                repaint();
                if(filOrd !=null)
                {
                   filOrd.setIma(image);
                }
            }
        };

        Action redoAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (undoManager.canRedo())
                {
                    undoManager.redo();

                }

                tool.deshacer.setEnabled(undoManager.canUndo());
                tool.rehacer.setEnabled(undoManager.canRedo());
                menu.deshacer.setEnabled(undoManager.canUndo());
                menu.rehacer.setEnabled(undoManager.canRedo());
                canvas.repaint();
                repaint();
                if(filOrd !=null)
                {
                   filOrd.setIma(image);
                }
            }
        };

        getActionMap().put(UNDO, undoAction);
        getActionMap().put(REDO, redoAction);

        InputMap[] inputMaps = new InputMap[]
        {
            getInputMap(JComponent.WHEN_FOCUSED),
            getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT),
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW),
        };
        for (InputMap i
                : inputMaps)
        {
            i.put(KeyStroke.getKeyStroke("control Z"), UNDO);
            i.put(KeyStroke.getKeyStroke("control Y"), REDO);
        }
    }

    private void abajo()
    {
        if (y1 > 0)
        {
            if (Pos_Marca_new_Y > this.image.getHeight(null))
            {
                alto = this.image.getHeight(null) - y1;
            } else if (Pos_Marca_new_Y <= y1 + 5)
            {
                alto = 5;
            } else
            {
                alto = Pos_Marca_new_Y - (y1 - 0);
            }
        } else
        {
            if (Pos_Marca_new_Y > this.image.getHeight(null))
            {
                alto = this.image.getHeight(null) - y1;
            } else if (Pos_Marca_new_Y <= y1)
            {
                alto = y1 + 5;
            } else
            {

                alto = Pos_Marca_new_Y;
            }
        }
    }

    private void derecho()
    {
        if (x1 > 0)
        {
            if (Pos_Marca_new_X > this.image.getWidth(null))
            {
                ancho = this.image.getWidth(null) - x1;
            } else if (Pos_Marca_new_X <= x1 + 5)
            {
                ancho = 5;
            } else
            {
                ancho = Pos_Marca_new_X - (x1 - 0);
            }
        } else
        {
            if (Pos_Marca_new_X > this.image.getWidth(null))
            {
                ancho = this.image.getWidth(null) - x1;
            } else if (Pos_Marca_new_X <= x1)
            {
                ancho = x1 + 5;
            } else
            {
                ancho = Pos_Marca_new_X;
            }
        }
    }

    private void izquierdo()
    {
        if (x1 > 0)
        {
            if (Pos_Marca_new_X + 5 > x1 + ancho)
            {
                System.out.println("x1+ancho: " + x1 + ancho + ", pos_marca_new_X: " + Pos_Marca_new_X + 5);
                x1 = x1 + ancho - 5;
                ancho = 5;
            } else if (Pos_Marca_new_X < this.image.getWidth(null) && Pos_Marca_new_X > 1)
            {

                ancho += x1 - Pos_Marca_new_X;
                x1 = Pos_Marca_new_X;

            } else if (Pos_Marca_new_X <= 1)
            {
                ancho += x1 - 1;
                x1 = 1;
            } else
            {
                ancho = 5;
                x1 = this.image.getWidth(null) - 5;
            }
        } else
        {
            if (Pos_Marca_new_X + 5 > x1 + ancho)
            {
                x1 = x1 + ancho;
                ancho = 5;
            } else if (Pos_Marca_new_X < this.image.getWidth(null) && Pos_Marca_new_X > 0)
            {
                ancho += x1 - Pos_Marca_new_X;
                x1 = Pos_Marca_new_X;
            } else if (Pos_Marca_new_X <= 0)
            {
                ancho = x1 + ancho;
                x1 = 1;
            } else
            {
                ancho = 5;
                x1 = this.image.getWidth(null);
            }
        }
    }

    private void arriba()
    {
        if (y1 > 0)
        {
            if (Pos_Marca_new_Y + 5 > (y1 + alto))
            {
                y1 = (y1 + alto) - 5;
                alto = 5;
            } else if (Pos_Marca_new_Y < this.image.getHeight(null) && Pos_Marca_new_Y > 0)
            {
                alto += y1 - Pos_Marca_new_Y;
                y1 = Pos_Marca_new_Y;
            } else if (Pos_Marca_new_Y <= 0)
            {
                alto += y1 - 1;
                y1 = 1;
            } else
            {
                alto = 5;
                y1 = this.image.getHeight(null) - 5;
            }
        } else
        {
            if (Pos_Marca_new_Y + 5 > y1 + alto)
            {
                y1 = y1 + alto - 5;
                alto = 5;
            } else if (Pos_Marca_new_Y < this.image.getHeight(null) && Pos_Marca_new_Y > 0)
            {
                alto += y1 - Pos_Marca_new_Y;
                y1 = Pos_Marca_new_Y;
            } else if (Pos_Marca_new_Y <= 0)
            {
                y1 = 1;
            } else
            {
                alto = 5;
                y1 = this.image.getHeight(null) - 5;
            }
        }
    }

    boolean isWriting()
    {
        if (text == null)
        {
            return true;
        }
        return false;
    }

    private int FindDistancia(int[] x)
    {
        int xMin = x[0];
        int xMax = x[0];

        for (int i = 0;
                i < x.length;
                i++)
        {
            if (x[i] > xMax)
            {
                xMax = x[i];
            }
            if (x[i] < xMin)
            {
                xMin = x[i];
            }

        }
        return xMax - xMin;
    }

    private int FindMin(int[] x)
    {
        int xMin = x[0];

        for (int i = 0;
                i < x.length;
                i++)
        {

            if (x[i] < xMin)
            {
                xMin = x[i];
            }

        }
        return xMin;
    }

    private boolean valida(Point p, ImageCollage get)
    {
        int x = (int) p.getX();
        int y = (int) p.getY();
        Image image1 = get.getImage();
        int width = image1.getWidth(null);
        int height = image1.getHeight(null);
        int xL = get.location.x;
        int yL = get.location.y;
        if (x <= xL + width && y <= yL + height && x > xL && y > yL)
        {
            return true;
        }
        return false;
    }

    //spray uses Math.random() to get a random set of pixels within a radius coloured in	
    public BufferedImage spray(int x, int y, int ancho, int alto)
    {

        BufferedImage bi = convertImageToBufferredImage(image);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(this.colorActual);

        int tempx;
        int tempy;

        for (int i = 0;
                i < 100;
                i++)
        {

            // use static final ints now
            tempx = x + (int) Math.round(2 * SMALL_OFFSET * (Math.random() - 0.5));
            tempy = y + (int) (((Math.random() - 0.5) * 2) * Math.sqrt(
                    (SMALL_OFFSET * SMALL_OFFSET) - ((x - tempx) * (x - tempx))));
            g2.drawLine(tempx, tempy, tempx, tempy);
        }

        for (int i = 0;
                i < 70;
                i++)
        {
            tempx = (x + (int) Math.round(2 * BIG_OFFSET * (Math.random() - 0.5)));
            tempy = (y + (int) (((Math.random() - 0.5) * 2) * Math.sqrt(
                    (BIG_OFFSET * BIG_OFFSET) - ((x - tempx) * (x - tempx)))));
            g2.drawLine(tempx, tempy, tempx, tempy);
        }
        return bi;
    }

    void setFO(FiltrosOrdenamiento filtrosOrd)
    {
        this.filOrd = filtrosOrd;
    }

    class UndoablePaintSquare extends AbstractUndoableEdit
    {

        protected Image imagen;
        protected ArrayList<Image> imagenes;

        public UndoablePaintSquare(Image image, ArrayList<Image> v)
        {
            imagenes = v;
            imagen = image;
        }

        @Override
        public void undo()
        {
            super.undo();
            imagenes.remove(imagen);
        }

        @Override
        public void redo()
        {
            super.redo();
            imagenes.add(imagen);
        }

        @Override
        public boolean canUndo()
        {
            if (imagenes.size() > 1 && super.canUndo())
            {
                return true;
            }
            return false;
        }
        //------------------------------------------
    }

}
