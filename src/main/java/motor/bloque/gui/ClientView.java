package motor.bloque.gui;

import motor.bloque.handlers.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;

public class ClientView extends JFrame {

    private ClientView() {

        initUI();
    }

    private void initUI() {

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
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.linkSize(SwingConstants.HORIZONTAL, newCardButton,
                payButton, rechargeMoneyButton, changePinButton,
                consultBalanceButton, consultMovementsButton);

        pane.setToolTipText("Content pane");

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
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(newCardButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(payButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(rechargeMoneyButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(changePinButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(consultBalanceButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(consultMovementsButton))
                .addGroup(gl.createParallelGroup(BASELINE)
                    .addComponent(quitButton))
        );

        pack();

    }



    public static void startClientApp() {

        EventQueue.invokeLater(() -> {
            ClientView ex = new ClientView();
            ex.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if(JOptionPane.showConfirmDialog(ex, "Are you sure ?") == JOptionPane.OK_OPTION){
                        ex.setVisible(false);
                        new ClientController.QuitButton().actionPerformed(null);
                        ex.dispose();
                    }
                }
            });
            ex.setVisible(true);
        });
    }
}

