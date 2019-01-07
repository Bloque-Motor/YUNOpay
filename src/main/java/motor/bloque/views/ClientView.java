package motor.bloque.views;

import motor.bloque.controllers.*;

import javax.swing.*;

import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class ClientView extends JFrame {

    private JPanel currentPanel;

    private JTextField cardNumberField;
    private JTextField amountField;

    private JPasswordField pinField;
    private JPasswordField confirmPinField;

    private JLabel nameLabel = new JLabel("Name:");
    private JLabel surnameLabel = new JLabel("Surname:");
    private JLabel pinLabel = new JLabel("PIN:");
    private JLabel newPinLabel = new JLabel("New PIN:");
    private JLabel confirmPinLabel = new JLabel("Confirm new PIN:");
    private JLabel amountLabel = new JLabel("Amount:");
    private JLabel cardNumberLabel = new JLabel("Card number:");
    private String ticket;

    private static final String OKTT = "Validate data and perform selected operation";

    JButton cancelButton = new JButton("Cancel");

    public enum panels {MAIN, NEWCARD, PAY, RECHARGE, CHANGEPIN, CHECKBALANCE, CHECKMOVES, TICKET}


    public ClientView() {
        mainMenu();
        cancelButton.setToolTipText("Cancel current operation and return to main menu");
        cancelButton.addActionListener(new MainMenu.CancelButton());
    }

    private void mainMenu() {

        JButton newCardButton = new JButton("New card");
        newCardButton.setToolTipText("Create a new card to use in the system");
        newCardButton.addActionListener(new MainMenu.NewCardButton());

        JButton payButton = new JButton("Pay");
        payButton.setToolTipText("Makes a payment with a card, provided there are sufficient funds in it");
        payButton.addActionListener(new MainMenu.PayButton());

        JButton rechargeMoneyButton = new JButton("Recharge money");
        rechargeMoneyButton.setToolTipText("recharge the card's balance with more money");
        rechargeMoneyButton.addActionListener(new MainMenu.RechargeMoneyButton());

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setToolTipText("Change the PIN code of a card");
        changePinButton.addActionListener(new MainMenu.ChangePinButton());

        JButton consultBalanceButton = new JButton("Consult balance");
        consultBalanceButton.setToolTipText("Check the amount of money left in a card");
        consultBalanceButton.addActionListener(new MainMenu.ConsultBalanceButton());

        JButton consultMovementsButton = new JButton("Consult movements");
        consultMovementsButton.setToolTipText("Check the movements of a card for the current session");
        consultMovementsButton.addActionListener(new MainMenu.ConsultMovementsButton());

        JButton quitButton = new JButton("Quit");
        quitButton.setToolTipText("Quit program");
        quitButton.addActionListener(new MainMenu.QuitButton());

        setTitle("YUNO pay");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        GroupLayout gl = makeGL();

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
        GroupLayout gl = makeGL();

        JTextField nameField = new JTextField(10);
        nameField.getDocument().addDocumentListener(new NewCard.NameReader());

        JTextField surnameField = new JTextField(10);
        surnameField.getDocument().addDocumentListener(new NewCard.SurameReader());

        pinField = new JPasswordField(4);
        pinField.getDocument().addDocumentListener(new NewCard.PinReader());

        confirmPinField = new JPasswordField(4);
        confirmPinField.getDocument().addDocumentListener(new NewCard.ConfirmPinReader());

        amountField = new JTextField(10);
        amountField.getDocument().addDocumentListener(new NewCard.AmountReader());


        JButton okButton = new JButton("OK");
        okButton.setToolTipText(OKTT);
        okButton.addActionListener(new NewCard.OkButton());

        setTitle("New card");

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(nameLabel)
                        .addComponent(surnameLabel)
                        .addComponent(newPinLabel)
                        .addComponent(confirmPinLabel)
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
                        .addComponent(cancelButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(surnameLabel)
                        .addComponent(surnameField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(newPinLabel)
                        .addComponent(pinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(confirmPinLabel)
                        .addComponent(confirmPinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(amountLabel)
                        .addComponent(amountField))
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(cancelButton))
        );

        gl.linkSize(SwingConstants.HORIZONTAL, okButton, cancelButton);

        pack();
        
    }

    private void pay() {
        setTitle("pay");
        makeCardPinAmountPanel(panels.PAY);
    }

    private void recharge() {
        setTitle("Recharge card");
        makeCardPinAmountPanel(panels.RECHARGE);
    }

    private void changePin() {
        GroupLayout gl = makeGL();

        cardNumberField = new JTextField(10);
        confirmPinField.getDocument().addDocumentListener(new ChangePin.CardNumberReader());

        pinField = new JPasswordField(4);
        pinField.getDocument().addDocumentListener(new ChangePin.PinReader());

        JPasswordField newPinField = new JPasswordField(4);
        newPinField.getDocument().addDocumentListener(new ChangePin.NewPinReader());

        confirmPinField = new JPasswordField(4);
        confirmPinField.getDocument().addDocumentListener(new ChangePin.ConfirmPinReader());

        JButton okButton = new JButton("OK");
        okButton.setToolTipText(OKTT);
        okButton.addActionListener(new ChangePin.OkButton());

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(cardNumberLabel)
                        .addComponent(pinLabel)
                        .addComponent(newPinLabel)
                        .addComponent(confirmPinLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cardNumberField)
                        .addComponent(pinField)
                        .addComponent(newPinField)
                        .addComponent(confirmPinField))
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(okButton)
                .addComponent(cancelButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(cardNumberLabel)
                        .addComponent(cardNumberField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(pinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(newPinLabel)
                        .addComponent(newPinField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(confirmPinLabel)
                        .addComponent(confirmPinField))
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(cancelButton))
        );
        pack();

    }

    private void checkBalance(){
        setTitle("Consult card balance");
        makeCardPinPanel(panels.CHECKBALANCE);
    }

    private void checkMovements(){
        setTitle("Consult card movements");
        makeCardPinPanel(panels.CHECKMOVES);
    }

    private void makeCardPinPanel(panels pane) {
        GroupLayout gl = makeGL();

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);

        JButton okButton = new JButton("OK");
        okButton.setToolTipText(OKTT);
        switch (pane){
            case CHECKBALANCE:
                okButton.addActionListener(new ConsultBalance.OkButton());
                cardNumberField.getDocument().addDocumentListener(new ConsultBalance.CardNumberReader());
                pinField.getDocument().addDocumentListener(new ConsultBalance.PinReader());
                break;
            case CHECKMOVES:
                okButton.addActionListener(new ConsultMovements.OkButton());
                cardNumberField.getDocument().addDocumentListener(new ConsultMovements.CardNumberReader());
                pinField.getDocument().addDocumentListener(new ConsultMovements.PinReader());
                break;
            default:
                okButton.addActionListener(new MainMenu.CancelButton());
        }

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(cardNumberLabel)
                        .addComponent(pinLabel))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cardNumberField)
                        .addComponent(pinField))
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(okButton)
                .addComponent(cancelButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(cardNumberLabel)
                        .addComponent(cardNumberField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(pinField))
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(cancelButton))
        );

        pack();
    }

    private void makeCardPinAmountPanel(panels pane) {
        GroupLayout gl = makeGL();

        cardNumberField = new JTextField(10);
        pinField = new JPasswordField(4);
        amountField = new JTextField(10);

        JButton okButton = new JButton("OK");
        okButton.setToolTipText(OKTT);
        switch (pane){
            case PAY:
                okButton.addActionListener(new Pay.OkButton());
                cardNumberField.getDocument().addDocumentListener(new Pay.CardNumberReader());
                pinField.getDocument().addDocumentListener(new Pay.PinReader());
                amountField.getDocument().addDocumentListener(new Pay.AmountReader());
                break;
            case RECHARGE:
                okButton.addActionListener(new Recharge.OkButton());
                cardNumberField.getDocument().addDocumentListener(new Recharge.CardNumberReader());
                pinField.getDocument().addDocumentListener(new Recharge.PinReader());
                amountField.getDocument().addDocumentListener(new Recharge.AmountReader());
                break;
            default:
                okButton.addActionListener(new MainMenu.CancelButton());
        }

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
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(okButton)
                .addComponent(cancelButton)
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
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(cancelButton))
        );

        pack();
    }

    private void ticket(){
        GroupLayout gl = makeGL();

        JLabel ticketLabel = new JLabel(ticket);
        ticketLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        ticketLabel.setForeground(new Color(50, 50, 25));

        JButton okButton = new JButton("OK");
        okButton.setToolTipText("Finish current operation and go back to main menu");
        okButton.addActionListener(new Ticket.OkButton());

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(ticketLabel)
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(okButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                .addComponent(ticketLabel))
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton))
        );
        pack();
    }

    public void setTicket(String text){
        this.ticket = text;
    }

    private GroupLayout makeGL(){
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
        return gl;
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
            case TICKET:
                ticket();
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

