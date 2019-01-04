package motor.bloque.controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ConsultMovements extends AbstractAction {
    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
        }
    }
}
