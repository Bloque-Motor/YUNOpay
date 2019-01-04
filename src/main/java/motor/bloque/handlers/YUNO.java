package motor.bloque.handlers;

//TODO: Replace Terminal with GUI

import motor.bloque.controllers.MainMenu;

public class YUNO {

    public static void main(String[] args){
        Persistence.loadPersistence();
        MainMenu.startClientApp();
    }
}
