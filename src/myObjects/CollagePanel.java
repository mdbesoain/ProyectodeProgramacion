/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import elements.Circle;
import elements.Element;
import elements.Line;
import elements.Rectangle;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import proyectoprogra.CommandNames;

public class CollagePanel extends JScrollPane implements MouseListener, MouseMotionListener, ListSelectionListener, ActionListener
{

    public boolean isMoving;
    public boolean isDeliting;
    public boolean isRotating;
    public Image imageAux;
    public UndoManager undoManager;
    public MyToolBar toolbar;
    public MyMenuBar menu;
    public ArrayList<Image> imagenes = new ArrayList<>();
    private ArrayList<Integer> copyAreaX;
    private ArrayList<Integer> copyAreaY;
    public boolean a;
    private Boolean b = true;
    public CollageCanvas canvas;
    public MyCollageToolbar collageToolbar;
    public int listSelectedItem;
    private Image image;
    private JScrollPane scroll;
    private Point puntoInicial;
    private Point puntoFinal;
    //-------------------------------------Start Parametros copias clase JPicturePanel

    private ArrayList<Element> elements;
    private Line auxLine;
    private Circle circulo;
    private Rectangle rectangulo;
    //private ArrayList<Rectangle> eraser;
    private int dibujar;
    private Color colorActual;
    private String thin;
    private boolean brush;
    private boolean move;
    private String optionsLine;
    private String styleLine;

    private String conectorLine;
    private Image imageAUX;
    private MyText text = null;
    private Cursor customCursor;
    //-------------------------------------End Parametros copias clase JPicturePanel
    private boolean filled;
    private Clipboard portapapeles;
    private boolean selec;
    private int countImages;
    
    private int Pos_Marca_new_X;
    private int Pos_Marca_new_Y;
    private int Pos_Marca_X;
    private int Pos_Marca_Y;
    private int Dist_X;
    private int Dist_Y;
    private float x1;
    private float y1;
    private float ancho;
    private float alto;
    private boolean rotarAll;

        //-------------------------------------End Parametros copias clase JPicturePanel
    public CollagePanel(Dimension dim, MyToolBar toolBar, MyMenuBar menu, MyCollageToolbar collageToolBar)

    {
        super();
        this.rotarAll = false;
        this.countImages = 0;
        this.copyAreaX = new ArrayList<Integer>();
        this.copyAreaY = new ArrayList<Integer>();
        this.filled = false;
        super.setSize(900, 600);
        super.setBackground(Color.DARK_GRAY);
        super.setLayout(null);
        this.canvas = new CollageCanvas(dim);
        this.setPreferredSize(dim);
        this.add(canvas, BorderLayout.CENTER);
        
        //addCanvas();
        this.isMoving = false;
        
        this.isDeliting = false;
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(this);
        this.toolbar = toolBar;
        this.menu = menu;
        this.undoManager = new UndoManager();
        this.a = false;
        this.collageToolbar = collageToolBar;
        this.listSelectedItem = -1;
        this.collageToolbar.getlist().addListSelectionListener(this);

        //-------------------------------------------Start Parametros JPicturePanel
        this.elements = new ArrayList<>();
        this.auxLine = new Line();
        this.circulo = new Circle();
        this.rectangulo = new Rectangle();
        this.dibujar = 0;
        this.thin = CommandNames.GROSOR_1PX;
        this.colorActual = Color.BLACK;
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.conectorLine = CommandNames.ICON_LineaNormal;
        //-------------------------------------------End Parametros JPicturePanel
        //----------------------------------------------------------------
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point cursorHotSpot = new Point(0, 0);
        customCursor = toolkit.createCustomCursor(new ImageIcon(CommandNames.ICON_ROTATE_CURSOR).getImage(), cursorHotSpot, "Cursor");
        
    }

