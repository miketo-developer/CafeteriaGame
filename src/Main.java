import control.Controller;
import model.Model;
import nespressoui.CafeteraUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        Model model = new Model();
        CafeteraUI vista = new CafeteraUI();
        Controller controller = new Controller(vista, model);

        controller.activateEvents();
        vista.setWaterFilledHeight(50);
        vista.setVisibleHearts(2);
        vista.setVisible(true);
    }

}



