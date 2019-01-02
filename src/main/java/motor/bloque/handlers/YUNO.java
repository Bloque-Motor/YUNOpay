package motor.bloque.handlers;

//TODO: Replace Terminal with GUI

import motor.bloque.gui.Terminal;

public class YUNO {

    public static void main(String[] args){
        Persistence.loadPersistence();

        //Provisional
        Terminal.mainMenu();
        Persistence.saveAll();
    }
}
