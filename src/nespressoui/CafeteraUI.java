package nespressoui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import util.Colors;
import util.Strings;
import util.Utilities;

/**
 *
 * @author Miketo
 */
public class CafeteraUI extends javax.swing.JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCafeteraOff;
    private components.BtnCafetera btnDeliveryCup;
    private javax.swing.JButton btnMakeCoffee;
    private components.BtnCafetera btnMoveCup;
    private components.BtnCafetera btnNextTxt;
    private components.BtnCafetera btnOut;
    private components.BtnCafetera btnPrevTxt;
    private components.BtnCafetera btnPutCup;
    private components.BtnCafetera btnRefillCoffee;
    private components.BtnCafetera btnRefillWater;
    private javax.swing.JButton btnTypeAmericano;
    private javax.swing.JButton btnTypeDoble;
    private javax.swing.JButton btnTypeExpreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArrowAmericano;
    private javax.swing.JLabel lblArrowDoble;
    private javax.swing.JLabel lblArrowExpresso;
    private javax.swing.JLabel lblArrowOFF;
    private javax.swing.JLabel lblArrowON;
    private javax.swing.JLabel lblCoffeeStream1;
    private javax.swing.JLabel lblCoffeeStream2;
    private javax.swing.JLabel lblHeart1;
    private javax.swing.JLabel lblHeart2;
    private javax.swing.JLabel lblHeart3;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlArrowsCoffeeType;
    private javax.swing.JPanel pnlBtnsCoffeeType;
    private javax.swing.JPanel pnlBtnsOnOff;
    private javax.swing.JPanel pnlBtnsOnOff1;
    private javax.swing.JPanel pnlButtons;
    private components.CafeteraBackground pnlCafeteraBackground;
    private components.PnlFilling pnlCoffee;
    private javax.swing.JPanel pnlExit;
    private javax.swing.JPanel pnlHearts;
    private javax.swing.JPanel pnlInstructions;
    private javax.swing.JPanel pnlLiquidCoffee;
    private javax.swing.JPanel pnlNotifications;
    private javax.swing.JPanel pnlTazas;
    private components.PnlFilling pnlWater;
    private components.Taza taza1;
    private components.Taza taza2;
    private javax.swing.JTextArea txtInformation;
    // End of variables declaration//GEN-END:variables
    private boolean isOn;
    private boolean isDelivered;
    private boolean isCup1;
    private int coffeeType;
    private ArrayList<JLabel> hearts;
    public final int EXPRESO = 0;
    public final int DOBLE = 1;
    public final int AMERICANO = 2;


    public CafeteraUI() {
        initFrame();
        initComponents();
        
        setAnimatePanels();
        setVariables();
        applyType(Strings.EXPRESO);
        switchOnOff(Strings.OFF);
        btnEntregarTazaAction();
        resetCoffeeStream();
        setVisibleHearts(0);
        
        setTxtNotifications();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initFrame() {
        this.setUndecorated(true);                //elimina las decoraciones de la ventana
        this.setBackground(Colors.blackTransparency);  //aplicamos transparencia a la ventana
    }
    
    private void setAnimatePanels() {
        pnlInstructions.setBackground(Colors.grayTransparency);
        pnlHearts.setBackground(Colors.grayTransparency);
        
        pnlWater.setFillColor(Colors.blue);
        pnlWater.setFillingSpeed(150);
        pnlWater.setFilledHeight(0);
        pnlWater.setOpaque(false);
        pnlCafeteraBackground.add(pnlWater, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 298, 52, 170));
        
        pnlCoffee.setFillColor(Colors.brown);
        pnlCoffee.setFillingSpeed(150);
        pnlCoffee.setFilledHeight(0);
        pnlCoffee.setOpaque(false);
        pnlCafeteraBackground.add(pnlCoffee, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 298, 66, 170));
    }
    
    private void setVariables() {
        this.isOn = false;
        this.isDelivered = false;
        this.isCup1 = true;
        this.coffeeType = EXPRESO;
        
        hearts = new ArrayList<>();
        hearts.add(this.lblHeart1);
        hearts.add(this.lblHeart2);
        hearts.add(this.lblHeart3);
    }
    
    private void resetIconsAndArrows() {
        btnTypeExpreso.setIcon(new ImageIcon(getClass().getResource(Strings.getIconPath(Strings.blue, Strings.OFF))));
        btnTypeDoble.setIcon(new ImageIcon(getClass().getResource(Strings.getIconPath(Strings.purple, Strings.OFF))));
        btnTypeAmericano.setIcon(new ImageIcon(getClass().getResource(Strings.getIconPath(Strings.yellow, Strings.OFF))));
        
        lblArrowExpresso.setVisible(false);
        lblArrowDoble.setVisible(false);
        lblArrowAmericano.setVisible(false);
    }
    
    private void updateArrowVisibility(int coffeeTypeIndex) {
        JLabel[] arrows = {lblArrowExpresso, lblArrowDoble, lblArrowAmericano};
        
        for (int i = 0; i < arrows.length; i++) {
            arrows[i].setVisible(i == coffeeTypeIndex);
        }
    }
    
    /**
     * Enciende el color del botón elegido del tipo de café 
     * y dibuja una flecha sobre el tipo de café a producir.
     * 
     * @param type Recibe el tipo de café
     * <ul>
     *  <li>{@code CafeteraUI.EXPRESO}</li>
     *  <li>{@code CafeteraUI.DOBLE}</li>
     *  <li>{@code CafeteraUI.AMERICANO}</li>
     * </ul>
     */
    public final void applyType(int type) {
        coffeeType = type;
        resetIconsAndArrows();
        
        JButton[] buttons = {btnTypeExpreso, btnTypeDoble, btnTypeAmericano};
        
        if (coffeeType >= 0 && coffeeType < buttons.length) {
            buttons[coffeeType].setIcon(new ImageIcon(getClass().getResource(Strings.iconPathsTypeOn[coffeeType])));
            updateArrowVisibility(coffeeType);
        }

    }
    
    private void setTxtNotifications() {
        String cadMod = Utilities.setCad(Strings.touchButtons, 23);
        
        txtInformation.setText(cadMod);
    }
    
    /**
     * Enciende la cafetera
     */
    public void btnCoffeeMakerOnAction() {
        switchOnOff(Strings.ON);
    }
    
    /**
     * Apaga la cafetera
     */
    public void btnCoffeeMakerOffAction() {
        switchOnOff(Strings.OFF);
        
        resetCoffeeStream();
    }
    
    private void switchOnOff(boolean isOn) {
        boolean coffeeButtonState = isOn ? Strings.ON : Strings.OFF;
        boolean cafeteraButtonState = isOn ? Strings.OFF : Strings.ON;
        boolean arrowOnVisibility = isOn;
        boolean arrowOffVisibility = !isOn;

        // Aplicar los cambios
        btnMakeCoffee.setIcon(new ImageIcon(getClass().getResource(Strings.getIconPath(Strings.green, coffeeButtonState))));
        btnCafeteraOff.setIcon(new ImageIcon(getClass().getResource(Strings.getIconPath(Strings.red, cafeteraButtonState))));
        lblArrowON.setVisible(arrowOnVisibility);
        lblArrowOFF.setVisible(arrowOffVisibility);
    }
    
    /**
     * Apaga la vista de los flujos de café
     */
    public final void resetCoffeeStream() {
        lblCoffeeStream1.setVisible(false);
        lblCoffeeStream2.setVisible(false);
    }
    
    public void btnSurtirAguaAction() {
        pnlWater.startFilling();
    }
    
    public void btnSurtirCafeAction() {
        pnlCoffee.startFilling();
    }
    
    public void btnMoverTazaAction() {
        isCup1 = !isCup1;
        
        btnPonerTazaAction();
    }
    
    public final void btnEntregarTazaAction() {
        taza1.setVisible(false);
        taza2.setVisible(false);
    }
    
    public void btnPonerTazaAction() {
        if (isCup1) {
            taza1.setVisible(true);
            taza2.setVisible(false);
            return;
        }
        
        taza1.setVisible(false);
        taza2.setVisible(true);
    }
    
    
    /**
     * Activa los eventos de los botones:
     * <ul>
     *   <li>{@code btnMakeCoffee}</li>
     *   <li>{@code btnCafeteraOff}</li>
     *   <li>{@code btnTypeExpreso}</li>
     *   <li>{@code btnTypeDoble}</li>
     *   <li>{@code btnTypeAmericano}</li>
     *   <li>{@code btnRefillWater}</li>
     *   <li>{@code btnRefillCoffee}</li>
     *   <li>{@code btnMoveCup}</li>
     *   <li>{@code btnPutCup}</li>
     *   <li>{@code btnDeliveryCup}</li>
     *   <li>{@code btnOut}</li>
     * </ul>
     */
