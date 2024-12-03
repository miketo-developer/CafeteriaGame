package nespressoui;

/**
 *
 * @author Miketo
 */
public class NespressoUI {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CafeteraUI cafetera = new CafeteraUI();
                
                //cafetera.activateEvents();
                cafetera.setVisible(true);
                cafetera.setVisibleHearts(2);
                //cafetera.ac
            }
        });
        
        
    }
    
}
