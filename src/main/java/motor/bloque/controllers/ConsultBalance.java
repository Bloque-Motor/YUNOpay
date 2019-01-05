package motor.bloque.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ConsultBalance extends AbstractAction {
    private static final Logger logger = LogManager.getLogger(ConsultBalance.class);
    private static String cardNumber;
    private static String pin;

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
            System.out.println("Card number: " + cardNumber);
            System.out.println("PIN: " + pin);
        }
    }

    public static class PinReader implements DocumentListener{

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
                ConsultBalance.pin = doc.getText(0, len);
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
                ConsultBalance.cardNumber = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }

    }
}
