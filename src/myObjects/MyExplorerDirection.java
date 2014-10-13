/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Gloria
 */
public  class  MyExplorerDirection {
    
    class Pila
     {
         private String[] arreglo;
         private int tope;
         private int MAX_ELEM=20; //
         
         public Pila()
         {
             arreglo = new String[MAX_ELEM];
             tope=-1;
         }
         
         public void apilar(String x)
         {
             if(tope+1<MAX_ELEM)
             {
                 tope++;
                 arreglo[tope]=x;
             }
         }
         
         public String desapilar()
         {
             if(!estaVacia())
             {
                 String x=arreglo[tope];
                 tope--;
                 return x;
             }
             
           return null;
         }
         public String tope()
         {
             if(!estaVacia())
             {
                 String x= arreglo[tope];
                 return x;
             }
           return null;
         }
         
         public boolean estaVacia()
         {
             if(tope ==-1)
             {
                 return true;
             }
            return false;
         }
         
        
    
     }
    
    public static Pila forward;
    public static Pila back;
    
    public MyExplorerDirection()
    {
        forward = new  Pila();
        back = new  Pila();
        back.apilar ( System.getProperty ("user.home"));
    }
    
    public String getBack()
    {   if(!back.estaVacia ())
        {String string =back.desapilar ();
        forward.apilar (string);
        return string ;
        }
       return null;
    }
    public boolean goBack(String path)
    {   /*if(!path.equals ( System.getProperty ("user.home")) && !path.equals ( System.getProperty ("C:\\")) 
            && !path.equals ( System.getProperty ("D:\\"))
            )
        {*/
        boolean opcion= false;
        if(isApilable ( back , path ))
        {this.back.apilar (path);
         opcion=true;   
        }
        return opcion;
    }
    public String getForward()
    {
        String string =forward.desapilar ();
        back.apilar (string);
        return string ;
    }
    
    public boolean goForward(String path)
    {   
        boolean opcion= false;
        if(isApilable ( forward, path))
        {this.forward.apilar (path);
         opcion=true;   
        }
        return opcion;
    }
    
    public boolean hasForward()
    {
        return !this.forward.estaVacia ();
    }
    public boolean hasBack()
    {
        return !this.back.estaVacia ();
    }
    
    public boolean isApilable(Pila pila, String apilando)
    {   
        if(!pila.estaVacia ())
        {String desapilado= pila.desapilar ();
        pila.apilar ( desapilado );
        if(desapilado.equals ( apilando))
        {
            return false;
        }
        }
        return true;
    }
     public void obtenerRuta (DefaultMutableTreeNode p)
    {
        
        String ruta = "";
        for (int i = 0; i < p.getPath ().length - 1; i ++)
        {
            ruta += p.getPath ()[i + 1];
            if (ruta.charAt (ruta.length () - 1) != '/')
            {
                ruta += "/";
            }
        }
        goBack(ruta);
    }
     
    
     
     
     
}
