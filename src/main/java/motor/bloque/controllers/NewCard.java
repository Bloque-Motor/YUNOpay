package motor.bloque.controllers;

import motor.bloque.entities.PrepayCard;
import motor.bloque.handlers.Persistence;
import motor.bloque.views.ClientView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public abstract class NewCard extends AbstractAction {
    private static final Logger logger = LogManager.getLogger(NewCard.class);

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.info("Ok button pressed");
            ClientView frame = MainMenu.getFrame();
            String name = frame.getNameField().getText();
            String surname = frame.getSurnameField().getText();
            String pin = Arrays.toString(frame.getPinField().getPassword());
            String confirmPin = Arrays.toString(frame.getConfirmPinField().getPassword());
            int initialAmount = Integer.parseInt(frame.getAmountField().getText());
            if (name.isEmpty() || surname.isEmpty() || pin.isEmpty() || confirmPin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty");
                logger.error("Some fields are empty");
            }

            logger.info("Attempting to create new card with the following data: " + name + " " + surname + " " + pin + " " + initialAmount);
            PrepayCard newCard = new PrepayCard(name, surname, pin, initialAmount);
            Persistence.putCard(newCard);

        }
    }
}
