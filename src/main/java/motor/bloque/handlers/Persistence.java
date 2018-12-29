package motor.bloque.handlers;

//TODO: this class will handle file read and write operations
//I personally suggest writing JSON objects as plain text for persistence

import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Persistence {

    private Map<Integer,Card> cards;
    private ArrayList<Movement> movements;

    public Persistence(){}


    public static List getCards() {
        return null;
    }

    public static List getMovements() {
        return null;
    }

    public static void saveCards(List cards) {
    }

    public static void saveMovements(List movements) {
    }
}
