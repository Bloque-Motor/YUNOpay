package motor.bloque.controllers;

import motor.bloque.entities.PrepayCard;
import motor.bloque.handlers.Persistence;
import motor.bloque.views.ClientView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public abstract class NewCard extends AbstractAction {
    private static final Logger logger = LogManager.getLogger(NewCard.class);

    private static String name = new String();
    private static String surname = new String();
    private static String pin = new String();
    private static String confirmPin = new String();
    private static String initialAmount = new String();

    private static final String ERROR = "Error";

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (name.isEmpty() || surname.isEmpty() || pin.isEmpty() || confirmPin.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Fields cannot be empty", ERROR, JOptionPane.ERROR_MESSAGE);
                logger.error("Some fields are empty");
            }else if (pin.length() != 4) {
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "Incorrect PIN format", ERROR, JOptionPane.ERROR_MESSAGE);
            }else if (!pin.equals(confirmPin)){
                JOptionPane.showMessageDialog(MainMenu.getFrame(), "PINs don't match", ERROR, JOptionPane.ERROR_MESSAGE);
            }else {
                logger.info("Ok button pressed");

                double initialAmount = Double.parseDouble(NewCard.initialAmount);
                logger.info("Attempting to create new card with the following data: " + name + " " + surname + " " + " " + initialAmount);
                PrepayCard newCard = new PrepayCard(name, surname, pin, initialAmount);
                Persistence.putCard(newCard);
                name = new String();
                surname = new String();
                pin = new String();
                confirmPin = new String();
                NewCard.initialAmount = new String();
                Ticket.newCard(newCard.getNumber(),newCard.getName(),newCard.getBalance());
            }
        }
    }

    public static class NameReader implements DocumentListener{

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
                NewCard.name = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }

    }

    public static class SurameReader implements DocumentListener{

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
                NewCard.surname = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
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
                NewCard.pin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
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
                NewCard.confirmPin = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }
    }

    public static class AmountReader implements DocumentListener{

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
                NewCard.initialAmount = doc.getText(0, len);
            }catch (BadLocationException ex){
                logger.warn("Bad location", ex);
            }
        }

    }
}
