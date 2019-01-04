package motor.bloque.controllers;

import motor.bloque.views.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NewCardController extends AbstractAction {

    public static class cancelButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel button pressed");
            EventQueue.invokeLater(() -> MainMenuController.getFrame().changePanel(ClientView.panels.MAIN));
        }
    }

    public static class okButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
            //String name =
        }
    }
}
