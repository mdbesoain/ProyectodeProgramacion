/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Gloria
 */
public class JPrinter implements Printable{

    public PrinterJob pj;
    public PageFormat pf;
    public ImageObserver imageObserver;
    public Image image;
    public PrintRequestAttributeSet aset;
    public int x;
    public int y;
    

    public void JPrinter(  )
            
    {   aset = new HashPrintRequestAttributeSet();
        pj = PrinterJob.getPrinterJob ();
        setPrintableServices();
        this.pf = pj.pageDialog(aset);

    }

    
    public void setPrintableServices()
    {
        PrintService[] services = PrinterJob.lookupPrintServices ();
        for( PrintService service : services)
        {
            try
            {
                this.pj.setPrintService ( service );
            } catch ( PrinterException ex )
            {
                Logger.getLogger ( JPrinter.class.getName() ).log ( Level.SEVERE , null , ex );
            }
        }
    }
    public void pageSetup ()
    {
        pf = pj.pageDialog ( pj.defaultPage () );
    }
    
    public void setValues(Image image, int x , int y )
    {
        this.image=image;
        this.x=x;
        this.y=y;
    }
   
    @Override
    public int print ( Graphics g, PageFormat pf , int page) throws PrinterException
    {
       
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(this.image != null)
        {
            g2d.drawImage (this.image, 0 ,  0, null);
        }
        
        return PAGE_EXISTS;
    }
    
  
}
