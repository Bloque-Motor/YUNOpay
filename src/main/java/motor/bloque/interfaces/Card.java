package motor.bloque.interfaces;

import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;

import java.util.List;

public interface Card {

    String getName();
    String getNumber();
    String getHashedPIN();
    String getSalt();
    int getBalance();
    List<Movement> getMovements();

    boolean changePIN(String oldPIN, String newPIN);
    boolean recharge(int amount, String pin) throws NegativeAmount;
    boolean addMovement(String pin, Movement movement) throws InsufficientFunds, NegativeAmount;

    void setName(String name);
    void setNumber(String number);
    void setHashedPIN(String hashedPIN);
    void setSalt(String salt);
    void setBalance(int balance);
    void setMovements(List<Movement> movements);


}
