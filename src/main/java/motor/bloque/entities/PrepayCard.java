package motor.bloque.entities;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.handlers.Credentials;
import motor.bloque.interfaces.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrepayCard implements Card {

    private String name;
    private String cardNumber;
    private String hashedPIN;
    private String salt;
    private int balance;
    private List<Movement> movements;
    private LocalDateTime expirationDate;

    public PrepayCard(){

       this.movements = new ArrayList<>();
    }

    public PrepayCard(String name, String surname, String pin, int amount){
        this.name = name + surname;
        Map<Credentials.HASHED, String> hashed = Credentials.hashNewPassword(pin);
        this.hashedPIN = hashed.get(Credentials.HASHED.PASSWORD);
        this.salt = hashed.get(Credentials.HASHED.SALT);
        this.balance = amount;
        this.movements = new ArrayList<>();
        this.cardNumber = Credentials.generateCardNumber();
        this.expirationDate = LocalDateTime.now().plusYears(1);
    }

    public String getName() {
        return name;
    }
    
    public String getNumber() {
        return cardNumber;
    }

    public String getHashedPIN() {
        return hashedPIN;
    }

    public String getSalt() {
        return salt;
    }

    public int getBalance() {
        return balance;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public LocalDateTime getExpirationDate(){

        return expirationDate;
    }

    public boolean changePIN(String oldPIN, String newPIN) {
        try {
            if(!Credentials.validatePassword(oldPIN, cardNumber)) return false;
            Map<Credentials.HASHED, String> map = Credentials.hashNewPassword(newPIN);
            this.setHashedPIN(map.get(Credentials.HASHED.PASSWORD));
            this.setSalt(map.get(Credentials.HASHED.SALT));
        }catch (NoSuchCard e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean recharge(int amount, String pin) throws NegativeAmount {
        try {
            if(!Credentials.validatePassword(pin, cardNumber)) return false;
            if (amount < 0) throw new NegativeAmount();
            balance = balance + amount;
        }catch (NoSuchCard e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addMovement(String pin, Movement movement) throws  InsufficientFunds, NegativeAmount{
        try {
            if(!Credentials.validatePassword(pin, cardNumber)) return false;
            int amount = movement.getAmount();
            if(amount < 0) throw new NegativeAmount();
            if((balance - amount) >= 0){
                balance -= amount;
                movement.setRemainingBalance(balance);
                movements.add(movement);
            }else{
                throw new InsufficientFunds(amount - balance);
            }
        }catch (NoSuchCard e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.cardNumber = number;
    }

    public void setHashedPIN(String hashedPIN) {
        this.hashedPIN = hashedPIN;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public void setExpirationDate(LocalDateTime expirationDate){ this.expirationDate = expirationDate; }
}