//    public void activateEvents() {
//        btnMakeCoffee.addActionListener((e) -> btnCoffeeMakerOnAction());
//        btnCafeteraOff.addActionListener((e) -> btnCoffeeMakerOffAction());
//        
//        btnTypeExpreso.addActionListener((e) -> {
//            applyType(Strings.EXPRESO);
//        });
//        btnTypeDoble.addActionListener((e) -> {
//            applyType(Strings.DOBLE);
//        });
//        btnTypeAmericano.addActionListener((e) -> {
//            applyType(Strings.AMERICANO);
//        });
//        
//        btnRefillWater.addActionListener((e) -> btnSurtirAguaAction());
//        btnRefillCoffee.addActionListener((e) -> btnSurtirCafeAction());
//        btnMoveCup.addActionListener((e) -> btnMoverTazaAction());
//        btnPutCup.addActionListener((e) -> btnPonerTazaAction());
//        btnDeliveryCup.addActionListener((e) -> btnEntregarTazaAction());
//        
//        btnOut.addActionListener((e) -> System.exit(0));
//    }
    
    
    
    //On / Of
//    public JButton getBtnMakeCoffe() {
//        return btnMakeCoffee;
//    }
//    
//    public JButton getBtnCafeteraOff() {
//        return btnCafeteraOff;
//    }
    
    public void setBtnMakeCoffeActionListener(ActionListener listener) {
        btnMakeCoffee.addActionListener(listener);
    }
    
    public void setBtnCafeteraOffActionListener(ActionListener listener) {
        btnCafeteraOff.addActionListener(listener);
    }
    
    
    
    //Tipos de café
