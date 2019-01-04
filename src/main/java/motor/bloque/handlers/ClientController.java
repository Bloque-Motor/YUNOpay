package motor.bloque.handlers;

import motor.bloque.gui.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//TODO: This class must handle all operations for the elements on the gui.

public abstract class ClientController extends AbstractAction {

    private static ClientView clientView;

    public static void startClientApp() {

        EventQueue.invokeLater(() -> {
            ClientView frame = new ClientView();
            ClientController.setFrame(frame);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    //if(JOptionPane.showConfirmDialog(frame, "Are you sure ?") == JOptionPane.OK_OPTION){
                    frame.setVisible(false);
                    new ClientController.QuitButton().actionPerformed(null);
                    frame.dispose();
                    //}
                }
            });
            frame.setVisible(true);
        });
    }

    public static void setFrame(ClientView frame){
        clientView = frame;
    }

    public static class NewCardButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("New card button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.NEWCARD));

        }
    }

    public static class PayButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pay button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.PAY));
        }
    }

    public static class RechargeMoneyButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Recharge money button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.RECHARGE));
        }
    }

    public static class ChangePinButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Change PIN button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.CHANGEPIN));
        }
    }

    public static class ConsultBalanceButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Consult balance button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.CHECKBALANCE));
        }
    }

    public static class ConsultMovementsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Consult movements button pressed");
            EventQueue.invokeLater(() ->clientView.changePanel(ClientView.panels.CHECKMOVES));
        }
    }

    public static class QuitButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quit button pressed");
            Persistence.saveAll();
            System.exit(0);
        }
    }
}
