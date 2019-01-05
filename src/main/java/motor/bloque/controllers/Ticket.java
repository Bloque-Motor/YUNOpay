package motor.bloque.controllers;

import motor.bloque.views.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Ticket extends AbstractAction {
    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
            EventQueue.invokeLater(() ->MainMenu.getFrame().changePanel(ClientView.panels.MAIN));
        }
    }
}
