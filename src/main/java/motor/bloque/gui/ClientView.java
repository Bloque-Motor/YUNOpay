package motor.bloque.gui;

import motor.bloque.handlers.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.GroupLayout.Alignment.*;

public class ClientView extends JFrame {

    private JPanel currentPanel;

    public enum panels {MAIN, NEWCARD, PAY, RECHARGE, CHANGEPIN, CHECKBALANCE, CHECKMOVES}

    public ClientView() {
        MainMenu();
    }

    private void MainMenu() {

        JButton newCardButton = new JButton("New card");
        newCardButton.setToolTipText("Create a new card to use in the system");
        newCardButton.addActionListener(new ClientController.NewCardButton());

        JButton payButton = new JButton("Pay");
        payButton.setToolTipText("Makes a payment with a card, provided there are sufficient funds in it");
        payButton.addActionListener(new ClientController.PayButton());

        JButton rechargeMoneyButton = new JButton("Recharge money");
        rechargeMoneyButton.setToolTipText("Recharge the card's balance with more money");
        rechargeMoneyButton.addActionListener(new ClientController.RechargeMoneyButton());

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setToolTipText("Change the PIN code of a card");
        changePinButton.addActionListener(new ClientController.ChangePinButton());

        JButton consultBalanceButton = new JButton("Consult balance");
        consultBalanceButton.setToolTipText("Check the amount of money left in a card");
        consultBalanceButton.addActionListener(new ClientController.ConsultBalanceButton());

        JButton consultMovementsButton = new JButton("Consult movements");
        consultMovementsButton.setToolTipText("Check the movements of a card for the current session");
        consultMovementsButton.addActionListener(new ClientController.ConsultMovementsButton());

        JButton quitButton = new JButton("Quit");
        quitButton.setToolTipText("Quit program");
        quitButton.addActionListener(new ClientController.QuitButton());



        setTitle("YUNO Pay");
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

    private void NewCard() {
        this.currentPanel = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel pinLabel = new JLabel("PIN:");
        JLabel confirmpinLabel = new JLabel("Confirm PIN:");
        JLabel amountLabel = new JLabel("Initial amount:");

        JTextField field1 = new JTextField(10);
        JTextField field2 = new JTextField(10);
        JPasswordField field3 = new JPasswordField(4);
        JPasswordField field4 = new JPasswordField(4);
        JTextField field5 = new JTextField(10);

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
                        .addComponent(field1)
                        .addComponent(field2)
                        .addComponent(field3)
                        .addComponent(field4)
                        .addComponent(field5))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(field1))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(surnameLabel)
                        .addComponent(field2))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(pinLabel)
                        .addComponent(field3))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(confirmpinLabel)
                        .addComponent(field4))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(amountLabel)
                        .addComponent(field5))
        );

        pack();
        
    }

    private void Pay() {
        this.currentPanel = (JPanel) getContentPane();;
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
        JLabel cardNumberLabel = new JLabel("Card number:");
        JLabel pinLabel = new JLabel("PIN:");
        JLabel amountLabel = new JLabel("Amount:");

        JTextField cardNumberField = new JTextField(10);
        JPasswordField pinField = new JPasswordField(4);
        JTextField amountField = new JTextField(10);

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

    private void Recharge() {
        this.currentPanel = (JPanel) getContentPane();;
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
    }

    private void ChangePin() {
        this.currentPanel = (JPanel) getContentPane();;
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
    }

    private void CheckBalance(){
        this.currentPanel = (JPanel) getContentPane();;
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
    }

    private void CheckMovements(){
        this.currentPanel = (JPanel) getContentPane();;
        GroupLayout gl = new GroupLayout(currentPanel);
        currentPanel.setLayout(gl);
    }

    public void changePanel(panels panels) {
        getContentPane().removeAll();
        switch (panels){
            case MAIN:
            default:
                MainMenu();
                break;
            case NEWCARD:
                NewCard();
                break;
            case PAY:
                Pay();
                break;
            case RECHARGE:
                Recharge();
                break;
            case CHANGEPIN:
                ChangePin();
                break;
            case CHECKBALANCE:
                CheckBalance();
                break;
            case CHECKMOVES:
                CheckMovements();
                break;
        }

        getContentPane().doLayout();
        update(getGraphics());
    }

}

