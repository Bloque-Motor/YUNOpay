package motor.bloque.handlers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: This class must handle all operations for the elements on the gui.

public abstract class ClientController extends AbstractAction {

    public static class NewCardButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("New card button pressed");
        }
    }

    public static class PayButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pay button pressed");
        }
    }

    public static class RechargeMoneyButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Recharge money button pressed");
        }
    }

    public static class ChangePinButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Change PIN button pressed");
        }
    }

    public static class ConsultBalanceButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Consult balance button pressed");
        }
    }

    public static class ConsultMovementsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Consult movements button pressed");
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
