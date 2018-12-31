package motor.bloque.handlers;

//TODO: Replace Terminal with GUI

import motor.bloque.gui.Terminal;
import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YUNO {

    public static Persistence persistence;

    public static void main(String[] args){
        Persistence.loadPersistence();

        //Provisional
        Terminal.mainMenu();
        Persistence.saveAll();
    }
}
