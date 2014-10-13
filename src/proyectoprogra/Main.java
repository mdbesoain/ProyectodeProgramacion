/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogra;

import javax.swing.UIManager;

/**
 * @author Matías Días
 * @author Sergio Flores
 * @author Gonzalo Mardones
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main ( String[] args )
    {
        // YEAH YA CRAKEE LA LLAVE Y ME DIO ESTO :
        // C1410294-61B64AAC-4B7D3039-834A82A1-37E5D695
        String[] li =
        {
            "Licensee=AppWork UG" , "LicenseRegistrationNumber=289416475" , "Product=Synthetica" , "LicenseType=Small Business License" , "ExpireDate=--.--.----" , "MaxVersion=2.999.999"
        };
        UIManager.put ( "Synthetica.license.info" , li );
        UIManager.put ( "Synthetica.license.key" , "C1410294-61B64AAC-4B7D3039-834A82A1-37E5D695" );
        try
        {
            UIManager.setLookAndFeel ( "de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel" );
        }
        catch ( Exception e )
        {
        }

        //Bienvenida
        new ScreenSplash().animar();
        /*VentanaBienvenida ventanaBienvenida = new VentanaBienvenida ();
        ventanaBienvenida.setVisible ( true );
        ventanaBienvenida.iterate ();
        ventanaBienvenida.dispose ();*/
        
    }
}