//    public JButton getBtnTypeExpreso() {
//        return btnTypeExpreso;
//    }
//    
//    public JButton getBtnTypeDoble() {
//        return btnTypeDoble;
//    }
//    
//    public JButton getBtnTypeAmericano() {
//        return btnTypeAmericano;
//    }
    
    public void setBtnTypeExpresoActionListener(ActionListener listener) {
        btnTypeExpreso.addActionListener(listener);
    }
    
    public void setBtnTypeDobleActionListener(ActionListener listener) {
        btnTypeDoble.addActionListener(listener);
    }

    public void setBtnTypeAmericanoActionListener(ActionListener listener) {
        btnTypeAmericano.addActionListener(listener);
    }
    
    

    //Texto Acciones
//    public BtnCafetera getBtnPrevTxt() {
//        return btnPrevTxt;
//    }
//
//    public BtnCafetera getBtnNextTxt() {
//        return btnNextTxt;
//    }
    
    public void setBtnPrevTxtActionListener(ActionListener listener) {
        btnPrevTxt.addActionListener(listener);
    }

    public void setBtnNextTxtActionListener(ActionListener listener) {
        btnNextTxt.addActionListener(listener);
    }

//    public JTextArea getTxtInformation() {
//        return txtInformation;
//    }
    
    
    
    //Botonera Acciones
