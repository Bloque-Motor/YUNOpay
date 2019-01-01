package motor.bloque.interfaces;

import motor.bloque.exceptions.InsufficientFunds;

import java.util.List;

public interface Card {

    String getName();
    String getNumber();
    String getHashedPIN();
    String getSalt();
    int getBalance();
    List<Movement> getMovements();

    boolean changePIN(String oldPIN, String newPIN);
    boolean recharge(int amount, String PIN);
    boolean addMovement(String PIN, Movement movement) throws InsufficientFunds;

    void setName(String name);
    void setNumber(String number);
    void setHashedPIN(String hashedPIN);
    void setSalt(String salt);
    void setBalance(int balance);
    void setMovements(List<Movement> movements);


}
