package motor.bloque.controllers;

import motor.bloque.entities.CardMovement;
import motor.bloque.exceptions.*;
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

public abstract class Recharge extends AbstractAction {
    private static String pin;
    private static String cardNumber;
    private static String amount;
    private static final String ERROR = "Error";
    private static final String BL = "Bad Location";

    private static final Logger logger = LogManager.getLogger(Recharge.class);

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cardNumber.length() != 12) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect card number format", ERROR, JOptionPane.ERROR_MESSAGE);
            } else if (pin.length() != 4) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect PIN format", ERROR, JOptionPane.ERROR_MESSAGE);
            } else if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Amount field cannot be empty");
            }else{
                try {
                    double amount = Double.parseDouble(Recharge.amount);
                    Card card = Persistence.getCard(cardNumber, pin);
                    card.recharge(amount);
                    cardNumber = null;
                    pin = null;
                    Recharge.amount = null;
                    //Unset the fields for security reasons.
                    Ticket.pay(card.getNumber(), card.getBalance(), card.getName(), amount);
                } catch (NoSuchCard nsc) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), nsc.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
                } catch (IncorrectPin ip) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), ip.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
                } catch (ExpiredCard ec) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), ec.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
                } catch (NegativeAmount na) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), na.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class CardNumberReader implements DocumentListener {
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
                Recharge.cardNumber = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
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
                Recharge.pin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
            }
        }
    }

    public static class AmountReader implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateAmount(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateAmount(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //Do nothing. Never triggered by plain text documents but required by interface.
        }

        private void updateAmount(DocumentEvent e){
            Document doc = e.getDocument();
            int len = doc.getLength();
            try{
                Recharge.amount = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
            }
        }
    }
}
