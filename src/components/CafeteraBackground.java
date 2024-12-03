package components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Miketo
 */
public class CafeteraBackground extends JPanel {
    private Image backgroundImg;

    /**
     * Creates new form CafeteraBackground
     */
    public CafeteraBackground() {
        initComponents();
        try {
            backgroundImg = ImageIO.read(getClass().getResourceAsStream("/imgs/cafetera_tras.png"));
            //backgroundImg = ImageIO.read(new File("src/imgs/cafetera_tras.png"));
            //backgroundImg = ImageIO.read(new File("C:\\Users\\miket\\Documents\\a UVEG\\Topicos Selectos\\NespressoUI\\src\\imgs\\cafetera_tras.png"));
        } catch (IOException e) {
            System.out.println("Error al cargar el fondo. " + e);
        }
        
//        try {
//            
//        } catch (IOException e) {
//            System.out.println("Error al cargar imagen. " + e);
//        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
