package control;

import model.Model;
import nespressoui.CafeteraUI;
import util.Strings;

public class Controller {
    private CafeteraUI cafeteraUI;
    private Model model;

    public Controller(CafeteraUI cafeteraUI, Model model) {
        this.cafeteraUI = cafeteraUI;
        this.model = model;
    }

    public void activateEvents() {
        cafeteraUI.setBtnMakeCoffeActionListener(e -> cafeteraUI.btnCoffeeMakerOnAction());
        cafeteraUI.setBtnCafeteraOffActionListener(e -> cafeteraUI.btnCoffeeMakerOffAction());

        cafeteraUI.setBtnTypeExpresoActionListener(e -> cafeteraUI.applyType(Strings.EXPRESO));
        cafeteraUI.setBtnTypeDobleActionListener(e -> cafeteraUI.applyType(Strings.DOBLE));
        cafeteraUI.setBtnTypeAmericanoActionListener(e -> cafeteraUI.applyType(Strings.AMERICANO));
        cafeteraUI.setBtnRefillWaterActionListener(e -> cafeteraUI.btnSurtirAguaAction());
        cafeteraUI.setBtnRefillCoffeeActionListener(e -> cafeteraUI.btnSurtirCafeAction());
        cafeteraUI.setBtnMoveCupActionListener(e -> cafeteraUI.btnMoverTazaAction());
        cafeteraUI.setBtnPutCupActionListener(e -> cafeteraUI.btnPonerTazaAction());
        cafeteraUI.setBtnDeliveryCupActionListener(e -> cafeteraUI.btnEntregarTazaAction());

        cafeteraUI.setBtnOutActionListener(e -> System.exit(0));
    }

}
