/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author SergioElÃ­as
 */
public class ImageTransform 
{
    private AffineTransform at;
    private int alturaImagen;
    private int anchoImagen;
    private double grados;
 
    public ImageTransform(int alturaImagen, int anchuraImagen) {
        at = new AffineTransform();
        this.alturaImagen = alturaImagen;
        this.anchoImagen = anchuraImagen;
    }
 
    public AffineTransform getTransform (){
        return at;
    }
    public void rotate (double grados)
    {
        this.grados = grados;
        //at.rotate(Math.toRadians(grados), anchoImagen/2.0, alturaImagen/2.0);
        at.rotate(Math.toRadians(grados), 0, 0);
    }
    //metodo collage, para respetar el centro de la imagen
     public void rotate (double grados, Point centro)
    {
        this.grados = grados;
        //at.rotate(Math.toRadians(grados), anchoImagen/2.0, alturaImagen/2.0);
        at.rotate(Math.toRadians(grados), centro.x, centro.y);
    }
    
    public void findTranslation ()
    {
        Point2D p2din, p2dout;
        p2din = hallarPtoATraslacion();
        p2dout = at.transform(p2din, null);
        double ytrans = p2dout.getY();

        p2din = hallarPtoBTraslacion();
        p2dout = at.transform(p2din, null);
        double xtrans = p2dout.getX();
        AffineTransform tat = new AffineTransform();

        tat.translate(-xtrans, -ytrans);
        at.preConcatenate(tat);
    }
 
    private Point2D hallarPtoATraslacion (){
        Point2D p2din;
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, 0.0);
        }else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(0.0, alturaImagen);
        }else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        }else{
            p2din = new Point2D.Double(anchoImagen, 0.0);
        }
        return p2din;
    }

    private Point2D hallarPtoBTraslacion (){
        Point2D p2din;
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, alturaImagen);
        }else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        }else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, 0.0);
        }else{
            p2din = new Point2D.Double(0.0, 0.0);
        }
        return p2din;
    }
    
    public static BufferedImage rotacionImagen(BufferedImage origen, double grados)
    {
        BufferedImage destinationImage;
        ImageTransform imTransform = new ImageTransform(origen.getHeight(), origen.getWidth());
        imTransform.rotate(grados);
        imTransform.findTranslation();
        AffineTransformOp ato = new AffineTransformOp(imTransform.getTransform(), AffineTransformOp.TYPE_BILINEAR);
        destinationImage = ato.createCompatibleDestImage(origen, origen.getColorModel());
        return ato.filter(origen, destinationImage);
    }
    
    //Metodo para imagen collage, que recibe el centro de la imagen, para mantener el centro
     public static BufferedImage rotacionImagen(BufferedImage bufferRotado, double angulo,int xcentro ,int ycentro)
    {
        /*BufferedImage destinationImage;
        ImageTransform imTransform = new ImageTransform(origen.getHeight(), origen.getWidth());
        imTransform.rotate(grados);
        imTransform.findTranslation();
        AffineTransformOp ato = new AffineTransformOp(imTransform.getTransform(), AffineTransformOp.TYPE_BILINEAR);
        destinationImage = ato.createCompatibleDestImage(origen, origen.getColorModel());
        return ato.filter(origen, destinationImage);*/
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(angulo, xcentro, ycentro); // ¡rotando!
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        return op.filter(bufferRotado, null);
    }
}