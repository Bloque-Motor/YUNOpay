package motor.bloque.views;

import motor.bloque.controllers.MainMenuController;
import motor.bloque.controllers.NewCardController;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class ClientView extends JFrame {

    private JPanel currentPanel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField cardNumberField;
    private JPasswordField pinField;
    private JPasswordField newPinField;
    private JPasswordField confirmPinField;
    private JTextField amountField;
    private static final String CARDNUM = "Card number:";
    private static final String PINSTRING = "PIN:";
    public enum panels {MAIN, NEWCARD, PAY, RECHARGE, CHANGEPIN, CHECKBALANCE, CHECKMOVES}


    public ClientView() {
        mainMenu();
    }

    private void mainMenu() {

        JButton newCardButton = new JButton("New card");
        newCardButton.setToolTipText("Create a new card to use in the system");
        newCardButton.addActionListener(new MainMenuController.NewCardButton());

        JButton payButton = new JButton("pay");
        payButton.setToolTipText("Makes a payment with a card, provided there are sufficient funds in it");
        payButton.addActionListener(new MainMenuController.PayButton());

        JButton rechargeMoneyButton = new JButton("recharge money");
        rechargeMoneyButton.setToolTipText("recharge the card's balance with more money");
        rechargeMoneyButton.addActionListener(new MainMenuController.RechargeMoneyButton());

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setToolTipText("Change the PIN code of a card");
        changePinButton.addActionListener(new MainMenuController.ChangePinButton());

        JButton consultBalanceButton = new JButton("Consult balance");
        consultBalanceButton.setToolTipText("Check the amount of money left in a card");
        consultBalanceButton.addActionListener(new MainMenuController.ConsultBalanceButton());

        JButton consultMovementsButton = new JButton("Consult movements");
        consultMovementsButton.setToolTipText("Check the movements of a card for the current session");
        consultMovementsButton.addActionListener(new MainMenuController.ConsultMovementsButton());

        JButton quitButton = new JButton("Quit");
        quitButton.setToolTipText("Quit program");
        quitButton.addActionListener(new MainMenuController.QuitButton());

        setTitle("YUNO pay");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        gl.linkSize(SwingConstants.HORIZONTAL, newCardButton,
                payButton, rechargeMoneyButton, changePinButton,
                consultBalanceButton, consultMovementsButton);

        currentPanel.setToolTipText("Main menu");

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(newCardButton)
                    .addComponent(payButton)
                    .addComponent(rechargeMoneyButton)
                    .addComponent(changePinButton)
                    .addComponent(consultBalanceButton)
                    .addComponent(consultMovementsButton)
                    .addComponent(quitButton)
                )
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(newCardButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(payButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(rechargeMoneyButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(changePinButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(consultBalanceButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(consultMovementsButton))
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(quitButton))
        );

        pack();

    }

    private void newCard() {
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel pinLabel = new JLabel(PINSTRING);
        JLabel confirmpinLabel = new JLabel("Confirm PIN:");
        JLabel amountLabel = new JLabel("Initial amount:");

         nameField = new JTextField(10);
         surnameField = new JTextField(10);
         pinField = new JPasswordField(4);
         confirmPinField = new JPasswordField(4);
         amountField = new JTextField(10);

        JButton okButton = new JButton("OK");
        okButton.setToolTipText("Validate data");
        okButton.addActionListener(new NewCardController.okButton());

        JButton closeButton = new JButton("Cancel");
        closeButton.setToolTipText("Cancel current operation and return to main menu");
        closeButton.addActionListener(new NewCardController.cancelButton());

        setTitle("New card");

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(nameLabel)
                        .addComponent(surnameLabel)
                        .addComponent(pinLabel)
                        .addComponent(confirmpinLabel)
                        .addComponent(amountLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(nameField)
                        .addComponent(surnameField)
                        .addComponent(pinField)
                        .addComponent(confirmPinField)
                        .addComponent(amountField))
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addComponent(closeButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(surnameLabel)
                        .addComponent(surnameField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(pinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(confirmpinLabel)
                        .addComponent(confirmPinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(amountLabel)
                        .addComponent(amountField))
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(closeButton))
        );

        gl.linkSize(SwingConstants.HORIZONTAL, okButton, closeButton);

        pack();
        
    }

    private void pay() {
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel cardNumberLabel = new JLabel(CARDNUM);
        JLabel pinLabel = new JLabel(PINSTRING);
        JLabel amountLabel = new JLabel("Amount to pay:");

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);
        amountField = new JTextField(10);

        setTitle("pay");

        makeCardPinAmountPanel(gl, cardNumberLabel, pinLabel, amountLabel, cardNumberField, pinField, amountField);

    }

    private void recharge() {
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel cardNumberLabel = new JLabel(CARDNUM);
        JLabel pinLabel = new JLabel(PINSTRING);
        JLabel amountLabel = new JLabel("Amount to recharge:");

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);
        amountField = new JTextField(10);

        setTitle("Recharge card");

        makeCardPinAmountPanel(gl, cardNumberLabel, pinLabel, amountLabel, cardNumberField, pinField, amountField);
    }

    private void changePin() {
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel cardNumberLabel = new JLabel(CARDNUM);
        JLabel oldPinLabel = new JLabel("Old PIN:");
        JLabel newPinLabel = new JLabel("New PIN:");
        JLabel confirmPinLabel = new JLabel("Confirm new PIN:");

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);
        newPinField = new JPasswordField(4);
        confirmPinField = new JPasswordField(4);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(cardNumberLabel)
                        .addComponent(oldPinLabel)
                        .addComponent(newPinLabel)
                        .addComponent(confirmPinLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cardNumberField)
                        .addComponent(pinField)
                        .addComponent(newPinField)
                        .addComponent(confirmPinField))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(cardNumberLabel)
                        .addComponent(cardNumberField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(oldPinLabel)
                        .addComponent(pinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(newPinLabel)
                        .addComponent(newPinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(confirmPinLabel)
                        .addComponent(confirmPinField))
        );

        pack();

    }

    private void checkBalance(){
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel cardNumberLabel = new JLabel(CARDNUM);
        JLabel pinLabel = new JLabel(PINSTRING);

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);

        setTitle("Consult card balance");

        makeCardPinPanel(gl, cardNumberLabel, pinLabel, cardNumberField, pinField);
    }

    private void checkMovements(){
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);

        JLabel cardNumberLabel = new JLabel(CARDNUM);
        JLabel pinLabel = new JLabel(PINSTRING);

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);

        setTitle("Consult card movements");

        makeCardPinPanel(gl, cardNumberLabel, pinLabel, cardNumberField, pinField);
    }

    private void makeCardPinPanel(GroupLayout gl, JLabel cardNumberLabel, JLabel pinLabel, JTextField cardNumberField, JPasswordField pinField) {
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(cardNumberLabel)
                        .addComponent(pinLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cardNumberField)
                        .addComponent(pinField))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(cardNumberLabel)
                        .addComponent(cardNumberField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(pinField))
        );

        pack();
    }

    private void makeCardPinAmountPanel(GroupLayout gl, JLabel cardNumberLabel, JLabel pinLabel, JLabel amountLabel, JTextField cardNumberField, JPasswordField pinField, JTextField amountField) {
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(cardNumberLabel)
                        .addComponent(pinLabel)
                        .addComponent(amountLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cardNumberField)
                        .addComponent(pinField)
                        .addComponent(amountField))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(cardNumberLabel)
                        .addComponent(cardNumberField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(pinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(amountLabel)
                        .addComponent(amountField))
        );

        pack();
    }

    public JTextField getNameField(){
        return nameField;
    }

    public JPasswordField getConfirmPinField() {
        return confirmPinField;
    }

    public JPasswordField getNewPinField() {
        return newPinField;
    }

    public JPasswordField getPinField() {
        return pinField;
    }

    public JTextField getCardNumberField() {
        return cardNumberField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public void changePanel(panels panels) {
        getContentPane().removeAll();
        switch (panels){
            case NEWCARD:
                newCard();
                break;
            case PAY:
                pay();
                break;
            case RECHARGE:
                recharge();
                break;
            case CHANGEPIN:
                changePin();
                break;
            case CHECKBALANCE:
                checkBalance();
                break;
            case CHECKMOVES:
                checkMovements();
                break;
            case MAIN:
            default:
                mainMenu();
                break;
        }

        getContentPane().doLayout();
        update(getGraphics());
    }

}

