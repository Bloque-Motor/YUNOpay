package motor.bloque.handlers;

import motor.bloque.entities.CardMovement;
import motor.bloque.entities.PrepayCard;
import motor.bloque.exceptions.IncorrectPin;
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

    public static Card getCard(String cardNumber, String pin) throws NoSuchCard, IncorrectPin {
        if(cards.containsKey(cardNumber)){
            if(Credentials.validatePassword(pin, cardNumber)) return cards.get(cardNumber);
            throw new IncorrectPin();
        }
        throw new NoSuchCard(cardNumber);
    }

    public static void putCard(Card card){
        cards.put(card.getNumber(), card);
    }

    public static boolean existsCard(String cardNumber){
        return cards.containsKey(cardNumber);
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
                    card.setExpirationDate(LocalDateTime.parse(cardJSON.getString("Expiration Date")));

                    cards.put(card.getNumber(), card);
                }
            }
        }
    }

    public static boolean saveAll() {
        JSONObject data = new JSONObject();
        Iterator<String> it = cards.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            Card card = cards.get(key);
            JSONObject cardDetails = new JSONObject();
            cardDetails.put("name", card.getName());
            cardDetails.put("number", card.getNumber());
            cardDetails.put("hashedPIN", card.getHashedPIN());
            cardDetails.put("salt", card.getSalt());
            cardDetails.put("balance", card.getBalance());
            cardDetails.put("Expiration Date", card.getExpirationDate().toString());

            data.put(card.getNumber(), cardDetails);
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

    private Persistence(){
        throw new IllegalStateException("Utility class");
    }

    public static void requestCredentials(String cardNumber) {
        Card card = cards.get(cardNumber);
        Credentials.setPin(card.getHashedPIN());
        Credentials.setSalt(card.getSalt());
    }
}
