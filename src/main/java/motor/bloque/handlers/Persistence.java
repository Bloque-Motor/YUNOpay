package motor.bloque.handlers;

//TODO: this class will handle file read and write operations
//I personally suggest writing JSON objects as plain text for persistence

import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persistence {

    private Map<Integer,Card> cards;

    public Persistence(){
        String resourceName = "data.json";
        InputStream is = Persistence.class.getResourceAsStream(resourceName);
        if (is == null) {
            cards = new HashMap<>();
        }else {
            JSONTokener tokener = new JSONTokener(is);
            JSONObject data = new JSONObject(tokener);

            //TODO: extract relevant data from JSON into Card objects to put on Map
        }
    }

    public boolean saveAll() {
        JSONObject data = new JSONObject();
        Integer[] cardNumbers = (Integer[]) cards.keySet().toArray();

        for (Integer num : cardNumbers){
            Card card = cards.get(num);
            JSONObject cardDetails = new JSONObject();
            cardDetails.put("name", card.getName());
            cardDetails.put("number", card.getNumber());
            cardDetails.put("hashedPIN", card.getHashedPIN());
            cardDetails.put("balance", card.checkBalance());

            JSONArray movementJSON = new JSONArray();
            List<Movement> moves = card.checkMovements();

            for (Movement move : moves){
                JSONObject moveJSON = new JSONObject();
                moveJSON.put("amount", move.getAmount());
                moveJSON.put("remainingBalance", move.getRemainingBalance());
                moveJSON.put("date", move.getDate().toString());
                movementJSON.put(moveJSON);
            }
            cardDetails.put("movements", movementJSON);
            data.put(num.toString(), cardDetails);
        }

        boolean result = true;
        String fileName = "data.json";

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            data.write(writer, 4, 0);
            writer.write("\n");
        } catch (Exception ex) {
            System.err.println("Couldn't write to file\n" + ex.getMessage());
            result = false;
        }
        return result;

        
    }
}
