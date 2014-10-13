
package proyectoprogra;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class Navegador extends javax.swing.JDialog {
            JEditorPane panelEditor;
            JScrollPane editorScrollPane;

    /** Creates new form Browser */
    public Navegador(java.awt.Frame parent, boolean modal, String ruta){
        super(parent, modal);
        panelEditor = new JEditorPane();
        panelEditor.setEditable(false);
        try {
            panelEditor.setPage(new URL(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
        panelEditor.addHyperlinkListener(new HyperlinkListener() 
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        panelEditor.setPage(e.getURL());
                    } catch (IOException ex) {
                        //Logger.getLogger(Navegador.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
            }
        });

        editorScrollPane = new JScrollPane(panelEditor);

        this.getContentPane().add(editorScrollPane);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Navegador dialog = new Navegador(new javax.swing.JFrame(), true, ""); //Modificar esta línea
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
