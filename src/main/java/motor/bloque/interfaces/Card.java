package motor.bloque.interfaces;

import java.util.List;

public interface Card {

    void setName(String name);
    void setNumber(int number);
    boolean setPIN(int oldPIN, int newPIN);
    String getName();
    int getNumber();
    byte[] getHashedPIN();
    boolean recharge(int amount);
    int checkBalance();
    List<Movement> checkMovements();
}