//    public BtnCafetera getBtnSurtirAgua() {
//        return btnRefillWater;
//    }
//
//    public BtnCafetera getBtnSurtirCafe() {
//        return btnRefillCoffee;
//    }
//    
//    public BtnCafetera getBtnMoverTaza() {
//        return btnMoveCup;
//    }
//
//    public BtnCafetera getBtnPonerTaza() {
//        return btnPutCup;
//    }
//
//    public BtnCafetera getBtnEntregarTaza() {
//        return btnDeliveryCup;
//    }
//
//    public BtnCafetera getBtnSalir() {
//        return btnOut;
//    }

    public void setBtnRefillWaterActionListener(ActionListener listener) {
        btnRefillWater.addActionListener(listener);
    }

    public void setBtnRefillCoffeeActionListener(ActionListener listener) {
        btnRefillCoffee.addActionListener(listener);
    }

    public void setBtnMoveCupActionListener(ActionListener listener) {
        btnMoveCup.addActionListener(listener);
    }

    public void setBtnPutCupActionListener(ActionListener listener) {
        btnPutCup.addActionListener(listener);
    }

    public void setBtnDeliveryCupActionListener(ActionListener listener) {
        btnDeliveryCup.addActionListener(listener);
    }

    public void setBtnOutActionListener(ActionListener listener) {
        btnOut.addActionListener(listener);
    }
    
    
    
    //Del café
//    public JLabel getLblCafeTaza1() {
//        return lblCoffeeStream1;
//    }
//
//    public JLabel getLblCafeTaza2() {
//        return lblCoffeeStream2;
//    }
//
//    public Taza getTaza1() {
//        return taza1;
//    }
//
//    public Taza getTaza2() {
//        return taza2;
//    }
    
    //De la animación
//    public pnlFilling getPnlCoffee() {
//        return pnlCoffee;
//    }
//
//    public pnlFilling getPnlWater() {
//        return pnlWater;
//    }
    
    // Métodos para pnlCoffee
//    public void setCoffeeFillingSpeed(int speed) {
//        pnlCoffee.setFillingSpeed(speed);
//    }
//
//    public void setCoffeeFillColor(Color color) {
//        pnlCoffee.setFillColor(color);
//    }

    public void setCoffeeFilledHeight(int filledHeight) {
        pnlCoffee.setFilledHeight(filledHeight);
    }

    // Métodos para pnlWater
//    public void setWaterFillingSpeed(int speed) {
//        pnlWater.setFillingSpeed(speed);
//    }
//
//    public void setWaterFillColor(Color color) {
//        pnlWater.setFillColor(color);
//    }

    public void setWaterFilledHeight(int filledHeight) {
        pnlWater.setFilledHeight(filledHeight);
    }
    
    
    
    
    //Variables de estado
    public boolean isIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public boolean isCup1() {
        return isCup1;
    }

    public void setIsCup1(boolean isCup1) {
        this.isCup1 = isCup1;
    }

    public int getCoffeeType() {
        return coffeeType;
    }

    /**
     * Modifica el tipo de café que se va a producir en la cafetera
     * 
     * @param coffeeType Se pueden elegir las opciones estáticas:
     * <ul>
     *   <li>{@code Strings.EXPRESO} = 0;</li>
     *   <li>{@code Strings.DOBLE} = 1;</li>
     *   <li>{@code Strings.AMERICANO} = 2;</li>
     * </ul>
     */
    public void setCoffeeType(int coffeeType) {
        this.coffeeType = coffeeType;
    }
