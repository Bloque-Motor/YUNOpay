package motor.bloque.entities;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.handlers.Hasher;
import motor.bloque.interfaces.*;

//TODO: class to hold all user and card data.

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrepayCard implements Card {

    private String name;
    private int cardNumber;
    private String surname;
    private String hashedPIN;
    private String salt;
    private int balance;
    private List<Movement> movements;

    public PrepayCard(){}

    public PrepayCard(String name, String surname, String PIN, int amount){
        this.name = name + surname;
        Map<String, String> hashed = Hasher.hashNewPassword(PIN);
        this.hashedPIN = hashed.get("password");
        this.salt = hashed.get("salt");
        this.balance = amount;
        this.movements = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return cardNumber;
    }

    @Override
    public String getHashedPIN() {
        return hashedPIN;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public List<Movement> getMovements() {
        return movements;
    }

    @Override
    public boolean changePIN(String oldPIN, String newPIN) {
        return false;
    }

    @Override
    public boolean recharge(int amount, String PIN) {
        try {
            if(!Hasher.validatePassword(PIN, cardNumber)) return false;
            balance = balance + amount;
        }catch (NoSuchCard e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addMovement(String PIN, int amount) {
        //TODO: maybe this should be implemented through PaymentTerminal, or maybe that class should be eliminated
        return false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNumber(int number) {
        this.cardNumber = number;
    }

    @Override
    public void setHashedPIN(String hashedPIN) {
        this.hashedPIN = hashedPIN;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }


}
