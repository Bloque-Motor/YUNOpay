package motor.bloque.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;

//TODO: this is the main class of the program's gui. Should only create visual elements.
//Operations must be handled by the ClientController class

public class ClientView extends JFrame {

    private ClientView() {

        initUI();
    }

    private void initUI() {
        JButton quitButton = new JButton("Quit");
        setTitle("YUNO Pay");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void startClientApp() {

        EventQueue.invokeLater(() -> {
            ClientView ex = new ClientView();
            ex.setVisible(true);
        });
    }
}

