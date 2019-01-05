package motor.bloque.controllers;

import motor.bloque.interfaces.Movement;
import motor.bloque.views.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Ticket extends AbstractAction {

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button pressed");
            EventQueue.invokeLater(() ->MainMenu.getFrame().changePanel(ClientView.panels.MAIN));
        }
    }

    public static void balanceMessage(String cardNumber, int balance, String name){
        String ticket = "Dear " + name + "<br><br>Card Number: ";
        String hiddenNumber = "XXXX XXXX " + cardNumber.substring(7);
        ticket = ticket + hiddenNumber + "<br>" + "Balance: " + balance;
        MainMenu.getFrame().setTicket(ticket);
        EventQueue.invokeLater(() ->MainMenu.getFrame().changePanel(ClientView.panels.TICKET));
    }

    public static void movementsMessage(String cardNumber, List<Movement> movements, String name) {
        String ticket = "Dear " + name + "<br><br>Card Number: ";
        String hiddenNumber = "XXXX XXXX " + cardNumber.substring(7);
        ticket = ticket + hiddenNumber + "<br><br>";
        for(Movement move : movements){
            LocalDateTime date = move.getDate();
            ticket = ticket + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()%100;
            ticket = ticket + " " + move.getAmount() + "<br>";
        }
        MainMenu.getFrame().setTicket(ticket);
        EventQueue.invokeLater(() ->MainMenu.getFrame().changePanel(ClientView.panels.TICKET));
    }


}
