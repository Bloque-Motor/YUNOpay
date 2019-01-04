package motor.bloque.handlers;

//TODO: Replace Terminal with GUI

import motor.bloque.controllers.MainMenuController;

public class YUNO {

    public static void main(String[] args){
        Persistence.loadPersistence();
        MainMenuController.startClientApp();
    }
}
