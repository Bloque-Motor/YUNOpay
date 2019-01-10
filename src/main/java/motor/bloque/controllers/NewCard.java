package motor.bloque.controllers;

import org.apache.commons.lang3.StringUtils;
import motor.bloque.entities.PrepayCard;
import motor.bloque.handlers.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public abstract class NewCard extends AbstractAction {
    private static final Logger logger = LogManager.getLogger(NewCard.class);

    private static String name;
    private static String surname;
    private static String pin;
    private static String confirmPin;
    private static String initialAmount;

    private static final String ERROR = "Error";
    private static final String BL = "Bad location";

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (name.isEmpty() || surname.isEmpty() || pin.isEmpty() || confirmPin.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Fields cannot be empty", ERROR, JOptionPane.ERROR_MESSAGE);
                logger.error("Some fields are empty");
            } else if ((pin.length() != 4) || !StringUtils.isNumeric(pin)) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect PIN format", ERROR, JOptionPane.ERROR_MESSAGE);
            } else if (!pin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "PINs don't match", ERROR, JOptionPane.ERROR_MESSAGE);
            } else {
                logger.info("Ok button pressed");
                try {
                    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                    Number number = format.parse(initialAmount);
                    double d = number.doubleValue();
                    DecimalFormat decformat = new DecimalFormat("#.00");
                    double parsedAmount = Double.parseDouble(decformat.format(d));
                    logger.info("Attempting to create new card with the following data: " + name + " " + surname + " " + " " + initialAmount);
                    PrepayCard newCard = new PrepayCard(name, surname, pin, parsedAmount);
                    Persistence.putCard(newCard);
                    name = null;
                    surname = null;
                    pin = null;
                    confirmPin = null;
                    NewCard.initialAmount = null;
                    Ticket.newCard(newCard.getNumber(), newCard.getName(), newCard.getBalance());
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(MainMenu.getFrame(), "Amount field format is incorrect. Amounts should use a period and a maximum of 2 decimal spaces", ERROR, JOptionPane.ERROR_MESSAGE);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
            }
        }
    }
    public static class NameReader implements DocumentListener {

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

        private void updatePin(DocumentEvent e) {
            Document doc = e.getDocument();
            int len = doc.getLength();
            try {
                NewCard.name = doc.getText(0, len);
            } catch (BadLocationException ex) {
                logger.warn(BL, ex);
            }
        }

    }

    public static class SurameReader implements DocumentListener {

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

        private void updatePin(DocumentEvent e) {
            Document doc = e.getDocument();
            int len = doc.getLength();
            try {
                NewCard.surname = doc.getText(0, len);
            } catch (BadLocationException ex) {
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

        private void updatePin(DocumentEvent e) {
            Document doc = e.getDocument();
            int len = doc.getLength();
            try {
                NewCard.pin = doc.getText(0, len);
            } catch (BadLocationException ex) {
                logger.warn(BL, ex);
            }
        }

    }

    public static class ConfirmPinReader implements DocumentListener {

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

        private void updatePin(DocumentEvent e) {
            Document doc = e.getDocument();
            int len = doc.getLength();
            try {
                NewCard.confirmPin = doc.getText(0, len);
            } catch (BadLocationException ex) {
                logger.warn(BL, ex);
            }
        }
    }

    public static class AmountReader implements DocumentListener {

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

        private void updatePin(DocumentEvent e) {
            Document doc = e.getDocument();
            int len = doc.getLength();
            try {
                NewCard.initialAmount = doc.getText(0, len);
            } catch (BadLocationException ex) {
                logger.warn(BL, ex);
            }
        }

    }
}

