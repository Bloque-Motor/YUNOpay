package motor.bloque.interfaces;

import motor.bloque.exceptions.ExpiredCard;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;

import java.time.LocalDateTime;
import java.util.List;

public interface Card {

    String getName();
    String getNumber();
    String getHashedPIN();
    String getSalt();
    int getBalance();
    List<Movement> getMovements();
    LocalDateTime getExpirationDate();

    boolean changePIN(String newPIN);
    boolean recharge(int amount) throws NegativeAmount, ExpiredCard;
    boolean addMovement(Movement movement) throws InsufficientFunds, NegativeAmount, ExpiredCard;

    void setName(String name);
    void setNumber(String number);
    void setHashedPIN(String hashedPIN);
    void setSalt(String salt);
    void setBalance(int balance);
    void setMovements(List<Movement> movements);
    void setExpirationDate(LocalDateTime expirationDate);


}
