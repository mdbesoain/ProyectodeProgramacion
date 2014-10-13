package myObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
 
public   class ZoomImagen extends JPanel{

   
 
    private static  Image FOTO_ORIGINAL;
    private static Image FOTO_tmp;
    private static BufferedImage Imagen_en_memoria;
    private static Graphics2D g2D;
    private static boolean con_foto = false;   
 
    private static int valEscalaX=0;
    private static int valEscalaY=0;
    private static ZoomImagen zoom;
 
    
    /* al crear el objeto se crea con una imagen pasada como parametro*/
    public ZoomImagen()
    {
    }
    
    void instancear(BufferedImage f, int anchoPanel, int altoPanel)
    {
        setSize(anchoPanel, altoPanel);
        FOTO_ORIGINAL = f;
        FOTO_tmp = f;
        con_foto=true;
        
    }
 
 
    BufferedImage getBufferedImage(Image f )
    {
        return new BufferedImage(f.getWidth(null), f.getHeight(null), BufferedImage.TYPE_INT_RGB);
    }
     
    Image aumentar(int Valor_Zoom,Image f, int anchoPanel, int altoPanel)
    {   
       
        instancear(getBufferedImage( f ),anchoPanel,altoPanel);
        
        //se calcula el incremento
        valEscalaX =  (int) (FOTO_tmp.getWidth(this) * escala(Valor_Zoom) );
        valEscalaY =  (int) (FOTO_tmp.getHeight(this) * escala(Valor_Zoom) );
        //se escala la imagen sumado el nuevo incremento
        this.FOTO_tmp = FOTO_tmp.getScaledInstance((int) (FOTO_tmp.getWidth(this) + valEscalaX), (int) (FOTO_tmp.getHeight(this) + valEscalaY), Image.SCALE_AREA_AVERAGING);
        resize();
        return this.FOTO_tmp;
    }
 
      Image disminuir(int Valor_Zoom,Image f, int anchoPanel, int altoPanel)
    {
        instancear(getBufferedImage( f ),anchoPanel,altoPanel);
        valEscalaX =  (int) (FOTO_tmp.getWidth(this) * escala(Valor_Zoom) );
        valEscalaY =  (int) (FOTO_tmp.getHeight(this) * escala(Valor_Zoom) );
        this.FOTO_tmp = FOTO_tmp.getScaledInstance((int) (FOTO_tmp.getWidth(this) - valEscalaX), (int) (FOTO_tmp.getHeight(this) - valEscalaY), Image.SCALE_AREA_AVERAGING);
 
        resize();
        return this.FOTO_tmp;
     }
 
     float escala(int v){
        return  v/100f;
    }
 
     void restaurar(){
        this.FOTO_tmp = this.FOTO_ORIGINAL;
        resize();
    }
 
     void resize(){
        setSize(FOTO_tmp.getWidth(this),FOTO_tmp.getHeight(this));
    }
    
}
