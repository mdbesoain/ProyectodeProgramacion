package proyectoprogra;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;

import javazoom.jl.player.Player;


public class JMp3 {
    private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public JMp3(String filename) {
        this.filename = filename;
        play();
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (JavaLayerException e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found " + filename);
            System.out.println(e);
        }
       

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();




    }


    

}
