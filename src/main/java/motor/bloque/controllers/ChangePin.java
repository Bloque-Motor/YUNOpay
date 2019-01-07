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

public abstract class ChangePin extends AbstractAction {
    private static String confirmPin;
    private static String cardNumber;
    private static String pin;
    private static String newPin;

    private static final String ERROR = "Error";
    private static final String BL = "Bad Location";

    private static final Logger logger = LogManager.getLogger(ChangePin.class);


    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cardNumber.isEmpty() || pin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Fields cannot be empty", ERROR, JOptionPane.ERROR_MESSAGE);
                logger.error("Some fields are empty");
            }else if (cardNumber.length() != 12) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect card number format", ERROR, JOptionPane.ERROR_MESSAGE);
            } else if (pin.length() != 4) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect PIN format", ERROR, JOptionPane.ERROR_MESSAGE);
            }else if (!pin.equals(confirmPin)){
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "PINs don't match", ERROR, JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    Card card = Persistence.getCard(cardNumber, pin);
                    card.changePIN(newPin);
                    confirmPin = null;
                    cardNumber = null;
                    pin = null;
                    newPin = null;
                    Ticket.changePin(card.getName());
                } catch (NoSuchCard nsc) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), nsc.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
                } catch (IncorrectPin ip) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), ip.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
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
                ChangePin.cardNumber = doc.getText(0, len);
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
                ChangePin.pin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
            }
        }

    }

    public static class NewPinReader implements DocumentListener {

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
                ChangePin.newPin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
            }
        }

    }

    public static class ConfirmPinReader implements DocumentListener{

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
                ChangePin.confirmPin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn(BL, ex);
            }
        }
    }
}
