package proyectoprogra;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.SplashScreen;

public final class ScreenSplash {

  final SplashScreen splash  ;
  //texto que se muestra a medida que se va cargando el screensplah
  final String[] texto = {"Frame" ,"Configuration", "Library",
                          "Marcos Files","Forms","Iconos","Properties",
                          "Collage Patrons", "Filters", "Anonymous",
                          "Database" ,"Server","Sergio - Matias - Gonzalo",
                          ""};

  public ScreenSplash() 
  {
      splash = SplashScreen.getSplashScreen();
  }

   public void animar()
   {
        if (splash != null)
        {
            new JMp3 ( CommandNames.sound_opening );
            Graphics2D g = splash.createGraphics();
            for(int i=1; i<texto.length; i++)
            {                       
                //se pinta texto del array
                g.setColor( new Color(4,52,101));//color de fondo
                g.fillRect(203, 258,305,12);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.drawString("Loading "+texto[i-1]+"...", 203, 268);                
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(204, 238,(i*307/texto.length), 12);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {2.0f};
                BasicStroke dashed = new BasicStroke(9.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash1, 0.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor( new Color(4,52,101));
                g.drawLine(205,244, 508, 244);                
                //se actualiza todo
                splash.update();
                try 
                {
                    Thread.sleep(521);
                } 
                catch(Exception e) { }
            }
            splash.close();
        }
        //una vez terminada la animación se muestra la aplicación principal
         try 
         {
            //Ventana Principal
             MainWindow ventana = new MainWindow ();
             ventana.setVisible ( true );
         }
        catch (Exception e) {
            //System.out.println(e.getMessage());
        }
   }

}