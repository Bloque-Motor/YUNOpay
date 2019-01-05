package motor.bloque.controllers;

import motor.bloque.exceptions.IncorrectPin;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.handlers.Persistence;
import motor.bloque.interfaces.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ConsultMovements extends AbstractAction {
    private static final Logger logger = LogManager.getLogger(ConsultBalance.class);
    private static String cardNumber = new String();
    private static String pin = new String();

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
            if (cardNumber.length() != 12) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect card number format", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (pin.length() != 4) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect PIN format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Card card = Persistence.getCard(cardNumber, pin);
                    cardNumber = new String();
                    pin = new String();
                    //Unset the fields for security reasons.
                    Ticket.movementsMessage(card.getNumber(), card.getMovements(), card.getName());
                } catch (NoSuchCard nsc) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), nsc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IncorrectPin ip) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), ip.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class PinReader implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            updatePin(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updatePin(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //Do nothing. Never triggered by plain text documents but required by interface.
        }

        private void updatePin(DocumentEvent e){
            Document doc = e.getDocument();
            int len = doc.getLength();
            try{
                ConsultMovements.pin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }

    }

    public static class CardNumberReader implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateCardNumber(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateCardNumber(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //Do nothing. Never triggered by plain text documents but required by interface.
        }

        private void updateCardNumber(DocumentEvent e){
            Document doc = e.getDocument();
            int len = doc.getLength();
            try{
                ConsultMovements.cardNumber = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }

    }

}