    public int randomLocation()
    {
        return (0 + (int) (Math.random() * ((super.getSize().width - 0) + 1))) / 4;
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

    public void obtenerDatosPortapapeles()
    {
        portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable datos = portapapeles.getContents(null);
        if (datos.isDataFlavorSupported(DataFlavor.imageFlavor))
        {
            try
            {
                countImages++;
                this.drawImage((Image) datos.getTransferData(DataFlavor.imageFlavor), "Imagen"+ countImages);
                repaint();

            } catch (Exception ex)
            {

            }
        }
        canvas.repaint();
        repaint();
    }
    
     public void cambiaTamano(int ancho, int alto)
    {
        canvas.imax.image = canvas.imax.image.getScaledInstance(ancho, alto, Image.SCALE_REPLICATE);
       this.canvas.repaint();
    }
    public void copiarAlPortapapeles()
    {
        if (!copyAreaX.isEmpty())
        {
            canvas.resetPoints();
            int[] x = new int[copyAreaX.size()];
            int[] y = new int[copyAreaX.size()];
            ArrayCopy(copyAreaX.toArray(), x);
            ArrayCopy(copyAreaY.toArray(), y);
            int wid = FindDistancia(x);
            int hei = FindDistancia(y);
            int min = FindMin(x);
            int minY = FindMin(y);
            Dimension pSize = canvas.getPreferredSize();
            BufferedImage bi = new BufferedImage((int) pSize.getWidth(), (int) pSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            g.clip(new Polygon(x, y, x.length));
            copyAreaX = new ArrayList<>();
            copyAreaY = new ArrayList<>();
            canvas.resetPoints();
            canvas.paint(g);

            try
            {
                bi = recortar(bi, min, minY, wid, hei);
                portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
                ImageSelection ima = new ImageSelection(bi);
                portapapeles.setContents(ima, null);
            } catch (Exception ex)
            {

            }
        }
        copyAreaX = new ArrayList<>();
        copyAreaY = new ArrayList<>();
        canvas.resetPoints();
        canvas.repaint();
        repaint();
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

    public BufferedImage recortar(BufferedImage bi, int x1, int y1, int ancho, int alto)
    {
        BufferedImage tmp = bi.getSubimage((int) x1, (int) y1, (int) ancho, (int) alto);
        return tmp;
    }

    public void drawImage(Image image, String name)
    {
        ImageCollage pic = new ImageCollage(image, name);
        pic.setLocation(0, 0);
        canvas.addImage(pic);
        checkFirstOneList();
        collageToolbar.addLabel(name);
        this.canvas.repaint();
        collageToolbar.setImagen(canvas.getAll());
        this.validate();
        this.updateUI();
        this.repaint();

    }

    public void delete()
    {
        if ( isDeliting && listSelectedItem!= -1 )
        {   
            
            if(!canvas.imageList.isEmpty() && collageToolbar.isPicture(listSelectedItem))

            {
                canvas.deletePicture();
                collageToolbar.delLabel ( listSelectedItem );
                canvas.repaint();
                collageToolbar.setImagen(canvas.getAll());
                checkFirstOneList ();
                isDeliting = false;
            }
            if(!canvas.textos.isEmpty() && !collageToolbar.isPicture(listSelectedItem))
            {   canvas.deleteText();
                collageToolbar.delLabel ( listSelectedItem );
                canvas.repaint();
                collageToolbar.setImagen(canvas.getAll());
                checkFirstOneList ();
                isDeliting = false;
            }
            if(!canvas.imageList.isEmpty())

            {
                canvas.deletePicture();
                collageToolbar.delLabel ( listSelectedItem );
                canvas.repaint();
                collageToolbar.setImagen(canvas.getAll());
                checkFirstOneList ();
                isDeliting = false;
            }
             
        }

        this.canvas.repaint();
        this.repaint();
    }
    
    public void enviarAlFrente()
    {
        if ( listSelectedItem!= -1 )
        { 
            if(!canvas.textos.isEmpty() && !collageToolbar.isPicture(listSelectedItem))
            {
                JLabel textAux = canvas.textAux.getLabel();
                String name = canvas.imax.name;
                canvas.deleteText();
                collageToolbar.delLabel ( listSelectedItem );
                collageToolbar.setImagen(canvas.getAll());
                checkFirstOneList ();
                setText(textAux);
                repaint();
            }
            else{
                Image imagenAux = canvas.imax.getImage();
                String name = canvas.imax.name;
                canvas.deletePicture();
                collageToolbar.delLabel ( listSelectedItem );
                collageToolbar.setImagen(canvas.getAll());
                checkFirstOneList ();
                drawImage(imagenAux, name);
                repaint();
            }
        }
        repaint();
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

    public void saveTo(File selectedFile)
    {
        try
        {
            BufferedImage bi = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
           
            canvas.paint(g);
            
            ImageIO.write(bi, getExtension(selectedFile.getPath()).toUpperCase(), selectedFile);
            //JOptionPane.showMessageDialog(this, CommandNames.CAPTION_GUARDADO, CommandNames.CAPTION_TITLE, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex)
        {
            //JOptionPane.showMessageDialog(this, CommandNames.CAPTION_ERROR, CommandNames.CAPTION_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    public BufferedImage getImage()
    {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        return image;
    }

    public void checkFirstOneList()
    {
        if (collageToolbar.getListMode().isEmpty())

        {
            collageToolbar.getListMode().addElement("none");
        } 
        else
        {
            if (collageToolbar.getListMode().getElementAt(0).toString().equals("none"))
            {
                collageToolbar.delLabel(0);
            }
        }

        this.canvas.repaint();
        this.updateUI();
        
        this.repaint();

    }

    public void addCanvas()
    {
        Insets insets = super.getInsets();
        Dimension size = canvas.getPreferredSize();
        canvas.setBounds(0 + insets.left, 2 + insets.top,
                size.width, size.height);
        this.canvas.repaint();
    }

    public void canvasSetSize(Dimension size)
    {
        canvas.setSize(size);
        this.setPreferredSize(size);
        //addCanvas();
        this.updateUI();
        this.repaint();

    }

    public BufferedImage convertImageToBufferredImage(Image ig)
    {
        BufferedImage imgsel = new BufferedImage(ig.getWidth(null), ig.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = imgsel.createGraphics();
        bGr.drawImage(ig, 0, 0, ig.getWidth(null), ig.getHeight(null), rowHeader);
        bGr.dispose();
        return imgsel;
    }

     
    public Point imageCenter()
    {
        int x= canvas.imax.location.x + (canvas.imax.getWidth()/2);
        int y= canvas.imax.location.y + (canvas.imax.getHeight()/2);
        return new Point(x,y);
    }
   
    
    public void girarUno(int grados,ImageCollage image)
    {
         if(image!=null)
       { BufferedImage aux = convertImageToBufferredImage(image.original);
        
         
        double pcx = image.getWidth() / 2;
        double pcy = image.getHeight() / 2;
        
        int numColumnas =image.getWidth();
        int numFilas = image.getHeight();
        int [] ex = new int[4];
        int [] ey = new int[4];
        int [] exp = new int[4];
        int [] eyp = new int[4];
        double angulo = Math.toRadians(grados);
        double ct = Math.cos(angulo);
        double st = Math.sin(angulo);
        double xp, yp;
        double xr, yr;
        
        //asignamos valores a las esquinas de la imagen
        ex[0] = 0;            ey[0] = 0;
        ex[1] = numColumnas;  ey[1] = 0;
        ex[2] = numColumnas;  ey[2] = numFilas;
        ex[3] = 0;            ey[3] = numFilas;
        
        for( int i = 0; i < 4 ; i++ ){
            // trasladando esquinas al punto caliente
            xp = ex[i] - pcx;
            yp = ey[i] - pcy;
            
            // rotando esquinas
            xr = (xp * ct) - (yp * st);
            yr = (yp * ct) + (xp * st);
            
            // trasladando esquinas rotadas
            exp[i] = (int)(xr + pcx);
            eyp[i] = (int)(yr + pcy);             
        }
        
        // Calculando tamaño de la imagen rotada
        int xmin = exp[0];
        int xmax = xmin;
        int ymin = eyp[0];
        int ymax = ymin;
        
        for( int i = 1; i < 4; i++ ){
            xmin = Math.min(xmin, exp[i]);
            xmax = Math.max(xmax, exp[i]);
            ymin = Math.min(ymin, eyp[i]);
            ymax = Math.max(ymax, eyp[i]);
        
        }
        
        int widthFinal = xmax - xmin;
        int heightFinal = ymax - ymin;

        BufferedImage bufferRotado = new BufferedImage(widthFinal, heightFinal, BufferedImage.TYPE_INT_ARGB);
        int x0 = (int)((widthFinal/2) - pcx);
        int y0 = (int)((heightFinal/2)-pcy);

        Graphics g = bufferRotado.createGraphics();
        g.drawImage(image.image, x0, y0, null);
        g.dispose();
        int xcentro = widthFinal/2;
        int ycentro = heightFinal/2;
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(angulo, xcentro, ycentro); // ¡rotando!
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        aux = op.filter(bufferRotado, null);
        image.image=aux.getSubimage(0, 0, widthFinal, heightFinal);
        Point location = image.getLocation();
        
        int hip=(int) (1/Math.cos(grados))*(image.getHeight()) ;
        
        image.setLocation( location.x - (int)Math.sin(hip), (location.y -(int)Math.cos(hip)));
        this.validate();
        this.updateUI();
        this.repaint();
       }
    }

    public void girar(int grados)
    { 
        System.out.println("rotateAll "+canvas.rotateAll);  
      if(canvas.rotateAll)
          {
              for(ImageCollage imageCollage:canvas.imageList)
              {
                  girarUno(grados,imageCollage);
              }
          }
      if(!canvas.rotateAll)
      {
          girarUno(grados,canvas.imax);
      }
    }

    public void actualizarImagen(Image image)
    {

        if (a == false)
        {
            this.imageAux = image;
            this.imagenes.add(this.imageAux);

            undoManager.undoableEditHappened(new UndoableEditEvent(this, new CollagePanel.UndoablePaintSquare(this.imageAux, imagenes)));
            setupUndoHotkeys();
            this.toolbar.deshacer.setEnabled(undoManager.canUndo());
            this.toolbar.rehacer.setEnabled(undoManager.canRedo());
            this.menu.deshacer.setEnabled(undoManager.canUndo());
            this.menu.rehacer.setEnabled(undoManager.canRedo());
            repaint();
        } else
        {
            this.imageAux = image;
        }
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        super.setPreferredSize(new Dimension(width, height));
        super.validate();

        super.repaint();
    }

    //----------------------------------------------------------- Start Metodos Clsse JPicturePanel----------------------
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
            default:
                this.colorActual = Color.BLACK;
                break;
        }
    }

    public void setAuxLine()
    {
        this.auxLine = new Line();

        super.repaint();

    }

    public void setElements()
    {
        this.elements = new ArrayList<>();
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
        this.filled = false;
        this.thin = CommandNames.GROSOR_1PX;
        this.selectionCursor(dibujar);
        this.copyAreaX = new ArrayList<>();
        this.copyAreaY = new ArrayList<>();
        canvas.resetPoints();
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
                super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            case 6:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case 7:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
            default:
                super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
        }
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

    public void setConectorLine(String conectorLine)
    {
        this.conectorLine = conectorLine;
    }

    public MyText getText()
    {
        return text;
    }

    public void setText(JLabel text)
    {
        canvas.addText(new MyText(text, canvas.getWidth()));
        collageToolbar.addLabel(new MyText(text, canvas.getWidth()).getLabel().getText());
        collageToolbar.setImagen(canvas.getAll());
        canvas.repaint();
    }

    public void setText()
    {
        this.text = null;
    }

    public boolean  canvasEmpty()
    {
        return (canvas.imageList.isEmpty() && canvas.textos.isEmpty() && canvas.elements.isEmpty());
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
    
    public void presionarDentroImagen(int posicionXMouse, int posicionYMouse)

    {

        Dist_X = posicionXMouse - Pos_Marca_X;
        Dist_Y = posicionYMouse - Pos_Marca_Y;
        Pos_Marca_X = Pos_Marca_X + Dist_X;
        Pos_Marca_Y = Pos_Marca_Y + Dist_Y;
        //se coloca la nueva posicion
        x1 = x1 + Dist_X;
        y1 = y1 + Dist_Y;
                
    }
    
   //----------------------------------------------------------- End Metodos Clsse JPicturePanel----------------------
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (isMoving)
        {

            if (canvas.getImax() != null && canvas.insidePicture(e.getPoint()))
            {
                Pos_Marca_new_X = e.getX();
                Pos_Marca_new_Y = e.getY();
                x1 = canvas.imax.getLocation().x;
                y1 = canvas.imax.getLocation().y;
                ancho = (float) (canvas.imax.getWidth());
                alto = (float) (canvas.imax.getHeight());
                presionarDentroImagen(Pos_Marca_new_X, Pos_Marca_new_Y);
                this.canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                canvas.moveImage(new Point((int)x1,(int)y1));

            }

            if (canvas.getTextAux() != null  && canvas.insideText(e.getPoint()))
            {
                Pos_Marca_new_X = e.getX();
                Pos_Marca_new_Y = e.getY();
                x1 = canvas.textAux.getPosX();
                y1 = canvas.textAux.getPosY();
                ancho = canvas.textAux.getLabel().getWidth();
                alto = canvas.textAux.getLabel().getHeight();
                presionarDentroImagen(Pos_Marca_new_X, Pos_Marca_new_Y);
                this.canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                canvas.moveText(new Point((int) x1, (int) y1));

            }
            repaint();
            canvas.repaint();

        }
        if (!isMoving )
        {
            if (this.dibujar == 1)
            {

                {
                    this.auxLine.add(e.getPoint());
                    this.auxLine.setColor(this.colorActual);
                }
                repaint();
                canvas.repaint();
            }
            if (this.dibujar == 2)
            {

                {
                    this.circulo.setPointFinal(e.getPoint());
                    this.circulo.setColor(this.colorActual);
                    this.circulo.setThin(this.thin);
                    this.circulo.setFill(filled);
                }
                repaint();
                canvas.repaint();
            }
            if (this.dibujar == 3)
            {

                {
                    this.rectangulo.setPointFinal(e.getPoint());
                    this.rectangulo.setFill(false);
                    this.rectangulo.setFilled(filled);
                    this.rectangulo.setColor(this.colorActual);
                    this.rectangulo.setThin(this.thin);
                }
                repaint();
                canvas.repaint();
            }
            if (this.dibujar == 4)
            {

                {
                    this.rectangulo = new Rectangle();
                    this.rectangulo.setFill(true);
                    this.rectangulo.setThin(this.thin);
                    this.rectangulo.setPointInicio(e.getPoint());
                    this.elements.add(this.rectangulo);
                }
                repaint();
                canvas.repaint();
            }
            if (this.dibujar == 5)
            {
                try
                {
                    this.text.move(e.getX(), e.getY());
                } catch (Exception ex)
                {
                }
                repaint();
            }
            if (this.dibujar == 7)
            
            {

                {
                    this.auxLine.setPointFinal(e.getPoint());
                    this.auxLine.setColor(colorActual);
                    this.auxLine.setThin(thin);
                }
                repaint();
                canvas.repaint();
            }
            if (this.dibujar == 10)
            {
                if (copyAreaX.size() > 2)
                {
                    if (e.getX() > copyAreaX.get(0) - 2 && e.getX() < copyAreaX.get(0) + 2
                            && e.getY() > copyAreaY.get(0) - 2 && e.getY() < copyAreaY.get(0) + 2)
                    {
                        selec = false;
                        copyAreaX.add(copyAreaX.get(0));
                        copyAreaY.add(copyAreaY.get(0));
                        canvas.addPointX(copyAreaX.get(0));
                        canvas.addPointY(copyAreaY.get(0));
                    }
                }
                if (selec)
                {
                    copyAreaX.add( e.getX());
                    copyAreaY.add( e.getY());
                    canvas.addPointX(e.getX());
                    canvas.addPointY(e.getY());
                }

                repaint();
                canvas.repaint();
            }

        }
        collageToolbar.setImagen(canvas.getAll());
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
       
        collageToolbar.setImagen(canvas.getAll());
        canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

       
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
        collageToolbar.setImagen(canvas.getAll());

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

        if (!isMoving )
        {
            if (this.dibujar == 1)
            {
                this.auxLine = new Line();
                this.auxLine.add(e.getPoint());
                this.auxLine.setColor(this.colorActual);
                this.auxLine.setThin(this.thin);
                if (brush)
                {
                    this.auxLine.setThin(CommandNames.GROSOR_1PX);
                }
                this.auxLine.setBrush(this.brush);
                this.canvas.addElement(this.auxLine);
                canvas.repaint();
                repaint();
            }
            if (this.dibujar == 2)
            {
                this.circulo = new Circle();
                this.circulo.setPointInicial(e.getPoint());
                this.circulo.setPointFinal(e.getPoint());
                this.circulo.setColor(this.colorActual);
                this.circulo.setThin(this.thin);
                this.canvas.addElement(this.circulo);
                canvas.repaint();
                repaint();
            }
            if (this.dibujar == 3)
            {
                this.rectangulo = new Rectangle();
                this.rectangulo.setFill(false);
                this.rectangulo.setPointInicio(e.getPoint());
                this.rectangulo.setPointFinal(e.getPoint());
                this.rectangulo.setColor(this.colorActual);
                this.rectangulo.setThin(this.thin);
                this.canvas.addElement(this.rectangulo);
                canvas.repaint();
                repaint();
            }
            if (this.dibujar == 4)
            {
                this.rectangulo = new Rectangle();
                this.rectangulo.setFill(true);
                this.rectangulo.setThin(this.thin);
                this.rectangulo.setPointInicio(e.getPoint());
                this.canvas.addElement(this.rectangulo);
                canvas.repaint();
                repaint();
            }
            
            if (this.dibujar == 7)
            {
                this.auxLine = new Line(true, optionsLine, styleLine, conectorLine);
                this.auxLine.setPointInicio(e.getPoint());
                this.auxLine.setPointFinal(e.getPoint());
                this.auxLine.setColor(this.colorActual);
                this.auxLine.setThin(this.thin);
                this.canvas.addElement(this.auxLine);
                canvas.repaint();
                repaint();
            }
            if (this.dibujar == 10)
            {

                if (copyAreaY.size() > 1)
                {
                    this.selec = true;
                    copyAreaX = new ArrayList<>();
                    copyAreaY = new ArrayList<>();
                    canvas.resetPoints();
                    copyAreaX.add(e.getX());
                    copyAreaY.add(e.getY());
                    canvas.addPointX(e.getX());
                    canvas.addPointY(e.getY());
                    
                }
                else
                {
                    this.selec = true;
                    copyAreaX = new ArrayList<>();
                    copyAreaY = new ArrayList<>();
                    copyAreaX.add(e.getX());
                    copyAreaY.add(e.getY()); 
                    canvas.resetPoints();
                    canvas.addPointX(e.getX());
                    canvas.addPointY(e.getY());
                }

                canvas.repaint();
                repaint();
            }
        }
        if (isMoving)
        {
            Pos_Marca_X = e.getX();
            Pos_Marca_Y = e.getY();
        }
        collageToolbar.setImagen(canvas.getAll());
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

        this.canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        if (!canvas.getElements().isEmpty())
        {
            countImages++;
            Dimension pSize = canvas.getSize();
            BufferedImage bi = new BufferedImage(pSize.width, pSize.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D cG = bi.createGraphics();
            ArrayList<Element> elements1 = canvas.getElements();
            for (Element ele: elements1){
                ele.pintar(cG);
            }
            cG.dispose();
            canvas.setElements(new ArrayList<Element>());
            this.elements = new ArrayList<>();
            this.drawImage(bi, "Dibujo"+countImages);
        }
        
        canvas.repaint();
        repaint();
        collageToolbar.setImagen( canvas.getAll());
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public void paint(Graphics g)
    {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setColor(new Color(0, 191, 255, 255));
        for (Element ele: elements)
        {
            ele.pintar(g2);
        }
        if (!copyAreaX.isEmpty())
        {
            float[] dash
                    =
                    {
                        15
                    };

            if (copyAreaX.size() == 1)
            {
                Point point = new Point(copyAreaX.get(0), copyAreaY.get(0));
                g2.drawLine(point.x, point.y, point.x, point.y);
            } else
            {
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
////////////////////////////////////////////////////////////////////////////////////

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
                toolbar.deshacer.setEnabled(undoManager.canUndo());
                toolbar.rehacer.setEnabled(undoManager.canRedo());
                menu.deshacer.setEnabled(undoManager.canUndo());
                menu.rehacer.setEnabled(undoManager.canRedo());
                repaint();
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

                toolbar.deshacer.setEnabled(undoManager.canUndo());
                toolbar.rehacer.setEnabled(undoManager.canRedo());
                menu.deshacer.setEnabled(undoManager.canUndo());
                menu.rehacer.setEnabled(undoManager.canRedo());
                repaint();
            }
        };

        //getActionMap().put(UNDO, undoAction);
        //getActionMap().put(REDO, redoAction);
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

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        listSelectedItem = collageToolbar.getlist().getSelectedIndex();
        canvas.imax = null;
        canvas.textAux = null;

        if (e.getValueIsAdjusting() == false && listSelectedItem != -1)

        {
            canvas.getSelectImage(collageToolbar.getlist().getSelectedValue().toString());
            canvas.getSelectedText(collageToolbar.getlist().getSelectedValue().toString());

        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFill(boolean b)
    {
        this.filled = b;
    }

    public void setRotarTodo(boolean b)
    {
        this.canvas.rotateAll= b;
    }
    
    class UndoablePaintSquare extends AbstractUndoableEdit
    {

        protected ArrayList<Image> imagenes;
        protected Image imagen;

        public UndoablePaintSquare(Image image, ArrayList<Image> v)
        {
            imagenes = v;
            imagen = image;
        }

        public void undo()
        {
            super.undo();
            imagenes.remove(imagen);

        }

        public void redo()
        {
            super.redo();
            imagenes.add(imagen);
        }

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
