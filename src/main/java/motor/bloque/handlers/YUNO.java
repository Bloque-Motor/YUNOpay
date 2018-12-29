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

    private static Map cards;
    private static List movements;

    public static void main(String[] args){
        cards = Persistence.getCards();
        if (cards == null) cards = new HashMap<Integer, Card>();
        movements = Persistence.getMovements();
        if (movements == null) movements = new ArrayList<Movement>();

        //Provisional
        Terminal.mainMenu();
        if (!cards.isEmpty()) Persistence.saveCards(cards);
        if (!movements.isEmpty()) Persistence.saveMovements(movements);
    }
}
