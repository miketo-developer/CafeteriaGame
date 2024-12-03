package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Miketo
 */
public class PnlFilling extends JPanel {

    /**
     * Creates new form pnlFilling
     */
    private int filledHeight = 0;   //nivel de llenado actual
    private int fillingSpeed = 10; // Velocidad de llenado (milisegundos entre pasos)
    private Color fillColor = new Color(51, 51, 51); // Color de los rectángulos
    private Timer timer;

    public PnlFilling() {
        initComponents();
        
        // Configurar el temporizador para controlar el llenado
        timer = new Timer(fillingSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filledHeight += 5; // Incrementa la altura del llenado en cada paso
                if (filledHeight >= getHeight()) {
                    filledHeight = getHeight();
                    timer.stop(); // Detiene el temporizador cuando se llena completamente
                }
                repaint(); // Vuelve a pintar el panel
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Habilitar renderizado anti-aliasing para suavizar los gráficos
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar el rectángulo que se va llenando desde abajo hacia arriba
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int y = panelHeight - filledHeight;

        g2d.setColor(fillColor);
        g2d.fillRect(0, y, panelWidth, filledHeight);
    }

    // Método para iniciar el llenado del panel
    public void startFilling() {
        timer.start(); // Iniciar el temporizador
    }

    // Método para establecer la velocidad del llenado
    public void setFillingSpeed(int speed) {
        this.fillingSpeed = speed;
        timer.setDelay(fillingSpeed);
    }

    // Método para establecer el color de llenado
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    //Establecer el nivel de llenado inicial [0 - 100]
    public void setFilledHeight(int filledHeight) {
        this.filledHeight = convertToPercent(filledHeight);
        repaint();
    }
    
    private int convertToPercent(int value) {
        double unity = getHeight() / 100.0;
        
        return (int)(value * unity);
    }

    public int getFillingMax() {
        return getHeight();
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