//    
//    public ArrayList<JLabel> getHearts() {
//        ArrayList<JLabel> heartsClone = new ArrayList<>();
//        
//        hearts.forEach(h -> heartsClone.add(h));
//        
//        return heartsClone;
//    }
    
    /**
     * Hace visibles los corazones según la cantidad solicitada
     * @param amount Cantidad de corazones 1 a 3
     */
    public final void setVisibleHearts(int amount) {
        hearts.forEach(h -> h.setVisible(false));
        
        for (int i = 0; i < hearts.size() && i < amount; i++) {
            hearts.get(i).setVisible(true);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlActions = new javax.swing.JPanel();
        pnlNotifications = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInformation = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnPrevTxt = new components.BtnCafetera();
        btnNextTxt = new components.BtnCafetera();
        pnlButtons = new javax.swing.JPanel();
        btnRefillWater = new components.BtnCafetera();
        btnRefillCoffee = new components.BtnCafetera();
        btnMoveCup = new components.BtnCafetera();
        btnPutCup = new components.BtnCafetera();
        btnDeliveryCup = new components.BtnCafetera();
        pnlExit = new javax.swing.JPanel();
        btnOut = new components.BtnCafetera();
        pnlCafeteraBackground = new components.CafeteraBackground();
        pnlArrowsCoffeeType = new javax.swing.JPanel();
        lblArrowExpresso = new javax.swing.JLabel();
        lblArrowDoble = new javax.swing.JLabel();
        lblArrowAmericano = new javax.swing.JLabel();
        pnlBtnsCoffeeType = new javax.swing.JPanel();
        btnTypeExpreso = new javax.swing.JButton();
        btnTypeDoble = new javax.swing.JButton();
        btnTypeAmericano = new javax.swing.JButton();
        pnlBtnsOnOff1 = new javax.swing.JPanel();
        lblArrowON = new javax.swing.JLabel();
        lblArrowOFF = new javax.swing.JLabel();
        pnlBtnsOnOff = new javax.swing.JPanel();
        btnMakeCoffee = new javax.swing.JButton();
        btnCafeteraOff = new javax.swing.JButton();
        pnlLiquidCoffee = new javax.swing.JPanel();
        lblCoffeeStream1 = new javax.swing.JLabel();
        lblCoffeeStream2 = new javax.swing.JLabel();
        pnlTazas = new javax.swing.JPanel();
        taza1 = new components.Taza();
        taza2 = new components.Taza();
        pnlInstructions = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlWater = new components.PnlFilling();
        pnlCoffee = new components.PnlFilling();
        pnlHearts = new javax.swing.JPanel();
        lblHeart1 = new javax.swing.JLabel();
        lblHeart2 = new javax.swing.JLabel();
        lblHeart3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(862, 626));
        setResizable(false);

        pnlActions.setBackground(new java.awt.Color(255, 255, 153));
        pnlActions.setMaximumSize(new java.awt.Dimension(230, 626));
        pnlActions.setMinimumSize(new java.awt.Dimension(230, 626));

        pnlNotifications.setBackground(new java.awt.Color(255, 255, 204));
        pnlNotifications.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51)));
        pnlNotifications.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtInformation.setEditable(false);
        txtInformation.setBackground(new java.awt.Color(255, 255, 204));
        txtInformation.setColumns(20);
        txtInformation.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        txtInformation.setForeground(new java.awt.Color(51, 51, 51));
        txtInformation.setRows(5);
        txtInformation.setText("Toca los botones para\nmanipular la cafetera.\nEsta es otra línea.\nUna más.\nAcá la quinta.\nSexta.\nSéptima.\nOctava.\nNovena.");
        txtInformation.setAutoscrolls(false);
        txtInformation.setBorder(null);
        txtInformation.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtInformation.setFocusable(false);
        txtInformation.setName(""); // NOI18N
        jScrollPane1.setViewportView(txtInformation);

        pnlNotifications.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        btnPrevTxt.setText("<");
        jPanel1.add(btnPrevTxt);

        btnNextTxt.setText(">");
        jPanel1.add(btnNextTxt);

        pnlNotifications.add(jPanel1, java.awt.BorderLayout.SOUTH);

        pnlButtons.setOpaque(false);
        pnlButtons.setLayout(new java.awt.GridLayout(5, 1, 0, 20));

        btnRefillWater.setText("surtir agua");
        pnlButtons.add(btnRefillWater);

        btnRefillCoffee.setText("surtir café");
        pnlButtons.add(btnRefillCoffee);

        btnMoveCup.setText("mover taza");
        pnlButtons.add(btnMoveCup);

        btnPutCup.setText("poner taza");
        pnlButtons.add(btnPutCup);

        btnDeliveryCup.setText("entregar taza");
        pnlButtons.add(btnDeliveryCup);

        pnlExit.setOpaque(false);
        pnlExit.setLayout(new java.awt.GridLayout(1, 1, 0, 10));

        btnOut.setText("salir");
        pnlExit.add(btnOut);

        javax.swing.GroupLayout pnlActionsLayout = new javax.swing.GroupLayout(pnlActions);
        pnlActions.setLayout(pnlActionsLayout);
        pnlActionsLayout.setHorizontalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNotifications, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlActionsLayout.setVerticalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pnlNotifications, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(pnlExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlCafeteraBackground.setPreferredSize(new java.awt.Dimension(626, 626));
        pnlCafeteraBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlArrowsCoffeeType.setOpaque(false);
        pnlArrowsCoffeeType.setLayout(new java.awt.GridLayout(1, 3, 1, 0));

        lblArrowExpresso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arrow.png"))); // NOI18N
        pnlArrowsCoffeeType.add(lblArrowExpresso);

        lblArrowDoble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arrow.png"))); // NOI18N
        pnlArrowsCoffeeType.add(lblArrowDoble);

        lblArrowAmericano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arrow.png"))); // NOI18N
        pnlArrowsCoffeeType.add(lblArrowAmericano);

        pnlCafeteraBackground.add(pnlArrowsCoffeeType, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 120, 30));

        pnlBtnsCoffeeType.setOpaque(false);
        pnlBtnsCoffeeType.setLayout(new java.awt.GridLayout(1, 3, 1, 0));

        btnTypeExpreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/blue_button_off.png"))); // NOI18N
        btnTypeExpreso.setBorder(null);
        btnTypeExpreso.setBorderPainted(false);
        btnTypeExpreso.setContentAreaFilled(false);
        btnTypeExpreso.setFocusPainted(false);
        pnlBtnsCoffeeType.add(btnTypeExpreso);

        btnTypeDoble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/purple_button_off.png"))); // NOI18N
        btnTypeDoble.setBorder(null);
        btnTypeDoble.setBorderPainted(false);
        btnTypeDoble.setContentAreaFilled(false);
        btnTypeDoble.setFocusPainted(false);
        pnlBtnsCoffeeType.add(btnTypeDoble);

        btnTypeAmericano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/yellow_button_off.png"))); // NOI18N
        btnTypeAmericano.setBorder(null);
        btnTypeAmericano.setBorderPainted(false);
        btnTypeAmericano.setContentAreaFilled(false);
        btnTypeAmericano.setFocusPainted(false);
        pnlBtnsCoffeeType.add(btnTypeAmericano);

        pnlCafeteraBackground.add(pnlBtnsCoffeeType, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 120, -1));

        pnlBtnsOnOff1.setOpaque(false);
        pnlBtnsOnOff1.setLayout(new java.awt.GridLayout(1, 2));

        lblArrowON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arrow.png"))); // NOI18N
        pnlBtnsOnOff1.add(lblArrowON);

        lblArrowOFF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arrow.png"))); // NOI18N
        pnlBtnsOnOff1.add(lblArrowOFF);

        pnlCafeteraBackground.add(pnlBtnsOnOff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 140, 100, 30));

        pnlBtnsOnOff.setOpaque(false);
        pnlBtnsOnOff.setLayout(new java.awt.GridLayout(1, 2));

        btnMakeCoffee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/green_button_off.png"))); // NOI18N
        btnMakeCoffee.setBorder(null);
        btnMakeCoffee.setBorderPainted(false);
        btnMakeCoffee.setContentAreaFilled(false);
        btnMakeCoffee.setFocusPainted(false);
        pnlBtnsOnOff.add(btnMakeCoffee);

        btnCafeteraOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/red_button_on.png"))); // NOI18N
        btnCafeteraOff.setBorder(null);
        btnCafeteraOff.setBorderPainted(false);
        btnCafeteraOff.setContentAreaFilled(false);
        btnCafeteraOff.setFocusPainted(false);
        pnlBtnsOnOff.add(btnCafeteraOff);

        pnlCafeteraBackground.add(pnlBtnsOnOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 185, 92, 30));

        pnlLiquidCoffee.setMaximumSize(new java.awt.Dimension(200, 50));
        pnlLiquidCoffee.setMinimumSize(new java.awt.Dimension(200, 50));
        pnlLiquidCoffee.setOpaque(false);
        pnlLiquidCoffee.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLiquidCoffee.setLayout(new java.awt.GridLayout(1, 2, 50, 0));

        lblCoffeeStream1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/cafe_liquido.png"))); // NOI18N
        pnlLiquidCoffee.add(lblCoffeeStream1);

        lblCoffeeStream2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/cafe_liquido.png"))); // NOI18N
        pnlLiquidCoffee.add(lblCoffeeStream2);

        pnlCafeteraBackground.add(pnlLiquidCoffee, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 200, 50));

        pnlTazas.setOpaque(false);
        pnlTazas.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        javax.swing.GroupLayout taza1Layout = new javax.swing.GroupLayout(taza1);
        taza1.setLayout(taza1Layout);
        taza1Layout.setHorizontalGroup(
            taza1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );
        taza1Layout.setVerticalGroup(
            taza1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        pnlTazas.add(taza1);

        javax.swing.GroupLayout taza2Layout = new javax.swing.GroupLayout(taza2);
        taza2.setLayout(taza2Layout);
        taza2Layout.setHorizontalGroup(
            taza2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );
        taza2Layout.setVerticalGroup(
            taza2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        pnlTazas.add(taza2);

        pnlCafeteraBackground.add(pnlTazas, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 379, 260, 120));

        pnlInstructions.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/red_button_on.png"))); // NOI18N
        jLabel1.setText("Detener");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/green_button_off.png"))); // NOI18N
        jLabel2.setText("Iniciar");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/blue_button_off.png"))); // NOI18N
        jLabel5.setText("Espresso");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/purple_button_off.png"))); // NOI18N
        jLabel4.setText("Doble");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/yellow_button_off.png"))); // NOI18N
        jLabel3.setText("Americano");

        javax.swing.GroupLayout pnlInstructionsLayout = new javax.swing.GroupLayout(pnlInstructions);
        pnlInstructions.setLayout(pnlInstructionsLayout);
        pnlInstructionsLayout.setHorizontalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInstructionsLayout.setVerticalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlCafeteraBackground.add(pnlInstructions, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 110, 220));

        javax.swing.GroupLayout pnlWaterLayout = new javax.swing.GroupLayout(pnlWater);
        pnlWater.setLayout(pnlWaterLayout);
        pnlWaterLayout.setHorizontalGroup(
            pnlWaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnlWaterLayout.setVerticalGroup(
            pnlWaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        pnlCafeteraBackground.add(pnlWater, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 50, 170));

        pnlCoffee.setMaximumSize(new java.awt.Dimension(60, 180));
        pnlCoffee.setMinimumSize(new java.awt.Dimension(60, 180));
        pnlCoffee.setPreferredSize(new java.awt.Dimension(60, 180));

        javax.swing.GroupLayout pnlCoffeeLayout = new javax.swing.GroupLayout(pnlCoffee);
        pnlCoffee.setLayout(pnlCoffeeLayout);
        pnlCoffeeLayout.setHorizontalGroup(
            pnlCoffeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        pnlCoffeeLayout.setVerticalGroup(
            pnlCoffeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        pnlCafeteraBackground.add(pnlCoffee, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 60, 170));

        pnlHearts.setBackground(new java.awt.Color(51, 51, 51));
        pnlHearts.setLayout(new java.awt.GridLayout(1, 3, 6, 0));

        lblHeart1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeart1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/heart.png"))); // NOI18N
        pnlHearts.add(lblHeart1);

        lblHeart2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeart2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/heart.png"))); // NOI18N
        pnlHearts.add(lblHeart2);

        lblHeart3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeart3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/heart.png"))); // NOI18N
        pnlHearts.add(lblHeart3);

        pnlCafeteraBackground.add(pnlHearts, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 160, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCafeteraBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCafeteraBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
}
