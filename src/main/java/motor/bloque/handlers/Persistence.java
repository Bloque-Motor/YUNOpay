package motor.bloque.handlers;

import motor.bloque.entities.CardMovement;
import motor.bloque.entities.PrepayCard;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Persistence {

    private static Map<String,Card> cards = new HashMap<>();

    public static Card getCard(String cardNumber) throws NoSuchCard{
        if(cards.containsKey(cardNumber)) return cards.get(cardNumber);
        throw new NoSuchCard(cardNumber);
    }

    public static void putCard(Card card){
        cards.put(card.getNumber(), card);
    }

    public static void loadPersistence(){
        String resourceName = "data.json";
        InputStream is = Persistence.class.getResourceAsStream(resourceName);
        if (is == null) {
            cards = new HashMap<>();
        }else {
            JSONTokener tokener = new JSONTokener(is);
            JSONObject data = new JSONObject(tokener);
            Iterator<String> it = data.keys();
            while(it.hasNext()) {
                String key = it.next();
                if (data.get(key) instanceof JSONObject) {
                    JSONObject cardJSON = (JSONObject) data.get(key);
                    Card card = new PrepayCard();
                    card.setName(cardJSON.getString("name"));
                    card.setNumber(cardJSON.getString("number"));
                    card.setHashedPIN(cardJSON.getString("hashedPIN"));
                    card.setSalt(cardJSON.getString("salt"));
                    card.setBalance(cardJSON.getInt("balance"));

                    List<Movement> movements = new ArrayList<>();
                    JSONArray movementsJSON = cardJSON.getJSONArray("movements");
                    for (int i = 0; i < movementsJSON.length(); i++){
                        JSONObject moveJSON = (JSONObject) movementsJSON.get(i);
                        Movement move = new CardMovement();
                        move.setAmount(moveJSON.getInt("amount"));
                        move.setDate(LocalDateTime.parse(moveJSON.getString("date")));
                        move.setRemainingBalance(moveJSON.getInt("remainingBalance"));
                        movements.add(move);
                    }
                    card.setMovements(movements);
                    cards.put(card.getNumber(), card);
                }
            }
        }
    }

    public static boolean saveAll() {
        JSONObject data = new JSONObject();
        Integer[] cardNumbers = cards.keySet().toArray(new Integer[cards.keySet().size()]);

        for (Integer num : cardNumbers){
            Card card = cards.get(num);
            JSONObject cardDetails = new JSONObject();
            cardDetails.put("name", card.getName());
            cardDetails.put("number", card.getNumber());
            cardDetails.put("hashedPIN", card.getHashedPIN());
            cardDetails.put("salt", card.getSalt());
            cardDetails.put("balance", card.getBalance());

            JSONArray movementJSON = new JSONArray();
            List<Movement> moves = card.getMovements();

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
