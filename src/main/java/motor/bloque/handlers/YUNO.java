package motor.bloque.handlers;

//TODO: Replace Terminal with GUI

import motor.bloque.gui.ClientView;

public class YUNO {

    public static void main(String[] args){
        Persistence.loadPersistence();
        ClientController.startClientApp();
    }
}
