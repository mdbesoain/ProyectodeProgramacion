/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import com.drew.imaging.ImageMetadataReader;

import com.drew.imaging.ImageProcessingException;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gloria
 */
public class MyExif {
    public Metadata metadata;
    public ExifSubIFDDirectory directory;
    public Date date;
    public DateFormat df;
    public String ext;
        
    public MyExif(String path)
    {   this.ext=ext;
        File file = new File(path);
        
        try
        {
             this.metadata = ImageMetadataReader.readMetadata(file);
             
        } catch ( ImageProcessingException ex )
        {
            
        } catch ( IOException ex )
        {
            
        }
        
       if(metadata != null)
       {
        
        this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
        if(directory!=null)
        {  date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            if(date!=null)
            {   df = DateFormat.getDateInstance ();
                df.format ( date );
            }
        }
       }
    }
    
    public  String getMonth()
    {   if(date != null)
        {return Integer.toString ( df.getCalendar().get( Calendar.MONTH));}
        return null;
    }
    
    public  String getYear()
    {   
        if(date != null)
        {return Integer.toString ( df.getCalendar().get( Calendar.YEAR ));}
        return null;
    }
    
    public Iterator getIterator()
    {
        if(directory==null)
        {
            return null;
        }
        return directory.getTags ().iterator ();
       
    }
            
        
    
}
