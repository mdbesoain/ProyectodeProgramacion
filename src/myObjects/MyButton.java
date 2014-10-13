/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myObjects;

import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import proyectoprogra.CommandNames;

/**
 *
 * @author SergioElías
 */
public class MyButton extends Button
{
   public MyButton()
   {
       super();
   }

    MyButton(String name, Color color) {
        super(name);
        this.setBackground(color);
    }
    
    MyButton(String name) {
        super(name);
    }
    
}
