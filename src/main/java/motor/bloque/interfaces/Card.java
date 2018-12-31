package motor.bloque.interfaces;

import java.util.List;

public interface Card {

    String getName();
    int getNumber();
    String getHashedPIN();
    String getSalt();
    int getBalance();
    List<Movement> getMovements();

    boolean changePIN(String oldPIN, String newPIN);
    boolean recharge(int amount, String PIN);
    boolean addMovement(String PIN, int amount);

    void setName(String name);
    void setNumber(int number);
    void setHashedPIN(String hashedPIN);
    void setSalt(String salt);
    void setBalance(int balance);
    void setMovements(List<Movement> movements);


}
