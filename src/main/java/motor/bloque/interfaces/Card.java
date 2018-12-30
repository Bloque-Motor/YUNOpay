package motor.bloque.interfaces;

import java.util.List;

public interface Card {

    String getName();
    int getNumber();
    byte[] getHashedPIN();
    int getBalance();
    List<Movement> getMovements();

    boolean changePIN(int oldPIN, int newPIN);
    boolean recharge(int amount);

    void setName(String name);
    void setNumber(int number);
    void setHashedPIN(byte[] hashedPIN);
    void setBalance(int balance);
    void setMovements(List<Movement> movements);


}
